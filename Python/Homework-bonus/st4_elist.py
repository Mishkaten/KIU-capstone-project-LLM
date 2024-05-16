import random

class UIDGenerator:
    id_seed = 1000  # Starting with 1000 as the initial seed for hexavigesimal IDs
    
    @staticmethod
    def generateRandomNumberString(length):
        return ''.join(str(random.randint(0, 9)) for _ in range(length))

    @staticmethod
    def decimalToHexavigesimal(number):
        chars = []
        while number > 0:
            number, remainder = divmod(number, 26)
            chars.append(chr(remainder + 65))
        return ''.join(reversed(chars)) if chars else 'A'

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
                self.moveNodeTowardsEnd(current)
                return current.data
            current = current.next
        return None

    def moveNodeTowardsEnd(self, node):
        if node.next:
            next_node = node.next
            if next_node.next:
                next_node.next.previous = node
            node.next = next_node.next
            next_node.next = node
            next_node.previous = node.previous
            if node.previous:
                node.previous.next = next_node
            node.previous = next_node
            if node == self.head:
                self.head = next_node

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
        if not self.head or not self.head.next:
            return
        
        def mergeSort(node):
            if not node or not node.next:
                return node
            middle = getMiddle(node)
            next_to_middle = middle.next
            middle.next = None
            left = mergeSort(node)
            right = mergeSort(next_to_middle)
            sorted_list = sortedMerge(left, right)
            return sorted_list
        
        def getMiddle(node):
            if not node:
                return node
            slow = node
            fast = node.next
            while fast and fast.next:
                slow = slow.next
                fast = fast.next.next
            return slow
        
        def sortedMerge(a, b):
            if not a:
                return b
            if not b:
                return a
            if a.getPopularity() <= b.getPopularity():
                result = a
                result.next = sortedMerge(a.next, b)
            else:
                result = b
                result.next = sortedMerge(a, b.next)
            return result
        
        self.head = mergeSort(self.head)

# Example usage
if __name__ == "__main__":
    elist = EvilList()
    id1 = elist.put(10)
    id2 = elist.put(20)
    print(elist.findByID(id1))  # Accessing data by ID
    print(elist.findByID(id2))
    elist.printList()  # Printing the list
    elist.sort()  # Sorting by popularity
    elist.printList()
