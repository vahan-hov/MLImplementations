import matplotlib.pyplot as plt
from math import sqrt
from matplotlib import style
import numpy as np
from sklearn import preprocessing
import pandas as pd

style.use('ggplot')

# X = np.array([[1, 2],
#               [1.5, 1.8],
#               [5, 8],
#               [8, 8],
#               [1, 0.6],
#               [9, 11]])

colors = ['r', 'g', 'b', 'c', 'k', 'o', 'y']


class UnOptimizableError(Exception):
    """Can not optimize the data set"""


class KMeans:
    def __init__(self, k=2, tol=0.001, max_iter=30):
        self.k = k
        self.tol = tol
        self.max_iter = max_iter
        self.centroid1 = None
        self.centroid2 = None

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
        self.centroid1 = data[0]
        self.centroid2 = data[1]
        class1 = []
        class2 = []
        counter = 0
        while True:
            for i, val in enumerate(data):
                if self.euclidian_distance(self.centroid1, val) < self.euclidian_distance(self.centroid2, val):
                    class1.append(val)
                else:
                    class2.append(val)

            counter += 1

            prev_centroid1 = self.centroid1
            prev_centroid2 = self.centroid2

            self.centroid1 = self.arithmetic_mean(class1)
            self.centroid2 = self.arithmetic_mean(class2)

            if counter == self.max_iter:
                raise UnOptimizableError('Can not optimize the data set')
            if (self.euclidian_distance(self.centroid1, prev_centroid1) <= self.tol) and (
                        self.euclidian_distance(self.centroid2, prev_centroid2) <= self.tol):
                plt.scatter(self.centroid1[0], self.centroid1[1], s=130, color='k')
                plt.scatter(self.centroid2[0], self.centroid2[1], s=130, color='k')
                for item in class1:
                    plt.scatter(item[0], item[1], s=100, color='g', marker='x', linewidths=5)
                for item in class2:
                    plt.scatter(item[0], item[1], s=100, color='r', marker='x', linewidths=5)
                return

            class1 = []
            class2 = []

    def predict(self, point):
        if self.euclidian_distance(self.centroid1, point) < self.euclidian_distance(self.centroid2, point):
            plt.scatter(point[0], point[1], s=150, color='g', marker="*")
            return 0
        else:
            plt.scatter(point[0], point[1], s=150, color='r', marker="*")
            return 1


df = pd.read_excel('titanic.xls')
df.drop(['body', 'name'], 1, inplace=True)
# df.convert_objects(convert_numeric=True)
# print(df.head())
df.fillna(0, inplace=True)


def handle_non_numerical_data(df):
    # handling non-numerical data: must convert.
    columns = df.columns.values

    for column in columns:
        text_digit_vals = {}

        def convert_to_int(val):
            return text_digit_vals[val]

        # print(column,df[column].dtype)
        if df[column].dtype != np.int64 and df[column].dtype != np.float64:

            column_contents = df[column].values.tolist()
            # finding just the uniques
            unique_elements = set(column_contents)
            # great, found them.
            x = 0
            for unique in unique_elements:
                if unique not in text_digit_vals:
                    # creating dict that contains new
                    # id per unique string
                    text_digit_vals[unique] = x
                    x += 1
            # now we map the new "id" vlaue
            # to replace the string.
            df[column] = list(map(convert_to_int, df[column]))

    return df


df = handle_non_numerical_data(df)

# add/remove features just to see impact they have.
df.drop(['ticket', 'home.dest'], 1, inplace=True)

X = np.array(df.drop(['survived'], 1).astype(float))
X = preprocessing.scale(X)
y = np.array(df['survived'])

# X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.5)

clf = KMeans()
clf.fit(X)

correct = 0
for i, val in enumerate(X):
    predict_me = np.array(X[i].astype(float))
    predict_me = predict_me.reshape(-1, len(predict_me))
    for j, point in enumerate(predict_me):
        prediction = clf.predict(point)
        if prediction == y[i]:
            correct += 1

print(correct / float(len(X)))
