# pieces.py
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
            raise ValueError("Invalid position")

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def checkMove(self, dest):
        # This needs to be implemented specifically in derived classes
        return False

    def getName(self):
        return type(self).__name__

    def getIcon(self):
        icons = {'Pawn': '♟', 'Knight': '♞', 'Bishop': '♝', 'Rook': '♜', 'Queen': '♛', 'King': '♚'}
        return icons[self.getName()]

# Example implementation for a Rook
class Rook(Piece):
    def checkMove(self, dest):
        # Simplified example: Can move horizontally or vertically
        if self.position[0] == dest[0] or self.position[1] == dest[1]:
            return True
        return False
