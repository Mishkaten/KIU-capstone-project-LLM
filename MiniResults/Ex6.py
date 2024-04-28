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

    def drive(self, kms):
        # Calculate the fuel consumption for the given distance
        fuel_consumed = (self.__litresPer100km * kms) / 100
        # Subtract the consumed fuel from the tank
        self.__fuel -= fuel_consumed

    def distanceToE(self):
        # Calculate the distance that can be driven with the remaining fuel
        distance = (self.__fuel / self.__litresPer100km) * 100
        return distance

# Example usage:
car = Car("Toyota", "Corolla")
car.addFuel(50)  # Adding 50 litres of fuel
print(car.getMake())  # Output: Toyota
print(car.getModel())  # Output: Corolla
print(car.getFuel())  # Output: 50
car.drive(100)  # Drive 100 km
print(car.getFuel())  # Output: 42.0
print(f"Distance until empty: {car.distanceToE()} km")  # Distance left until the tank is empty
