package Claude.St4.HW1;

public class RescueThePenguins extends MiniJava {
    public static void main(String[] args) {
        int numSteps = getNumSteps();
        int[] population = getInitialPopulation();

        for (int step = 0; step < numSteps; step++) {
            int food = collectFood(population);
            distributeFood(population, food);
            breedPenguins(population);
            agePopulation(population);
        }

        printFinalPopulation(population);
    }

    private static int getNumSteps() {
        int numSteps;
        do {
            numSteps = readInt("Please insert the number of time steps (>= 1):");
        } while (numSteps < 1);
        return numSteps;
    }

    private static int[] getInitialPopulation() {
        int[] population = new int[5];
        String[] ageGroups = {"young", "young adults", "adults", "old adults", "elders"};

        for (int i = 0; i < 5; i++) {
            do {
                population[i] = readInt("Initial number of " + ageGroups[i] + ":");
            } while (population[i] < 0);
        }

        return population;
    }

    private static int collectFood(int[] population) {
        return population[1] * 3 + population[2] * 2;
    }

    private static void distributeFood(int[] population, int food) {
        int totalPenguins = getTotalPenguins(population);

        for (int i = 0; i < 5 && food >= totalPenguins; i++) {
            int penguinsInGroup = population[i];
            int foodForGroup = Math.min(penguinsInGroup, food);
            population[i] = foodForGroup;
            food -= foodForGroup;
            totalPenguins -= penguinsInGroup;
        }

        for (int i = 0; i < 5; i++) {
            population[i] = Math.min(population[i], food);
        }
    }

    private static void breedPenguins(int[] population) {
        int young = population[1] / 4 + population[2] / 2 + population[3] / 3;
        population[0] += young;
    }

    private static void agePopulation(int[] population) {
        for (int i = 4; i > 0; i--) {
            population[i] = population[i - 1];
        }
        population[0] = 0;
    }

    private static int getTotalPenguins(int[] population) {
        int total = 0;
        for (int penguins : population) {
            total += penguins;
        }
        return total;
    }

    private static void printFinalPopulation(int[] population) {
        String[] ageGroups = {"young", "young adults", "adults", "old adults", "elders"};

        for (int i = 0; i < 5; i++) {
            write("Final number of " + ageGroups[i] + ":");
            write(population[i]);
        }
    }
}