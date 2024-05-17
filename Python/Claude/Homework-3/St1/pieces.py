blackIcons = {"Pawn" : "♙", "Rook" : "♖", "Knight" : "♘", "Bishop" : "♗", "King" : "♔", "Queen" : "♕" }
whiteIcons = {"Pawn" : "♟", "Rook" : "♜", "Knight" : "♞", "Bishop" : "♝", "King" : "♚", "Queen" : "♛" }

class Piece:
    def __init__(self, color, board, position):
        self._color = color
        self._position = position
        self._board = board

    @property
    def color(self):
        return self._color

    @property
    def position(self):
        return self._position

    @position.setter
    def position(self, value):
        if isinstance(value, tuple) and len(value) == 2:
            self._position = value
        else:
            raise ValueError("Invalid position. Position must be a tuple of length 2.")

    def checkMove(self, dest):
        return False

    def move(self, dest):
        return False

    def getName(self):
        return self.__class__.__name__

    def getIcon(self):
        return None

class Knight(Piece):
    def checkMove(self, dest):
        # Check if the move is valid for a knight
        # Implement the logic based on the knight's movement rules
        pass

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            self._board.setPiece(dest, self)
            self._board.setPiece(self.position, None)
            return True
        return False

    def getIcon(self):
        return blackIcons["Knight"] if self.color == "Black" else whiteIcons["Knight"]

class Rook(Piece):
    def checkMove(self, dest):
        # Check if the move is valid for a rook
        # Implement the logic based on the rook's movement rules
        pass

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            self._board.setPiece(dest, self)
            self._board.setPiece(self.position, None)
            return True
        return False

    def getIcon(self):
        return blackIcons["Rook"] if self.color == "Black" else whiteIcons["Rook"]

class Bishop(Piece):
    def checkMove(self, dest):
        # Check if the move is valid for a bishop
        # Implement the logic based on the bishop's movement rules
        pass

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            self._board.setPiece(dest, self)
            self._board.setPiece(self.position, None)
            return True
        return False

    def getIcon(self):
        return blackIcons["Bishop"] if self.color == "Black" else whiteIcons["Bishop"]

class Queen(Piece):
    def checkMove(self, dest):
        # Check if the move is valid for a queen
        # Implement the logic based on the queen's movement rules
        pass

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            self._board.setPiece(dest, self)
            self._board.setPiece(self.position, None)
            return True
        return False

    def getIcon(self):
        return blackIcons["Queen"] if self.color == "Black" else whiteIcons["Queen"]

class King(Piece):
    def checkMove(self, dest):
        # Check if the move is valid for a king
        # Implement the logic based on the king's movement rules
        pass

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            self._board.setPiece(dest, self)
            self._board.setPiece(self.position, None)
            return True
        return False

    def getIcon(self):
        return blackIcons["King"] if self.color == "Black" else whiteIcons["King"]

class Pawn(Piece):
    def checkMove(self, dest):
        # Check if the move is valid for a pawn
        # Implement the logic based on the pawn's movement rules
        pass

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            self._board.setPiece(dest, self)
            self._board.setPiece(self.position, None)
            return True
        return False

    def getIcon(self):
        return blackIcons["Pawn"] if self.color == "Black" else whiteIcons["Pawn"]

