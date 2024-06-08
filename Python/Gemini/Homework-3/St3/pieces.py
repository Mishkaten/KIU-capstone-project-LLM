blackIcons = {"Pawn": "♙", "Rook": "♖", "Knight": "♘", "Bishop": "♗", "King": "♔", "Queen": "♕"}
whiteIcons = {"Pawn": "♟", "Rook": "♜", "Knight": "♞", "Bishop": "♝", "King": "♚", "Queen": "♛"}

class Piece:
    def __init__(self, color, board, position):
        """Initializes a chess piece with its color, board, and position."""
        self._color = color
        self.board = board
        self._position = position

    @property
    def color(self):
        """Gets the color of the piece."""
        return self._color

    @property
    def position(self):
        """Gets the position of the piece."""
        return self._position

    @position.setter
    def position(self, new_position):
        """Sets the position of the piece after validating it."""
        if self.checkValidPosition(new_position):
            self._position = new_position
        else:
            raise ValueError("Invalid position")

    def checkValidPosition(self, position):
        """Checks if the given position is valid on the chess board."""
        return 'A' <= position[0] <= 'H' and 1 <= position[1] <= 8

    def checkMove(self, dest):
        """Checks if a move to the destination is valid (to be overridden by subclasses)."""
        return False  # Base class cannot move

    def move(self, dest):
        """Moves the piece to the destination if the move is valid (to be overridden by subclasses)."""
        if self.checkMove(dest):
            self.position = dest
            return True
        return False

    def getName(self):
        """Returns the name of the piece's class."""
        return self.__class__.__name__

    def getIcon(self):
        """Returns the icon representing the piece based on its color."""
        return whiteIcons.get(self.getName()) if self.color == "White" else blackIcons.get(self.getName())

# (Rest of the code for Knight, Rook, Bishop, Queen, King, and Pawn classes would go here)
