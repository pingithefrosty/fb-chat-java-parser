# JAVA Parser into CSV for downloaded Facebook Chat Archive

## Important note
Please note that this is an alpha version done by a junior developer in his spare time and is intended for developers. Right know it only works in a IDE (I used IntelliJ) and you need to customize some things in order to make it work for your individual needs.

## What is a "Facebook Chat Archive"?
Facebook Messenger records all your conversation history. If you want it you can ask Facebook for an archive of all your data [here](https://www.facebook.com/dyi), which you will receive in few hours as a zip archive.

In this archive you have everything including, your videos, posts, but we will only deal with the messages here. You can find them in the messages folder along with all the attachments. Although the program only searches for html documents it is cleaner if you put all the message html files into a new folder and define it's path in the program.

##  Features
* The main aim of this project is to create a searchable database from the html Facebook provides.
* Can read all html files in a folder and combine them into a single csv.
* Determines participants.
* Replaces special html characters.
* Reformats time into a 24 hour format and separate columns for year, month, day, day of the week, and hour/minute.
* Counts characters and words in a message.
* Decides if it is a groupconversation or not.

##  Description of the program
* The message class stores each individual message. 
* The thread class contains the participants and all of the messages for that conversation. 
* The readwrite class does exactly what it says, that is it scans a folder for html documents and writes the results into a single csv. 
* The main class contains all the parsing and conversions.

## Known issues
* Doesn't work on the conversation with yourself
* Doesn't work if any of the participants' name is written in a message
* Doesn't work in some cases if you left the conversation

## Future plans / possible contributions
* I would like it to work from the command line or even as a single exe so that not so tech-savvy people could use it as well.
* Adding different output formats, like json for example.
* Separating methods into several smaller ones.
* Better handling of individual needs like different time formats, and regional differences.
