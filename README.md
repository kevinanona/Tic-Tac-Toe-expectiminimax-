# Tic-Tac-Toe-expectiminimax-
Once the Tic Tac Toe application has been started, you will be presented with a 3x3 board with one cell being filled by the AI (the computer) with the symbol “O”. You, the user, are player X, meaning you can place the symbol “X” on the board to play against the computer by typing “X” and pressing the “Enter” key in the desired field. You can place an “X” in any cell that is not occupied. It does not matter whether you type a lowercase or an uppercase “x” into a cell, it will accept either one when you hit the “Enter” key and, if not already in uppercase, convert it to an uppercase “X”. A cell will not accept anything else other than an “X”. 

You and the computer will take turns placing the corresponding symbol onto the board which means when the application is started, the AI will wait for you to place your symbol since it will have already placed its own. Once you have entered your symbol “X”, the AI will place another “O” in a cell on the board, and then it will be your turn again, and so on. 

The goal is for you to place your symbol on the board such that three X’s are in the same row, column or diagonal. The same applies to the AI with its symbol. 
There are three different states that can be reached playing this game. Either you successfully place three X’s in a row, column or diagonal which means you win, the AI places three O’s in a row, column or diagonal which means the AI wins, or you and the AI tie. In the last case neither one has reached the goal state. A textbox on the GUI will display whether you won, lost or tied with the AI. 

The “New Game” button will reset the entire board and have the AI place one “O” in a cell to start the game. The “Exit” button closes the application.
