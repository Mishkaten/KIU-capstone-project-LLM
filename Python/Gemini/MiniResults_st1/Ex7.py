class FirstBase:
    """Base class with a property 'X'."""

    def __init__(self):
        self._x = 0

    @property
    def X(self):
        """Getter for the 'X' property."""
        return self._x

    @X.setter
    def X(self, value):
        """Setter for the 'X' property, ensuring positive values."""
        if value >= 0:
            self._x = value

    def printX(self):
        """Prints the value of X."""
        print(self.X)

    def printName(self):
        print("FirstBase")


class SecondBase:
    """Base class with a property 'Y'."""

    def __init__(self):
        self._y = 1

    @property
    def Y(self):
        """Getter for the 'Y' property."""
        return self._y

    @Y.setter
    def Y(self, value):
        """Setter for the 'Y' property, ensuring positive values."""
        if value >= 0:
            self._y = value

    def printY(self):
        """Prints the value of Y."""
        print(self.Y)

    def printName(self):
        print("SecondBase")


class Derived(FirstBase, SecondBase):
    """Derived class inheriting from FirstBase and SecondBase."""

    def printXYSum(self):
        """Prints the sum of X and Y."""
        print(self.X + self.Y)

    def printName(self):
        print("Derived")


# Example usage
d = Derived()

d.printName()  # Output: Derived
d.X = 5
d.Y = 3
d.printX()  # Output: 5
d.printY()  # Output: 3
d.printXYSum()  # Output: 8

d.X = -3  # Invalid assignment, X remains 5
d.Y = 1
d.printX()  # Output: 5
d.printY()  # Output: 1
d.printXYSum()
