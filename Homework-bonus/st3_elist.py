import random

class UIDGenerator:
    id_seed = 1000  # Starting with any 4 digit integer, e.g., 1000
    
    @staticmethod
    def generateRandomNumberString(length):
        return ''.join(str(random.randint(0, 9)) for _ in range(length))
    
    @staticmethod
    def decimalToHexavigesimal(num):
        if num == 0:
            return 'A'
        mapping = [chr(i) for i in range(ord('A'), ord('Z') + 1)]
        result = ''
        while num > 0:
            num, remainder = divmod(num, 26)
            result = mapping[remainder] + result
        return result

    @classmethod
    def generate_id(cls):
        random_part = UIDGenerator.generateRandomNumberString(3)
        unique_part = UIDGenerator.decimalToHexavigesimal(cls.id_seed)
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
        if not self.head:
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node
            new_node.previous = current
        return new_node.getID()
    
    def removeByID(self, node_id):
        current = self.head
        while current:
            if current.getID() == node_id:
                if current.previous:
                    current.previous.next = current.next
                if current.next:
                    current.next.previous = current.previous
                if current == self.head:
                    self.head = current.next
                return
            current = current.next

    def findByID(self, node_id):
        current = self.head
        while current:
            if current.getID() == node_id:
                current.recordAccess()
                # move accessed node one step back if it is not already the last node
                if current.next:
                    if current.previous:
                        current.previous.next = current.next
                    current.next.previous = current.previous
                    # Now put current after its next
                    temp = current.next
                    if temp.next:
                        temp.next.previous = current
                    current.next = temp.next
                    temp.next = current
                    current.previous = temp
                    if current == self.head:
                        self.head = temp
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
        # Implementing a simple bubble sort for demonstration purposes
        if not self.head or not self.head.next:
            return
        changed = True
        while changed:
            changed = False
            current = self.head
            while current and current.next:
                if current.getPopularity() > current.next.getPopularity():
                    # Swap nodes
                    if current.previous:
                        current.previous.next = current.next
                    current.next.previous = current.previous
                    temp = current.next.next
                    current.next.next = current
                    current.previous = current.next
                    current.next = temp
                    if temp:
                        temp.previous = current
                    if current == self.head:
                        self.head = current.previous
                    changed = True
                else:
                    current = current.next