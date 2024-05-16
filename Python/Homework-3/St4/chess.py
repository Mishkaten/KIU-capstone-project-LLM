from board import *
class Chess:
    def __init__(self):
        self.board = Board()
        self.currentPlayer = "White"

    def swapPlayers(self):
        self.currentPlayer = "Black" if self.currentPlayer == "White" else "White"

    def isStringValidMove(self, move):
        # Basic validation of move format
        return len(move.split()) == 2

    def play(self):
        while True:
            self.board.displayBoard()
            print(f"{self.currentPlayer}'s turn. Enter a move:")
            move = input()
            if move.upper() == "EXIT":
                break
            if self.isStringValidMove(move):
                start, end = move.split()
                if not self.board.makeMove(self.currentPlayer, start, end):
                    print("Invalid move, try again.")
            else:
                print("Invalid input, try again.")
            self.swapPlayers()

if __name__ == "__main__":
    game = Chess()
    game.play()
