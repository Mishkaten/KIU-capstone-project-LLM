from pieces import *

class Board:
    def __init__(self):
        self.board = [[None] * 8 for _ in range(8)]
        self.placePieces()

    def placePieces(self):
        # Place white pieces
        self.board[7][0] = Rook('White', ('A', 8), self)
        self.board[7][1] = Knight('White', ('B', 8), self)
        self.board[7][2] = Bishop('White', ('C', 8), self)
        self.board[7][3] = Queen('White', ('D', 8), self)
        self.board[7][4] = King('White', ('E', 8), self)
        self.board[7][5] = Bishop('White', ('F', 8), self)
        self.board[7][6] = Knight('White', ('G', 8), self)
        self.board[7][7] = Rook('White', ('H', 8), self)
        for i in range(8):
            self.board[6][i] = Pawn('White', (chr(ord('A') + i), 7), self)

        # Place black pieces
        self.board[0][0] = Rook('Black', ('A', 1), self)
        self.board[0][1] = Knight('Black', ('B', 1), self)
        self.board[0][2] = Bishop('Black', ('C', 1), self)
        self.board[0][3] = Queen('Black', ('D', 1), self)
        self.board[0][4] = King('Black', ('E', 1), self)
        self.board[0][5] = Bishop('Black', ('F', 1), self)
        self.board[0][6] = Knight('Black', ('G', 1), self)
        self.board[0][7] = Rook('Black', ('H', 1), self)
        for i in range(8):
            self.board[1][i] = Pawn('Black', (chr(ord('A') + i), 2), self)

    def setPiece(self, position, piece):
        col, row = ord(position[0]) - ord('A'), 8 - int(position[1])
        self.board[row][col] = piece

    def getPiece(self, position):
        col, row = ord(position[0]) - ord('A'), 8 - int(position[1])
        return self.board[row][col]

    def makeMove(self, startPosition, endPosition, player):
        start_col, start_row = ord(startPosition[0]) - ord('A'), 8 - int(startPosition[1])
        end_col, end_row = ord(endPosition[0]) - ord('A'), 8 - int(endPosition[1])

        piece = self.board[start_row][start_col]
        if piece is None or piece.color != player:
            return False

        if piece.move(endPosition):
            self.board[end_row][end_col] = piece
            piece.position = endPosition
            self.board[start_row][start_col] = None
            return True
        return False

    def displayBoard(self):
        print("   (A)(B)(C)(D)(E)(F)(G)(H)")
        for row in range(8):
            print(f"({8-row}) ", end="")
            for col in range(8):
                piece = self.board[row][col]
                if piece is None:
                    print("[ ]", end="")
                else:
                    print(f"[{piece.getIcon()}]", end="")
            print(f" ({8-row})")
        print("   (A)(B)(C)(D)(E)(F)(G)(H)")