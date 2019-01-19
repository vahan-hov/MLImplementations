import cv2 as cv
import numpy as np

vid = cv.VideoCapture(0)
sensitivity = 45

try:
    while True:
        _, frame = vid.read()
        hsv = cv.cvtColor(frame, cv.COLOR_BGR2HSV)

        lower_green = np.array([60 - sensitivity, 100, 150])
        upper_green = np.array([60 + sensitivity, 255, 255])

        mask = cv.inRange(hsv, lower_green, upper_green)
        res = cv.bitwise_and(frame, frame, mask=mask)

        cv.imshow('Frame', frame)
        cv.imshow('Mask', mask)
        cv.imshow('Result', res)

        k = cv.waitKey(5) & 0xFF
        if k == 27:
            cv.destroyAllWindows()
            vid.release()
            break
except:
    cv.destroyAllWindows()
    vid.release()
