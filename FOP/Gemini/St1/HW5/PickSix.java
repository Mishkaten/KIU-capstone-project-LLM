package St1.HW5;

import java.util.Arrays;

public class PickSix extends MiniJava {

    public static void main(String[] args) throws IllegalAccessException {
        int[][] playerCards = new int[2][10];
        int[] playerPoints = new int[2];
        int[][] batches = new int[4][5]; // 4 batches, max 5 cards each
        int[] batchSizes = new int[4]; // To track the number of cards in each batch

        // Game preparation
        givePlayerCards(playerCards);
        for (int i = 0; i < 4; i++) {
            batches[i][0] = drawCard();
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
            // begin of insert
            int j = 0;
            while (j < i && a[i] > b[j]) ++j;
            // end of locate
            for (int k = i - 1; k >= j; --k) b[k + 1] = b[k];
            // end of shift
            b[j] = a[i];
            // end of insert
        }
        return b;
    } // end of sort

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
}
