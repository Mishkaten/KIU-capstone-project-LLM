blackIcons = {"Pawn": "♙", "Rook": "♖", "Knight": "♘", "Bishop": "♗", "King": "♔", "Queen": "♕"}
whiteIcons = {"Pawn": "♟", "Rook": "♜", "Knight": "♞", "Bishop": "♝", "King": "♚", "Queen": "♛"}

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
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(dest[1] - self.position[1])
        if (dx == 2 and dy == 1) or (dx == 1 and dy == 2):
            return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def getIcon(self):
        if self.color == "White":
            return whiteIcons["Knight"]
        else:
            return blackIcons["Knight"]

class Rook(Piece):
    def checkMove(self, dest):
        if self.position[0] == dest[0] or self.position[1] == dest[1]:
            return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def getIcon(self):
        if self.color == "White":
            return whiteIcons["Rook"]
        else:
            return blackIcons["Rook"]

class Bishop(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(dest[1] - self.position[1])
        if dx == dy:
            return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def getIcon(self):
        if self.color == "White":
            return whiteIcons["Bishop"]
        else:
            return blackIcons["Bishop"]

class Queen(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(dest[1] - self.position[1])
        if dx == dy or self.position[0] == dest[0] or self.position[1] == dest[1]:
            return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def getIcon(self):
        if self.color == "White":
            return whiteIcons["Queen"]
        else:
            return blackIcons["Queen"]

class King(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(dest[1] - self.position[1])
        if dx <= 1 and dy <= 1:
            return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def getIcon(self):
        if self.color == "White":
            return whiteIcons["King"]
        else:
            return blackIcons["King"]

class Pawn(Piece):
    def checkMove(self, dest):
        dx = ord(dest[0]) - ord(self.position[0])
        dy = dest[1] - self.position[1]
        if self.color == "White":
            if dx == 0 and dy == 1:
                return True
            elif dx == 0 and dy == 2 and self.position[1] == 2:
                return True
        else:
            if dx == 0 and dy == -1:
                return True
            elif dx == 0 and dy == -2 and self.position[1] == 7:
                return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def getIcon(self):
        if self.color == "White":
            return whiteIcons["Pawn"]
        else:
            return blackIcons["Pawn"]