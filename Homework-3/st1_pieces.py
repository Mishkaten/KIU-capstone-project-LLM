blackIcons = {"Pawn" : "♙", "Rook" : "♖", "Knight" : "♘", "Bishop" : "♗", "King" : "♔", "Queen" : "♕" }
whiteIcons = {"Pawn" : "♟", "Rook" : "♜", "Knight" : "♞", "Bishop" : "♝", "King" : "♚", "Queen" : "♛" }

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
        if isinstance(value, tuple) and len(value) == 2 and isinstance(value[0], str) and isinstance(value[1], int):
            self._position = value
        else:
            raise ValueError("Invalid position")

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def checkMove(self, dest):
        return False  # This should be implemented in each subclass

    def getName(self):
        return type(self).__name__

    def getIcon(self):
        icons = {'White': whiteIcons, 'Black': blackIcons}
        return icons[self.color][self.getName()]

# Example derived class for a Pawn
class Pawn(Piece):
    def move(self, dest):
        # Implement specific pawn logic here
        return super().move(dest)

    def checkMove(self, dest):
        # Implement pawn movement check here
        return True
