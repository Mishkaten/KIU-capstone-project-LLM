package Claude.St1.HW1;

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
            distributeFood(food, young, youngAdults, adults, oldAdults, elders);
            breed(young, youngAdults, adults, oldAdults);
            ageUp(young, youngAdults, adults, oldAdults, elders);
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

    private static void distributeFood(int food, int young, int youngAdults, int adults, int oldAdults, int elders) {
        int totalPenguins = young + youngAdults + adults + oldAdults + elders;
        if (food < totalPenguins) {
            int fedPenguins = 0;
            if (food >= young) {
                food -= young;
                fedPenguins += young;
            } else {
                fedPenguins += food;
                food = 0;
            }
            if (food >= youngAdults) {
                food -= youngAdults;
                fedPenguins += youngAdults;
            } else {
                fedPenguins += food;
                food = 0;
            }
            if (food >= adults) {
                food -= adults;
                fedPenguins += adults;
            } else {
                fedPenguins += food;
                food = 0;
            }
            if (food >= oldAdults) {
                food -= oldAdults;
                fedPenguins += oldAdults;
            } else {
                fedPenguins += food;
                food = 0;
            }
            if (food >= elders) {
                fedPenguins += elders;
            } else {
                fedPenguins += food;
            }
            young = youngAdults = adults = oldAdults = elders = 0;
            young = fedPenguins;
        }
    }

    private static void breed(int young, int youngAdults, int adults, int oldAdults) {
        young += youngAdults / 4 + adults / 2 + oldAdults / 3;
    }

    private static void ageUp(int young, int youngAdults, int adults, int oldAdults, int elders) {
        elders = oldAdults;
        oldAdults = adults;
        adults = youngAdults;
        youngAdults = young;
        young = 0;
    }
}