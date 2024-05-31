package Claude.St3.HW1;

public class RescueThePenguins extends MiniJava {
    public static void main(String[] args) {
        int steps = readInt("Please insert the number of time steps (>= 1):");
        if (steps < 1) {
            System.out.println("number of steps >= 1 required");
            return;
        }

        int young = readInt("Initial number of young:");
        int youngAdults = readInt("Initial number of young adults:");
        int adults = readInt("Initial number of adults:");
        int oldAdults = readInt("Initial number of old adults:");
        int elders = readInt("Initial number of elders:");

        for (int i = 0; i < steps; i++) {
            int food = collectFood(youngAdults, adults);
            int[] fedPenguins = distributeFood(food, young, youngAdults, adults, oldAdults, elders);
            young = fedPenguins[0];
            youngAdults = fedPenguins[1];
            adults = fedPenguins[2];
            oldAdults = fedPenguins[3];
            elders = fedPenguins[4];

            int newYoung = breed(youngAdults, adults, oldAdults);
            elders = oldAdults;
            oldAdults = adults;
            adults = youngAdults;
            youngAdults = young;
            young = newYoung;
        }

        System.out.println("Final number of young:");
        System.out.println(young);
        System.out.println("Final number of young adults:");
        System.out.println(youngAdults);
        System.out.println("Final number of adults:");
        System.out.println(adults);
        System.out.println("Final number of old adults:");
        System.out.println(oldAdults);
        System.out.println("Final number of elders:");
        System.out.println(elders);
    }

    private static int collectFood(int youngAdults, int adults) {
        return youngAdults * 3 + adults * 2;
    }

    private static int[] distributeFood(int food, int young, int youngAdults, int adults, int oldAdults, int elders) {
        int[] fedPenguins = new int[5];
        int totalPenguins = young + youngAdults + adults + oldAdults + elders;

        if (food >= totalPenguins) {
            fedPenguins[0] = young;
            fedPenguins[1] = youngAdults;
            fedPenguins[2] = adults;
            fedPenguins[3] = oldAdults;
            fedPenguins[4] = elders;
        } else {
            int fedYoung = Math.min(young, food);
            food -= fedYoung;
            int fedYoungAdults = Math.min(youngAdults, food);
            food -= fedYoungAdults;
            int fedAdults = Math.min(adults, food);
            food -= fedAdults;
            int fedOldAdults = Math.min(oldAdults, food);
            food -= fedOldAdults;
            int fedElders = Math.min(elders, food);

            fedPenguins[0] = fedYoung;
            fedPenguins[1] = fedYoungAdults;
            fedPenguins[2] = fedAdults;
            fedPenguins[3] = fedOldAdults;
            fedPenguins[4] = fedElders;
        }

        return fedPenguins;
    }

    private static int breed(int youngAdults, int adults, int oldAdults) {
        return youngAdults / 4 + adults / 2 + oldAdults / 3;
    }
}