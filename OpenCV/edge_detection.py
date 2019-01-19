import numpy as np
import cv2

cap = cv2.VideoCapture(0)
sensitivity = 45
while True:
    _, frame = cap.read()
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    lower_green = np.array([60 - sensitivity, 100, 150])
    upper_green = np.array([60 + sensitivity, 255, 255])

    mask = cv2.inRange(hsv, lower_green, upper_green)
    res = cv2.bitwise_and(frame, frame, mask=mask)

    laplacian = cv2.Laplacian(frame, cv2.CV_64F)
    edges = cv2.Canny(frame, 100, 200)
    # sobelx = cv2.Sobel(frame, cv2.CV_64F, 1, 0, ksize=5)
    # sobely = cv2.Sobel(frame, cv2.CV_64F, 0, 1, ksize=5)

    cv2.imshow('Original', frame)
    # cv2.imshow('Mask', mask)
    # cv2.imshow('laplacian', laplacian)
    # cv2.imshow('res', res)
    # cv2.imshow('sobelx', sobelx)
    # cv2.imshow('sobely', sobely)
    cv2.imshow('edges',edges)

    k = cv2.waitKey(5) & 0xFF
    if k == 27:
        break

cv2.destroyAllWindows()
cap.release()
