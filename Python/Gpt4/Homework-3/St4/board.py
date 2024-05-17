from pieces import *

class Board:
    def __init__(self):
        self._pieces = {}  # Dictionary to store pieces by their positions
        self.placePieces()

    def placePieces(self):
        # Initialize pawns
        for i in range(8):
            self._pieces[(i, 1)] = Pawn("White", (i, 1), self)
            self._pieces[(i, 6)] = Pawn("Black", (i, 6), self)
        # Initialize rooks
        self._pieces[(0, 0)] = Rook("White", (0, 0), self)
        self._pieces[(7, 0)] = Rook("White", (7, 0), self)
        self._pieces[(0, 7)] = Rook("Black", (0, 7), self)
        self._pieces[(7, 7)] = Rook("Black", (7, 7), self)
        # Initialize knights
        self._pieces[(1, 0)] = Knight("White", (1, 0), self)
        self._pieces[(6, 0)] = Knight("White", (6, 0), self)
        self._pieces[(1, 7)] = Knight("Black", (1, 7), self)
        self._pieces[(6, 7)] = Knight("Black", (6, 7), self)
        # Initialize bishops
        self._pieces[(2, 0)] = Bishop("White", (2, 0), self)
        self._pieces[(5, 0)] = Bishop("White", (5, 0), self)
        self._pieces[(2, 7)] = Bishop("Black", (2, 7), self)
        self._pieces[(5, 7)] = Bishop("Black", (5, 7), self)
        # Initialize queens
        self._pieces[(3, 0)] = Queen("White", (3, 0), self)
        self._pieces[(3, 7)] = Queen("Black", (3, 7), self)
        # Initialize kings
        self._pieces[(4, 0)] = King("White", (4, 0), self)
        self._pieces[(4, 7)] = King("Black", (4, 7), self)

    def getPiece(self, position):
        return self._pieces.get(position)

    def setPiece(self, position, piece):
        self._pieces[position] = piece

    def makeMove(self, player, start_pos, end_pos):
        piece = self.getPiece(start_pos)
        if piece and piece.color == player and piece.move(end_pos):
            self.setPiece(end_pos, piece)
            self.setPiece(start_pos, None)
            return True
        return False

    def displayBoard(self):
        # Display the board in a simple text format
        board = [[" " for _ in range(8)] for _ in range(8)]
        for position, piece in self._pieces.items():
            if piece:
                board[position[1]][position[0]] = str(piece)[0]
        print("  a b c d e f g h")
        for row in range(8):
            print(str(8-row) + " " + " ".join(board[row]))

