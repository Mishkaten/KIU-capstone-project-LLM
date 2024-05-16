import st2_pieces


class Board:
    def __init__(self):
        self.board = {}
        self.placePieces()

    def placePieces(self):
        # This method should populate the initial positions of the pieces on the board
        # Example for just pawns and rooks
        self.setPiece(('A', 2), st2_pieces.Pawn("White", ('A', 2), self))
        self.setPiece(('A', 1), Rook("White", ('A', 1), self))
        # Add all other pieces similarly

    def setPiece(self, position, piece):
        self.board[position] = piece

    def getPiece(self, position):
        return self.board.get(position)

    def update_position(self, piece, new_position):
        del self.board[piece.position]
        self.board[new_position] = piece
        piece.position = new_position

    def displayBoard(self):
        # Method to print the board in a user-friendly way
        pass
