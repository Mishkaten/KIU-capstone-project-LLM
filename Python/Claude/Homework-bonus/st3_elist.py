import random
import string

class UIDGenerator:
    id_seed = 1000

    @staticmethod
    def generateRandomNumberString(length):
        return ''.join(random.choices(string.digits, k=length))

    @staticmethod
    def decimalToHexavigesimal(decimal):
        hexavigesimal = ''
        while decimal > 0:
            decimal, remainder = divmod(decimal, 26)
            hexavigesimal = string.ascii_uppercase[remainder] + hexavigesimal
        return hexavigesimal

    @classmethod
    def generate_id(cls):
        random_part = cls.generateRandomNumberString(3)
        unique_part = cls.decimalToHexavigesimal(cls.id_seed)
        cls.id_seed += 1
        return f"{random_part}-{unique_part}"

class DLNode:
    def __init__(self, data):
        self.__id = UIDGenerator.generate_id()
        self.__popularity = 0
        self.data = data
        self.next = None
        self.previous = None

    def recordAccess(self):
        self.__popularity += 1

    def getPopularity(self):
        return self.__popularity

    def getID(self):
        return self.__id

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
                break
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
                    while current.next:
                        current = current.next
                    current.next = DLNode(current.data)
                    current.next.previous = current
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

    def sort(self):
        if self.head is None or self.head.next is None:
            return

        def merge(left, right):
            dummy = DLNode(0)
            current = dummy
            while left and right:
                if left.getPopularity() <= right.getPopularity():
                    current.next = left
                    left.previous = current
                    left = left.next
                else:
                    current.next = right
                    right.previous = current
                    right = right.next
                current = current.next
            if left:
                current.next = left
                left.previous = current
            if right:
                current.next = right
                right.previous = current
            return dummy.next

        def mergeSort(node):
            if node is None or node.next is None:
                return node
            slow = node
            fast = node.next
            while fast and fast.next:
                slow = slow.next
                fast = fast.next.next
            mid = slow.next
            slow.next = None
            mid.previous = None
            left = mergeSort(node)
            right = mergeSort(mid)
            return merge(left, right)

        self.head = mergeSort(self.head)

# Test cases
evil_list = EvilList()

# Test put method
id1 = evil_list.put(10)
id2 = evil_list.put(20)
id3 = evil_list.put(30)
print("List after putting elements:")
evil_list.printList()  # Output: 10 20 30

# Test findByID method
print("Find element with ID", id2)
data = evil_list.findByID(id2)
print("Data:", data)  # Output: 20

# Test removeByID method
print("Remove element with ID", id2)
evil_list.removeByID(id2)
print("List after removing element:")
evil_list.printList()  # Output: 10 30

# Test size method
print("Size of the list:", evil_list.size())  # Output: 2

# Test sort method
evil_list.findByID(id1)
evil_list.findByID(id1)
evil_list.findByID(id3)
print("List before sorting:")
evil_list.printList()  # Output: 30 10
evil_list.sort()
print("List after sorting:")
evil_list.printList()  # Output: 10 30