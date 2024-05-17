# chess.py
from board import *

class Chess:
    def __init__(self):
        self.board = Board()
        self.currentPlayer = 'White'

    def swapPlayers(self):
        self.currentPlayer = 'Black' if self.currentPlayer == 'White' else 'White'

    def isStringValidMove(self, move):
        if len(move.split()) == 2 and all(x.isalpha() and len(x) == 2 for x in move.split()):
            return True
        return False

    def play(self):
        while True:
            self.board.displayBoard()
            print(f"{self.currentPlayer}'s turn. Enter a move or 'EXIT' to exit:")
            move = input()
            if move.lower() == 'exit':
                break
            if not self.isStringValidMove(move):
                print("Invalid move format")
                continue
            start, end = move.split()
            if not self.board.makeMove(start, end):
                print("Invalid move")
                continue
            self.swapPlayers()

if __name__ == "__main__":
    game = Chess()
    game.play()
