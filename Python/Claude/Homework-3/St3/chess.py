from board import *
from pieces import *

class Chess:
    def __init__(self):
        self.board = Board()
        self.currentPlayer = "White"

    def swapPlayers(self):
        if self.currentPlayer == "White":
            self.currentPlayer = "Black"
        else:
            self.currentPlayer = "White"

    def isStringValidMove(self, moveStr):
        if len(moveStr) != 5:
            return False
        if moveStr[2] != ' ':
            return False
        startPos = moveStr[:2]
        endPos = moveStr[3:]
        if not (startPos[0] in 'ABCDEFGH' and startPos[1] in '12345678'):
            return False
        if not (endPos[0] in 'ABCDEFGH' and endPos[1] in '12345678'):
            return False
        return True

    def play(self):
        while True:
            self.board.displayBoard()
            print(f"{self.currentPlayer}'s turn. Enter a move:")
            moveStr = input().upper()

            if moveStr == "EXIT":
                print("Exiting the game.")
                break

            if not self.isStringValidMove(moveStr):
                print("INVALID move. Please enter a valid move.")
                continue

            startPos = (moveStr[0], int(moveStr[1]))
            endPos = (moveStr[3], int(moveStr[4]))

            if not self.board.makeMove(self.currentPlayer, startPos, endPos):
                print("INVALID move. Please enter a valid move.")
                continue

            promotedPiece = None
            if isinstance(self.board.getPiece(endPos), Pawn) and (endPos[1] == 1 or endPos[1] == 8):
                while True:
                    print("Pawn promoted! Choose a piece to promote to (Queen/Rook/Bishop/Knight):")
                    pieceType = input().capitalize()
                    if pieceType in ["Queen", "Rook", "Bishop", "Knight"]:
                        promotedPiece = pieceType
                        break
                    else:
                        print("INVALID piece type. Please choose a valid piece.")

                if promotedPiece:
                    self.board.setPiece(endPos, promotedPiece(self.currentPlayer, endPos, self.board))

            self.swapPlayers()


if __name__ == "__main__":
    game = Chess()
    game.play()