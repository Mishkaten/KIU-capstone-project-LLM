from board import *
class Chess:
    def __init__(self):
        self.board = Board()
        self.currentPlayer = "White"

    def swapPlayers(self):
        self.currentPlayer = "Black" if self.currentPlayer == "White" else "White"

    def isStringValidMove(self, move):
        # Validate move format
        return True

    def play(self):
        while True:
            self.board.displayBoard()
            print(f"{self.currentPlayer}'s turn. Enter a move:")
            move = input()
            if move.lower() == "exit":
                break
            if not self.isStringValidMove(move):
                continue
            start, end = move.split()
            if not self.board.makeMove(start, end):
                print("Invalid move, try again.")
                continue
            self.swapPlayers()
