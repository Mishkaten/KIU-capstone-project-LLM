import unittest
from chess import Chess
from pieces import *

class TestChessGame(unittest.TestCase):
    def setUp(self):
        self.game = Chess()

    def test_initial_player_white(self):
        self.assertEqual(self.game.currentPlayer, "White", "Initial player should be White")

    def test_swap_players(self):
        self.game.swapPlayers()
        self.assertEqual(self.game.currentPlayer, "Black", "After swap, player should be Black")
        self.game.swapPlayers()
        self.assertEqual(self.game.currentPlayer, "White", "After swap, player should return to White")

    def test_pawn_move_forward(self):
        initial_pos = 'e2'
        final_pos = 'e4'
        self.assertTrue(self.game.board.makeMove('White', initial_pos, final_pos), "Pawn should move forward two spaces on its first move")

    def test_pawn_capture(self):
        self.game.board.setPiece('d4', Pawn('Black', 'd4', self.game.board))
        self.assertTrue(self.game.board.makeMove('White', 'e3', 'd4'), "Pawn should capture an opposing piece diagonally")

    def test_invalid_move_returns_false(self):
        self.assertFalse(self.game.board.makeMove('White', 'e2', 'e5'), "Invalid move should return False")

    def test_checkmate(self):
        # Set up a simple checkmate position
        self.game.board.setPiece('f7', King('Black', 'f7', self.game.board))
        self.game.board.setPiece('g5', Queen('White', 'g5', self.game.board))
        self.game.board.setPiece('h6', Queen('White', 'h6', self.game.board))
        self.assertTrue(self.game.isCheckmate('Black'), "Black should be in checkmate")

    # Additional tests can be added to test specific chess scenarios, different pieces, and special rules

if __name__ == '__main__':
    unittest.main()
