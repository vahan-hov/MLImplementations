# Gesture Recognition

It's my first deep learning project. I use neural networks from keras and opencv library to recognize some of the human hand gestures. Images are taken directly from your camera. 

# Getting started
Clone the project
## Installings
Install python 3.6 version or later using
```sh
$ sudo add-apt-repository ppa:jonathonf/python-3.6
```
```sh
$ sudo apt-get update
```
```sh
$ sudo apt-get install python3.6
```
 
Install 'pip' for python 3.6 using
```sh
$ curl https://bootstrap.pypa.io/get-pip.py | sudo -H python3.6z
```
Install dependencies from 'requirements.txt' file using 
```sh
$ pip install -r path/to/requirements.txt
```

## Downloads

Follow the [google drive link](https://drive.google.com/open?id=1E45EhMohzLEt_OvaGAxNxWZl-ZykwL7W) and download 'data.zip' containing images of hand gestures and 'resources.zip' containing trained model and a 'pickle' file. Unzip the folders and place under the project directory.

After this you should be able to run 'recognize_gestures.py' file with python3.6.

# Usage
Run the 'recognize_gestures.py' file with python3.6 and try to keep the camera static. The program will start calibration and get the background. After 30 frames it will start comparing the background and find differences. Raise your hand and show one of the following hand gestures: 'Palm', 'Peace', 'Fist', 'Ok' or 'None'.

# Author
- ##### Vahan Hovhannisyan
# Acknoledgements
- [pythonprogramming.net](https://pythonprogramming.net/introduction-deep-learning-python-tensorflow-keras/)
- [Gogul09](https://github.com/Gogul09/gesture-recognition)