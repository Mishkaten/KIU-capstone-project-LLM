package GPT.St2.HW5;

import java.util.Scanner;

public class PickSix {

    public static void main(String[] args) throws IllegalAccessException {
        int[][] player1Cards = new int[1][10];
        int[][] player2Cards = new int[1][10];

        givePlayerCards(player1Cards);
        givePlayerCards(player2Cards);

        int[][] batches = new int[4][5];

        for (int round = 0; round < 10; round++) {
            int card1 = playerSelectCard(1, player1Cards);
            placeCardInBatch(card1, batches, player1Cards[0]);

            int card2 = playerSelectCard(2, player2Cards);
            placeCardInBatch(card2, batches, player2Cards[0]);

            outputBatch(batches);
        }

        int player1Points = calculatePoints(player1Cards[0]);
        int player2Points = calculatePoints(player2Cards[0]);

        outputResult(new int[]{player1Points, player2Points});
    }

    public static void givePlayerCards(int[][] playerCards) throws IllegalAccessException {
        for (int i = 0; i < playerCards.length; i++) {
            for (int j = 0; j < playerCards[i].length; j++) {
                playerCards[i][j] = MiniJava.drawCard();
            }
        }
    }

    public static int getValueOfCard(int card) {
        if (card == 0) {
            return 0;
        }

        int value = 1;

        if (card % 10 == 5) {
            value += 1;
        } else if (card % 10 == 0) {
            value += 2;
        }

        String cardStr = String.valueOf(card);
        boolean isRepdigit = true;
        for (int i = 1; i < cardStr.length(); i++) {
            if (cardStr.charAt(i) != cardStr.charAt(0)) {
                isRepdigit = false;
                break;
            }
        }

        if (isRepdigit && cardStr.length() > 1) {
            value += 5;
        }

        return value;
    }

    public static int calculatePoints(int[] lostCards) {
        int totalPoints = 0;
        for (int card : lostCards) {
            totalPoints += getValueOfCard(card);
        }
        return totalPoints;
    }

    public static void outputBatch(int[][] batch) {
        for (int i = 0; i < batch.length; i++) {
            System.out.print("Batch " + (i + 1) + ": ");
            for (int j = 0; j < batch[i].length; j++) {
                if (batch[i][j] <= 0) {
                    break;
                }
                System.out.print(batch[i][j] + " ");
            }
            System.out.println();
        }
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

    public static int playerSelectCard(int player, int[][] playerCards) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Player " + player + ", you have the following cards: ");
            for (int card : playerCards[player - 1]) {
                System.out.print(card + " ");
            }
            System.out.println();
            System.out.println("Which card do you want to play?");
            int selectedCard = scanner.nextInt();
            for (int i = 0; i < playerCards[player - 1].length; i++) {
                if (playerCards[player - 1][i] == selectedCard) {
                    playerCards[player - 1][i] = 0; // Set the card to 0 after playing it
                    return selectedCard;
                }
            }
        }
    }

    public static void placeCardInBatch(int card, int[][] batches, int[] playerCards) {
        int bestBatchIndex = -1;
        int minDifference = Integer.MAX_VALUE;

        for (int i = 0; i < batches.length; i++) {
            if (batches[i][4] != 0) {
                continue;
            }
            for (int j = 0; j < 5; j++) {
                if (batches[i][j] == 0) {
                    if (j == 0 || card > batches[i][j - 1]) {
                        int difference = (j == 0) ? 0 : card - batches[i][j - 1];
                        if (difference < minDifference) {
                            bestBatchIndex = i;
                            minDifference = difference;
                        }
                    }
                    break;
                }
            }
        }

        if (bestBatchIndex != -1) {
            for (int j = 0; j < 5; j++) {
                if (batches[bestBatchIndex][j] == 0) {
                    batches[bestBatchIndex][j] = card;
                    return;
                }
            }
        }

        for (int i = 0; i < batches.length; i++) {
            if (batches[i][4] != 0) {
                for (int j = 0; j < 5; j++) {
                    playerCards[j] = batches[i][j];
                    batches[i][j] = 0;
                }
                batches[i][0] = card;
                return;
            }
        }

        for (int i = 0; i < 5; i++) {
            playerCards[i] = batches[0][i];
            batches[0][i] = 0;
        }
        batches[0][0] = card;
    }
}
