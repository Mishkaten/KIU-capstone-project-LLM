import java.util.*;

public class ShutTheBox {
    public static void main(String[] args) {
        boolean[] boxes = new boolean[8];
        Arrays.fill(boxes, true); // All boxes are initially open

        int player1Points = 0;
        int player2Points = 0;

        for (int round = 1; round <= 8; round++) {
            if (playTurn(1, boxes)) {
                System.out.println("Player 1 shuts all boxes! Player 1 wins!");
                return;
            }
            if (playTurn(2, boxes)) {
                System.out.println("Player 2 shuts all boxes! Player 2 wins!");
                return;
            }
        }

        player1Points = calculatePoints(boxes, player1Points, 1);
        player2Points = calculatePoints(boxes, player2Points, 2);

        System.out.println("Player 1 points: " + player1Points);
        System.out.println("Player 2 points: " + player2Points);

        if (player1Points < player2Points) {
            System.out.println("Player 1 wins!");
        } else if (player1Points > player2Points) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("Draw!");
        }
    }

    public static boolean playTurn(int player, boolean[] boxes) {
        int dice1 = MiniJava.dice();
        int dice2 = MiniJava.dice();
        int sum = dice1 + dice2;

        System.out.println("Player " + player + " has thrown the numbers:");
        System.out.println(dice1);
        System.out.println(dice2);

        outputBoxes(boxes);

        while (true) {
            System.out.println("Which boxes to shut by player " + player + "? (0 for no valid combination)");

            System.out.print("Box 1: ");
            int box1 = MiniJava.readInt();
            System.out.print("Box 2: ");
            int box2 = MiniJava.readInt();

            if (box1 == 0 || box2 == 0) {
                return false;
            }

            if (isValidMove(box1, box2, sum, boxes)) {
                boxes[box1 - 1] = false;
                boxes[box2 - 1] = false;

                if (areAllBoxesClosed(boxes)) {
                    return true;
                }
                return false;
            }

            System.out.println("Invalid combination. Try again.");
        }
    }

    public static void outputBoxes(boolean[] boxes) {
        System.out.print("Open boxes: ");
        for (int i = 0; i < boxes.length; i++) {
            System.out.print((i + 1) + ":" + boxes[i] + " ");
        }
        System.out.println();
    }

    public static boolean isValidMove(int box1, int box2, int sum, boolean[] boxes) {
        return (box1 >= 1 && box1 <= 8 && box2 >= 1 && box2 <= 8 && box1 != box2 && boxes[box1 - 1] && boxes[box2 - 1] && (box1 + box2 == sum));
    }

    public static boolean areAllBoxesClosed(boolean[] boxes) {
        for (boolean box : boxes) {
            if (box) {
                return false;
            }
        }
        return true;
    }

    public static int calculatePoints(boolean[] boxes, int playerPoints, int player) {
        int sum = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i]) {
                sum += (i + 1);
            }
        }
        playerPoints += sum;
        return playerPoints;
    }
}
