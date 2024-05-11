class Car:
    def __init__(self, make, model, fuel=0, litresPer100km=8.0):
        self._make = make  # Hidden attribute for the car's make
        self._model = model  # Hidden attribute for the car's model
        self._fuel = fuel  # Hidden attribute, fuel in the tank in litres
        self._litresPer100km = litresPer100km  # Fuel consumption per 100 km

    def getMake(self):
        """Return the make of the car."""
        return self._make

    def getModel(self):
        """Return the model of the car."""
        return self._model

    def getFuel(self):
        """Return the current fuel level."""
        return self._fuel

    def addFuel(self, fuel):
        """Add fuel to the car."""
        self._fuel += fuel

    def drive(self, distance):
        """Drive the car a certain distance, decreasing the fuel accordingly."""
        fuel_used = (self._litresPer100km / 100) * distance
        self._fuel -= fuel_used

    def distanceToE(self):
        """Calculate how many kilometers can be driven until the tank is empty."""
        return (self._fuel / self._litresPer100km) * 100

# Example usage:
my_car = Car("Toyota", "Corolla")
my_car.addFuel(50)  # Add 50 litres of fuel
print(f"Initial fuel: {my_car.getFuel()} liters")  # Check initial fuel
my_car.drive(100)  # Drive 100 kilometers
print(f"Fuel after driving 100 km: {my_car.getFuel()} liters")  # Check remaining fuel
print(f"Distance to empty: {my_car.distanceToE()} km")  # Distance until empty
