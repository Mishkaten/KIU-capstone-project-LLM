package St1.HW3;

import java.util.Arrays;

public class ShutTheBox extends MiniJava {
    public static void main(String[] args) throws IllegalAccessException {

        boolean[] boxes = new boolean[8];
        Arrays.fill(boxes, true); // All boxes start open

        int player1Score = 0, player2Score = 0;
        boolean player1Turn = true;
        boolean gameOver = false;

        for (int round = 1; round <= 8 && !gameOver; round++) {
            for (int player = 1; player <= 2 && !gameOver; player++) {
                write("Player " + player + " has thrown the numbers:");
                int roll1 = dice();
                int roll2 = dice();
                write(roll1);
                write(roll2);

                outputBoxes(boxes);

                int targetSum = roll1 + roll2;
                int box1 = -1, box2 = -1;

                while (!gameOver && !isValidChoice(box1, box2, targetSum, boxes)) {
                    write("Which boxes to shut by player " + player + "? (0 for no valid combination)");
                    write("Box 1:");
                    box1 = readInt() - 1; // Adjust for 0-based indexing
                    if (box1 == -1) { // Player gives up
                        break;
                    }
                    write("Box 2:");
                    box2 = readInt() - 1;
                    if (box2 == -1) {
                        break;
                    }

                    if (!isValidChoice(box1, box2, targetSum, boxes)) {
                        write("Invalid choice. Please try again.");
                    } else {
                        boxes[box1] = boxes[box2] = false; // Shut the boxes
                        if (areAllBoxesShut(boxes)) {
                            gameOver = true;
                            write("Player " + player + " shuts all boxes! Player " + player + " wins!");
                        }
                    }
                }

                if (box1 == -1) { // Player gave up
                    if (player == 1) {
                        player1Score += getOpenBoxesSum(boxes);
                    } else {
                        player2Score += getOpenBoxesSum(boxes);
                    }
                }

                player1Turn = !player1Turn; // Switch turns
            }
        }

        if (!gameOver) {
            write("Player 1 points:");
            write(player1Score);
            write("Player 2 points:");
            write(player2Score);

            if (player1Score < player2Score) {
                write("Player 1 wins!");
            } else if (player1Score > player2Score) {
                write("Player 2 wins!");
            } else {
                write("Draw!");
            }
        }
    }

    private static boolean isValidChoice(int box1, int box2, int targetSum, boolean[] boxes) {
        return box1 >= 0 && box1 < 8 && box2 >= 0 && box2 < 8 && box1 != box2
                && boxes[box1] && boxes[box2] && box1 + box2 + 2 == targetSum; // +2 for 1-based indexing
    }

    private static boolean areAllBoxesShut(boolean[] boxes) {
        for (boolean open : boxes) {
            if (open) return false;
        }
        return true;
    }

    private static int getOpenBoxesSum(boolean[] boxes) {
        int sum = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i]) {
                sum += i + 1;
            }
        }
        return sum;
    }
}
