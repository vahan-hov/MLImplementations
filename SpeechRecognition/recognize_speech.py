import speech_recognition as sr

recording = sr.Recognizer()

with sr.Microphone() as source:
    recording.adjust_for_ambient_noise(source)

    while True:
        print("Please Say something:")
        audio = recording.listen(source)
        try:
            speech = recording.recognize_google(audio)
            print("You said: " + speech)
            if speech == 'quit':
                break
        except:
            print('failed at duty')
