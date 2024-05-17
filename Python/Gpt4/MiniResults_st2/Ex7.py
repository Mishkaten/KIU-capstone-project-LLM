class FirstBase:
    def __init__(self):
        self._x = 0  # Hidden attribute with initial value of 0

    @property
    def X(self):
        return self._x

    @X.setter
    def X(self, value):
        if value > 0:  # Ensure that X is positive
            self._x = value

    def printX(self):
        print(self._x)

    def printName(self):
        print("FirstBase")


class SecondBase:
    def __init__(self):
        self._y = 1  # Hidden attribute with initial value of 1

    @property
    def Y(self):
        return self._y

    @Y.setter
    def Y(self, value):
        if value > 0:  # Ensure that Y is positive
            self._y = value

    def printY(self):
        print(self._y)

    def printName(self):
        print("SecondBase")


class Derived(FirstBase, SecondBase):
    def printXYSum(self):
        print(self.X + self.Y)

    def printName(self):
        print("Derived")


# Example usage:
d = Derived()
d.printName()  # Should print "Derived"
d.X = 5
d.Y = 3
d.printX()  # Should print 5
d.printY()  # Should print 3
d.printXYSum()  # Should print 8
d.X = -3  # Should not modify X
d.Y = 1  # Will modify Y because it is a valid positive number
d.printX()  # Should still print 5
d.printY()  # Should print 1
d.printXYSum()  # Should print 6
