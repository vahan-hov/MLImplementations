import cv2
import os
import urllib.request


def store_raw_images():
    neg_images_link = 'http://image-net.org/api/text/imagenet.synset.geturls?wnid=n00007846'
    neg_image_urls = urllib.request.urlopen(neg_images_link).read().decode()

    if not os.path.exists('neg'):
        os.makedirs('neg')

    # pic_num = len(os.listdir('neg')) + 1
    pic_num = 3007
    for url in neg_image_urls.split('\n'):
        try:
            print(url)
            print('pic num: ', pic_num)
            img_path = f'neg/neg_sample_{pic_num}.jpg'

            urllib.request.urlretrieve(url, img_path)
            img = cv2.imread(img_path, cv2.IMREAD_GRAYSCALE)
            resized = cv2.resize(img, (100, 100))
            cv2.imwrite(img_path, resized)
            pic_num += 1
        except Exception as e:
            print(str(e))


def remove_uglies():
    for img in os.listdir('neg'):
        for ugly in os.listdir('uglies'):
            try:
                question = cv2.imread(f'neg/{img}')
                ugly = cv2.imread(f'uglies/{ugly}')
                if ugly.all() == question.all():
                    print(f'removing neg/{img}')
                    os.remove(f'neg/{img}')
            except Exception as e:
                print(e)


def create_pos_and_neg():
    # for file_type in ['neg', 'pos']:
    for file_type in ['neg']:
        for img in os.listdir(file_type):
            if file_type == 'pos':
                line = file_type + '/' + img + ' 1 0 0 50 50\n'
                with open('info.dat', 'a') as f:
                    f.write(line)
            elif file_type == 'neg':
                line = file_type + '/' + img + '\n'
                with open('bg.txt', 'a') as f:
                    f.write(line)


def reformat_manually_added_pos_imgs():
    for img_name in os.listdir('pos'):
        img = cv2.imread(f'pos/{img_name}', cv2.IMREAD_GRAYSCALE)
        resized = cv2.resize(img, (50, 50))
        cv2.imwrite(f'pos/{img_name}', resized)


def reformat_manually_added_neg_imgs():
    counter = 1
    neg_imgs = sorted(os.listdir('neg'))
    for img_name in neg_imgs:
        try:
            print(img_name)
            img = cv2.imread(f'neg/{img_name}', cv2.IMREAD_GRAYSCALE)
            resized = cv2.resize(img, (100, 100))
            os.remove(f'neg/{img_name}')
            img_name = f'neg_sample_{counter}.jpg'
            cv2.imwrite(f'neg/{img_name}', resized)
            counter += 1
            print('result name: ', img_name)
        except Exception as e:
            print('================exception====', str(e))


def detect_watch_and_face():
    # multiple pos https://github.com/mrnugget/opencv-haar-classifier-training

    path_to_watch_cascade = 'cascades/watch_cascade.xml'
    path_to_face_cascade = 'cascades/haarcascade_frontalface_default.xml'

    face_cascade = cv2.CascadeClassifier(path_to_face_cascade)
    watch_cascade = cv2.CascadeClassifier(path_to_watch_cascade)

    if not os.path.exists(path_to_watch_cascade) or not os.path.exists(path_to_face_cascade):
        raise Exception('The path is wrong!')

    cap = cv2.VideoCapture(0)

    while True:
        ret, img = cap.read()
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

        faces = face_cascade.detectMultiScale(gray, 1.3, 5)

        # image, reject levels level weights.
        watches = watch_cascade.detectMultiScale(gray, 4, 4)

        for (x, y, w, h) in watches:
            font = cv2.FONT_HERSHEY_SIMPLEX
            cv2.putText(img, 'Watch', (x - w, y - h), font, 1.5, (11, 255, 255), thickness=3)

        for (x, y, w, h) in faces:
            cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)

        cv2.imshow('img', img)
        k = cv2.waitKey(30) & 0xff
        if k == 27:
            break

    cap.release()
    cv2.destroyAllWindows()


detect_watch_and_face()
