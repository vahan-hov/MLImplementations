from statistics import mean
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import style
import random

style.use('fivethirtyeight')

xs = np.array([1, 2, 3, 4, 5, 6], dtype=np.float64)
ys = np.array([5, 4, 6, 5, 6, 7], dtype=np.float64)


def create_dataset(hm, variance):
    xs_local = []
    ys_local = []
    for i in range(0, hm, 1):
        xs_local.append(i)
        ys_local.append(random.randrange(0, variance))

    return np.array(xs_local), np.array(ys_local)


def best_fit_slope_and_intercept(xs, ys):
    m_local = ((xs.mean() * ys.mean() - (xs * ys).mean()) /
               (xs.mean() ** 2 - (xs ** 2).mean()))
    b_local = ys.mean() - m_local * xs.mean()
    return m_local, b_local


def calc_regression_line(xs):
    regression_line_local = []
    for x in xs:
        regression_line_local.append(m * x + b)
    return regression_line_local


def squared_error(ys_orig, ys_line):
    return sum((ys_line - ys_orig) ** 2)


def coefficient_of_determination(ys_orig, ys_line):
    SSEreg_line = squared_error(ys_orig, ys_line)
    SSEmean_y = squared_error(ys_orig, mean(ys_orig))
    return 1 - SSEreg_line / SSEmean_y


# xs, ys = create_dataset(40, 10)
m, b = best_fit_slope_and_intercept(xs, ys)
regression_line = calc_regression_line(xs)

predict_x = 8
predict_y = m * predict_x + b

print 'Data set ', ys
print 'Confidence is: ', coefficient_of_determination(ys, regression_line) * 100, '%'

plt.scatter(xs, ys)
plt.scatter(predict_x, predict_y, color='g')
plt.plot(xs, regression_line)
plt.show()
