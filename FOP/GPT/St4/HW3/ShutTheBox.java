package GPT.St4.HW3;

import GPT.St4.HW2.MiniJava;

public class ShutTheBox {

    public static void main(String[] args) {
        boolean[] boxes = new boolean[9];
        for (int i = 1; i < boxes.length; i++) {
            boxes[i] = true;
        }

        int player1Score = 0;
        int player2Score = 0;

        for (int round = 1; round <= 8; round++) {
            player1Score += playRound(1, boxes);
            if (allBoxesClosed(boxes)) {
                MiniJava.write("Player 1 shuts all boxes! Player 1 wins!");
                return;
            }

            player2Score += playRound(2, boxes);
            if (allBoxesClosed(boxes)) {
                MiniJava.write("Player 2 shuts all boxes! Player 2 wins!");
                return;
            }
        }

        MiniJava.write("Player 1 points:\n<" + player1Score);
        MiniJava.write("Player 2 points:\n<" + player2Score);

        if (player1Score < player2Score) {
            MiniJava.write("Player 1 wins!");
        } else if (player2Score < player1Score) {
            MiniJava.write("Player 2 wins!");
        } else {
            MiniJava.write("Draw!");
        }
    }

    private static int playRound(int player, boolean[] boxes) {
        int dice1 = MiniJava.dice();
        int dice2 = MiniJava.dice();
        int sum = dice1 + dice2;

        MiniJava.write("Player " + player + " has thrown the numbers:\n<" + dice1 + "\n<" + dice2);
        outputBoxes(boxes);

        while (true) {
            int box1 = MiniJava.readInt("<Box 1:\n>");
            int box2 = MiniJava.readInt("<Box 2:\n>");

            if (box1 == 0 || box2 == 0) {
                return sumOfOpenBoxes(boxes);
            }

            if (isValidChoice(boxes, box1, box2, sum)) {
                boxes[box1] = false;
                boxes[box2] = false;
                break;
            } else {
                MiniJava.write("<Invalid choice. Try again.");
            }
        }
        return 0;
    }

    private static boolean allBoxesClosed(boolean[] boxes) {
        for (int i = 1; i < boxes.length; i++) {
            if (boxes[i]) return false;
        }
        return true;
    }

    private static void outputBoxes(boolean[] boxes) {
        StringBuilder sb = new StringBuilder("<Open boxes:");
        for (int i = 1; i < boxes.length; i++) {
            sb.append(" ").append(i).append(":").append(boxes[i]);
        }
        MiniJava.write(sb.toString());
    }

    private static int sumOfOpenBoxes(boolean[] boxes) {
        int sum = 0;
        for (int i = 1; i < boxes.length; i++) {
            if (boxes[i]) sum += i;
        }
        return sum;
    }

    private static boolean isValidChoice(boolean[] boxes, int box1, int box2, int sum) {
        return boxes[box1] && boxes[box2] && box1 + box2 == sum;
    }
}
