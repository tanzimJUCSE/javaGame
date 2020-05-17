import pyttsx3
import speech_recognition as sr
import datetime
import wikipedia as wp
import webbrowser
import os
import smtplib
engine=pyttsx3.init('sapi5')
voices=engine.getProperty('voices')
#voice 0 for Male voice 1 for female
#print(voices[o].id)
engine.setProperty('voice',voices[0].id)

def speak(audio):
    engine.say(audio)
    engine.runAndWait()

def wishMe():
    hour=int(datetime.datetime.now().hour)
    if hour >=0 and hour<12:
        speak("Good Morning Sir")
    elif hour>=12 and hour< 18:
        speak("Good Afternoon Sir")
    else:
        speak("Good Evening Sir")
    
    speak("How can i help you?")  
def takeCommand():
    """
    This function takes microphone input and return string output
    
    """
    r=sr.Recognizer()
    
    with sr.Microphone() as source:
        print("Listening...")
        r.pause_threshold=1
        audio=r.listen(source)
        
        try:
            print("Recognizing...")
            query=r.recognize_google(audio)
            print(f"User said: {query.lower()}\n")
            
        except Exception as e:
            print(e)
            print("Please Say that again")
            return "none"
        return query
def sendEmail(to,content):
    server=smtplib.SMTP('smtp.gmail.com',587)
    server.ehlo()
    server.starttls()
    server.login("mahfuz.bipu96@gmail.com","googlemyaccount")  
    server.sendmail("mahfuz.bipu96@gmail.com",to,content)
    server.close()            
#main function call
if __name__=="__main__":
    wishMe() 
    if 1:
        user_query=takeCommand().lower()
        if 'wikipedia' in user_query:
            speak('Searching Wikipedia...')
            result=wp.summary(user_query,sentences=2)
            speak('according too wikipedia')
            print(result)
            speak(result)
        elif 'open youtube' in user_query:
            webbrowser.open("youtube.com")
        elif 'open google' in user_query:
            webbrowser.open("google.com")
        elif 'open d drive' in user_query:
            path="D:\\"
            os.startfile(path)
        elif 'open c drive' in user_query:
            path="C:\\"
            os.startfile(path)
        elif 'open e drive' in user_query:
            path="E:\\"
            os.startfile(path)   
        elif 'open f drive' in user_query:
            path="F:\\"
            os.startfile(path) 
        elif 'open chrome' in user_query:
            path="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"
            os.startfile(path)  
        elif 'tell the time' in user_query:
            time=datetime.datetime.now().strftime("%H:%M:%S") 
            speak(f"Sir , the time is {time}")
        elif 'play music' in user_query:
            music_dir="E:\\Songs"
            songs=os.listdir(music_dir)
            os.startfile(os.path.join(music_dir,songs[0]))
            """The songs list can be updated by using dictionary giving every song id a particular name 
            And playing music accroding to the individual song name"""
        elif 'send email' in user_query:
            try:
                speak('Please write the mail address,Sir: ')
                mail_to=input("Enter the mail address:\n")
                speak("What should i say ?")
                content=takeCommand()
                sendEmail(mail_to,content)
                speak("Message has been sent Sir")
            except Exception as e:
                speak("Something wrong sir")    
        elif 'quit' in user_query:
            exit()    




