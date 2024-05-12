import unittest
from board import Board
from chess import Chess
from pieces import *

class TestBoard(unittest.TestCase):
    def setUp(self):
        self.board = Board()

    def test_initialization(self):
        # Assuming the initial setup should at least place one Rook for simplicity
        self.assertIsInstance(self.board.getPiece(('B', 1)), Rook)

    def test_set_and_get_piece(self):
        rook = Rook('White', ('C', 3), self.board)
        self.board.setPiece(('C', 3), rook)
        self.assertEqual(self.board.getPiece(('C', 3)), rook)

    def test_move_piece(self):
        start_pos = ('B', 1)
        end_pos = ('B', 3)
        self.board.makeMove(start_pos, end_pos)
        self.assertIsNone(self.board.getPiece(start_pos))
        self.assertIsInstance(self.board.getPiece(end_pos), Rook)

    def test_invalid_move(self):
        start_pos = ('B', 1)
        end_pos = ('C', 2)  # Invalid for Rook
        self.assertFalse(self.board.makeMove(start_pos, end_pos))
        self.assertIsNotNone(self.board.getPiece(start_pos))
        self.assertIsNone(self.board.getPiece(end_pos))

class TestChess(unittest.TestCase):
    def setUp(self):
        self.game = Chess()

    def test_player_swap(self):
        current_player = self.game.currentPlayer
        self.game.swapPlayers()
        self.assertNotEqual(self.game.currentPlayer, current_player)

    def test_valid_string_move(self):
        self.assertTrue(self.game.isStringValidMove("A1 A3"))

    def test_invalid_string_move(self):
        self.assertFalse(self.game.isStringValidMove("A1 23"))

class TestPieces(unittest.TestCase):
    def setUp(self):
        self.board = Board()
        self.rook = Rook('White', ('H', 1), self.board)

    def test_rook_valid_move(self):
        self.assertTrue(self.rook.move(('H', 3)))

    def test_rook_invalid_move(self):
        self.assertFalse(self.rook.move(('G', 2)))

    def test_position_setter(self):
        with self.assertRaises(ValueError):
            self.rook.position = ('Invalid', 123)

if __name__ == "__main__":
    unittest.main()
