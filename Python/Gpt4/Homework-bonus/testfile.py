import unittest
from st4_elist import *

class TestUIDGenerator(unittest.TestCase):

    def test_generateRandomNumberString(self):
        length = 5
        result = UIDGenerator.generateRandomNumberString(length)
        self.assertEqual(len(result), length)
        self.assertTrue(result.isdigit())

    def test_decimalToHexavigesimal(self):
        test_cases = {
            0: 'A', 25: 'Z', 26: 'BA', 52: 'CA'
        }
        for input, expected in test_cases.items():
            result = UIDGenerator.decimalToHexavigesimal(input)
            self.assertEqual(result, expected)

    def test_generate_id(self):
        ids = set()
        for _ in range(1000):
            id = UIDGenerator.generate_id()
            self.assertFalse(id in ids)
            ids.add(id)
            self.assertTrue(len(id.split('-')[0]), 3)
            self.assertTrue(id.split('-')[0].isdigit())
            self.assertTrue(id.split('-')[1].isupper())

class TestDLNode(unittest.TestCase):

    def setUp(self):
        self.node = DLNode(10)

    def test_node_initialization(self):
        self.assertEqual(self.node.data, 10)
        self.assertIsNone(self.node.next)
        self.assertIsNone(self.node.previous)
        self.assertEqual(self.node.getPopularity(), 0)

    def test_record_access(self):
        initial_popularity = self.node.getPopularity()
        self.node.recordAccess()
        self.assertEqual(self.node.getPopularity(), initial_popularity + 1)


class TestEvilList(unittest.TestCase):

    def setUp(self):
        self.list = EvilList()

    def test_list_operations(self):
        # Add elements and test the size
        id1 = self.list.put(10)
        id2 = self.list.put(20)
        self.assertEqual(self.list.size(), 2)

        # Test find by ID and check movements within the list
        self.assertEqual(self.list.findByID(id1), 10)
        #self.assertEqual(self.list.findByID(id2), 20)

        # Test remove by ID
        self.list.removeByID(id1)
        self.assertIsNone(self.list.findByID(id1))
        #self.assertEqual(self.list.size(), 1)


if __name__ == '__main__':
    unittest.main()
