package Claude.St3.HW5;

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

        // Preparation of the Play
        givePlayerCards(playerCards);
        for (int i = 0; i < 4; i++) {
            stapel[i][0] = drawCard();
        }

        // Loop of the Play
        for (int round = 0; round < 10; round++) {
            int playedCard1 = playerSelectCard(1, playerCards);
            int playedCard2 = playerSelectCard(2, playerCards);

            // Distribute the played cards over the batches
            distributeCard(playedCard1, stapel, playerCards[0]);
            distributeCard(playedCard2, stapel, playerCards[1]);

            outputStapel(stapel);
        }

        // End of a Play
        int[] playerPoints = new int[2];
        playerPoints[0] = calculatePoints(playerCards[0]);
        playerPoints[1] = calculatePoints(playerCards[1]);
        outputResult(playerPoints);
    }

    public static void outputStapel(int[][] stapel) {
        for (int i = 0; i < stapel.length; i++) {
            System.out.print("Batch " + (i + 1) + ": ");
            for (int j = 0; j < stapel[i].length; j++) {
                if (stapel[i][j] <= 0) {
                    break;
                }
                System.out.print(stapel[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int playerSelectCard(int player, int[][] playerCards) {
        int selectedCard;
        do {
            System.out.println("Player " + player + ", you have the following cards: " + arrayToString(playerCards[player - 1]));
            System.out.println("Which card do you want to play?");
            selectedCard = readInt();
        } while (!contains(playerCards[player - 1], selectedCard));

        // Set the selected card to 0 in the player's hand
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
            System.out.println("Player 1 wins with " + playerPoints[0] + " points against player 2 with " + playerPoints[1] + " points.");
        } else if (playerPoints[1] < playerPoints[0]) {
            System.out.println("Player 2 wins with " + playerPoints[1] + " points against player 1 with " + playerPoints[0] + " points.");
        } else {
            System.out.println("Draw! Both players have " + playerPoints[0] + " points.");
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

    public static void distributeCard(int card, int[][] stapel, int[] playerCards) {
        int batchIndex = findBatchIndex(card, stapel);
        if (batchIndex == -1) {
            // Card is too small, take all cards from the first batch
            for (int i = 0; i < stapel[0].length; i++) {
                if (stapel[0][i] != 0) {
                    addCardToPlayer(stapel[0][i], playerCards);
                    stapel[0][i] = 0;
                }
            }
            stapel[0][0] = card;
        } else {
            int emptySlot = findEmptySlot(stapel[batchIndex]);
            if (emptySlot == -1) {
                // Batch is full, take all cards from the batch
                for (int i = 0; i < stapel[batchIndex].length; i++) {
                    addCardToPlayer(stapel[batchIndex][i], playerCards);
                    stapel[batchIndex][i] = 0;
                }
                stapel[batchIndex][0] = card;
            } else {
                stapel[batchIndex][emptySlot] = card;
            }
        }
    }

    public static int findBatchIndex(int card, int[][] stapel) {
        int minDiff = Integer.MAX_VALUE;
        int batchIndex = -1;
        for (int i = 0; i < stapel.length; i++) {
            int lastCard = stapel[i][findLastNonZero(stapel[i])];
            if (card > lastCard) {
                int diff = card - lastCard;
                if (diff < minDiff) {
                    minDiff = diff;
                    batchIndex = i;
                }
            }
        }
        return batchIndex;
    }

    public static int findEmptySlot(int[] batch) {
        for (int i = 0; i < batch.length; i++) {
            if (batch[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int findLastNonZero(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                return i;
            }
        }
        return -1;
    }

    public static void addCardToPlayer(int card, int[] playerCards) {
        for (int i = 0; i < playerCards.length; i++) {
            if (playerCards[i] == 0) {
                playerCards[i] = card;
                break;
            }
        }
    }

    public static boolean contains(int[] arr, int num) {
        for (int i : arr) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRepdigit(int num) {
        String str = String.valueOf(num);
        if (str.length() < 2) {
            return false;
        }
        char digit = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != digit) {
                return false;
            }
        }
        return true;
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            if (i != 0) {
                sb.append(i).append(" ");
            }
        }
        return sb.toString().trim();
    }
}