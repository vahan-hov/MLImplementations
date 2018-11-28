import matplotlib.pyplot as plt
from math import sqrt
from matplotlib import style

import numpy as np

style.use('ggplot')

X = np.array([[1, 2],
              [1.5, 1.8],
              [5, 8],
              [8, 8],
              [1, 0.6],
              [9, 11]])

colors = ['r', 'g', 'b', 'c', 'k', 'o', 'y']

plt.scatter(X[:, 0], X[:, 1], s=100)


class UnOptimizableError(Exception):
    """Can not optimize the data set"""


class KMeans:
    def __init__(self, k=2, tol=0.001, max_iter=30):
        self.k = k
        self.tol = tol
        self.max_iter = max_iter

    def euclidian_distance(self, num1, num2):
        return sqrt((num1[0] - num2[0]) ** 2 + (num1[1] - num2[1]) ** 2)

    def arithmetic_mean(self, lists):
        sum_xs = 0
        sum_ys = 0
        for list in lists:
            sum_xs += list[0]
            sum_ys += list[1]
        length = float(len(lists))
        return sum_xs / length, sum_ys / length

    def fit(self, data):
        centroid1 = data[0]
        centroid2 = data[1]
        class1 = []
        class2 = []
        counter = 0
        while True:
            for i, val in enumerate(data):
                if self.euclidian_distance(centroid1, val) < self.euclidian_distance(centroid2, val):
                    class1.append(val)
                else:
                    class2.append(val)

            counter += 1

            prev_centroid1 = centroid1
            prev_centroid2 = centroid2

            centroid1 = self.arithmetic_mean(class1)
            centroid2 = self.arithmetic_mean(class2)

            if counter is self.max_iter:
                raise UnOptimizableError('Can not optimize the data set')
            if (self.euclidian_distance(centroid1, prev_centroid1) <= self.tol) and (
                        self.euclidian_distance(centroid2, prev_centroid2) <= self.tol):
                plt.scatter(centroid1[0], centroid1[1], s=130, color='r')
                plt.scatter(centroid2[0], centroid2[1], s=130, color='r')
                for item in class1:
                    plt.scatter(item[0], item[1], s=100, color='g')
                for item in class2:
                    plt.scatter(item[0], item[1], s=100, color='b')
                return centroid1, centroid2

            class1 = []
            class2 = []

    def predict(self, data, point):
        centroid1, centroid2 = self.fit(data)
        if self.euclidian_distance(centroid1, point) < self.euclidian_distance(centroid2, point):
            plt.scatter(point[0], point[1], s=150, color='g')



        else:
            plt.scatter(point[0], point[1], s=150, color='b')


k_means = KMeans()
k_means.predict(X, [5, 5])
plt._show()
