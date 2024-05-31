package GPT.St4.HW1;

public class RescueThePenguins {
    public static void main(String[] args) {
        int steps = MiniJava.readInt("Please insert the number of time steps (>= 1): ");
        if (steps < 1) {
            MiniJava.write("Number of steps >= 1 required");
            return;
        }

        int young = MiniJava.readInt("Initial number of young: ");
        int youngAdults = MiniJava.readInt("Initial number of young adults: ");
        int adults = MiniJava.readInt("Initial number of adults: ");
        int oldAdults = MiniJava.readInt("Initial number of old adults: ");
        int elders = MiniJava.readInt("Initial number of elders: ");

        if (young < 0 || youngAdults < 0 || adults < 0 || oldAdults < 0 || elders < 0) {
            MiniJava.write("Negative input not accepted");
            return;
        }

        for (int step = 0; step < steps; step++) {
            int food = youngAdults * 3 + adults * 2;

            int totalPenguins = young + youngAdults + adults + oldAdults + elders;
            int requiredFood = totalPenguins;
            if (food < requiredFood) {
                int shortage = requiredFood - food;
                while (shortage > 0 && totalPenguins > 0) {
                    if (elders > 0) {
                        elders--;
                    } else if (oldAdults > 0) {
                        oldAdults--;
                    } else if (adults > 0) {
                        adults--;
                    } else if (youngAdults > 0) {
                        youngAdults--;
                    } else if (young > 0) {
                        young--;
                    }
                    totalPenguins--;
                    shortage--;
                }
            }

            int newYoung = (youngAdults / 4) + (adults / 2) + (oldAdults / 3);
            young += newYoung;

            elders = oldAdults;
            oldAdults = adults;
            adults = youngAdults;
            youngAdults = young;
            young = 0;
        }

        MiniJava.write("Final number of young: " + young);
        MiniJava.write("Final number of young adults: " + youngAdults);
        MiniJava.write("Final number of adults: " + adults);
        MiniJava.write("Final number of old adults: " + oldAdults);
        MiniJava.write("Final number of elders: " + elders);
    }
}
