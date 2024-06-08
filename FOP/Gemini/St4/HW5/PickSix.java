package St4.HW5;

public class PickSix extends MiniJava {
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
    public static void main(String[] args) throws IllegalAccessException {
        //Prepare the game
        int[][] playerCards = new int[2][10];
        givePlayerCards(playerCards);

        int[][] batches = new int[4][5];

        //Main game loop
        for(int round = 0; round < 10; round++) {
            // Each player selects a card and the selected card is removed from their hand (set to 0).
            int card1 = playerSelectCard(1, playerCards);
            int card2 = playerSelectCard(2, playerCards);

            // Determine which player goes first based on the card value.
            int firstPlayer = (card1 < card2) ? 1 : 2;
            int secondPlayer = (firstPlayer == 1) ? 2 : 1;

            // First player plays their card
            playCard(firstPlayer, card1, batches, playerCards);
            // Second player plays their card
            playCard(secondPlayer, card2, batches, playerCards);

            outputStapel(batches);
        }

        //Calculate and output the result
        int[] playerPoints = new int[2];
        for (int i = 0; i < 2; i++) {
            playerPoints[i] = calculatePoints(playerCards[i]);
        }

        outputResult(playerPoints);
    }

    /**
     *  Plays the card onto the appropriate batch, potentially returning cards to the player
     *  or taking cards from the first batch.
     */
    public static void playCard(int player, int card, int[][] batches, int[][] playerCards){
        int targetBatch = -1;
        int minDiff = Integer.MAX_VALUE;

        // Find the batch with the closest top card
        for (int i = 0; i < batches.length; i++) {
            for (int j = 0; j < batches[i].length; j++) {
                if (batches[i][j] == 0) { // Empty slot found, this is the target batch
                    targetBatch = i;
                    minDiff = 0;
                    break; // Stop searching this batch
                } else if (card > batches[i][j] && card - batches[i][j] < minDiff) {
                    targetBatch = i;
                    minDiff = card - batches[i][j];
                }
            }
            if (minDiff == 0) break; // No need to search further if an empty slot was found
        }

        if (targetBatch == -1) { // Card is too small for any batch
            returnCardsToPlayer(player, batches[0], playerCards);
            batches[0][0] = card;
        } else {
            if (batches[targetBatch][4] != 0) { // Batch is full
                returnCardsToPlayer(player, batches[targetBatch], playerCards);
            }

            for (int j = 0; j < batches[targetBatch].length; j++) {
                if (batches[targetBatch][j] == 0) {
                    batches[targetBatch][j] = card;
                    break;
                }
            }
        }
    }


    public static void outputStapel(int[][] stapel) {
        for (int i = 0; i < stapel.length; i++) {
            if (stapel[i][0] <= 0) break;

            System.out.print("Batch " + (i + 1) + ": ");
            for (int j = 0; j < stapel[i].length && stapel[i][j] > 0; j++) {
                System.out.print(stapel[i][j] + " ");
            }
            System.out.println();
        }
    }



    public static int playerSelectCard(int player, int[][] playerCards) {
        while (true) {
            System.out.println("Player " + player + ", you have the following cards:");
            for (int card : playerCards[player - 1]) {
                if (card != 0) {
                    System.out.print(card + " ");
                }
            }
            System.out.println();

            System.out.println("Which card do you want to play?");
            int card = readInt();

            for (int i = 0; i < playerCards[player - 1].length; i++) {
                if (playerCards[player - 1][i] == card) {
                    playerCards[player - 1][i] = 0;
                    return card;
                }
            }
            System.out.println("Invalid card. Please choose a card from your hand.");
        }
    }



    public static int calculatePoints(int[] lostCards) {
        int points = 0;
        for (int card : lostCards) {
            points += getValueOfCard(card);
        }
        return points;
    }

    public static void outputResult(int[] playerPoints) {
        if (playerPoints[0] < playerPoints[1]) {
            System.out.println("Player 1 wins with " + playerPoints[0] + " points against player 2 with " + playerPoints[1] + " points.");
        } else if (playerPoints[1] < playerPoints[0]) {
            System.out.println("Player 2 wins with " + playerPoints[1] + " points against player 1 with " + playerPoints[0] + " points.");
        } else {
            System.out.println("Draw! Both players have " + playerPoints[0] + " points.");
        }
    }



    public static int getValueOfCard(int card) {
        if (card == 0) return 0;

        int value = 1;
        if (card % 10 == 5) value++;
        if (card % 10 == 0) value += 2;
        if (card >= 11 && card % 11 == 0) value += 5; // Check for repdigits
        return value;
    }

    public static void givePlayerCards(int[][] playerCards) throws IllegalAccessException {
        for (int i = 0; i < playerCards.length; i++) {
            for (int j = 0; j < playerCards[i].length; j++) {
                playerCards[i][j] = drawCard();
            }
        }
    }

    public static void returnCardsToPlayer(int player, int[] batchCards, int[][] playerCards) {
        // Find the first empty slot in the player's hand
        int emptySlot = 0;
        while (playerCards[player - 1][emptySlot] != 0) {
            emptySlot++;
        }

        // Copy cards from the batch to the player's hand
        for (int card : batchCards) {
            if (card != 0) {
                playerCards[player - 1][emptySlot++] = card;
            }
        }

        // Sort the player's cards
        playerCards[player - 1] = sort(playerCards[player - 1]);

        // Clear the batch
        for (int i = 0; i < batchCards.length; i++) {
            batchCards[i] = 0;
        }
    }
}
