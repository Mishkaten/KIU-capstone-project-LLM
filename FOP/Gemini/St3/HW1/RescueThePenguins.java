package St3.HW1;

public class RescueThePenguins extends MiniJava {
    public static void main(String[] args) {
        int numSteps = readInt("Please insert the number of time steps (>= 1):");
        while (numSteps < 1) {
            numSteps = readInt("Number of steps must be at least 1. Please try again:");
        }

        int[] penguins = new int[5]; // young, young adult, adult, old adult, elder
        String[] groupNames = {"young", "young adults", "adults", "old adults", "elders"};

        for (int i = 0; i < penguins.length; i++) {
            penguins[i] = readInt("Initial number of " + groupNames[i] + ":");
            while (penguins[i] < 0) {
                penguins[i] = readInt("Number cannot be negative. Please try again:");
            }
        }

        for (int step = 0; step < numSteps; step++) {
            int totalFood = penguins[1] * 3 + penguins[2] * 2;
            int requiredFood = penguins[0] + penguins[1] + penguins[2] + penguins[3] + penguins[4];

            if (totalFood < requiredFood) {
                // Starvation handling (CORRECTED)
                int remainingFood = totalFood;

                for (int i = 0; i < penguins.length && remainingFood > 0; i++) {
                    if (penguins[i] <= remainingFood) {
                        remainingFood -= penguins[i]; // Feed the entire group
                    } else {
                        penguins[i] = remainingFood; // Feed as many as possible
                        remainingFood = 0;         // No food left
                    }
                }

                for (int i = 0; i < penguins.length; i++) {
                    if (penguins[i] > remainingFood) {
                        penguins[i] = 0; // Those who didn't get fed, starve
                    }
                }

            } else {
                // Normal population dynamics (no changes here)
                int newYoung = penguins[1] / 4 + penguins[2] / 2 + penguins[3] / 3;
                penguins = new int[]{newYoung, penguins[0], penguins[1], penguins[2], penguins[3]};
            }
        }

        for (int i = 0; i < penguins.length; i++) {
            write("Final number of " + groupNames[i] + ":");
            write(penguins[i]);
        }
    }
}
