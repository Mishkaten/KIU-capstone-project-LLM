package St1.HW5;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // Set up the game scenario
        int[][] playerCards = {
                {3, 5, 7, 9, 11, 13, 15, 17, 19, 21},
                {4, 6, 8, 10, 12, 14, 16, 18, 20, 22}
        };
        int[] playerPoints = new int[2];
        int[][] batches = new int[4][5]; // 4 batches, max 5 cards each
        int[] batchSizes = new int[4]; // To track the number of cards in each batch

        // Game preparation
        givePlayerCards(playerCards);
        for (int i = 0; i < 4; i++) {
            batches[i][0] = drawCard(i);
            batchSizes[i] = 1;
        }

        // Game loop
        for (int round = 1; round <= 10; round++) {
            for (int player = 0; player < 2; player++) {
                outputStapel(batches);
                int card = playerSelectCard(player + 1, playerCards);
                int batchIndex = findBatchIndex(card, batches, batchSizes);

                if (batchIndex == -1) { // Card doesn't fit anywhere
                    playerPoints[player] += calculatePoints(batches[0]);
                    batches[0] = sort(returnCardsToPlayer(batches[0], batchSizes[0], playerCards[player]));
                    batchSizes[0] = batches[0].length;
                    batches[0][0] = card;
                } else {
                    if (batchSizes[batchIndex] == 5) { // Batch is full
                        playerPoints[player] += calculatePoints(batches[batchIndex]);
                        batches[batchIndex] = sort(returnCardsToPlayer(batches[batchIndex], batchSizes[batchIndex], playerCards[player]));
                        batchSizes[batchIndex] = batches[batchIndex].length;
                    }
                    batches[batchIndex][batchSizes[batchIndex]++] = card; // Add card to batch
                    batches[batchIndex] = sort(batches[batchIndex]); // Sort the batch
                }
            }
        }
        outputResult(playerPoints);
    }

    public static int[] sort(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            int j = 0;
            while (j < i && a[i] > b[j]) ++j;
            for (int k = i - 1; k >= j; --k) b[k + 1] = b[k];
            b[j] = a[i];
        }
        return b;
    }

    // Simulate drawing a card
    private static int drawCard(int index) {
        return index + 1;
    }

    // Simulate player selecting a card
    private static int playerSelectCard(int player, int[][] playerCards) {
        return playerCards[player - 1][0]; // Always select the first card for simplicity
    }

    // Helper method to find the appropriate batch for a card
    private static int findBatchIndex(int card, int[][] batches, int[] batchSizes) {
        int bestIndex = -1;
        int minDifference = Integer.MAX_VALUE;
        for (int i = 0; i < batches.length; i++) {
            if (batchSizes[i] < 5) {
                int diff = card - batches[i][batchSizes[i] - 1];
                if (diff > 0 && diff < minDifference) {
                    minDifference = diff;
                    bestIndex = i;
                }
            }
        }
        return bestIndex;
    }

    // Helper method to return cards from a full batch to a player
    private static int[] returnCardsToPlayer(int[] batch, int batchSize, int[] playerCards) {
        int[] newPlayerCards = new int[playerCards.length + batchSize - 1];
        System.arraycopy(playerCards, 0, newPlayerCards, 0, playerCards.length);
        System.arraycopy(batch, 1, newPlayerCards, playerCards.length, batchSize - 1);
        return newPlayerCards;
    }

    // Simulate giving player cards
    private static void givePlayerCards(int[][] playerCards) {
        // Assuming playerCards are already provided in the main method
    }

    // Simulate outputting batches to the console
    private static void outputStapel(int[][] batches) {
        for (int i = 0; i < batches.length; i++) {
            System.out.print("Batch " + (i + 1) + ": ");
            for (int j = 0; j < batches[i].length; j++) {
                System.out.print(batches[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Calculate points for a batch (assuming each card is worth its value)
    private static int calculatePoints(int[] batch) {
        int points = 0;
        for (int card : batch) {
            points += card;
        }
        return points;
    }

    // Output the final result
    private static void outputResult(int[] playerPoints) {
        System.out.println("Player 1 points: " + playerPoints[0]);
        System.out.println("Player 2 points: " + playerPoints[1]);
    }
}
