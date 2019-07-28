import numpy as np
import matplotlib.pyplot as plt
import pickle
from matplotlib import cm
from scipy.stats import multivariate_normal
from sklearn.cluster import KMeans
from sklearn.datasets import make_blobs


def get_data(visualize=True):
	# generate some random cluster data
	# x, _ = make_blobs(n_samples=100, n_features=2, centers=10)
	# rng = np.random.RandomState(74)
	# transformation = rng.normal(size=(x.shape[1], x.shape[1]))
	# x = np.dot(x, transformation)
	# if visualize:
	# 	plt.scatter(x[:, 0], x[:, 1])
	# 	plt.xlabel("Feature 0")
	# 	plt.ylabel("Feature 1")
	# 	plt.show()
	# return x
	with open('data.pickle', 'rb') as f:
		return pickle.load(f)


class GaussianMixture:
	def __init__(self, k=5, tol=0.001, max_iter=30):
		# use private
		self.k = k
		self.tol = tol
		self.max_iter = max_iter
		self.means = None
		self.variances = None
		self.cluster_probabilities = None
		self.n_features = None

	@staticmethod
	def _visualize(clusters, centers, show=True):
		# TODO check if 2 dimensional
		clusters = [np.array(cluster) for cluster in clusters]
		if clusters[0].shape[1] != 2:
			print('Can not plot a data that has different number of dimensions than 2')
			return

		colors = cm.rainbow(np.linspace(0, 1, len(clusters)))
		for cluster, center, color in zip(clusters, centers, colors):
			# getting rid of silly matplotlib warning
			color = [color]
			plt.scatter(cluster[:, 0], cluster[:, 1], c=color)
			plt.scatter(center[0], center[1], marker='*', c=color, s=200)
		if show:
			plt.show()

	def derive_initial_parameters(self, x):
		k_means = KMeans(n_clusters=self.k, random_state=0).fit(x)
		clusters = []
		variances = []
		cluster_probabilities = []
		means = k_means.cluster_centers_
		for i in range(self.k):
			cluster = x[np.where(k_means.labels_ == i)]
			clusters.append(cluster)
			variances.append(np.cov(cluster, rowvar=False))
			cluster_probabilities.append(len(cluster))

		return means, variances, cluster_probabilities

	def fit(self, x: np.ndarray, visualization_level: int):
		self.n_features = x.shape[1]
		means, variances, cluster_probabilities = self.derive_initial_parameters(x)

		for iteration in range(self.max_iter):
			print(f'iteration {iteration}')
			clusters = [[] for _ in range(self.k)]
			for data_point in x:
				likelihoods = []
				for i, (center, variance, cluster_probability) in enumerate(
						zip(means, variances, cluster_probabilities)):
					log_likelihood = multivariate_normal.pdf(data_point, mean=center, cov=variance, allow_singular=True)
					# TODO add pi value to log likelihood
					likelihoods.append(log_likelihood * cluster_probability)
				clusters[np.argmax(likelihoods)].append(data_point)

			prev_centers = means.copy()

			# calculating means (means) and variances for new clusters
			# TODO is there a better way to assign new values to means and variances ?
			for i, cluster in enumerate(clusters):
				means[i] = np.mean(cluster, axis=0)
				cluster = np.array(cluster)
				variances[i] = np.cov(cluster, rowvar=False)

			# variances = [np.array(variance) for variance in variances]
			if iteration % 5 == 0 and visualization_level > 1:
				self._visualize(clusters, means, show=True)

			if np.allclose(means, prev_centers, atol=self.tol):
				self.means = means
				self.variances = variances
				self.cluster_probabilities = cluster_probabilities
				print('Optimized')
				if visualization_level > 0:
					self._visualize(clusters, means, show=True)
				return
		print('Unable to optimize in given steps.')

	# get several points as argument and return an array describing which point belongs to which cluster ([1,3,2] if k =3)
	def predict(self):
		pass


gm = GaussianMixture()
gm.fit(get_data(), visualization_level=0)
