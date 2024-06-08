import os

from pieces import *

class Board:
    def __init__(self):
        """Initializes the chessboard and places the pieces in their starting positions."""
        self.board = {}  # Dictionary to store pieces: {(row, col): Piece}
        self.placePieces()

    def placePieces(self):
        """Places the chess pieces in their initial positions on the board."""
        # Pawns
        for row in "ABCDEFGH":
            self.setPiece((row, 2), Pawn("White", self, (row, 2)))
            self.setPiece((row, 7), Pawn("Black", self, (row, 7)))

        # Other pieces
        pieces = [Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook]
        for i, piece_class in enumerate(pieces):
            self.setPiece((chr(ord('A') + i), 1), piece_class("White", self, (chr(ord('A') + i), 1)))
            self.setPiece((chr(ord('A') + i), 8), piece_class("Black", self, (chr(ord('A') + i), 8)))

    def setPiece(self, position, piece):
        """Sets a piece at the given position on the board."""
        self.board[position] = piece

    def getPiece(self, position):
        """Returns the piece at the given position, or None if no piece is there."""
        return self.board.get(position)

    def makeMove(self, startPosition, endPosition, player):
        """Attempts to make a move from startPosition to endPosition for the given player."""
        piece = self.getPiece(startPosition)
        if piece and piece.color == player and piece.checkMove(endPosition):
            captured_piece = self.getPiece(endPosition)
            if captured_piece and captured_piece.color != player:
                del self.board[endPosition]  # Remove captured piece
            piece.move(endPosition)
            return True
        return False

    def displayBoard(self):
        """Displays the chessboard in the console, clearing the screen first."""
        os.system('cls' if os.name == 'nt' else 'clear')  # Clear the screen
        print(" |1|2|3|4|5|6|7|8|")
        print("-----------------")
        for row in "ABCDEFGH":
            print(f"{row}|", end="")
            for col in range(1, 9):
                piece = self.getPiece((row, col))
                icon = piece.getIcon() if piece else " "
                print(f"{icon}|", end="")
            print()
        print("-----------------")

