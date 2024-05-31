package fop.intro.pop;

// Reading the content of the file ShutTheBox.java
public class ShutTheBox {
    public static void main(String[] args) {
        boolean[] boxes = new boolean[9]; // Boxes from 1 to 8, index 0 is unused
        for (int i = 1; i <= 8; i++) {
            boxes[i] = true; // All boxes are initially open
        }

        int player1Score = 0;
        int player2Score = 0;

        for (int round = 1; round <= 8; round++) {
            System.out.println("Round " + round);

            // Player 1's turn
            player1Score += playerTurn(boxes, "Player 1");

            if (allBoxesClosed(boxes)) {
                System.out.println("Player 1 shuts all boxes! Player 1 wins!");
                return;
            }

            // Player 2's turn
            player2Score += playerTurn(boxes, "Player 2");

            if (allBoxesClosed(boxes)) {
                System.out.println("Player 2 shuts all boxes! Player 2 wins!");
                return;
            }
        }

        // After all rounds, determine the winner
        System.out.println("Player 1 points: " + player1Score);
        System.out.println("Player 2 points: " + player2Score);

        if (player1Score < player2Score) {
            System.out.println("Player 1 wins!");
        } else if (player2Score < player1Score) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("draw!");
        }
    }

    private static int playerTurn(boolean[] boxes, String playerName) {
        int dice1 = MiniJava.dice();
        int dice2 = MiniJava.dice();
        int sum = dice1 + dice2;

        System.out.println(playerName + " has thrown the numbers:");
        System.out.println(dice1);
        System.out.println(dice2);

        outputBoxes(boxes);

        while (true) {
            int box1 = MiniJava.readInt("Which boxes to shut by " + playerName + "? (0 for no valid combination)\nBox 1:");
            int box2 = MiniJava.readInt("Box 2:");

            if (box1 == 0 || box2 == 0 || (boxes[box1] && boxes[box2] && box1 + box2 == sum)) {
                if (box1 == 0 || box2 == 0) {
                    int score = 0;
                    for (int i = 1; i <= 8; i++) {
                        if (boxes[i]) {
                            score += i;
                        }
                    }
                    System.out.println(playerName + " cannot find a valid combination. Points added: " + score);
                    return score;
                } else {
                    boxes[box1] = false;
                    boxes[box2] = false;
                    break;
                }
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }

        return 0;
    }

    private static boolean allBoxesClosed(boolean[] boxes) {
        for (int i = 1; i <= 8; i++) {
            if (boxes[i]) {
                return false;
            }
        }
        return true;
    }

    private static void outputBoxes(boolean[] boxes) {
        System.out.println("Open boxes:");
        for (int i = 1; i <= 8; i++) {
            System.out.print(i + ":" + boxes[i] + " ");
        }
        System.out.println();
    }
}
