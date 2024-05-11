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
        # Simple pawn logic, does not handle first move two steps, en passant, or promotion
        direction = 1 if self.color == "White" else -1
        start_row = 2 if self.color == "White" else 7
        dx, dy = destination[0] - self.position[0], destination[1] - self.position[1]
        if dx == 0 and ((dy == direction) or (self.position[1] == start_row and dy == 2 * direction)):
            if self._board.getPiece(destination) is None:
                self.position = destination
                return True
        return False

    def checkMove(self, destination):
        # Implement Pawn specific move validation here
        return True

    def getIcon(self):
        return '♟' if self.color == "White" else '♙'

# Implement other pieces (Knight, Rook, Bishop, Queen, King) similarly
