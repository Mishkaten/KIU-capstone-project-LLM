package Claude.St1.HW5;

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

        for (int i = 0; i < 4; i++) {
            stapel[i][0] = drawCard();
        }

        int[] lostCardsPlayer1 = new int[0];
        int[] lostCardsPlayer2 = new int[0];

        for (int round = 0; round < 10; round++) {
            outputStapel(stapel);

            int cardPlayer1 = playerSelectCard(1, playerCards);
            int cardPlayer2 = playerSelectCard(2, playerCards);

            int batchIndex = -1;
            int minDiff = Integer.MAX_VALUE;

            for (int i = 0; i < 4; i++) {
                int lastCard = 0;
                for (int j = 0; j < 5; j++) {
                    if (stapel[i][j] > 0) {
                        lastCard = stapel[i][j];
                    } else {
                        break;
                    }
                }
                int diff1 = cardPlayer1 - lastCard;
                int diff2 = cardPlayer2 - lastCard;
                if (diff1 >= 0 && diff1 < minDiff) {
                    minDiff = diff1;
                    batchIndex = i;
                }
                if (diff2 >= 0 && diff2 < minDiff) {
                    minDiff = diff2;
                    batchIndex = i;
                }
            }

            if (batchIndex == -1) {
                lostCardsPlayer1 = addToArray(lostCardsPlayer1, cardPlayer1);
                lostCardsPlayer2 = addToArray(lostCardsPlayer2, cardPlayer2);
                lostCardsPlayer1 = concatenateArrays(lostCardsPlayer1, stapel[0]);
                lostCardsPlayer2 = concatenateArrays(lostCardsPlayer2, stapel[0]);
                stapel[0] = new int[5];
                stapel[0][0] = cardPlayer1;
                stapel[0][1] = cardPlayer2;
            } else {
                int lastIndex = -1;
                for (int i = 0; i < 5; i++) {
                    if (stapel[batchIndex][i] == 0) {
                        lastIndex = i;
                        break;
                    }
                }
                if (lastIndex == 4) {
                    if (cardPlayer1 < cardPlayer2) {
                        lostCardsPlayer1 = concatenateArrays(lostCardsPlayer1, stapel[batchIndex]);
                        stapel[batchIndex] = new int[5];
                        stapel[batchIndex][0] = cardPlayer1;
                    } else {
                        lostCardsPlayer2 = concatenateArrays(lostCardsPlayer2, stapel[batchIndex]);
                        stapel[batchIndex] = new int[5];
                        stapel[batchIndex][0] = cardPlayer2;
                    }
                } else {
                    stapel[batchIndex][lastIndex] = cardPlayer1;
                    stapel[batchIndex][lastIndex + 1] = cardPlayer2;
                }
            }
        }

        int pointsPlayer1 = calculatePoints(lostCardsPlayer1);
        int pointsPlayer2 = calculatePoints(lostCardsPlayer2);

        outputResult(new int[]{pointsPlayer1, pointsPlayer2});
    }

    public static void outputStapel(int[][] stapel) {
        for (int i = 0; i < 4; i++) {
            System.out.print("Batch " + (i + 1) + ": ");
            for (int j = 0; j < 5; j++) {
                if (stapel[i][j] > 0) {
                    System.out.print(stapel[i][j] + " ");
                } else {
                    break;
                }
            }
            System.out.println();
        }
    }

    public static int playerSelectCard(int player, int[][] playerCards) {
        int[] cards = playerCards[player - 1];
        int selectedCard = 0;
        while (selectedCard == 0) {
            System.out.print("Player " + player + ", you have the following cards: ");
            for (int card : cards) {
                if (card != 0) {
                    System.out.print(card + " ");
                }
            }
            System.out.println("\nWhich card do you want to play?");
            selectedCard = readInt();
            boolean hasCard = false;
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] == selectedCard) {
                    hasCard = true;
                    cards[i] = 0;
                    break;
                }
            }
            if (!hasCard) {
                selectedCard = 0;
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
        if (card > 10 && card % 11 == 0) {
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

    public static int[] addToArray(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = value;
        return newArray;
    }

    public static int[] concatenateArrays(int[] array1, int[] array2) {
        int[] newArray = new int[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            newArray[i] = array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            newArray[array1.length + i] = array2[i];
        }
        return newArray;
    }
}