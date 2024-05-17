class FirstBase:
    def __init__(self):
        self._x = 0

    @property
    def X(self):
        return self._x

    @X.setter
    def X(self, value):
        if value >= 0:
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
        if value >= 0:
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


d = Derived()

d.printName()  # Derived
d.X = 5
d.Y = 3
d.printX()     # 5
d.printY()     # 3
d.printXYSum() # 8
d.X = -3
d.Y = 1
d.printX()     # 5
d.printY()     # 1
d.printXYSum() # 6
