package GPT.St2.HW1;
import java.util.Scanner;

public class RescueThePenguins {
    private int young;
    private int youngAdults;
    private int adults;
    private int oldAdults;
    private int elders;

    public RescueThePenguins(int young, int youngAdults, int adults, int oldAdults, int elders) {
        this.young = young;
        this.youngAdults = youngAdults;
        this.adults = adults;
        this.oldAdults = oldAdults;
        this.elders = elders;
    }

    public void simulateSteps(int steps) {
        if (steps < 1) {
            System.out.println("number of steps >= 1 required");
            return;
        }
        for (int i = 0; i < steps; i++) {
            collectFood();
            distributeFood();
            breedPenguins();
            agePenguins();
        }
        printPopulation();
    }

    private int collectFood() {
        int foodCollected = youngAdults * 3 + adults * 2;
        return foodCollected;
    }

    private void distributeFood() {
        int food = collectFood();
        int totalPenguins = young + youngAdults + adults + oldAdults + elders;

        if (food < totalPenguins) {
            // Not enough food, penguins starve
            int starveCount = totalPenguins - food;

            // Start starving from the elder group
            elders -= Math.min(starveCount, elders);
            starveCount -= Math.min(starveCount, elders);

            // Then old adults
            oldAdults -= Math.min(starveCount, oldAdults);
            starveCount -= Math.min(starveCount, oldAdults);

            // Then adults
            adults -= Math.min(starveCount, adults);
            starveCount -= Math.min(starveCount, adults);

            // Then young adults
            youngAdults -= Math.min(starveCount, youngAdults);
            starveCount -= Math.min(starveCount, youngAdults);

            // Finally, the young
            young -= Math.min(starveCount, young);
        }
    }

    private void breedPenguins() {
        int newYoung = (youngAdults / 4) + (adults / 2) + (oldAdults / 3);
        young += newYoung;
    }

    private void agePenguins() {
        elders += oldAdults;
        oldAdults = adults;
        adults = youngAdults;
        youngAdults = young;
        young = 0;
    }

    private void printPopulation() {
        System.out.println("Final number of young: " + young);
        System.out.println("Final number of young adults: " + youngAdults);
        System.out.println("Final number of adults: " + adults);
        System.out.println("Final number of old adults: " + oldAdults);
        System.out.println("Final number of elders: " + elders);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please insert the number of time steps (>= 1):");
        int steps = scanner.nextInt();

        System.out.println("Initial number of young:");
        int young = scanner.nextInt();

        System.out.println("Initial number of young adults:");
        int youngAdults = scanner.nextInt();

        System.out.println("Initial number of adults:");
        int adults = scanner.nextInt();

        System.out.println("Initial number of old adults:");
        int oldAdults = scanner.nextInt();

        System.out.println("Initial number of elders:");
        int elders = scanner.nextInt();

        RescueThePenguins simulation = new RescueThePenguins(young, youngAdults, adults, oldAdults, elders);
        simulation.simulateSteps(steps);
    }
}
