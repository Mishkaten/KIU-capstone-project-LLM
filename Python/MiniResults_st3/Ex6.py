class Car:
    def __init__(self, make, model, fuel=0, litresPer100km=8.0):
        # Hidden attributes with the convention of using a single underscore prefix
        self._make = make
        self._model = model
        self._fuel = fuel
        self._litresPer100km = litresPer100km

    # Getter methods
    def getMake(self):
        return self._make

    def getModel(self):
        return self._model

    def getFuel(self):
        return self._fuel

    # Method to add fuel
    def addFuel(self, fuel):
        self._fuel += fuel

    # Method to simulate driving the car
    def drive(self, kilometres):
        # Calculate the amount of fuel consumed over the given distance
        fuel_consumed = (kilometres / 100) * self._litresPer100km
        self._fuel -= fuel_consumed

    # Method to calculate distance to empty
    def distanceToE(self):
        # Calculate the maximum distance that can be driven with the remaining fuel
        return (self._fuel / self._litresPer100km) * 100

# Example usage
my_car = Car("Toyota", "Corolla")
my_car.addFuel(50)  # Add 50 litres of fuel
print(f"Make: {my_car.getMake()}")
print(f"Model: {my_car.getModel()}")
print(f"Fuel left: {my_car.getFuel()} litres")
my_car.drive(150)  # Drive 150 km
print(f"Fuel left after driving: {my_car.getFuel()} litres")
print(f"Distance to empty: {my_car.distanceToE()} km")
