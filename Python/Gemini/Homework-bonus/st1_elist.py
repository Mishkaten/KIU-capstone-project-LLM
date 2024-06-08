import random
import string

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
        hexavigesimal = ""
        while decimal > 0:
            remainder = decimal % 26
            hexavigesimal = chr(65 + remainder) + hexavigesimal  # Convert to letter
            decimal //= 26
        return hexavigesimal

    @classmethod
    def generate_id(cls):
        """Generates a unique ID in the format 'XXX-YYYYY'."""
        random_part = cls.generateRandomNumberString(3)
        unique_part = cls.decimalToHexavigesimal(cls.id_seed)
        cls.id_seed += 1  # Increment seed for next ID
        return f"{random_part}-{unique_part}"

class DLNode:
    def __init__(self, data):
        self.__id = UIDGenerator.generate_id()
        self.__popularity = 0
        self.data = data
        self.next = None
        self.prev = None

    def recordAccess(self):
        """Increases the node's popularity."""
        self.__popularity += 1

    def getPopularity(self):
        """Returns the node's popularity."""
        return self.__popularity

    def getID(self):
        """Returns the node's ID."""
        return self.__id

class EvilList:
    def __init__(self):
        self.head = None

    def insert(self, index, data):
        """Inserts a new node at the specified index."""
        new_node = DLNode(data)
        if index == 0:
            if self.head:
                new_node.next = self.head
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
        return new_node.getID()  # Return the ID of the inserted node

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
                    temp = current.next
                    current.next = temp.next
                    if temp.next:
                        temp.next.prev = current
                    temp.prev = current.prev
                    if current.prev:
                        current.prev.next = temp
                    else:
                        self.head = temp
                    current.prev = temp
                    temp.next = current
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
        print()  # Add a newline after printing

    def sort(self):
        """Sorts the list by node popularity using merge sort."""
        # ... (Implementation of merge sort for doubly linked list)
