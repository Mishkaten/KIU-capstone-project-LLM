from board import *
from pieces import *

class Chess:
    def __init__(self):
        self.board = Board()
        self.currentPlayer = "White"

    def swapPlayers(self):
        self.currentPlayer = "Black" if self.currentPlayer == "White" else "White"

    def isStringValidMove(self, moveStr):
        # ... (Code to validate the move string format)
        pass

    def play(self):
        # ... (Main game loop as described in the instructions)
        pass
