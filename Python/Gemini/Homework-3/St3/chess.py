from board import *
from pieces import *

class Chess:
    def __init__(self):
        """Initializes the chess game with a new board and sets the current player to White."""
        self.board = Board()
        self.currentPlayer = "White"

    def swapPlayers(self):
        """Switches the current player between White and Black."""
        self.currentPlayer = "Black" if self.currentPlayer == "White" else "White"

    def isStringValidMove(self, moveStr):
        """Checks if the given move string is valid."""
        try:
            start, end = moveStr.split()
            if not (start[0].isalpha() and start[1].isdigit() and end[0].isalpha() and end[1].isdigit()):
                return False
            return True
        except ValueError:
            return False

    def play(self):
        """Starts the chess game loop."""
        while True:
            self.board.displayBoard()
            print(f"{self.currentPlayer}'s turn. Enter a move (e.g., 'B2 B4') or 'EXIT' to quit:")
            moveStr = input()
            if moveStr.upper() == "EXIT":
                break
            if not self.isStringValidMove(moveStr):
                print("INVALID move. Please enter a valid move (e.g., 'B2 B4').")
                continue

            start = (moveStr.split()[0][0].upper(), int(moveStr.split()[0][1]))
            end = (moveStr.split()[1][0].upper(), int(moveStr.split()[1][1]))

            if not self.board.makeMove(start, end, self.currentPlayer):
                print("INVALID move. Try again.")
                continue

            # Check for pawn promotion (not implemented in this example)

            self.swapPlayers()

if __name__ == "__main__":
    game = Chess()
    game.play()
