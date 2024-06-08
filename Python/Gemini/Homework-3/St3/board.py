from pieces import *

class Board:
    def __init__(self):
        """Initializes the chess board and places the pieces in their starting positions."""
        self.board = {}  # Dictionary to store pieces with positions as keys
        self.placePieces()

    def placePieces(self):
        """Places the chess pieces in their initial positions on the board."""
        for color in ["White", "Black"]:
            # Pawns
            for i in range(8):
                self.setPiece((chr(ord('A') + i), 2 if color == "White" else 7), Pawn(color, self, (chr(ord('A') + i), 2 if color == "White" else 7)))

            # Rooks
            self.setPiece(('A', 1 if color == "White" else 8), Rook(color, self, ('A', 1 if color == "White" else 8)))
            self.setPiece(('H', 1 if color == "White" else 8), Rook(color, self, ('H', 1 if color == "White" else 8)))

            # Knights
            self.setPiece(('B', 1 if color == "White" else 8), Knight(color, self, ('B', 1 if color == "White" else 8)))
            self.setPiece(('G', 1 if color == "White" else 8), Knight(color, self, ('G', 1 if color == "White" else 8)))

            # Bishops
            self.setPiece(('C', 1 if color == "White" else 8), Bishop(color, self, ('C', 1 if color == "White" else 8)))
            self.setPiece(('F', 1 if color == "White" else 8), Bishop(color, self, ('F', 1 if color == "White" else 8)))

            # Queen and King
            self.setPiece(('D', 1 if color == "White" else 8), Queen(color, self, ('D', 1 if color == "White" else 8)))
            self.setPiece(('E', 1 if color == "White" else 8), King(color, self, ('E', 1 if color == "White" else 8)))

    def setPiece(self, position, piece):
        """Places a piece on the board at the given position."""
        self.board[position] = piece

    def getPiece(self, position):
        """Returns the piece at the given position, or None if the position is empty."""
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
        """Displays the chess board in the console."""
        print("  (1)(2)(3)(4)(5)(6)(7)(8)")
        for row in range(8, 0, -1):
            print(f"({chr(ord('A') + row - 1)})", end="")
            for col in range(8):
                piece = self.getPiece((chr(ord('A') + col), row))
                if piece:
                    print(f"[{piece.getIcon()}]", end="")
                else:
                    print("[ ]", end="")
            print()
