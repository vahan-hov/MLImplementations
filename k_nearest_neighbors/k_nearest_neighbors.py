from math import sqrt
import matplotlib.pyplot as plt
from matplotlib import style

style.use("fivethirtyeight")

data_set = {'k': [[1, 2], [2, 3], [3, 1]], 'r': [[6, 5], [7, 7], [8, 6]]}
new_feature = [4, 4]


def euclidian_distance(point1, point2):
    if type(point1) is list and type(point2) is list:
        return sqrt((point1[0] - point2[0]) ** 2 + (point1[1] - point2[1]) ** 2)
    return "No list provided, dumbass!"


def find_group_with_nearest_neighbors(ds, nf):
    # nearest_a1 = ds.get('k')[0]
    # nearest_a2 = ds.get('k')[1]
    # nearest_b1 = ds.get('r')[0]
    # nearest_b2 = ds.get('r')[1]
    last_index_a = ds.get('k').index(ds.get('k')[ds.__len__()])
    last_index_b = ds.get('r').index(ds.get('r')[ds.__len__()])
    # euclidian_distance_last_a = euclidian_distance(ds['k'][last_index_a], nf)
    # euclidian_distance_last_b = euclidian_distance(ds['r'][last_index_b], nf)
    total_distance_for_a = 0
    total_distance_for_b = 0
    for i in ds:
        for j in range(len(ds[i])):
            euclidian_distance_current = euclidian_distance(ds[i][j], nf)
            if i is 'k':
                print 'current distance a: ', euclidian_distance_current, '\n'
                total_distance_for_a += euclidian_distance_current
            elif i is 'r':
                total_distance_for_b += euclidian_distance_current
                print 'current distance b: ', euclidian_distance_current, '\n'

                # if euclidian_distance_current <= euclidian_distance_next and i is 'k':
                #     nearest_a2 = nearest_a1
                #     nearest_a1 = ds[i][j]
                # elif euclidian_distance_current <= euclidian_distance_next and i is 'r':
                #     nearest_b2 = nearest_b1
                #     nearest_b1 = ds[i][j]
    # print 'distance last a: ', euclidian_distance_last_a, '\n'
    # print 'distance last b: ', euclidian_distance_last_b, '\n'
    predicted_type = None
    if total_distance_for_a < total_distance_for_b:
        predicted_type = 'k'
    elif total_distance_for_b < total_distance_for_a:
        predicted_type = 'r'
    print 'Predicted type is: ', predicted_type
    return predicted_type


# def find_groups(ds, nf):
#     for i in ds:
#         for j in range(len(ds[i])):
#             print 'Item 1 ', three_nearest_neighbors(ds, nf), '.Comparing to ', ds[i][j]
#             if three_nearest_neighbors(ds, nf).__getitem__(0) is ds[i][j]:
#                 return i
#     return None


# print 'Predicted group '
# print find_groups(data_set, new_feature)
result = find_group_with_nearest_neighbors(data_set, new_feature)
print 'result is ', result, '\n'
[[plt.scatter(ii[0], ii[1], s=100, color=i) for ii in data_set[i]] for i in data_set]
plt.scatter(new_feature[0], new_feature[1], color=result)
plt._show()
