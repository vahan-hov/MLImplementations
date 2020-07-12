import numpy as np


class KMeans:
	def __init__(self, k=2, tol=0.001, max_iter=100):
		self.k = k
		self.tol = tol
		self.max_iter = max_iter
		self.n_features = None

		self.means = None
		self.clusters = None
		self.covariance_matrices = None
		self.cluster_probabilities = None

	@staticmethod
	def _derive_distances(data_point, means):
		distances = []
		for mean in means:
			distances.append(np.linalg.norm(data_point - mean))
		return distances

	def fit(self, x: np.ndarray):
		self.n_features = x.shape[1]
		means = []
		for _ in range(self.k):
			random_index = np.random.randint(0, len(x), 1)
			means.append(x[random_index])

		for iteration in range(self.max_iter):
			clusters = [[] for _ in range(self.k)]
			for data_point in x:
				# derive distances from all means
				distances = self._derive_distances(data_point, means)
				# get cluster with min distance from the point and append
				clusters[np.argmin(distances)].append(data_point)

			# remove empty clusters
			clusters = [cluster for cluster in clusters if cluster != []]

			prev_means = means.copy()

			# calculate means and for new clusters
			for i, cluster in enumerate(clusters):
				means[i] = np.mean(cluster, axis=0)

			# check if the new means have moved compared to the previous ones
			if np.allclose(means, prev_means, atol=self.tol):
				print('Optimized KMeans')
				self.means = means
				self.clusters = clusters
				self.covariance_matrices = []
				self.cluster_probabilities = []
				for i in range(self.k):
					self.covariance_matrices.append(np.cov(clusters[i], rowvar=False))
					self.cluster_probabilities.append(len(clusters[i]))
				return
		print('Unable to optimize in given steps.')

	def predict(self, x):
		if x.shape[1] != self.n_features:
			print('Prediction data must have the same number of dimensions as training data')
			return

		predictions = []
		for data_point in x:
			distances = self._derive_distances(data_point, self.means)
			predictions.append(np.argmin(distances))

		return predictions
