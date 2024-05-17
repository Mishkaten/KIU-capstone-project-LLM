blackIcons = {"Pawn": "♙", "Rook": "♖", "Knight": "♘", "Bishop": "♗", "King": "♔", "Queen": "♕"}
whiteIcons = {"Pawn": "♟", "Rook": "♜", "Knight": "♞", "Bishop": "♝", "King": "♚", "Queen": "♛"}

class Piece:
    def __init__(self, color, position, board):
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
        if self.color == "White":
            return whiteIcons.get(self.getName(), None)
        else:
            return blackIcons.get(self.getName(), None)

class Knight(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(int(dest[1]) - int(self.position[1]))
        if (dx == 2 and dy == 1) or (dx == 1 and dy == 2):
            dest_piece = self._board.getPiece(dest)
            if dest_piece is None or dest_piece.color != self.color:
                return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

class Rook(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(int(dest[1]) - int(self.position[1]))
        if (dx == 0 and dy != 0) or (dx != 0 and dy == 0):
            x_dir = 1 if dest[0] > self.position[0] else -1
            y_dir = 1 if dest[1] > self.position[1] else -1
            x = ord(self.position[0]) + x_dir
            y = int(self.position[1]) + y_dir
            while (chr(x), str(y)) != dest:
                if self._board.getPiece((chr(x), str(y))) is not None:
                    return False
                x += x_dir
                y += y_dir
            dest_piece = self._board.getPiece(dest)
            if dest_piece is None or dest_piece.color != self.color:
                return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self._board.setPiece(self.position, None)
            self._board.setPiece(dest, self)
            self.position = dest
            return True
        return False

class Bishop(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(int(dest[1]) - int(self.position[1]))
        if dx == dy:
            x_dir = 1 if dest[0] > self.position[0] else -1
            y_dir = 1 if dest[1] > self.position[1] else -1
            x = ord(self.position[0]) + x_dir
            y = int(self.position[1]) + y_dir
            while (chr(x), str(y)) != dest:
                if self._board.getPiece((chr(x), str(y))) is not None:
                    return False
                x += x_dir
                y += y_dir
            dest_piece = self._board.getPiece(dest)
            if dest_piece is None or dest_piece.color != self.color:
                return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self._board.setPiece(self.position, None)
            self._board.setPiece(dest, self)
            self.position = dest
            return True
        return False

class Queen(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(int(dest[1]) - int(self.position[1]))
        if (dx == 0 and dy != 0) or (dx != 0 and dy == 0) or (dx == dy):
            x_dir = 1 if dest[0] > self.position[0] else -1 if dest[0] < self.position[0] else 0
            y_dir = 1 if dest[1] > self.position[1] else -1 if dest[1] < self.position[1] else 0
            x = ord(self.position[0]) + x_dir
            y = int(self.position[1]) + y_dir
            while (chr(x), str(y)) != dest:
                if self._board.getPiece((chr(x), str(y))) is not None:
                    return False
                x += x_dir
                y += y_dir
            dest_piece = self._board.getPiece(dest)
            if dest_piece is None or dest_piece.color != self.color:
                return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self._board.setPiece(self.position, None)
            self._board.setPiece(dest, self)
            self.position = dest
            return True
        return False

class King(Piece):
    def checkMove(self, dest):
        dx = abs(ord(dest[0]) - ord(self.position[0]))
        dy = abs(int(dest[1]) - int(self.position[1]))
        if (dx <= 1 and dy <= 1) and (dx != 0 or dy != 0):
            dest_piece = self._board.getPiece(dest)
            if dest_piece is None or dest_piece.color != self.color:
                return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self._board.setPiece(self.position, None)
            self._board.setPiece(dest, self)
            self.position = dest
            return True
        return False

class Pawn(Piece):
    def checkMove(self, dest):
        dx = ord(dest[0]) - ord(self.position[0])
        dy = int(dest[1]) - int(self.position[1])
        if self.color == "White":
            if dx == 0 and dy == -1:
                if self._board.getPiece(dest) is None:
                    return True
            elif abs(dx) == 1 and dy == -1:
                dest_piece = self._board.getPiece(dest)
                if dest_piece is not None and dest_piece.color != self.color:
                    return True
            elif dx == 0 and dy == -2 and int(self.position[1]) == 7:
                if self._board.getPiece(dest) is None and self._board.getPiece((dest[0], str(int(dest[1])+1))) is None:
                    return True
        else:
            if dx == 0 and dy == 1:
                if self._board.getPiece(dest) is None:
                    return True
            elif abs(dx) == 1 and dy == 1:
                dest_piece = self._board.getPiece(dest)
                if dest_piece is not None and dest_piece.color != self.color:
                    return True
            elif dx == 0 and dy == 2 and int(self.position[1]) == 2:
                if self._board.getPiece(dest) is None and self._board.getPiece((dest[0], str(int(dest[1])-1))) is None:
                    return True
        return False

    def move(self, dest):
        if self.checkMove(dest):
            self._board.setPiece(self.position, None)
            self._board.setPiece(dest, self)
            self.position = dest
            return True
        return False