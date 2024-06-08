package St3.HW3;

public class ShutTheBox extends MiniJava {

    public static void main(String[] args) throws IllegalAccessException {
        // Initialize game
        boolean[] openBoxes = new boolean[8];
        for (int i = 0; i < openBoxes.length; i++) {
            openBoxes[i] = true;
        }
        int[] scores = {0, 0}; // Scores for player 1 and player 2

        // Game loop for 8 rounds
        for (int round = 1; round <= 8; round++) {
            for (int player = 0; player < 2; player++) {
                write("Player " + (player + 1) + " has thrown the numbers:");
                int dice1 = dice();
                int dice2 = dice();
                write(dice1);
                write(dice2);
                int targetSum = dice1 + dice2;

                outputBoxes(openBoxes);

                // Get player input (box selections)
                int box1 = -1, box2 = -1;
                while (true) {
                    box1 = readInt("Which box to shut by player " + (player + 1) + "? (0 for no valid combination) Box 1:");
                    if (box1 == 0) break; // Player gives up
                    box2 = readInt("Box 2:");
                    if (box2 == 0) break; // Player gives up

                    if (!openBoxes[box1 - 1] || !openBoxes[box2 - 1] || box1 + box2 != targetSum) {
                        write("Invalid combination. Please try again.");
                    } else {
                        break; // Valid combination found
                    }
                }

                // Update game state
                if (box1 == 0 || box2 == 0) {
                    scores[player] += calculateOpenBoxesSum(openBoxes);
                    write("Player " + (player + 1) + " receives penalty points.");
                } else {
                    openBoxes[box1 - 1] = false;
                    openBoxes[box2 - 1] = false;
                }

                // Check for winning condition
                if (calculateOpenBoxesSum(openBoxes) == 0) {
                    write("Player " + (player + 1) + " shuts all boxes! Player " + (player + 1) + " wins!");
                    return;
                }
            }
        }

        // Game over, determine winner or draw
        write("Player 1 points:");
        write(scores[0]);
        write("Player 2 points:");
        write(scores[1]);

        if (scores[0] < scores[1]) {
            write("Player 1 wins!");
        } else if (scores[0] > scores[1]) {
            write("Player 2 wins!");
        } else {
            write("Draw!");
        }
    }

    public static void outputBoxes(boolean[] boxes) {
        StringBuilder sb = new StringBuilder("Open boxes: 1:");
        sb.append(boxes[0]);
        for (int i = 1; i < boxes.length; i++) {
            sb.append(" ").append(i + 1).append(":").append(boxes[i]);
        }
        write(sb.toString());
    }


    // Helper method to calculate the sum of open boxes
    private static int calculateOpenBoxesSum(boolean[] openBoxes) {
        int sum = 0;
        for (int i = 0; i < openBoxes.length; i++) {
            if (openBoxes[i]) {
                sum += i + 1;
            }
        }
        return sum;
    }
}
