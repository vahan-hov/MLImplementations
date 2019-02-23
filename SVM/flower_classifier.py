from sklearn import datasets
from matplotlib import pyplot as plt
import numpy as np
from random import shuffle


def get_data(shuffle_data=True):
    iris = datasets.load_iris()
    x = iris['data'][:, :2]
    y = (iris['target'] != 0) * 1
    hm_many_test = int(len(x) * 0.25)

    y[:] = [-1 if x == 0 else x for x in y]

    x_train_orig = x[:-hm_many_test]

    if shuffle_data:
        xy = list(zip(x, y))
        shuffle(xy)
        x, y = zip(*xy)

    y = np.array(y)
    x = np.array(x)

    hm_non_versicolor_labels = 50

    x_train, x_test = x[:-hm_many_test], x[-hm_many_test:]
    y_train, y_test = y[:-hm_many_test], y[-hm_many_test:]

    y_train = y_train.reshape(-1, 1)
    y_test = y_test.reshape(-1, 1)

    b = 0
    w_initial = np.zeros((1, len(x_train[0])))
    w_initial = np.append(w_initial, [[b]], axis=1)

    column_to_add = np.ones((len(x_train), 1))
    x_train = np.append(x_train, column_to_add, axis=1)
    column_to_add = np.ones((len(x_test), 1))
    x_test = np.append(x_test, column_to_add, axis=1)
    w_initial = np.reshape(w_initial, len(x_train[0]))

    return x_train, x_test, y_train, y_test, hm_non_versicolor_labels, x_train_orig, w_initial


# works only with 2d data
def graph_data(xs, hm_non_versicolor_labels, w):
    if len(w) != 3 or len(xs[0]) != 2:
        print('Sorry the function can handle only 2D data.', w, '  ', xs[0])
        return
    plt.style.use('ggplot')
    plt.plot(xs[hm_non_versicolor_labels:][:, 0], xs[hm_non_versicolor_labels:][:, 1], 'r.')
    plt.plot(xs[:hm_non_versicolor_labels][:, 0], xs[:hm_non_versicolor_labels][:, 1], 'b.')
    plt.xlabel('Petal Length (cm)')
    plt.ylabel('Petal Width (cm)')
    plt.legend(['Versicolor', 'Non Versicolor'])

    slope = -w[0] / w[1]
    intercept = -w[2] / w[1]

    axes = plt.gca()
    x_vals = np.array(axes.get_xlim())
    y_vals = intercept + slope * x_vals
    plt.plot(x_vals, y_vals, '--')

    plt.show()


def fit(xs, ys, w, max_iter=5000, precision=0.1, lr=0.1):
    norm_w_prev = 1000000
    for i in range(max_iter):
        m = 0
        for j, _ in enumerate(xs):
            if ys[j][0] * (np.dot(w, xs[j])) <= 0.2:
                w = w + ys[j][0] * xs[j]
                m += 1
                break
        if m == 0:
            # minimizing wT * w
            for j in range(max_iter):
                norm_w = np.matmul(w, w.T)
                magnitude_diff = norm_w - norm_w_prev
                grad = 2 * w

                if abs(magnitude_diff) >= precision:
                    w += grad * -lr
                    norm_w_prev = norm_w
                    continue
                print(f'Optimized the model in {i} and {j}  steps .Weights are: {w}. Norm diff {norm_w - norm_w_prev}')
                return w

    print(f'Could not optimize in {i} and {j} steps.Returning the best that was managed to be found. Weights are: ', w)
    return w


def predict(x, w):
    return np.sign(np.dot(w, x))


def test(xs, ys, w):
    correct = 0
    pred_list = []

    for i, _ in enumerate(ys):
        pred = predict(xs[i], w)
        pred_list.append(pred)
        if pred == ys[i]:
            correct += 1

    print(f'list of predictions: {pred_list}')
    print(f'precision: {correct / len(ys)}')


def main():
    x_train, x_test, y_train, y_test, hm_non_versicolor_labels, x_train_orig, w_init = get_data()
    w = fit(x_train, y_train, w_init)
    test(x_test, y_test, w)
    graph_data(x_train_orig, hm_non_versicolor_labels, w)


main()
