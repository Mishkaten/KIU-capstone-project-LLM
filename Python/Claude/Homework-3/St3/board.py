from chess import *
from pieces import *
class Board:
    def __init__(self):
        self.board = {}
        self.placePieces()

    def placePieces(self):
        # Place pawns
        for col in range(ord('A'), ord('H') + 1):
            self.board[(chr(col), 2)] = Pawn("White", (chr(col), 2), self)
            self.board[(chr(col), 7)] = Pawn("Black", (chr(col), 7), self)

        # Place rooks
        self.board[('A', 1)] = Rook("White", ('A', 1), self)
        self.board[('H', 1)] = Rook("White", ('H', 1), self)
        self.board[('A', 8)] = Rook("Black", ('A', 8), self)
        self.board[('H', 8)] = Rook("Black", ('H', 8), self)

        # Place knights
        self.board[('B', 1)] = Knight("White", ('B', 1), self)
        self.board[('G', 1)] = Knight("White", ('G', 1), self)
        self.board[('B', 8)] = Knight("Black", ('B', 8), self)
        self.board[('G', 8)] = Knight("Black", ('G', 8), self)

        # Place bishops
        self.board[('C', 1)] = Bishop("White", ('C', 1), self)
        self.board[('F', 1)] = Bishop("White", ('F', 1), self)
        self.board[('C', 8)] = Bishop("Black", ('C', 8), self)
        self.board[('F', 8)] = Bishop("Black", ('F', 8), self)

        # Place queens
        self.board[('D', 1)] = Queen("White", ('D', 1), self)
        self.board[('D', 8)] = Queen("Black", ('D', 8), self)

        # Place kings
        self.board[('E', 1)] = King("White", ('E', 1), self)
        self.board[('E', 8)] = King("Black", ('E', 8), self)

    def setPiece(self, position, piece):
        self.board[position] = piece

    def getPiece(self, position):
        return self.board.get(position)

    def makeMove(self, startPosition, endPosition, player):
        piece = self.getPiece(startPosition)
        if piece is None:
            return False
        if piece.color != player:
            return False
        if piece.move(endPosition):
            self.board[endPosition] = piece
            del self.board[startPosition]
            return True
        return False

    def displayBoard(self):
        print("   (1)(2)(3)(4)(5)(6)(7)(8)")
        for row in range(8, 0, -1):
            print(f"({chr(row + 64)}) ", end="")
            for col in range(1, 9):
                piece = self.getPiece((chr(col + 64), row))
                if piece:
                    print(piece.getIcon(), end="")
                else:
                    print("[ ]", end="")
            print()