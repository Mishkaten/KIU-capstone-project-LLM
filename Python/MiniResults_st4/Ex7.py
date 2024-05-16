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
        print(self.X)

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
        print(self.Y)

    def printName(self):
        print("SecondBase")

class Derived(FirstBase, SecondBase):
    def __init__(self):
        FirstBase.__init__(self)
        SecondBase.__init__(self)

    def printXYSum(self):
        print(self.X + self.Y)

    def printName(self):
        print("Derived")

# Example usage:
d = Derived()
d.printName()  # Should print "Derived"
d.X = 5
d.Y = 3
d.printX()     # Should print 5
d.printY()     # Should print 3
d.printXYSum() # Should print 8
d.X = -3       # No change because it's negative
d.Y = 1
d.printX()     # Should still print 5
d.printY()     # Should print 1
d.printXYSum() # Should print 6
