# C64-Game
A full strategy game for the commodore 64, inspired by "Shopping AG" (C64 Game by Stefan Kluge).
With custom tools (Charseteditor, that lets the user draw icons and export a script to create a custom charset with the icons)

## How to use the charseteditor
Change the pixels of the 16x16 square by clicking on them. 
After the artwork is done, hit "ADD" and give it a name.
If you've finished all artworks you want to have (the maximum artwork count is 8) hit the "EXPORT" button.
Select a directory and save it as a ".txt" file. Select your starting line (C64 stuff) and hit ok.
The program will then export it as a full script you just need to copy into another script or type up in your original "breadbin".

## How the charseteditor works
The program is based on the "German Example" from https://www.c64-wiki.com/wiki/Character_set 

## How to run the charseteditor
Simply follow this instructions:
- Download the git repository
- Open terminal/cmd
- cd into the git folder and then into /"C64 Charseteditor"/src/
- run "javac Frame.java"
- run "java Frame"

To do so, you need to have the JDK installed (at least 1.8) and the PATH variable needs to be set correctly.

## How to run the game
Simply follow this instructions:
 - Download the git repository
 - Put the .prg file (found in the main directory) on a SDCard
 - Insert the SD into an SD2IEC module on your C64
 - On the C64: LOAD"\*",8,1

# Waranty and Licensing
It took me around an hour to write the code and another hour to understand how charsetmanipulation works. 
Please don't sell it or pretend it's your work. You're free to use it for private projects. 
If you create artworks with it and sell/release the product with charsets generated by my code, please let me know, I'm curious to see what you create.
I take no responsibility for damages to your C64, your PC or anything else.
