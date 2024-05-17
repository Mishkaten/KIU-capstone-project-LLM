import random


class UIDGenerator:
    id_seed = 1000

    @staticmethod
    def generateRandomNumberString(length):
        return ''.join(str(random.randint(0, 9)) for _ in range(length))

    @staticmethod
    def decimalToHexavigesimal(decimal):
        hexavigesimal = ''
        while decimal > 0:
            remainder = decimal % 26
            hexavigesimal = chr(65 + remainder) + hexavigesimal
            decimal //= 26
        return hexavigesimal

    @classmethod
    def generate_id(cls):
        random_part = cls.generateRandomNumberString(3)
        unique_part = cls.decimalToHexavigesimal(cls.id_seed)
        cls.id_seed += 1
        return f"{random_part}-{unique_part}"


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
        if self.head is None:
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
                else:
                    self.head = current.next
                if current.next:
                    current.next.previous = current.previous
                return
            current = current.next

    def findByID(self, id):
        current = self.head
        while current:
            if current.getID() == id:
                current.recordAccess()
                if current.next:
                    if current.previous:
                        current.previous.next = current.next
                    else:
                        self.head = current.next
                    current.next.previous = current.previous
                    tail = current.next
                    while tail.next:
                        tail = tail.next
                    tail.next = current
                    current.previous = tail
                    current.next = None
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

    def merge(self, left, right):
        dummy = DLNode(0)
        tail = dummy

        while True:
            if left is None:
                tail.next = right
                break
            if right is None:
                tail.next = left
                break

            if left.getPopularity() <= right.getPopularity():
                tail.next = left
                left = left.next
            else:
                tail.next = right
                right = right.next

            tail = tail.next

        return dummy.next

    def sortHelper(self, head):
        if head is None or head.next is None:
            return head

        slow = head
        fast = head.next

        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        mid = slow.next
        slow.next = None

        left = self.sortHelper(head)
        right = self.sortHelper(mid)

        return self.merge(left, right)

    def sort(self):
        self.head = self.sortHelper(self.head)


# Test UIDGenerator
print(UIDGenerator.generateRandomNumberString(3))  # Output: Random 3-digit string
print(UIDGenerator.decimalToHexavigesimal(678))    # Output: BAC
print(UIDGenerator.generate_id())                   # Output: Random ID in the format 'XXX-YYYY'

# Test EvilList
evil_list = EvilList()

# Put elements in the list
id1 = evil_list.put(10)
id2 = evil_list.put(20)
id3 = evil_list.put(30)

# Print the list
evil_list.printList()  # Output: 10 20 30

# Find elements by ID
print(evil_list.findByID(id1))  # Output: 10
print(evil_list.findByID(id2))  # Output: 20
print(evil_list.findByID(id3))  # Output: 30

# Find non-existent ID
print(evil_list.findByID("invalid_id"))  # Output: None

# Remove elements by ID
evil_list.removeByID(id2)
evil_list.printList()  # Output: 10 30

# Get the size of the list
print(evil_list.size())  # Output: 2

# Sort the list based on popularity
evil_list.findByID(id1)
evil_list.findByID(id1)
evil_list.findByID(id3)
evil_list.sort()
evil_list.printList()  # Output: 30 10