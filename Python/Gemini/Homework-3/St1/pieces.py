blackIcons = {"Pawn": "♙", "Rook": "♖", "Knight": "♘", "Bishop": "♗", "King": "♔", "Queen": "♕"}
whiteIcons = {"Pawn": "♟", "Rook": "♜", "Knight": "♞", "Bishop": "♝", "King": "♚", "Queen": "♛"}

class Piece:
    def __init__(self, color, board, position):
        self._color = color  # Use a property for color
        self._board = board
        self._position = position

    @property
    def color(self):
        return self._color

    @property
    def position(self):
        return self._position

    @position.setter
    def position(self, new_position):
        if self._is_valid_position(new_position):
            self._position = new_position
        else:
            raise ValueError("Invalid position")

    def _is_valid_position(self, position):
        """Helper to check if a position is within the board."""
        return ('A' <= position[0] <= 'H') and (1 <= position[1] <= 8)

    def checkMove(self, dest):
        return False  # Base class cannot move

    def move(self, dest):
        return False  # Base class cannot move

    def getName(self):
        return self.__class__.__name__

    def getIcon(self):
        return None  # Base class has no icon

# ... (Knight, Rook, Bishop, Queen, King, Pawn classes with their specific move logic)
