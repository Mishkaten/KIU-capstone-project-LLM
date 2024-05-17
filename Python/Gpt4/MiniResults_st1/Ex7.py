class FirstBase:
    def __init__(self):
        self.__x = 0

    @property
    def X(self):
        return self.__x

    @X.setter
    def X(self, value):
        if value > 0:
            self.__x = value

    def printX(self):
        print(self.__x)

    def printName(self):
        print("FirstBase")


class SecondBase:
    def __init__(self):
        self.__y = 1

    @property
    def Y(self):
        return self.__y

    @Y.setter
    def Y(self, value):
        if value > 0:
            self.__y = value

    def printY(self):
        print(self.__y)

    def printName(self):
        print("SecondBase")


class Derived(FirstBase, SecondBase):
    def printXYSum(self):
        # Since X and Y are properties, they are accessed like attributes
        print(self.X + self.Y)

    def printName(self):
        print("Derived")


# Example for testing 'Derived' class
d = Derived()

d.printName()  # Output: Derived
d.X = 5
d.Y = 3
d.printX()  # Output: 5
d.printY()  # Output: 3
d.printXYSum()  # Output: 8
d.X = -3
d.Y = 1
d.printX()  # Output: 5 (X is not changed because -3 is not a positive number)
d.printY()  # Output: 1
d.printXYSum()  # Output: 6
