import numpy as np
import matplotlib.pyplot as plt
from matplotlib import style
from numpy.linalg import pinv


def get_data():
    ys, xs = np.loadtxt('data/cricket_chirps_versus_temperature.txt', delimiter=',', unpack=True)
    xs_orig = xs
    xs = np.c_[np.ones(len(xs)), xs]
    return xs, ys, xs_orig


def graph_data(xs, ys, m, b):
    style.use('ggplot')
    plt.scatter(xs, ys)

    axes = plt.gca()
    x_vals = np.array(axes.get_xlim())
    y_vals = b + m * x_vals
    plt.plot(x_vals, y_vals, '--')

    plt.show()


def fit(xs, ys):
    xs_trans = xs.T
    xs_inv = pinv(np.matmul(xs_trans, xs))
    w = np.matmul(xs_inv, xs_trans)
    w = np.matmul(w, ys)
    return w[0], w[1]


def predict(x, m, b):
    y = m * x + b
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
    b, m = fit(xs, ys)
    r = coefficient_of_determination(xs_orig, ys, m, b)
    print(f'confidence is {r}')
    graph_data(xs_orig, ys, m, b)


main()
