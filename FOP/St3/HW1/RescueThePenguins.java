import java.util.*;

public class RescueThePenguins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of time steps
        System.out.println("Please insert the number of time steps (>= 1):");
        int steps = scanner.nextInt();
        if (steps < 1) {
            System.out.println("Number of steps >= 1 required");
            return;
        }

        // Input initial number of penguins in each age group
        int[] penguins = new int[5]; // {young, young adult, adult, old adult, elder}
        String[] ageGroups = {"young", "young adults", "adults", "old adults", "elders"};

        for (int i = 0; i < penguins.length; i++) {
            int count;
            do {
                System.out.println("Initial number of " + ageGroups[i] + ":");
                count = scanner.nextInt();
                if (count < 0) {
                    System.out.println("Negative input is not accepted.");
                }
            } while (count < 0);
            penguins[i] = count;
        }

        // Simulate the specified number of time steps
        for (int step = 0; step < steps; step++) {
            penguins = simulateStep(penguins);
        }

        // Output the final number of penguins in each age group
        System.out.println("Final number of young:");
        System.out.println(penguins[0]);
        System.out.println("Final number of young adults:");
        System.out.println(penguins[1]);
        System.out.println("Final number of adults:");
        System.out.println(penguins[2]);
        System.out.println("Final number of old adults:");
        System.out.println(penguins[3]);
        System.out.println("Final number of elders:");
        System.out.println(penguins[4]);
    }

    private static int[] simulateStep(int[] penguins) {
        int young = penguins[0];
        int youngAdults = penguins[1];
        int adults = penguins[2];
        int oldAdults = penguins[3];
        int elders = penguins[4];

        // Collect food
        int totalFoodCollected = (youngAdults * 3) + (adults * 2);

        // Distribute food and starve penguins if necessary
        int totalFoodNeeded = young + youngAdults + adults + oldAdults + elders;
        if (totalFoodCollected < totalFoodNeeded) {
            int foodShortage = totalFoodNeeded - totalFoodCollected;
            int[] groups = {young, youngAdults, adults, oldAdults, elders};
            for (int i = 0; i < groups.length; i++) {
                if (foodShortage >= groups[i]) {
                    foodShortage -= groups[i];
                    groups[i] = 0;
                } else {
                    groups[i] -= foodShortage;
                    foodShortage = 0;
                }
            }
            young = groups[0];
            youngAdults = groups[1];
            adults = groups[2];
            oldAdults = groups[3];
            elders = groups[4];
        }

        // Breed new young penguins
        int newYoung = (youngAdults / 4) + (adults / 2) + (oldAdults / 3);

        // Age progression
        elders += oldAdults;
        oldAdults = adults;
        adults = youngAdults;
        youngAdults = young;
        young = newYoung;

        // Return updated penguin population
        return new int[]{young, youngAdults, adults, oldAdults, elders};
    }
}
