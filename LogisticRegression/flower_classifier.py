from sklearn import datasets
from matplotlib import pyplot as plt
import numpy as np
from random import shuffle
import copy


def get_data():
    iris = datasets.load_iris()
    x = iris['data'][:, :2]
    y = (iris['target'] != 0) * 1
    hm_many_test = int(len(x) * 0.25)

    x_train_orig = x[:-hm_many_test]
    xy = list(zip(x, y))
    shuffle(xy)

    x, y = zip(*xy)

    y = np.array(y)
    x = np.array(x)

    non_versicolor_labels = 50

    x_train, x_test = x[:-hm_many_test], x[-hm_many_test:]
    y_train, y_test = y[:-hm_many_test], y[-hm_many_test:]

    y_train = y_train.reshape(-1, 1)
    y_test = y_test.reshape(-1, 1)

    return x_train, x_test, y_train, y_test, non_versicolor_labels, x_train_orig


def graph_data(xs, hm_non_versicolor_labels, w, b):
    plt.plot(xs[hm_non_versicolor_labels:][:, 0], xs[hm_non_versicolor_labels:][:, 1], 'r.')
    plt.plot(xs[:hm_non_versicolor_labels][:, 0], xs[:hm_non_versicolor_labels][:, 1], 'b.')
    plt.xlabel('Petal Length (cm)')
    plt.ylabel('Petal Width (cm)')
    plt.legend(['Versicolor', 'Non Versicolor'])

    axes = plt.gca()
    x_vals = np.array(axes.get_xlim()).reshape(-1, 1)
    y_vals = -(x_vals * w[0][0] + b) / w[1][0]

    plt.plot(x_vals, y_vals)
    plt.show()


def sigmoid(z):
    return 1 / (1 + np.e ** (-z))


def loss_function(y, y_hat):
    return -np.mean(y * np.log(y_hat) + (1 - y) * np.log(1 - y_hat))


def fit(xs, ys, learning_rate, max_iter, precision):
    mean = len(ys)
    w = np.zeros((2, 1))
    b = np.zeros((1, 1))

    for i in range(max_iter):
        z = np.matmul(xs, w) + b
        y_hat = sigmoid(z)

        dz = y_hat - ys
        dw = (1 / mean) * np.matmul(xs.T, dz)
        db = np.sum(dz)

        w_prev = copy.copy(w)

        w -= learning_rate * dw
        b -= learning_rate * db

        if np.linalg.norm(w_prev - w) < precision:
            print(f'Optimized successfully in {i} steps. Weights {w, b}')
            return w, b

    print(f'Could not optimize in {i} steps: {w, b}')
    return w, b


def predict(p):
    if p >= 0.5:
        return 1
    return 0


def test(ys, z):
    correct = 0
    pred_list = []

    for i, p in enumerate(sigmoid(z)):
        pred = predict(p)
        pred_list.append(pred)
        if pred == ys[i][0]:
            correct += 1

    print(pred_list)
    print(f'precision: {correct / len(ys)}')


def main():
    x_train, x_test, y_train, y_test, non_versicolor_labels, x_train_orig = get_data()
    w, b = fit(x_train, y_train, learning_rate=0.01, max_iter=10000, precision=0.001)
    z = np.matmul(x_test, w) + b
    test(y_test, z)
    graph_data(x_train_orig, non_versicolor_labels, w, b)


main()
