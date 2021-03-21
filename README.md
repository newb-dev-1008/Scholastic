# Scholastic
A one-stop campus management application, encompassing AR, NLP and Machine Learning to enhance vanilla online learning.

# Problem Statement

Develop tools that would increase Productivity for students
and teachers.
In the past 10-15 years we have seen the transition of things around us
from offline to online, whether it's business, entertainment activities, daily
needs, and now even education. Productivity tools have been a success
with businesses and firms. Develop productivity tools for students and
teachers in any domain of your choice that can achieve the same
success in the educational field in the future.

# Problem Solution

In this post - covid era, the education sector has erupted, with a plethora of new opportunities. Scholastic provides a complete and comprehensive education portal for students as well as staff. 
- The USP of the application are lab sessions simulated using Augmented Reality. 
- Other features include usage of virtual assistants like Alexa to provide reminders, complete timetable and file integration
- A blockchain based  digital report card system where teachers can upload report cards for students & send it to parents.  
- Plagiarism checker for assignments. 
It is a one - stop solution to all needs such as announcements and circulars from institution or a staff member, fee payment and even a chatbot for additional support.

## Tech Stack
- Google Assistant For Chatbot
- Via the Actions Console 
- Python3 for Plagiarism Checker
- Gensim
- NumPy
- NLP Models ( Word Embedding)
- Heroku (For Deployment & making API Calls)
- Android Studio with Java For Main Android App
- AR Foundation For Simulated Lab Sessions with Blender & Unity 
- Ethereum, Solidity & React.js For Blockchain Based Storage for Report Cards (Along with Ganache & Truffle Suite)

## Google Assistant
The bot was made using Google Assistant via the Actions Console. Various pre-programmed sets of outputs referring to the different intents had been programmed and trained to achieve the correct input.

#### How It Was Used
Custom intents were set referring to set time periods to which a question referred to. On an input from the user, the assistant determines the intent into sections like “today”, “tomorrow”, “next”, “fees”, etc.
Respective data is obtained from the database and displayed to the user in a basic manner.

<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/Assistant-1.png"><img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/Assistant-1.png">

## Simulation of Labs Using AR
Using Blender, Unity &  ARFoundation for creating simulated labs for different subjects

#### How It Was Used 
We used blender to create 3d models of atoms and electrical instruments. Used Unity and C# to write the logic between combination of electrical components and molecules and AR foundation to instantiate it in AR.


<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/AR-1.png"> <img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/AR-2.png"><img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/AR-3.png">



## Plagiarism Checker
Using Python with NLP & Embedding Models, we created a method to check if 2 submitted assignments ( done by students ) are similiar or not & to what extent

#### How It Was Used

- We utilised the power of word embeddings. We used FastText embeddings to enable sub-word feature matching as well. 
- The plagiarim checker loads the pretrained model and converts the two input documents into vectors. To obtain a single vector for the entire document, we convert word embeddings into sentence embeddings by first averaging them and then convert sentence embeddigs into a single document embedding by using PCA. We finally measure the similarity between the documents and return the score as a percentage.
- Additionally to increase accuracy, we have provided an option to allow the user to upload a PDF of the textbook for a course, to train embeddings on that to build better and specific domain knowledge which will outperform general, non-specific baseline word embeddings.
- The plagiarism checker has been hosted as a flask app and returns results via a simple API call. The API receives the documents, and optionally, the PDF of the book as arguments via a POST request

<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/Plagiarism-1.png" width = "400" height="400">
<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/Plagiarism-2.png">


## Blockchain Based Storage App
A Ethereum based storage app built on Solidity & react.js for Storing Digitized Report Cards

#### How It Was Used
- The app , built on React.js runs on a decentralized app system.
- We downloaded Truffle Suite as the framework for creating Fake Ethereum Smart Contracts & Used Ganache , a blockchain for deployment of these smart contracts
- It uses IPFS for File publishing & File hashing once uploaded.
- One more dependency that was used was Metamask ( Browser extension for ethereum wallets that connects with ganache)
- Once a report card is uploaded , a unique hash is created for it through which he /she can send it to parents.

<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/Blockchain-1.png" >
<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/Blockchain-2.png" width = "350" height="600">
<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/Blockchain-3.png" >


## Android App
The Android app uses Material Design, AndroidX components, Java and XML for development. At its core, the backend is mainly provided by services from Firebase, namely:
- Firebase Authentication
- Firebase Storage
- Cloud Firestore
- Cloud Run

#### How it Was Used
We used Heroku to deploy our model for plagiarism check, while blockchain transactions created unique links per report card, which were subsequently stored within HashMaps in Cloud Firestore.

Components and external libraries used: 
- RecyclerViews, ViewHolders, CardViews, Adapters, Fragments
- Firebase Authentication (for sign-in and sign-up), Firestore, Material Components

<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/App-1.png"  width="350" height="700"><img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/App-2.png" width="350" height="700">

<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/App-3.png"  width="350" height="700"><img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/App-4.png" width="350" height="700">

<img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/App-5.png"  width="350" height="700"><img src = "https://github.com/newb-dev-1008/Scholastic/blob/main/data/readme%20images/App-6.png" width="350" height="700">



## Future enhancements
- Proctoring can be added as a feature, which could not be done due to lack of time and load of OpenCV models
- Chatbot can be made more personalized for staff and students alike
- Moving more than just report card distribution to blockchain


## Team Details

* Track: Blockchain: Powered by Ethereum & Education Technology
* Team Name: BCMC Bois
* Team Members
    * [Aditeya Baral](https://github.com/aditeyabaral)
    * [Anshuman Singh](https://github.com/MysteriousAcadia)
    * [Kartikay Awasthi](https://github.com/barren-scp)
    * [Rohan Mathur](https://github.com/RohanMathur17)
    * [Sayak Mallick](https://github.com/sayak0809)
    * [Yash Umale](https://github.com/newb-dev-1008)



