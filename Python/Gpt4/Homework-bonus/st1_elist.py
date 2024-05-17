import random
import string

class UIDGenerator:
    id_seed = 1000  # Starting from a 4-digit integer as suggested

    @staticmethod
    def generateRandomNumberString(length):
        return ''.join(random.choices(string.digits, k=length))

    @staticmethod
    def decimalToHexavigesimal(num):
        hexavigesimal = ""
        while num > 0:
            num, remainder = divmod(num, 26)
            hexavigesimal = chr(65 + remainder) + hexavigesimal
        return hexavigesimal or "A"

    @classmethod
    def generate_id(cls):
        random_digits = UIDGenerator.generateRandomNumberString(3)
        hex_part = UIDGenerator.decimalToHexavigesimal(cls.id_seed)
        cls.id_seed += 1
        return f"{random_digits}-{hex_part}"

class DLNode:
    def __init__(self, data):
        self._id = UIDGenerator.generate_id()
        self._popularity = 0
        self.data = data
        self.next = None
        self.previous = None

    def recordAccess(self):
        self._popularity += 1

    def getPopularity(self):
        return self._popularity

    def getID(self):
        return self._id

class EvilList:
    def __init__(self):
        self.head = None

    def put(self, data):
        new_node = DLNode(data)
        if not self.head:
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node
            new_node.previous = current
        return new_node.getID()

    def removeByID(self, id):
        current = self.head
        while current:
            if current.getID() == id:
                if current.previous:
                    current.previous.next = current.next
                if current.next:
                    current.next.previous = current.previous
                if current == self.head:
                    self.head = current.next
                return
            current = current.next

    def findByID(self, id):
        current = self.head
        while current:
            if current.getID() == id:
                current.recordAccess()
                return current.data
            current = current.next
        return None

    def size(self):
        count = 0
        current = self.head
        while current:
            count += 1
            current = current.next
        return count

    def printList(self):
        current = self.head
        while current:
            print(current.data, end=' ')
            current = current.next
        print()





    

