import random
import string
import unittest


class UIDGenerator:
    id_seed = 1000  # Starting seed for unique IDs

    @staticmethod
    def generateRandomNumberString(length):
        """Generates a random string of digits of the given length."""
        return ''.join(random.choice(string.digits) for _ in range(length))

    @staticmethod
    def decimalToHexavigesimal(decimal):
        """Converts a decimal number to hexavigesimal (base-26 using A-Z)."""
        if decimal == 0:
            return 'A'
        result = ''
        while decimal > 0:
            remainder = decimal % 26
            result = chr(ord('A') + remainder) + result
            decimal //= 26
        return result

    @classmethod
    def generate_id(cls):
        """Generates a unique ID in the format 'XXX-YYYYY'."""
        random_part = cls.generateRandomNumberString(3)
        unique_part = cls.decimalToHexavigesimal(cls.id_seed)
        cls.id_seed += 1  # Increment the seed for the next ID
        return f"{random_part}-{unique_part}"

class DLNode:
    def __init__(self, data):
        """Initializes a doubly-linked list node."""
        self._id = UIDGenerator.generate_id()
        self._popularity = 0
        self.data = data
        self.next = None
        self.prev = None

    def recordAccess(self):
        """Increases the node's popularity."""
        self._popularity += 1

    def getPopularity(self):
        """Returns the node's popularity."""
        return self._popularity

    def getID(self):
        """Returns the node's ID."""
        return self._id

class EvilList:
    def __init__(self):
        """Initializes an empty EvilList."""
        self.head = None

    def insert(self, index, data):
        """Inserts data at the given index."""
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

    def removeByID(self, id):
        """Removes the node with the given ID."""
        current = self.head
        while current:
            if current.getID() == id:
                if current.prev:
                    current.prev.next = current.next
                else:
                    self.head = current.next
                if current.next:
                    current.next.prev = current.prev
                return  # Node removed
            current = current.next

    def findByID(self, id):
        """Finds the node with the given ID and returns its data."""
        current = self.head
        while current:
            if current.getID() == id:
                current.recordAccess()
                if current.next:  # Move the node one step back if not last
                    current.next.prev = current.prev
                    if current.prev:
                        current.prev.next = current.next
                    else:
                        self.head = current.next
                    current.prev = current.next
                    current.next = current.next.next
                    if current.next:
                        current.next.prev = current
                return current.data
            current = current.next
        return None  # Node not found

    def size(self):
        """Returns the number of nodes in the list."""
        count = 0
        current = self.head
        while current:
            count += 1
            current = current.next
        return count

    def printList(self):
        """Prints the data of all nodes in the list."""
        current = self.head
        while current:
            print(current.data, end=" ")
            current = current.next
        print()  # Newline after printing the list

    def sort(self):
        """Sorts the list by node popularity using merge sort."""
        if not self.head or not self.head.next:
            return  # List is already sorted or empty

        def merge_sort(head):
            if not head or not head.next:
                return head

            # Split the list into two halves
            mid = get_middle(head)
            second_half = mid.next
            mid.next = None
            second_half.prev = None

            # Recursively sort the two halves
            left = merge_sort(head)
            right = merge_sort(second_half)

            # Merge the sorted halves
            return merge(left, right)

        def get_middle(head):
            slow = head
            fast = head
            while fast and fast.next and fast.next.next:
                slow = slow.next
                fast = fast.next.next
            return slow

        def merge(left, right):
            dummy = DLNode(None)
            tail = dummy

            while left and right:
                if left.getPopularity() < right.getPopularity():
                    tail.next = left
                    left.prev = tail
                    left = left.next
                else:
                    tail.next = right
                    right.prev = tail
                    right = right.next
                tail = tail.next

            tail.next = left or right
            if tail.next:
                tail.next.prev = tail

            return dummy.next

        self.head = merge_sort(self.head)


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
        node_id = elist.head.next.getID()  # Get the ID of the second node
        elist.removeByID(node_id)
        self.assertEqual(elist.size(), 2)

    def test_find_by_id(self):
        elist = EvilList()
        elist.insert(0, "Node1")
        elist.insert(1, "Node2")
        elist.insert(2, "Node3")
        node_id = elist.head.next.getID()  # Get the ID of the second node
        data = elist.findByID(node_id)
        self.assertEqual(data, "Node2")

    def test_sort(self):
        elist = EvilList()
        elist.insert(0, "Node1")
        elist.insert(1, "Node2")
        elist.insert(2, "Node3")
        elist.head.recordAccess()  # Node1 gets 1 popularity
        elist.head.next.recordAccess()  # Node2 gets 1 popularity
        elist.head.next.recordAccess()  # Node2 gets 2 popularity
        elist.sort()
        # After sorting, Node3 should be first, Node1 second, and Node2 last
        self.assertEqual(elist.head.data, "Node3")
        self.assertEqual(elist.head.next.data, "Node1")
        self.assertEqual(elist.head.next.next.data, "Node2")


if __name__ == "__main__":
    unittest.main()

