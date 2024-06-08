import random
import string
import unittest


class UIDGenerator:
    id_seed = 1000  # Starting seed for unique ID generation

    @staticmethod
    def generate_random_number_string(length):
        """Generates a random string of digits of the specified length."""
        return ''.join(random.choice(string.digits) for _ in range(length))

    @staticmethod
    def decimal_to_hexavigesimal(decimal):
        """Converts a decimal number to hexavigesimal (base-26 using A-Z)."""
        if decimal == 0:
            return 'A'
        hexavigesimal = ""
        while decimal > 0:
            remainder = decimal % 26
            hexavigesimal = chr(ord('A') + remainder) + hexavigesimal
            decimal //= 26
        return hexavigesimal

    @classmethod
    def generate_id(cls):
        """Generates a unique ID in the format 'XXX-YYYYY'."""
        random_part = cls.generate_random_number_string(3)
        unique_part = cls.decimal_to_hexavigesimal(cls.id_seed)
        cls.id_seed += 1  # Increment the seed for the next ID
        return f"{random_part}-{unique_part}"

class DLNode:
    def __init__(self, data):
        """Initializes a doubly linked list node."""
        self._id = UIDGenerator.generate_id()
        self._popularity = 0
        self.data = data
        self.next = None
        self.prev = None

    def record_access(self):
        """Records an access to the node, increasing its popularity."""
        self._popularity += 1

    def get_popularity(self):
        """Returns the node's popularity."""
        return self._popularity

    def get_id(self):
        """Returns the node's unique ID."""
        return self._id


class EvilList:
    def __init__(self):
        """Initializes an empty EvilList."""
        self.head = None

    def insert(self, index, data):
        """Inserts data at the specified index."""
        new_node = DLNode(data)
        if index == 0:
            new_node.next = self.head
            if self.head:
                self.head.prev = new_node
            self.head = new_node
        else:
            current = self.head
            for _ in range(index - 1):
                if not current:
                    raise IndexError("Index out of range")
                current = current.next
            if current:
                new_node.next = current.next
                new_node.prev = current
                if current.next:
                    current.next.prev = new_node
                current.next = new_node
            else:
                raise IndexError("Index out of range")

    def remove_by_id(self, node_id):
        """Removes the node with the given ID from the list."""
        current = self.head
        while current:
            if current.get_id() == node_id:
                if current.prev:
                    current.prev.next = current.next
                else:
                    self.head = current.next
                if current.next:
                    current.next.prev = current.prev
                return  # Node found and removed
            current = current.next

    def find_by_id(self, node_id):
        """Finds the node with the given ID and returns its data."""
        current = self.head
        while current:
            if current.get_id() == node_id:
                current.record_access()
                self._move_to_back(current)  # Evil move to the back
                return current.data
            current = current.next
        return None  # Node not found

    def _move_to_back(self, node):
        """Moves the given node one position towards the back of the list."""
        if node.next:
            node.prev.next = node.next
            node.next.prev = node.prev
            node.prev = node.next
            node.next = node.next.next
            if node.next:
                node.next.prev = node
            else:
                node.prev.next = node

    def size(self):
        """Returns the number of nodes in the list."""
        count = 0
        current = self.head
        while current:
            count += 1
            current = current.next
        return count

    def print_list(self):
        """Prints the data of all nodes in the list."""
        current = self.head
        while current:
            print(current.data, end=" ")
            current = current.next
        print()  # Newline after printing

    def sort(self):
        """Sorts the list in ascending order of node popularity using merge sort."""
        if not self.head or not self.head.next:
            return  # List is already sorted or empty

        # Split the list into two halves
        mid = self._get_middle(self.head)
        second_half = mid.next
        mid.next = None
        second_half.prev = None

        # Recursively sort the two halves
        self._sort(self.head)
        self._sort(second_half)

        # Merge the sorted halves
        self.head = self._merge(self.head, second_half)

    def _get_middle(self, node):
        """Helper function to find the middle node of a linked list."""
        slow = node
        fast = node
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
        return slow

    def _merge(self, left, right):
        """Helper function to merge two sorted linked lists."""
        if not left:
            return right
        if not right:
            return left

        if left.get_popularity() < right.get_popularity():
            result = left
            result.next = self._merge(left.next, right)
        else:
            result = right
            result.next = self._merge(left, right.next)

        result.next.prev = result
        return result

    def _sort(self, node):
        """Recursive helper function for merge sort."""
        if not node or not node.next:
            return node

        mid = self._get_middle(node)
        next_to_mid = mid.next
        mid.next = None
        next_to_mid.prev = None

        left = self._sort(node)
        right = self._sort(next_to_mid)

        sorted_list = self._merge(left, right)
        return sorted_list



from datetime import datetime
from typing import Dict

def process_log_file(file_path: str) -> Dict[str, any]:
    """Processes a log file, counting log levels and identifying earliest/latest timestamps.

    Args:
        file_path (str): The path to the log file.

    Returns:
        Dict[str, any]: A dictionary containing log level counts, earliest timestamp, and latest timestamp.
    """
    log_counts = {}
    earliest_timestamp = None
    latest_timestamp = None

    with open(file_path, "r") as f:
        for line in f:
            line = line.strip()
            if not line:  # Skip empty lines
                continue

            # Parse log entry
            timestamp_str, log_level, _ = line.split(",", 2)  # Ignore message

            # Count log level
            log_counts[log_level] = log_counts.get(log_level, 0) + 1

            # Convert timestamp string to datetime object
            timestamp = datetime.strptime(timestamp_str, "%Y-%m-%d %H:%M:%S")

            # Update earliest and latest timestamps
            if earliest_timestamp is None or timestamp < earliest_timestamp:
                earliest_timestamp = timestamp
            if latest_timestamp is None or timestamp > latest_timestamp:
                latest_timestamp = timestamp

    return {
        "log_counts": log_counts,
        "earliest_timestamp": earliest_timestamp.strftime("%Y-%m-%d %H:%M:%S"),
        "latest_timestamp": latest_timestamp.strftime("%Y-%m-%d %H:%M:%S"),
    }



class TestEvilList(unittest.TestCase):

    def test_uid_generation(self):
        id1 = UIDGenerator.generate_id()
        id2 = UIDGenerator.generate_id()
        self.assertNotEqual(id1, id2)

    def test_insert_and_size(self):
        elist = EvilList()
        elist.insert(0, "Node1")
        elist.insert(1, "Node2")
        elist.insert(2, "Node3")
        self.assertEqual(elist.size(), 3)

    def test_remove_by_id(self):
        elist = EvilList()
        elist.insert(0, "Node1")
        elist.insert(1, "Node2")
        elist.insert(2, "Node3")
        node_id = elist.head.next.get_id()  # Get the ID of the second node
        elist.remove_by_id(node_id)
        self.assertEqual(elist.size(), 2)

    def test_find_by_id(self):
        elist = EvilList()
        elist.insert(0, "Node1")
        elist.insert(1, "Node2")
        elist.insert(2, "Node3")
        node_id = elist.head.next.get_id()  # Get the ID of the second node
        data = elist.find_by_id(node_id)
        self.assertEqual(data, "Node2")

    def test_sort(self):
        elist = EvilList()
        elist.insert(0, "Node1")
        elist.insert(1, "Node2")
        elist.insert(2, "Node3")
        elist.head.record_access()  # Node1 gets 1 popularity
        elist.head.next.record_access()  # Node2 gets 1 popularity
        elist.head.next.record_access()  # Node2 gets 2 popularity
        elist.sort()
        # After sorting, Node3 should be first, Node1 second, and Node2 last
        self.assertEqual(elist.head.data, "Node3")
        self.assertEqual(elist.head.next.data, "Node1")
        self.assertEqual(elist.head.next.next.data, "Node2")


if __name__ == "__main__":
    unittest.main()
