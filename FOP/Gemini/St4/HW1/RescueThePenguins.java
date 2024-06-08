package Claude.St3.HW1;

public class RescueThePenguins extends MiniJava {
    public static void main(String[] args) {
        int timeSteps = readInt("Please insert the number of time steps (>= 1):");

        // Input validation for time steps
        while (timeSteps < 1) {
            timeSteps = readInt("Number of steps must be at least 1. Please try again:");
        }

        int[] penguins = new int[5]; // Array to store penguin counts for each age group
        String[] ageGroups = {"young", "young adults", "adults", "old adults", "elders"};

        // Input and validation for initial penguin numbers
        for (int i = 0; i < 5; i++) {
            penguins[i] = readInt("Initial number of " + ageGroups[i] + ":");
            while (penguins[i] < 0) {
                penguins[i] = readInt("Invalid input. Please enter a non-negative number:");
            }
        }

        // Simulation loop
        for (int step = 0; step < timeSteps; step++) {
            int foodCollected = penguins[1] * 3 + penguins[2] * 2;  // Food collected by young adults and adults
            int foodNeeded = 0;

            // Calculate total food needed and adjust penguin numbers based on starvation
            for (int i = 0; i < 5; i++) {
                foodNeeded += penguins[i]; // all penguins need one food
                if(foodCollected<foodNeeded){
                    penguins[i]-=foodNeeded-foodCollected;
                    foodNeeded=foodCollected;
                }
            }

            // Breeding (new young penguins are born)
            int newYoung = penguins[1] / 4 + penguins[2] / 2 + penguins[3] / 3;
            penguins[0] = newYoung;

            // Age progression (shift penguins to the next age group)
            for (int i = 4; i > 0; i--) {
                penguins[i] = penguins[i - 1];
            }
            penguins[0]=newYoung; // Reset the youngest group to the new young penguins
        }

        // Output the final penguin numbers
        for (int i = 0; i < 5; i++) {
            write("Final number of " + ageGroups[i] + ":");
            write(penguins[i]);
        }
    }
}
