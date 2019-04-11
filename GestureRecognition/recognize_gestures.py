# organize imports
import cv2
import imutils
import os
from numpy import float32, where
from tensorflow.keras.models import load_model

# global variables
from utils.constants import MODEL_PATH, MAX_NUM_OF_IMAGES, \
    NEW_GESTURE, DATA_FOLDER, IMG_PATH, IMAGE_SIZE, CATEGORIES

BG = None


# -------------------------------------------------------------------------------
# Function - To find the running average over the background
# -------------------------------------------------------------------------------
def run_avg(image, a_weight):
    global BG
    # initialize the background
    if BG is None:
        BG = image.copy().astype('float')
        return

    # compute weighted average, accumulate it and update the background
    cv2.accumulateWeighted(image, BG, a_weight)


# -------------------------------------------------------------------------------
# Function - To segment the region of hand in the image
# -------------------------------------------------------------------------------
def segment(image, threshold=25):
    global BG
    # find the absolute difference between background and current frame
    diff = cv2.absdiff(BG.astype('uint8'), image)

    # threshold the diff image so that we get the foreground
    thresholded = cv2.threshold(diff,
                                threshold,
                                255,
                                cv2.THRESH_BINARY)[1]

    # get the contours in the thresholded image
    (_, cnts, _) = cv2.findContours(thresholded.copy(),
                                    cv2.RETR_EXTERNAL,
                                    cv2.CHAIN_APPROX_SIMPLE)

    # return None, if no contours detected
    if len(cnts) == 0:
        return
    else:
        # based on contour area, get the maximum contour which is the hand
        segmented = max(cnts, key=cv2.contourArea)
        return thresholded, segmented


# -------------------------------------------------------------------------------
# Function - To save images from camera to train neural networks later
# -------------------------------------------------------------------------------
def save_images(img):
    # create 'DATA_FOLDER' folder if not exists
    if not os.path.exists(DATA_FOLDER):
        os.mkdir(DATA_FOLDER)
        print(f'Created {DATA_FOLDER}')
    # create 'IMG_PATH' folder if not exists
    if not os.path.exists(IMG_PATH):
        print(f'Created {IMG_PATH}')
        os.mkdir(IMG_PATH)

    # stop saving images if the limit is hit
    curr_number_of_images = len(os.listdir(IMG_PATH))
    if curr_number_of_images >= MAX_NUM_OF_IMAGES:
        print(f'Got the required {MAX_NUM_OF_IMAGES} images of {NEW_GESTURE}.'
              f' Saved in {IMG_PATH}.Data collection is over.')
        return False

    # resize and save images
    img = cv2.resize(img, (IMAGE_SIZE, IMAGE_SIZE))
    cv2.imwrite(f'{IMG_PATH}/{curr_number_of_images}.jpg', img)
    print(f'Saved {curr_number_of_images}th image in {IMG_PATH} ...')
    return True


# -------------------------------------------------------------------------------
# Function - To predict the gesture from image using trained model
# -------------------------------------------------------------------------------
def predict(model, img):
    # resize image, just in case
    img = cv2.resize(img, (IMAGE_SIZE, IMAGE_SIZE))

    # the reshape of image array that keras requires
    img = img.reshape(-1, IMAGE_SIZE, IMAGE_SIZE, 1).astype(float32)
    prediction = model.predict(img)
    # prediction is a 2D array, take the index of element that has max value, which will be our prediction
    index = where(prediction[0] == max(prediction[0]))[0][0]
    prediction = CATEGORIES[index]
    return prediction


# -------------------------------------------------------------------------------
# Main function
# -------------------------------------------------------------------------------
def main(should_predict=True):
    # initialize weight for running average
    a_weight = 0.5

    # get the reference to the webcam
    camera = cv2.VideoCapture(0)

    # region of interest (ROI) coordinates
    top, right, bottom, left = 10, 350, 225, 590

    # initialize num of frames
    num_frames = 0

    # do not save images from camera until user presses 's'
    should_save = False

    # initialize prediction
    prediction = None

    # print name of the model being used
    print(MODEL_PATH)

    # load the model
    model = load_model(MODEL_PATH)

    # keep looping, until interrupted
    while True:
        # get the current frame
        (grabbed, frame) = camera.read()

        # resize the frame
        frame = imutils.resize(frame, width=700)

        # flip the frame so that it is not the mirror view
        frame = cv2.flip(frame, 1)

        # clone the frame
        clone = frame.copy()

        # get the rectangle shaped region of interest
        roi = frame[top:bottom, right:left]

        # convert the roi to gray scale and blur it to reduce noise
        gray = cv2.cvtColor(roi, cv2.COLOR_BGR2GRAY)
        gray = cv2.GaussianBlur(gray, (7, 7), 0)

        # to get the background, keep looking till a threshold is reached
        # so that our running average model gets calibrated
        if num_frames < 30:
            if num_frames == 1:
                print('Please wait! calibrating...')
            elif num_frames == 29:
                print('Calibration successful...')
            run_avg(gray, a_weight)
        else:
            # segment the hand region
            hand = segment(gray)

            # check whether hand region is segmented
            if hand is not None:
                # if yes, unpack the thresholded_img image and
                # segmented region
                (thresholded_img, segmented) = hand

                # if true make predictions every 20 frame
                if should_predict and num_frames % 20 == 0:
                    prediction = predict(model, thresholded_img)

                # if true show the prediction on screen
                if prediction is not None:
                    cv2.putText(clone, f'I see {prediction}', (50, 450), cv2.FONT_HERSHEY_COMPLEX, 3,
                                color=(0, 0, 255),
                                lineType=cv2.LINE_4)

                # draw the segmented region and display the frame
                cv2.drawContours(clone, [segmented + (right, top)], -1, (0, 0, 255))
                cv2.imshow('Thesholded', thresholded_img)

                # if true save images of the gesture to train neural networks later
                if should_save:
                    should_save = save_images(thresholded_img)

        # draw the segmented hand
        cv2.rectangle(clone, (left, top), (right, bottom), (0, 255, 0), 2)

        # increment the number of frames
        num_frames += 1

        # display the frame with segmented hand
        cv2.imshow('Video Feed', clone)

        # observe the keypress by the user
        keypress = cv2.waitKey(10) & 0xFF

        # if the user pressed 'q', then stop looping
        if keypress == ord('q'):
            print('Quiting ..')
            break

        # if true start saving images
        elif keypress == ord('s'):
            should_save = True
            print('Started saving...')

    # free up memory
    camera.release()
    cv2.destroyAllWindows()


# call the main function
if __name__ == '__main__':
    main()
