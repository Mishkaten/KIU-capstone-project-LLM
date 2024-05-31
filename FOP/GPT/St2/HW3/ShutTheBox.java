package GPT.St2.HW3;

import java.util.Scanner;

public class ShutTheBox {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean[] boxes = new boolean[9]; // boxes[0] is unused
        for (int i = 1; i <= 8; i++) {
            boxes[i] = true; // All boxes are initially open
        }

        int player1Points = 0;
        int player2Points = 0;

        for (int round = 1; round <= 8; round++) {
            System.out.println("Player 1 has thrown the numbers:");
            int dice1 = dice();
            int dice2 = dice();
            System.out.println(dice1);
            System.out.println(dice2);

            int sum = dice1 + dice2;
            System.out.println("Open boxes: " + getOpenBoxesString(boxes));
            int[] choice = getPlayerChoice(scanner, boxes, sum);
            if (choice[0] == 0 || choice[1] == 0) {
                player1Points += getSumOfOpenBoxes(boxes);
            } else {
                boxes[choice[0]] = false;
                boxes[choice[1]] = false;
            }

            if (checkAllBoxesClosed(boxes)) {
                System.out.println("Player 1 shuts all boxes! Player 1 wins!");
                return;
            }

            System.out.println("Player 2 has thrown the numbers:");
            dice1 = dice();
            dice2 = dice();
            System.out.println(dice1);
            System.out.println(dice2);

            sum = dice1 + dice2;
            System.out.println("Open boxes: " + getOpenBoxesString(boxes));
            choice = getPlayerChoice(scanner, boxes, sum);
            if (choice[0] == 0 || choice[1] == 0) {
                player2Points += getSumOfOpenBoxes(boxes);
            } else {
                boxes[choice[0]] = false;
                boxes[choice[1]] = false;
            }

            if (checkAllBoxesClosed(boxes)) {
                System.out.println("Player 2 shuts all boxes! Player 2 wins!");
                return;
            }
        }

        System.out.println("Player 1 points: " + player1Points);
        System.out.println("Player 2 points: " + player2Points);

        if (player1Points < player2Points) {
            System.out.println("Player 1 wins!");
        } else if (player2Points < player1Points) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("Draw!");
        }
    }

    public static int dice() {
        return (int) (Math.random() * 6) + 1; // Returns a number between 1 and 6
    }

    public static void outputBoxes(boolean[] boxes) {
        for (int i = 1; i < boxes.length; i++) {
            System.out.println("Box " + i + ": " + (boxes[i] ? "open" : "closed"));
        }
    }

    public static String getOpenBoxesString(boolean[] boxes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < boxes.length; i++) {
            sb.append(i).append(":").append(boxes[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static int[] getPlayerChoice(Scanner scanner, boolean[] boxes, int sum) {
        int[] choice = new int[2];
        while (true) {
            System.out.println("Which boxes to shut? (0 for no valid combination)");
            System.out.print("Box 1: ");
            choice[0] = scanner.nextInt();
            System.out.print("Box 2: ");
            choice[1] = scanner.nextInt();

            if (choice[0] == 0 || choice[1] == 0) {
                return new int[]{0, 0};
            }

            if (boxes[choice[0]] && boxes[choice[1]] && choice[0] + choice[1] == sum) {
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
        return choice;
    }

    public static int getSumOfOpenBoxes(boolean[] boxes) {
        int sum = 0;
        for (int i = 1; i < boxes.length; i++) {
            if (boxes[i]) {
                sum += i;
            }
        }
        return sum;
    }

    public static boolean checkAllBoxesClosed(boolean[] boxes) {
        for (int i = 1; i < boxes.length; i++) {
            if (boxes[i]) {
                return false;
            }
        }
        return true;
    }
}
