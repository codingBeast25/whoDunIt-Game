
PROJECT TITLE: WhoDunIt
PURPOSE OF PROJECT:Implement the famous game CLUEDO! without UI
VERSION or DATE: v1.0 - 1st April 2021
AUTHORS: KABIR BHAKTA
USER INSTRUCTIONS:

1.Unzip Folder
2.Transfer all the .java files into another folder EXCEPT the .jar and the test file(TestGame.java)
3.Compile all the .java files using,
   javac *.java
4.If you want to run and play the game,
    java Main
5. And follow the prompts and continue until game is over.
6.After all the files compile, now bring the .jar and TestGame.java into the same folder.
7.Compile using
	javac -cp .:junit-platform-console-standalone-1.6.0.jar TestGame.java
8.for running the tests
	java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path

Note:
I have commented few more cards. If you want to include more cards then uncomment them and play.
Thank you