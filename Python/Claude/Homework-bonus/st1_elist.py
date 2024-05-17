import random
import string

class UIDGenerator:
    id_seed = 1000

    @staticmethod
    def generateRandomNumberString(length):
        digits = string.digits
        return ''.join(random.choice(digits) for _ in range(length))

    @staticmethod
    def decimalToHexavigesimal(decimal):
        hexavigesimal = ''
        while decimal > 0:
            remainder = decimal % 26
            hexavigesimal = chr(ord('A') + remainder) + hexavigesimal
            decimal //= 26
        return hexavigesimal

    @classmethod
    def generate_id(cls):
        random_number = cls.generateRandomNumberString(3)
        hexavigesimal = cls.decimalToHexavigesimal(cls.id_seed)
        cls.id_seed += 1
        return f"{random_number}-{hexavigesimal}"

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
                    current.previous.next = current.next
                    current.next.previous = current.previous
                    current.next.next = current
                    current.previous = current.next
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

    def sort(self):
        if self.head is None or self.head.next is None:
            return

        # Merge sort implementation
        def merge(left, right):
            dummy = DLNode(None)
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

        def mergeSort(head):
            if head is None or head.next is None:
                return head

            # Split the list into two halves
            slow = head
            fast = head.next
            while fast and fast.next:
                slow = slow.next
                fast = fast.next.next

            second_half = slow.next
            slow.next = None
            second_half.previous = None

            # Recursively sort the two halves
            left = mergeSort(head)
            right = mergeSort(second_half)

            # Merge the sorted halves
            return merge(left, right)

        self.head = mergeSort(self.head)