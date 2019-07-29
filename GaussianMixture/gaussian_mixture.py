import numpy as np
import matplotlib.pyplot as plt
from matplotlib import cm
from scipy.stats import multivariate_normal
from sklearn.cluster import KMeans


class GaussianMixture:
	def __init__(self, k, tol=0.001, max_iter=100, visualization_level=2):
		self.k = k
		self.tol = tol
		self.max_iter = max_iter
		self.visualization_level = visualization_level
		self.means = None
		self.covariance_matrices = None
		self.cluster_probabilities = None
		self.n_features = None

	@staticmethod
	def _visualize(clusters=None, means=None, show=True):
		clusters = [np.array(cluster) for cluster in clusters]
		# check if data is 2 dimensional
		if clusters[0].shape[1] != 2:
			return
		# generate colors for each cluster
		colors = cm.rainbow(np.linspace(0, 1, len(clusters)))
		for cluster, color, mean in zip(clusters, colors, means):
			color = [color]
			plt.scatter(cluster[:, 0], cluster[:, 1], c=color)
			plt.scatter(mean[0], mean[1], marker='*', c=color, s=200)
		if show:
			plt.show()

	def _derive_initial_parameters(self, x):
		# use sklearn's implementation of KMeans for deriving initial parameters
		k_means = KMeans(n_clusters=self.k, random_state=0).fit(x)
		clusters = []
		covariance_matrices = []
		cluster_probabilities = []
		means = k_means.cluster_centers_
		for i in range(self.k):
			cluster = x[np.where(k_means.labels_ == i)]
			clusters.append(cluster)
			covariance_matrices.append(np.cov(cluster, rowvar=False))
			cluster_probabilities.append(len(cluster))

		return means, covariance_matrices, cluster_probabilities

	# calculating likelihoods that a data point belongs to each cluster
	@staticmethod
	def _derive_likelihoods(data_point, means, covariance_matrices, cluster_probabilities):
		likelihoods = []
		for i, (mean, covariance_matrix, cluster_probability) in enumerate(
				zip(means, covariance_matrices, cluster_probabilities)):
			log_likelihood = multivariate_normal.pdf(data_point, mean=mean, cov=covariance_matrix, allow_singular=True)
			likelihoods.append(log_likelihood * cluster_probability)
		return likelihoods

	def fit(self, x: np.ndarray):
		self.n_features = x.shape[1]
		means, covariance_matrices, cluster_probabilities = self._derive_initial_parameters(x)

		for iteration in range(self.max_iter):
			clusters = [[] for _ in range(self.k)]
			for data_point in x:
				likelihoods = self._derive_likelihoods(data_point, means, covariance_matrices, cluster_probabilities)
				# assign the data point to a cluster that has max likelihood of having it
				clusters[np.argmax(likelihoods)].append(data_point)

			prev_centers = means.copy()

			# calculate means and covariance matrices for new clusters
			for i, cluster in enumerate(clusters):
				means[i] = np.mean(cluster, axis=0)
				covariance_matrices[i] = np.cov(cluster, rowvar=False)

			if self.visualization_level > 1 and iteration % 5 == 0:
				self._visualize(clusters, means, show=True)

			# check if the new means have moved compared to the previous ones
			if np.allclose(means, prev_centers, atol=self.tol):
				self.means = means
				self.covariance_matrices = covariance_matrices
				self.cluster_probabilities = cluster_probabilities
				print('Optimized')

				if self.visualization_level > 0:
					self._visualize(clusters, means, show=True)
				return
		print('Unable to optimize in given steps.')

	def predict(self, x: np.ndarray):
		if x.shape[1] != self.n_features:
			print('Prediction data must have the same number of dimensions as training data')
			return

		predictions = []
		for data_point in x:
			likelihoods = \
				self._derive_likelihoods(data_point, self.means, self.covariance_matrices, self.cluster_probabilities)
			predictions.append(np.argmax(likelihoods))

		return predictions
