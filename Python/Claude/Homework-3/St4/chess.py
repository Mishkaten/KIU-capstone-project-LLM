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
        start = moveStr[:2]
        end = moveStr[3:]
        if start[0] not in 'ABCDEFGH' or end[0] not in 'ABCDEFGH':
            return False
        if int(start[1]) not in range(1, 9) or int(end[1]) not in range(1, 9):
            return False
        return True

    def play(self):
        while True:
            self.board.displayBoard()
            print(f"{self.currentPlayer}'s turn. Enter a move (e.g., 'A2 A4') or 'EXIT' to quit:")
            move = input().strip().upper()

            if move == "EXIT":
                print("Thanks for playing!")
                break

            if not self.isStringValidMove(move):
                print("Invalid move format. Please try again.")
                continue

            start, end = move.split(' ')
            if not self.board.makeMove(start, end, self.currentPlayer):
                print("Invalid move. Please try again.")
                continue

            piece = self.board.getPiece(end)
            if isinstance(piece, Pawn) and (end[1] == '1' or end[1] == '8'):
                while True:
                    print("Pawn promotion! Choose a piece (Queen, Rook, Bishop, Knight):")
                    promotion = input().strip().capitalize()
                    if promotion in ["Queen", "Rook", "Bishop", "Knight"]:
                        self.board.setPiece(end, eval(promotion)(self.currentPlayer, end, self.board))
                        break
                    else:
                        print("Invalid piece. Please choose a valid piece.")

            self.swapPlayers()

if __name__ == "__main__":
    game = Chess()
    game.play()