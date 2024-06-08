blackIcons = {"Pawn": "♙", "Rook": "♖", "Knight": "♘", "Bishop": "♗", "King": "♔", "Queen": "♕"}
whiteIcons = {"Pawn": "♟", "Rook": "♜", "Knight": "♞", "Bishop": "♝", "King": "♚", "Queen": "♛"}

class Piece:
    def __init__(self, color, board, position):
        self._color = color  # Use a protected attribute for color
        self.board = board
        self._position = position  # Use a protected attribute for position

    @property
    def color(self):
        return self._color

    @property
    def position(self):
        return self._position

    @position.setter
    def position(self, new_position):
        if isinstance(new_position, tuple) and len(new_position) == 2:
            row, col = new_position
            if row in "ABCDEFGH" and 1 <= col <= 8:
                self._position = new_position
            else:
                raise ValueError("Invalid position")
        else:
            raise ValueError("Invalid position")

    def checkMove(self, dest):
        """Base implementation: Always returns False (subclasses will override)."""
        return False

    def move(self, dest):
        """Moves the piece to the destination position, updating the board."""
        if self.checkMove(dest):
            self.board.setPiece(dest, self)  # Move the piece
            del self.board.board[self.position]  # Remove from old position
            self.position = dest  # Update piece's position
            return True
        return False


    def getName(self):
        """Returns the name of the piece's class."""
        return self.__class__.__name__

    def getIcon(self):
        """Returns the icon for the piece based on its color."""
        return whiteIcons[self.getName()] if self.color == "White" else blackIcons[self.getName()]

class Knight(Piece):
    def checkMove(self, dest):
        """Checks if a knight's move is valid."""
        dx = abs(ord(self.position[0]) - ord(dest[0]))
        dy = abs(self.position[1] - dest[1])
        return (dx == 2 and dy == 1) or (dx == 1 and dy == 2)

class Rook(Piece):
    def checkMove(self, dest):
        """Checks if a rook's move is valid."""
        return self.position[0] == dest[0] or self.position[1] == dest[1]

class Bishop(Piece):
    def checkMove(self, dest):
        """Checks if a bishop's move is valid."""
        dx = abs(ord(self.position[0]) - ord(dest[0]))
        dy = abs(self.position[1] - dest[1])
        return dx == dy

class Queen(Piece):
    def checkMove(self, dest):
        """Checks if a queen's move is valid (combines rook and bishop moves)."""
        return Rook.checkMove(self, dest) or Bishop.checkMove(self, dest)

class King(Piece):
    def checkMove(self, dest):
        """Checks if a king's move is valid."""
        dx = abs(ord(self.position[0]) - ord(dest[0]))
        dy = abs(self.position[1] - dest[1])
        return dx <= 1 and dy <= 1

class Pawn(Piece):
    def checkMove(self, dest):
        """Checks if a pawn's move is valid."""
        dx = ord(dest[0]) - ord(self.position[0])
        dy = dest[1] - self.position[1]
        direction = 1 if self.color == "White" else -1

        # Basic move
        if dx == 0 and dy == direction:
            return self.board.getPiece(dest) is None

        # Double move from starting position
        if (
            dx == 0
            and dy == 2 * direction
            and (self.position[1] == 2 or self.position[1] == 7)
            and self.board.getPiece(dest) is None
        ):
            return self.board.getPiece((dest[0], dest[1] - direction)) is None

        # Capture
        if abs(dx) == 1 and dy == direction:
            captured_piece = self.board.getPiece(dest)
            return captured_piece and captured_piece.color != self.color

        return False
