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
            raise ValueError("Invalid position format")

    def move(self, destination):
        if self.checkMove(destination):
            self._position = destination
            return True
        return False

    def checkMove(self, destination):
        return False  # Generic pieces don't move

    def getName(self):
        return self.__class__.__name__

    def getIcon(self):
        return None  # Default icon for generic Piece

class Pawn(Piece):
    def move(self, destination):
        direction = 1 if self.color == "White" else -1
        start_row = 1 if self.color == "White" else 6
        dx, dy = destination[0] - self.position[0], destination[1] - self.position[1]
        if dx == 0 and ((dy == direction) or (self.position[1] == start_row and dy == 2 * direction)):
            if self._board.getPiece(destination) is None:
                self.position = destination
                return True
        elif abs(dx) == 1 and dy == direction:
            if self._board.getPiece(destination) is not None and self._board.getPiece(destination).color != self.color:
                self.position = destination
                return True
        return False

    def checkMove(self, destination):
        return True

    def getIcon(self):
        return '♙' if self.color == "White" else '♟︎'

class Rook(Piece):
    def checkMove(self, destination):
        dx, dy = destination[0] - self.position[0], destination[1] - self.position[1]
        if dx == 0 or dy == 0:
            step_x = 0 if dx == 0 else (1 if dx > 0 else -1)
            step_y = 0 if dy == 0 else (1 if dy > 0 else -1)
            x, y = self.position
            while (x, y) != destination:
                x, y = x + step_x, y + step_y
                if (x, y) == destination:
                    return self._board.getPiece((x, y)) is None or self._board.getPiece((x, y)).color != self.color
                if self._board.getPiece((x, y)) is not None:
                    return False
            return True
        return False

    def getIcon(self):
        return '♖' if self.color == "White" else '♜'

class Knight(Piece):
    def checkMove(self, destination):
        dx, dy = abs(destination[0] - self.position[0]), abs(destination[1] - self.position[1])
        return (dx == 2 and dy == 1) or (dx == 1 and dy == 2) and (self._board.getPiece(destination) is None or self._board.getPiece(destination).color != self.color)

    def getIcon(self):
        return '♘' if self.color == "White" else '♞'

class Bishop(Piece):
    def checkMove(self, destination):
        dx, dy = abs(destination[0] - self.position[0]), abs(destination[1] - self.position[1])
        if dx == dy:
            step_x = 1 if destination[0] > self.position[0] else -1
            step_y = 1 if destination[1] > self.position[1] else -1
            x, y = self.position
            while (x, y) != destination:
                x += step_x
                y += step_y
                if (x, y) == destination:
                    return self._board.getPiece((x, y)) is None or self._board.getPiece((x, y)).color != self.color
                if self._board.getPiece((x, y)) is not None:
                    return False
            return True
        return False

    def getIcon(self):
        return '♗' if self.color == "White" else '♝'

class Queen(Piece):
    def checkMove(self, destination):
        dx, dy = abs(destination[0] - self.position[0]), abs(destination[1] - self.position[1])
        if dx == dy or dx == 0 or dy == 0:
            step_x = 0 if dx == 0 else (1 if destination[0] > self.position[0] else -1)
            step_y = 0 if dy == 0 else (1 if destination[1] > self.position[1] else -1)
            x, y = self.position
            while (x, y) != destination:
                x += step_x
                y += step_y
                if (x, y) == destination:
                    return self._board.getPiece((x, y)) is None or self._board.getPiece((x, y)).color != self.color
                if self._board.getPiece((x, y)) is not None:
                    return False
            return True
        return False

    def getIcon(self):
        return '♕' if self.color == "White" else '♛'

class King(Piece):
    def checkMove(self, destination):
        dx, dy = abs(destination[0] - self.position[0]), abs(destination[1] - self.position[1])
        return (dx <= 1 and dy <= 1) and (self._board.getPiece(destination) is None or self._board.getPiece(destination).color != self.color)

    def getIcon(self):
        return '♔' if self.color == "White" else '♚'
