package Claude.St4.HW5;

public class PickSix extends MiniJava {
    // sorting method from the lecture
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
        int[][] playerCards = new int[2][10];
        int[][] stapel = new int[4][5];

        givePlayerCards(playerCards);

        // Initialize the batches with one card each
        for (int i = 0; i < 4; i++) {
            stapel[i][0] = drawCard();
        }

        for (int round = 0; round < 10; round++) {
            int card1 = playerSelectCard(1, playerCards);
            int card2 = playerSelectCard(2, playerCards);

            // Distribute the played cards to the batches
            for (int i = 0; i < 4; i++) {
                if (card1 > 0) {
                    card1 = distributeCard(card1, stapel[i], playerCards[0]);
                }
                if (card2 > 0) {
                    card2 = distributeCard(card2, stapel[i], playerCards[1]);
                }
            }

            outputStapel(stapel);
        }

        int[] playerPoints = new int[2];
        playerPoints[0] = calculatePoints(playerCards[0]);
        playerPoints[1] = calculatePoints(playerCards[1]);

        outputResult(playerPoints);
    }

    public static void outputStapel(int[][] stapel) {
        for (int i = 0; i < stapel.length; i++) {
            writeConsole("Batch " + (i + 1) + ": ");
            for (int j = 0; j < stapel[i].length && stapel[i][j] > 0; j++) {
                writeConsole(stapel[i][j] + " ");
            }
            writeLineConsole();
        }
    }

    public static int playerSelectCard(int player, int[][] playerCards) {
        int selectedCard;
        do {
            writeConsole("Player " + player + ", you have the following cards: ");
            for (int card : playerCards[player - 1]) {
                if (card > 0) {
                    writeConsole(card + " ");
                }
            }
            writeLineConsole("\nWhich card do you want to play?");
            selectedCard = readInt();
        } while (!hasCard(selectedCard, playerCards[player - 1]));

        // Remove the selected card from the player's hand
        for (int i = 0; i < playerCards[player - 1].length; i++) {
            if (playerCards[player - 1][i] == selectedCard) {
                playerCards[player - 1][i] = 0;
                break;
            }
        }
        return selectedCard;
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
            writeLineConsole("Player 1 wins with " + playerPoints[0] + " points against player 2 with " + playerPoints[1] + " points.");
        } else if (playerPoints[1] < playerPoints[0]) {
            writeLineConsole("Player 2 wins with " + playerPoints[1] + " points against player 1 with " + playerPoints[0] + " points.");
        } else {
            writeLineConsole("Draw! Both players have " + playerPoints[0] + " points.");
        }
    }

    public static int getValueOfCard(int card) {
        if (card == 0) {
            return 0;
        }
        int value = 1;
        if (card % 10 == 5) {
            value++;
        }
        if (card % 10 == 0) {
            value += 2;
        }
        if (isRepdigit(card)) {
            value += 5;
        }
        return value;
    }

    public static void givePlayerCards(int[][] playerCards) throws IllegalAccessException {
        for (int i = 0; i < playerCards.length; i++) {
            for (int j = 0; j < playerCards[i].length; j++) {
                playerCards[i][j] = drawCard();
            }
        }
    }

    public static int distributeCard(int card, int[] batch, int[] playerCards) {
        for (int i = 0; i < batch.length; i++) {
            if (batch[i] == 0 || card < batch[i]) {
                if (i == 0) {
                    // The card is too small for the first batch
                    addCardsToPlayer(batch, playerCards);
                    batch[0] = card;
                } else {
                    // Insert the card into the batch
                    for (int j = batch.length - 1; j > i; j--) {
                        batch[j] = batch[j - 1];
                    }
                    batch[i] = card;
                    if (i == 4) {
                        // The batch is full, return the cards to the player
                        addCardsToPlayer(batch, playerCards);
                        batch[0] = card;
                        for (int j = 1; j < batch.length; j++) {
                            batch[j] = 0;
                        }
                    }
                }
                return 0;
            }
        }
        return card;
    }

    public static void addCardsToPlayer(int[] batch, int[] playerCards) {
        for (int i = 0; i < playerCards.length; i++) {
            if (playerCards[i] == 0) {
                playerCards[i] = batch[0];
                for (int j = 1; j < batch.length; j++) {
                    batch[j - 1] = batch[j];
                }
                batch[batch.length - 1] = 0;
            }
        }
    }

    public static boolean hasCard(int card, int[] playerCards) {
        for (int c : playerCards) {
            if (c == card) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRepdigit(int num) {
        if (num < 10) {
            return false;
        }
        int lastDigit = num % 10;
        while (num > 0) {
            if (num % 10 != lastDigit) {
                return false;
            }
            num /= 10;
        }
        return true;
    }
}