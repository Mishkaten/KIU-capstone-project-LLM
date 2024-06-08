package Claude.St3.HW3;

public class ShutTheBox extends MiniJava {

    public static void main(String[] args) throws IllegalAccessException {
        int[] playerScores = {0, 0}; // Scores for player 1 and 2
        boolean[] openBoxes = new boolean[8];
        for (int i = 0; i < openBoxes.length; i++) {
            openBoxes[i] = true;
        }

        for (int round = 1; round <= 8; round++) {
            for (int player = 0; player < 2; player++) {
                write("Player " + (player + 1) + " has thrown the numbers:");
                int dice1 = dice();
                int dice2 = dice();
                write(dice1);
                write(dice2);
                int targetSum = dice1 + dice2;

                outputBoxes(openBoxes);

                int box1 = -1, box2 = -1;
                while (!isValidChoice(box1, box2, targetSum, openBoxes)) {
                    write("Which boxes to shut by player " + (player + 1) + "? (0 for no valid combination)");
                    write("Box 1:");
                    box1 = readInt();
                    if (box1 == 0) { // Player gives up
                        break;
                    }
                    write("Box 2:");
                    box2 = readInt();

                    if (box1 == 0 || box2 == 0) { // Player gives up
                        break;
                    } else if (!isValidChoice(box1, box2, targetSum, openBoxes)) {
                        write("Invalid choice. Try again.");
                    }
                }

                if (box1 == 0) { // Player gives up or cannot find a valid combination
                    int remainingSum = calculateRemainingSum(openBoxes);
                    playerScores[player] += remainingSum;
                    write("Player " + (player + 1) + " scores " + remainingSum + " points.");
                } else {
                    openBoxes[box1 - 1] = false;
                    openBoxes[box2 - 1] = false;

                    // Check if all boxes are shut
                    if (allBoxesShut(openBoxes)) {
                        write("Player " + (player + 1) + " shuts all boxes! Player " + (player + 1) + " wins!");
                        return; // End the game
                    }
                }
            }
        }

        // Determine the winner based on points
        if (playerScores[0] < playerScores[1]) {
            write("Player 1 points:");
            write(playerScores[0]);
            write("Player 2 points:");
            write(playerScores[1]);
            write("Player 1 wins!");
        } else if (playerScores[0] > playerScores[1]) {
            write("Player 1 points:");
            write(playerScores[0]);
            write("Player 2 points:");
            write(playerScores[1]);
            write("Player 2 wins!");
        } else {
            write("Draw!");
        }
    }

    public static void outputBoxes(boolean[] boxes) {
        write("Open boxes: ");
        for (int i = 0; i < boxes.length; i++) {
            write((i + 1) + ":" + boxes[i]);
            if (i < boxes.length - 1) {
                write(" ");
            }
        }
        writeLineConsole(); // Add a line break for better formatting
    }

    //Helper methods

    private static boolean isValidChoice(int box1, int box2, int targetSum, boolean[] openBoxes) {
        return box1 > 0 && box1 <= 8 && box2 > 0 && box2 <= 8 && box1 != box2
                && openBoxes[box1 - 1] && openBoxes[box2 - 1] && (box1 + box2 == targetSum);
    }

    private static int calculateRemainingSum(boolean[] openBoxes) {
        int sum = 0;
        for (int i = 0; i < openBoxes.length; i++) {
            if (openBoxes[i]) {
                sum += i + 1;
            }
        }
        return sum;
    }

    private static boolean allBoxesShut(boolean[] openBoxes) {
        for (boolean open : openBoxes) {
            if (open) {
                return false;
            }
        }
        return true;
    }
}
