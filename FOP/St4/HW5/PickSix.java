public class PickSix {

    public static void main(String[] args) throws IllegalAccessException {
        int[][] player1Cards = new int[1][10];
        int[][] player2Cards = new int[1][10];

        givePlayerCards(player1Cards);
        givePlayerCards(player2Cards);

        int[][] batches = new int[4][5];

        for (int round = 0; round < 10; round++) {
            int player1Card = playerSelectCard(1, player1Cards);
            int player2Card = playerSelectCard(2, player2Cards);

            assignCardToBatch(batches, player1Card, player1Cards[0]);
            assignCardToBatch(batches, player2Card, player2Cards[0]);

            outputStapel(batches);
        }

        int player1Points = calculatePoints(player1Cards[0]);
        int player2Points = calculatePoints(player2Cards[0]);

        outputResult(new int[]{player1Points, player2Points});
    }

    public static void outputStapel(int[][] stapel) {
        for (int i = 0; i < stapel.length; i++) {
            System.out.print("Batch " + (i + 1) + ": ");
            for (int j = 0; j < stapel[i].length && stapel[i][j] > 0; j++) {
                System.out.print(stapel[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int playerSelectCard(int player, int[][] playerCards) {
        while (true) {
            MiniJava.printLine("Player " + player + ", you have the following cards: ");
            for (int card : playerCards[0]) {
                if (card > 0) {
                    MiniJava.printLine(card + " ");
                }
            }
            int selectedCard = MiniJava.readInt("Which card do you want to play?\n>");
            for (int i = 0; i < playerCards[0].length; i++) {
                if (playerCards[0][i] == selectedCard) {
                    playerCards[0][i] = 0;
                    return selectedCard;
                }
            }
            MiniJava.printLine("Invalid card selection. Try again.");
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
            MiniJava.printLine("Player 1 wins with " + playerPoints[0] + " points against player 2 with " + playerPoints[1] + " points.");
        } else if (playerPoints[1] < playerPoints[0]) {
            MiniJava.printLine("Player 2 wins with " + playerPoints[1] + " points against player 1 with " + playerPoints[0] + " points.");
        } else {
            MiniJava.printLine("Draw! Both players have " + playerPoints[0] + " points.");
        }
    }

    public static int getValueOfCard(int card) {
        if (card == 0) return 0;
        int value = 1;
        if (card % 10 == 5) value++;
        if (card % 10 == 0) value += 2;
        String cardStr = String.valueOf(card);
        if (cardStr.length() > 1 && cardStr.chars().distinct().count() == 1) value += 5;
        return value;
    }

    public static void givePlayerCards(int[][] playerCards) throws IllegalAccessException {
        for (int i = 0; i < playerCards.length; i++) {
            for (int j = 0; j < playerCards[i].length; j++) {
                playerCards[i][j] = MiniJava.drawCard();
            }
        }
    }

    private static void assignCardToBatch(int[][] batches, int card, int[] lostCards) {
        int minDifference = Integer.MAX_VALUE;
        int batchIndex = -1;
        for (int i = 0; i < batches.length; i++) {
            if (batches[i][0] == 0) {
                batches[i][0] = card;
                return;
            }
            int topCard = batches[i][batches[i].length - 1];
            if (topCard == 0) {
                for (int j = 0; j < batches[i].length; j++) {
                    if (batches[i][j] == 0) {
                        topCard = batches[i][j - 1];
                        break;
                    }
                }
            }
            if (topCard != 0 && card > topCard) {
                int difference = card - topCard;
                if (difference < minDifference) {
                    minDifference = difference;
                    batchIndex = i;
                }
            }
        }
        if (batchIndex != -1) {
            for (int j = 0; j < batches[batchIndex].length; j++) {
                if (batches[batchIndex][j] == 0) {
                    batches[batchIndex][j] = card;
                    return;
                }
            }
        }
        for (int j = 0; j < lostCards.length; j++) {
            if (lostCards[j] == 0) {
                lostCards[j] = card;
                return;
            }
        }
    }
}
