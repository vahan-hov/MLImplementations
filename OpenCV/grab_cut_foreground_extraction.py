import cv2
import numpy as np
import matplotlib.pyplot as plt
import os

home = os.getenv('HOME')
image = cv2.imread(f'{home}/Desktop/photos/vahanR.jpg')
mask = np.zeros(image.shape[:2], np.uint8)

bckg_model = np.zeros((1, 65), np.float64)
frg_model = np.zeros((1, 65), np.float64)

rect = (140, 80, 250, 240)

cv2.grabCut(image, mask, rect, bckg_model, frg_model, 5, cv2.GC_INIT_WITH_RECT)
mask2 = np.where((mask == 2) | (mask == 0), 0, 1).astype('uint8')
image = image * mask2[:, :, np.newaxis]
image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
plt.imshow(image)
plt.colorbar()
plt.show()
