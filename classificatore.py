# Import libraries and modules
import cv2
import os
import numpy as np
import shutil

from keras.models import load_model
from py4j.java_gateway import JavaGateway, GatewayParameters


def get_class_name(classNum):
    # convert the cell-class number into its name string.
    if classNum == 0:
        return "epiteliali"
    if classNum == 1:
        return "neutrofili"
    if classNum == 2:
        return "eosinofili"
    if classNum == 3:
        return "mastcellule"
    if classNum == 4:
        return "linfociti"
    if classNum == 5:
        return "mucipare"
    if classNum == 6:
        return "others"


def load_data(data_directory):
    """
    Loading images and labels from directory
    :param data_directory:
    :return: images, labels
    """
    directories = [d for d in os.listdir(data_directory)
                   if os.path.isdir(os.path.join(data_directory, d))]
    images = []

    for d in directories:
        # images are contained in a directory called cellule
        if(d=="cellule"):
            label_directory = os.path.join(data_directory, d)
            file_names = [os.path.join(label_directory, f)
                      for f in os.listdir(label_directory)
                      if f.endswith('.png') or f.endswith('.PNG')]
            for f in file_names:
                img = cv2.imread(f)
                (b, g, r) = cv2.split(img)
                img = cv2.merge([r, g, b])
                img = cv2.resize(img, (50, 50))
                images.append(img)

    return images, file_names


# load the trained model.
model=load_model('my_model.h5')
# load the best weights for the model.
model.load_weights('weights.best.hdf5')
model.compile(optimizer='rmsprop', loss='categorical_crossentropy')

# connect to the JVM
gateway = JavaGateway(gateway_parameters=GatewayParameters(port=25335))

# get the patient path.
patient = gateway.entry_point

# define the path of the folders where images are contained.
path = patient.getPathInput()

img_list, file_names = load_data(path)

# images to multi dimensional arrays
img_list = np.array(img_list)
img_list = img_list.astype("float32")
img_list /= 255
img_list = img_list.reshape(img_list.shape[0], 50, 50, 3)

# define a counter for the file names...
i = 0
for img in img_list:
    # for each image in the list
    img = img.reshape((1,)+img.shape)
    # add a dimension to the image
    img_class = model.predict_classes(img)
    # define the path of its folder-class (classify the image).
    class_path = os.path.join(path, get_class_name(img_class))
    # move it to the correct destination.
    shutil.move(file_names[i], class_path)
    i += 1
