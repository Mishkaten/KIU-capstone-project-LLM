import java.util.Scanner;

public class PickSix {
    public static void main(String[] args) {
        int[][] player1Cards = new int[1][10];
        int[][] player2Cards = new int[1][10];

        givePlayerCards(player1Cards);
        givePlayerCards(player2Cards);

        int[][] batches = new int[4][5];

        for (int round = 0; round < 10; round++) {
            int player1Card = playerSelectCard(1, player1Cards);
            int player2Card = playerSelectCard(2, player2Cards);

            // Game logic for distributing cards into batches would go here
            // This involves checking the rules specified in the prompt
            // Example rules for inserting cards into batches, etc.
        }

        int[] playerPoints = new int[2];
        playerPoints[0] = calculatePoints(player1Cards[0]);
        playerPoints[1] = calculatePoints(player2Cards[0]);

        outputResult(playerPoints);
    }

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
        String cardStr = Integer.toString(card);

        if (cardStr.endsWith("5")) {
            value += 1;
        }

        if (cardStr.endsWith("0")) {
            value += 2;
        }

        if (cardStr.matches("(\\d)\\1+")) {
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
        } else if (playerPoints[0] > playerPoints[1]) {
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
                if (card != 0) {
                    System.out.print(card + " ");
                }
            }
            System.out.println();
            System.out.println("Which card do you want to play?");
            System.out.print(">");
            int selectedCard = scanner.nextInt();

            for (int i = 0; i < playerCards[player - 1].length; i++) {
                if (playerCards[player - 1][i] == selectedCard) {
                    playerCards[player - 1][i] = 0;
                    return selectedCard;
                }
            }
            System.out.println("Invalid card. Please select a card you have.");
        }
    }
}
