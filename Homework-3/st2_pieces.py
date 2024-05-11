class Piece:
    def __init__(self, color, position, board):
        self._color = color
        self._position = position
        self._board = board  # Instance of Board class

    @property
    def color(self):
        return self._color

    @property
    def position(self):
        return self._position

    @position.setter
    def position(self, value):
        # Assuming value is a tuple (char, int)
        if isinstance(value, tuple) and len(value) == 2 and isinstance(value[1], int) and value[0].isalpha():
            self._position = value
        else:
            raise ValueError("Invalid position format")

    def move(self, dest):
        if self.checkMove(dest):
            self.position = dest
            self._board.update_position(self, dest)
            return True
        return False

    def checkMove(self, dest):
        # Base class does not move
        return False

    def getName(self):
        return self.__class__.__name__

    def getIcon(self):
        icons = {"Pawn": "♟", "Knight": "♞", "Rook": "♜", "Bishop": "♝", "Queen": "♛", "King": "♚"}
        return icons.get(self.getName(), "")

class Pawn(Piece):
    def move(self, dest):
        # Simplified Pawn movement: only forward one square (not considering first move two squares)
        start_col, start_row = self.position
        dest_col, dest_row = dest
        if start_col == dest_col and ((self.color == "White" and dest_row == start_row + 1) or
                                      (self.color == "Black" and dest_row == start_row - 1)):
            return super().move(dest)
        return False

    def checkMove(self, dest):
        # Check the basic legal move for Pawn
        return self.move(dest)
