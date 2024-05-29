// Reading the content of the file PickSix.java
import java.util.Scanner;

public class PickSix {

    public static void givePlayerCards(int[][] playerCards) {
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
        }

        if (card % 10 == 0) {
            value += 2;
        }

        if (isRepdigit(card)) {
            value += 5;
        }

        return value;
    }

    private static boolean isRepdigit(int card) {
        String cardStr = String.valueOf(card);
        for (int i = 1; i < cardStr.length(); i++) {
            if (cardStr.charAt(i) != cardStr.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public static int calculatePoints(int[] lostCards) {
        int points = 0;
        for (int card : lostCards) {
            points += getValueOfCard(card);
        }
        return points;
    }

    public static void outputBatch(int[][] batch) {
        for (int i = 0; i < batch.length; i++) {
            System.out.print("Batch " + (i + 1) + ":");
            for (int j = 0; j < batch[i].length; j++) {
                if (batch[i][j] <= 0) {
                    break;
                }
                System.out.print(" " + batch[i][j]);
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
            for (int i = 0; i < playerCards.length; i++) {
                for (int j = 0; j < playerCards[i].length; j++) {
                    if (playerCards[i][j] != 0) {
                        System.out.print(playerCards[i][j] + " ");
                    }
                }
            }
            System.out.println("\nWhich card do you want to play?");
            int selectedCard = scanner.nextInt();

            for (int i = 0; i < playerCards.length; i++) {
                for (int j = 0; j < playerCards[i].length; j++) {
                    if (playerCards[i][j] == selectedCard) {
                        playerCards[i][j] = 0;
                        return selectedCard;
                    }
                }
            }

            System.out.println("Invalid selection. Please choose a card you have.");
        }
    }

    public static void main(String[] args) {
        int[][] player1Cards = new int[1][10];
        int[][] player2Cards = new int[1][10];

        givePlayerCards(player1Cards);
        givePlayerCards(player2Cards);

        int[][] batches = new int[4][5];
        int[] player1LostCards = new int[50];
        int[] player2LostCards = new int[50];

        int player1LostCount = 0;
        int player2LostCount = 0;

        for (int round = 0; round < 10; round++) {
            int player1Card = playerSelectCard(1, player1Cards);
            int player2Card = playerSelectCard(2, player2Cards);

            int[] playedCards = {player1Card, player2Card};
            java.util.Arrays.sort(playedCards);

            for (int playedCard : playedCards) {
                boolean placed = false;
                for (int i = 0; i < batches.length; i++) {
                    if (batches[i][4] != 0) {
                        continue;
                    }
                    if (batches[i][0] == 0 || batches[i][4] == 0 && batches[i][batches[i].length - 1] < playedCard) {
                        for (int j = 0; j < batches[i].length; j++) {
                            if (batches[i][j] == 0) {
                                batches[i][j] = playedCard;
                                placed = true;
                                break;
                            }
                        }
                    }
                    if (placed) {
                        break;
                    }
                }
                if (!placed) {
                    int playerLostCount = playedCard == player1Card ? player1LostCount : player2LostCount;
                    int[] playerLostCards = playedCard == player1Card ? player1LostCards : player2LostCards;

                    for (int i = 0; i < batches[0].length; i++) {
                        if (batches[0][i] != 0) {
                            playerLostCards[playerLostCount++] = batches[0][i];
                        }
                    }
                    playerLostCards[playerLostCount++] = playedCard;

                    for (int i = 0; i < batches[0].length; i++) {
                        batches[0][i] = 0;
                    }
                    batches[0][0] = playedCard;
                }
            }

            outputBatch(batches);
        }

        int player1Points = calculatePoints(player1LostCards);
        int player2Points = calculatePoints(player2LostCards);

        int[] playerPoints = {player1Points, player2Points};
        outputResult(playerPoints);
    }
}
