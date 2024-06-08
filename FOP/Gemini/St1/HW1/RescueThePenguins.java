package St1.HW1;

public class RescueThePenguins extends MiniJava {
    public static void main(String[] args) {
        int numSteps = readInt("Please insert the number of time steps (>= 1):");
        while (numSteps < 1) {
            numSteps = readInt("Number of steps must be at least 1. Please enter again:");
        }

        int[] penguins = new int[5]; // young, young adult, adult, old adult, elder
        String[] groupNames = {"young", "young adults", "adults", "old adults", "elders"};

        for (int i = 0; i < 5; i++) {
            penguins[i] = readInt("Initial number of " + groupNames[i] + ":");
            while (penguins[i] < 0) {
                penguins[i] = readInt("Number cannot be negative. Please enter again:");
            }
        }

        for (int step = 0; step < numSteps; step++) {
            // Calculate food collected and needed
            int foodCollected = penguins[1] * 3 + penguins[2] * 2;
            int foodNeeded = penguins[0] + penguins[1] + penguins[2] + penguins[3] + penguins[4];

            // Distribute food and update population
            for (int i = 0; i < 5 && foodNeeded > 0; i++) {
                int foodForGroup = Math.min(penguins[i], foodCollected);
                penguins[i] -= (penguins[i] - foodForGroup); // Penguins who didn't get food die
                foodCollected -= foodForGroup;
                foodNeeded -= foodForGroup;
            }

            // Breeding and aging
            int newYoung = penguins[1] / 4 + penguins[2] / 2 + penguins[3] / 3;
            for (int i = 4; i > 0; i--) {
                penguins[i] = penguins[i - 1];
            }
            penguins[0] = newYoung;
        }

        // Print final population
        for (int i = 0; i < 5; i++) {
            write("Final number of " + groupNames[i] + ":");
            write(penguins[i]);
        }
    }
}
