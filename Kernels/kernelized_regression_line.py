import numpy as np
import matplotlib.pyplot as plt
from matplotlib import style
from numpy.linalg import pinv
from numpy import matmul, dot
import time


def get_data():
    ys, xs = np.loadtxt('data/cricket_chirps_versus_temperature.txt', delimiter=',', unpack=True)
    xs_orig = xs
    xs = np.c_[np.ones(len(xs)), xs]
    return xs, ys, xs_orig


def graph_data(xs, ys, m, b, new_feature=None, pred=None):
    style.use('ggplot')
    plt.scatter(xs, ys)

    axes = plt.gca()
    x_vals = np.array(axes.get_xlim())
    y_vals = b + m * x_vals
    plt.plot(x_vals, y_vals, '--')
    if new_feature is not None and pred is not None:
        plt.plot(new_feature, pred, 'o', color='green')

    plt.show()


def fit(xs, ys):
    start = time.perf_counter()
    xs_t = xs.T
    K = matmul(xs, xs_t)
    alpha_x = matmul(pinv(K), ys)
    alpha_x = matmul(alpha_x, xs)
    print(f'time taken for kernelized fit: {time.perf_counter() - start}')
    return alpha_x


def fit_no_kernel(xs, ys):
    start = time.perf_counter()
    xs_trans = xs.T
    xs_inv = pinv(np.matmul(xs_trans, xs))
    w = np.matmul(xs_inv, xs_trans)
    w = np.matmul(w, ys)
    print(f'time taken for regular fit: {time.perf_counter() - start}')
    return w[0], w[1]


def predict(w, b, x):
    y = dot(w, x)
    y = y + b
    return y


def squared_error(y, y_mean):
    return (y - y_mean) ** 2


def get_yhats(xs, ys, m, b):
    y_hats = []
    for i, val in enumerate(ys):
        y_hats.append(xs[i] * m + b)
        # print(xs[i] * m + b)
    return y_hats


def coefficient_of_determination(xs, ys, m, b):
    y_mean = ys.mean()
    actuals = 0
    for y_actual in ys:
        actuals += squared_error(y_actual, y_mean)

    predicted_ones = 0
    for y_predicted in get_yhats(xs, ys, m, b):
        predicted_ones += squared_error(y_predicted, y_mean)

    return predicted_ones / actuals


def main():
    xs, ys, xs_orig = get_data()
    alpha_x = fit(xs, ys)
    new_feature = 18
    pred = predict(alpha_x[1], alpha_x[0], new_feature)
    r = coefficient_of_determination(xs_orig, ys, alpha_x[1], alpha_x[0])
    print(f'confidence is {r}')
    graph_data(xs_orig, ys, alpha_x[1], alpha_x[0], new_feature, pred)


main()
