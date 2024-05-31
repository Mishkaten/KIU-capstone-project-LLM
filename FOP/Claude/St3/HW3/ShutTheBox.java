package Claude.St3.HW3;

public class ShutTheBox extends MiniJava {

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

        for (int round = 0; round < 8; round++) {
            // Player 1's turn
            int dice1 = dice();
            int dice2 = dice();
            int sum = dice1 + dice2;
            write("Player 1 has thrown the numbers:");
            write(dice1);
            write(dice2);
            outputBoxes(boxes);
            write("Which boxes to shut by player 1? (0 for no valid combination)");
            write("Box 1:");
            int box1 = readInt();
            write("Box 2:");
            int box2 = readInt();
            if (box1 == 0 || box2 == 0) {
                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i]) {
                        player1Points += i + 1;
                    }
                }
            } else if (box1 >= 1 && box1 <= 8 && box2 >= 1 && box2 <= 8 && boxes[box1 - 1] && boxes[box2 - 1] && box1 + box2 == sum) {
                boxes[box1 - 1] = false;
                boxes[box2 - 1] = false;
                if (!boxes[0] && !boxes[1] && !boxes[2] && !boxes[3] && !boxes[4] && !boxes[5] && !boxes[6] && !boxes[7]) {
                    write("Player 1 shuts all boxes! Player 1 wins!");
                    return;
                }
            }

            // Player 2's turn
            dice1 = dice();
            dice2 = dice();
            sum = dice1 + dice2;
            write("Player 2 has thrown the numbers:");
            write(dice1);
            write(dice2);
            outputBoxes(boxes);
            write("Which boxes to shut by player 2? (0 for no valid combination)");
            write("Box 1:");
            box1 = readInt();
            write("Box 2:");
            box2 = readInt();
            if (box1 == 0 || box2 == 0) {
                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i]) {
                        player2Points += i + 1;
                    }
                }
            } else if (box1 >= 1 && box1 <= 8 && box2 >= 1 && box2 <= 8 && boxes[box1 - 1] && boxes[box2 - 1] && box1 + box2 == sum) {
                boxes[box1 - 1] = false;
                boxes[box2 - 1] = false;
                if (!boxes[0] && !boxes[1] && !boxes[2] && !boxes[3] && !boxes[4] && !boxes[5] && !boxes[6] && !boxes[7]) {
                    write("Player 2 shuts all boxes! Player 2 wins!");
                    return;
                }
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
            write("It's a draw!");
        }
    }
}