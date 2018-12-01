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
              [9, 11],
              [8, 2],
              [10, 2],
              [9, 3], ])


class UnOptimizableError(Exception):
    """Can not optimize the data set"""


class MeanShift:
    def __init__(self, radius=4, MAX_ITER=5):
        self.radius = radius
        self.MAX_ITER = MAX_ITER
        self.centroids = None

    @staticmethod
    def euclidian_distance(num1, num2):
        return sqrt((num1[0] - num2[0]) ** 2 + (num1[1] - num2[1]) ** 2)

    @staticmethod
    def arithmetic_mean(lists):
        sum_xs = 0
        sum_ys = 0
        for item in lists:
            sum_xs += item[0]
            sum_ys += item[1]
        length = float(len(lists))
        return sum_xs / length, sum_ys / length

    def fit(self, data):
        centroids = []
        features_within_radius = [[] for i in range(len(data))]

        for item in data:
            centroids.append(item)

        counter = 0
        is_converged = False
        while not is_converged:
            for i, val in enumerate(centroids):
                for j, centroid in enumerate(centroids):
                    if self.euclidian_distance(centroids[i], centroid) < self.radius:
                        features_within_radius[i].append(centroid)

            centroids = []
            for i, centroid in enumerate(features_within_radius):
                if len(centroid) != 0:
                    centroids.append(self.arithmetic_mean(centroid))

            features_within_radius = [[] for i in range(len(data))]
            prev_centroids = centroids
            centroids = list(set(centroids))
            counter += 1
            if centroids == prev_centroids:
                self.centroids = centroids
                for i, centroid in enumerate(centroids):
                    plt.scatter(centroid[0], centroid[1], s=150, color='k')
                return
            if counter == self.MAX_ITER:
                raise UnOptimizableError

    def predict(self):
        pass


means_shift = MeanShift()
means_shift.fit(X)
# k_means.predict([5, 5])
plt.scatter(X[:, 0], X[:, 1], s=150, color='r')
plt._show()
