import matplotlib.pyplot as plt
import numpy as np
from sklearn.datasets import make_blobs
from sklearn.model_selection import train_test_split
from sklearn import mixture
from gaussian_mixture import GaussianMixture


# generate new data or use a previously generated data from pickle
def get_data(visualize=True, generate_new=False):
	# generate some random cluster data
	if generate_new:
		data_points, _ = make_blobs(n_samples=100, n_features=2, centers=2)
		rng = np.random.RandomState(74)
		transformation = rng.normal(size=(data_points.shape[1], data_points.shape[1]))
		data_points = np.dot(data_points, transformation)
		if visualize:
			plt.scatter(data_points[:, 0], data_points[:, 1])
			plt.xlabel("Feature 0")
			plt.ylabel("Feature 1")
			plt.show()
		return data_points

	import pickle
	with open('data.pickle', 'rb') as f:
		return pickle.load(f)


x = get_data()
x_train, x_test = train_test_split(x, test_size=0.1, random_state=1)

gm = GaussianMixture(k=2)
gm.fit(x_train)
predictions = gm.predict(x_test)

sk_gm = mixture.GaussianMixture(n_components=2)
sk_gm.fit(x_train)
sk_predictions = sk_gm.predict(x_test)

print(f'Custom implementation predictions: {predictions}')
print(f'SKlearn implementation predictions: {sk_predictions}')
