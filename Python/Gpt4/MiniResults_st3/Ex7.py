class FirstBase:
    def __init__(self):
        self._x = 0

    @property
    def X(self):
        return self._x

    @X.setter
    def X(self, value):
        if value > 0:
            self._x = value

    def printX(self):
        print(self.X)

    def printName(self):
        print("FirstBase")

class SecondBase:
    def __init__(self):
        self._y = 1

    @property
    def Y(self):
        return self._y

    @Y.setter
    def Y(self, value):
        if value > 0:
            self._y = value

    def printY(self):
        print(self.Y)

    def printName(self):
        print("SecondBase")

class Derived(FirstBase, SecondBase):
    def printXYSum(self):
        print(self.X + self.Y)

    def printName(self):
        print("Derived")

# Example for testing 'Derived' class
d = Derived()

d.printName()  # Outputs: Derived
d.X = 5
d.Y = 3
d.printX()  # Outputs: 5
d.printY()  # Outputs: 3
d.printXYSum()  # Outputs: 8
d.X = -3  # Invalid, should not change X
d.Y = 1  # Valid, Y is already 1
d.printX()  # Outputs: 5, X was not changed due to invalid input
d.printY()  # Outputs: 1
d.printXYSum()  # Outputs: 6
