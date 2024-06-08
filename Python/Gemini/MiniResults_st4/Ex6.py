class Car:
    """Represents a car with fuel consumption and driving capabilities."""

    def __init__(self, make, model):
        """
        Initializes a new Car object.

        Args:
            make: The make (brand) of the car.
            model: The model of the car.
        """
        self.__make = make  # Hidden attribute for make
        self.__model = model  # Hidden attribute for model
        self.__fuel = 0  # Hidden attribute, initial fuel is 0
        self.__litresPer100km = 8.0  # Hidden attribute, default consumption

    def getMake(self):
        """Returns the make of the car."""
        return self.__make

    def getModel(self):
        """Returns the model of the car."""
        return self.__model

    def getFuel(self):
        """Returns the amount of fuel left in the tank."""
        return self.__fuel

    def addFuel(self, litres):
        """Adds fuel to the tank.

        Args:
            litres: The amount of fuel to add (in litres).
        """
        self.__fuel += litres

    def drive(self, distance):
        """Simulates driving a certain distance, consuming fuel.

        Args:
            distance: The distance driven (in kilometers).
        """
        fuel_consumed = (distance / 100) * self.__litresPer100km
        self.__fuel -= fuel_consumed

    def distanceToE(self):
        """Calculates the remaining distance until the tank is empty."""
        return (self.__fuel / self.__litresPer100km) * 100


# Example usage:
car = Car("Toyota", "Corolla")
car.addFuel(50)  # Adding 50 litres of fuel
print(car.getMake())  # Output: Toyota
print(car.getModel())  # Output: Corolla
print(car.getFuel())  # Output: 50
car.drive(100)  # Drive 100 km
print(car.getFuel())  # Output: 42.0
print(f"Distance until empty: {car.distanceToE()} km")  # Distance left until the tank is empty
