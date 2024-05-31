package Claude.St4.HW3;

public class ShutTheBox extends MiniJava {

    // Output of open boxes. Don't modify this
    public static void outputBoxes(boolean[] boxes) {
        StringBuilder sb = new StringBuilder("Open boxes: 1:");
        sb.append(boxes[0]);
        for (int i = 1; i < boxes.length; i++) {
            sb.append(" ").append(i + 1).append(":").append(boxes[i]);
        }
        write(sb.toString());
    }

    public static void main(String[] args) throws IllegalAccessException {
        boolean[] boxes = new boolean[8];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = true;
        }

        int player1Points = 0;
        int player2Points = 0;

        for (int round = 1; round <= 8; round++) {
            write("Player 1 has thrown the numbers:");
            int dice1 = dice();
            int dice2 = dice();
            write(dice1);
            write(dice2);

            outputBoxes(boxes);

            write("Which boxes to shut by player 1? (0 for no valid combination)");
            int box1 = readInt("Box 1:");
            int box2 = readInt("Box 2:");

            if (box1 == 0 || box2 == 0 || box1 + box2 != dice1 + dice2 || !boxes[box1 - 1] || !boxes[box2 - 1]) {
                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i]) {
                        player1Points += i + 1;
                    }
                }
            } else {
                boxes[box1 - 1] = false;
                boxes[box2 - 1] = false;
            }

            int openBoxes = 0;
            for (boolean box : boxes) {
                if (box) {
                    openBoxes++;
                }
            }

            if (openBoxes == 0) {
                write("Player 1 shuts all boxes! Player 1 wins!");
                return;
            }

            write("Player 2 has thrown the numbers:");
            dice1 = dice();
            dice2 = dice();
            write(dice1);
            write(dice2);

            outputBoxes(boxes);

            write("Which boxes to shut by player 2? (0 for no valid combination)");
            box1 = readInt("Box 1:");
            box2 = readInt("Box 2:");

            if (box1 == 0 || box2 == 0 || box1 + box2 != dice1 + dice2 || !boxes[box1 - 1] || !boxes[box2 - 1]) {
                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i]) {
                        player2Points += i + 1;
                    }
                }
            } else {
                boxes[box1 - 1] = false;
                boxes[box2 - 1] = false;
            }

            openBoxes = 0;
            for (boolean box : boxes) {
                if (box) {
                    openBoxes++;
                }
            }

            if (openBoxes == 0) {
                write("Player 2 shuts all boxes! Player 2 wins!");
                return;
            }
        }

        write("Player 1 points:");
        write(player1Points);
        write("Player 2 points:");
        write(player2Points);

        if (player1Points < player2Points) {
            write("Player 1 wins!");
        } else if (player2Points < player1Points) {
            write("Player 2 wins!");
        } else {
            write("Draw!");
        }
    }
}