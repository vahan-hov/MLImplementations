# organize imports
import os
import cv2
import pickle
import numpy as np
from random import shuffle
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D
from tensorflow.keras.layers import Dense, Activation, Flatten
# global variables
from utils.constants import MODEL_PATH, RES_FOLDER, IMAGE_SIZE, DATA_FOLDER, CATEGORIES


# -------------------------------------------------------------------------------
# Function - To get data from 'pickle' file or from 'data' folder
# -------------------------------------------------------------------------------
def get_data(refresh=False):
    dataset_path = f'{RES_FOLDER}/xs_ys.pickle'
    # if no refresh required and 'pickle' file exists load it
    if not refresh and os.path.exists(dataset_path):
        print('Reading previous data ...')
        with open(dataset_path, 'rb') as input_file:
            xs, ys, gesture_count = pickle.load(input_file)
            # return image arrays their labels and number of gestures
            return xs, ys, gesture_count

    print('Refreshing data ...')
    gestures = []
    xs = []
    ys = []
    data = []

    # read data folder and get image arrays and their labels
    for gesture_directory in CATEGORIES:
        gestures.append(gesture_directory)
        img_path = f'{DATA_FOLDER}/{gesture_directory}'
        for img in os.listdir(img_path):
            img_arr = cv2.imread(f'{img_path}/{img}', cv2.IMREAD_GRAYSCALE)
            new_array = cv2.resize(img_arr, (IMAGE_SIZE, IMAGE_SIZE))
            data.append((new_array, gestures.index(gesture_directory)))

    print(gestures)
    # shuffle data and separate image arrays from labels
    shuffle(data)
    for img_arr, label in data:
        xs.append(img_arr)
        ys.append(label)

    # noinspection PyArgumentList
    # reshape required by keras
    xs = np.array(xs).reshape(-1, IMAGE_SIZE, IMAGE_SIZE, 1).astype(np.float32)

    # normalization
    xs /= 255

    # create 'RES_FOLDER' folder if not exists
    if not os.path.exists(RES_FOLDER):
        os.mkdir(RES_FOLDER)

    # save data in a'pickle' file
    with open(dataset_path, 'wb') as output_file:
        pickle.dump((xs, ys, len(gestures)), output_file)

    # return image arrays their labels and number of gestures
    return xs, ys, len(gestures)


# -------------------------------------------------------------------------------
# Function - To fit neural networks
# -------------------------------------------------------------------------------
def fit(xs, ys, gesture_count, save=False):
    # initialize sequential model
    model = Sequential()

    model.add(Conv2D(32, (3, 3), input_shape=xs.shape[1:]))
    model.add(Activation('relu'))
    model.add(MaxPooling2D(pool_size=(2, 2)))

    model.add(Conv2D(32, (3, 3)))
    model.add(Activation('relu'))
    model.add(MaxPooling2D(pool_size=(2, 2)))

    # convert our 3D feature maps to 1D feature vectors
    model.add(Flatten())

    model.add(Dense(64))

    model.add(Dense(gesture_count, activation='softmax'))

    model.compile(loss='sparse_categorical_crossentropy',
                  optimizer='adam',
                  metrics=['accuracy'])

    model.fit(xs, ys, batch_size=32, epochs=10, validation_split=0.1)

    # if true save the model
    if save:
        save_model(model)


# -------------------------------------------------------------------------------
# Function - To save model
# -------------------------------------------------------------------------------
def save_model(model):
    # create 'RES_FOLDER' folder if not exists
    if not os.path.exists(f'{RES_FOLDER}'):
        os.mkdir(RES_FOLDER)

    # save the modek
    model.save(MODEL_PATH)
    print(f'Saved the model in {MODEL_PATH}')


# -------------------------------------------------------------------------------
# Main Function
# -------------------------------------------------------------------------------
def main():
    x, y, gesture_count = get_data(refresh=False)
    fit(x, y, gesture_count, save=True)


# call the main function
if __name__ == '__main__':
    main()
