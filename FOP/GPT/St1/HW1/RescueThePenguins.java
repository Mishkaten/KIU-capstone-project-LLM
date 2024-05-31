// Reading the content of the file RescueThePenguins.java
package fop.intro.pop;

import java.util.Scanner;

public class RescueThePenguins {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of time steps to simulate
        System.out.print("Please insert the number of time steps (>= 1): ");
        int timeSteps = scanner.nextInt();
        if (timeSteps < 1) {
            System.out.println("Number of steps >= 1 required");
            return;
        }

        // Get the initial number of penguins in each age group
        int[] penguinGroups = new int[5];
        String[] ageGroups = {"young", "young adults", "adults", "old adults", "elders"};

        for (int i = 0; i < penguinGroups.length; i++) {
            System.out.print("Initial number of " + ageGroups[i] + ": ");
            penguinGroups[i] = scanner.nextInt();
            if (penguinGroups[i] < 0) {
                System.out.println("Negative input not accepted");
                return;
            }
        }

        // Simulate the time steps
        for (int step = 0; step < timeSteps; step++) {
            // Collect food
            int totalFood = penguinGroups[1] * 3 + penguinGroups[2] * 2;

            // Distribute food
            int remainingFood = totalFood;
            for (int i = 0; i < penguinGroups.length; i++) {
                if (remainingFood >= penguinGroups[i]) {
                    remainingFood -= penguinGroups[i];
                } else {
                    penguinGroups[i] = remainingFood;
                    remainingFood = 0;
                }
            }

            // Age the penguins and breed
            int newYoung = (penguinGroups[1] / 4) + (penguinGroups[2] / 2) + (penguinGroups[3] / 3);
            int[] nextPenguinGroups = new int[5];
            nextPenguinGroups[4] = penguinGroups[3];
            nextPenguinGroups[3] = penguinGroups[2];
            nextPenguinGroups[2] = penguinGroups[1];
            nextPenguinGroups[1] = penguinGroups[0];
            nextPenguinGroups[0] = newYoung;
            penguinGroups = nextPenguinGroups;
        }

        // Print the final number of penguins in each age group
        System.out.println("Final number of young: " + penguinGroups[0]);
        System.out.println("Final number of young adults: " + penguinGroups[1]);
        System.out.println("Final number of adults: " + penguinGroups[2]);
        System.out.println("Final number of old adults: " + penguinGroups[3]);
        System.out.println("Final number of elders: " + penguinGroups[4]);
    }
}
