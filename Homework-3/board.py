class Board:
    def __init__(self):
        self.pieces = {}
        self.placePieces()

    def placePieces(self):
        # Initial placement of pieces at game start
        pass

    def setPiece(self, position, piece):
        self.pieces[position] = piece

    def getPiece(self, position):
        return self.pieces.get(position, None)

    def makeMove(self, start, end):
        piece = self.getPiece(start)
        if piece and piece.move(end):
            self.setPiece(end, piece)
            self.setPiece(start, None)
            return True
        return False

    def displayBoard(self):
        # Print board state
        pass
