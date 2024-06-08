import os

from board import Board
from pieces import *

class Chess:
    def __init__(self):
        """Initializes the chess game with a new board and the white player starting."""
        self.board = Board()
        self.currentPlayer = "White"

    def swapPlayers(self):
        """Switches the current player."""
        self.currentPlayer = "Black" if self.currentPlayer == "White" else "White"

    def isStringValidMove(self, moveStr):
        """Checks if the input string represents a valid move (e.g., 'A2 A4')."""
        try:
            start, end = moveStr.split()
            return (
                len(start) == 2
                and len(end) == 2
                and start[0].isalpha()
                and start[1].isdigit()
                and end[0].isalpha()
                and end[1].isdigit()
            )
        except ValueError:
            return False

    def play(self):
        """Starts the chess game loop."""
        while True:
            os.system('cls' if os.name == 'nt' else 'clear')  # Clear the screen
            self.board.displayBoard()
            print(f"{self.currentPlayer}'s turn. Enter a move (e.g., 'A2 A4') or 'EXIT':")
            moveStr = input().upper()

            if moveStr == "EXIT":
                break
            if not self.isStringValidMove(moveStr):
                print("INVALID move. Please enter a valid move (e.g., 'A2 A4').")
                continue

            start = (moveStr[0], int(moveStr[1]))
            end = (moveStr[3], int(moveStr[4]))

            if not self.board.makeMove(start, end, self.currentPlayer):
                print("INVALID move. Try again.")
                continue

            # Check for pawn promotion
            promoted_row = 8 if self.currentPlayer == "White" else 1
            if end[1] == promoted_row and isinstance(self.board.getPiece(end), Pawn):
                while True:
                    promotion_choice = input("Pawn promotion! Choose Q, R, B, or N: ").upper()
                    if promotion_choice in ["Q", "R", "B", "N"]:
                        piece_class = {"Q": Queen, "R": Rook, "B": Bishop, "N": Knight}[promotion_choice]
                        self.board.setPiece(end, piece_class(self.currentPlayer, self.board, end))
                        break
                    else:
                        print("Invalid choice. Please enter Q, R, B, or N.")

            self.swapPlayers()

if __name__ == "__main__":
    game = Chess()
    game.play()
