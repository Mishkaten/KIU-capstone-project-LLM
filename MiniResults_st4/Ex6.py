class Car:
    def __init__(self, make, model, fuel=0, litresPer100km=8.0):
        self.__make = make
        self.__model = model
        self.__fuel = fuel
        self.__litresPer100km = litresPer100km

    def getMake(self):
        return self.__make

    def getModel(self):
        return self.__model

    def getFuel(self):
        return self.__fuel

    def addFuel(self, fuel):
        self.__fuel += fuel

    def drive(self, km):
        # Calculate the fuel consumption for the given distance
        fuel_consumed = (self.__litresPer100km * km) / 100
        self.__fuel -= fuel_consumed

    def distanceToE(self):
        # Calculate how many kilometers can be driven with the remaining fuel
        if self.__fuel == 0:
            return 0
        distance = (self.__fuel / self.__litresPer100km) * 100
        return distance

# Example usage:
car = Car("Toyota", "Corolla")
car.addFuel(50)  # Adding 50 litres of fuel
print(car.getMake())  # Outputs: Toyota
print(car.getModel())  # Outputs: Corolla
print(car.getFuel())  # Outputs: 50

car.drive(150)  # Drive 150 km
print(car.getFuel())  # Check remaining fuel

print(car.distanceToE())  # How far can we go with the remaining fuel?
