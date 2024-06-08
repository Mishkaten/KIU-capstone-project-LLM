package St1.HW4;

public class Main {
    public static void main(String[] args) {
        // Test case 1: Simple world with fish reachable
        int[][] world1 = {
                {0, 0, 0, 0},
                {0, 3, 1, 0},
                {0, 0, 0, 4},
                {1, 1, 0, 0}
        };
        int pinguRow1 = 0;
        int pinguColumn1 = 0;

        System.out.println("World 1:");
        Penguin.printWorld(world1, pinguRow1, pinguColumn1);
        System.out.println("Fish reachable: " + Penguin.isFishReachable(world1, pinguRow1, pinguColumn1));
        // Expected output: true

        // Test case 2: World with fish unreachable due to sharks
        int[][] world2 = {
                {0, 0, 0, 0},
                {0, 3, 1, 0},
                {0, 3, 0, 4},
                {1, 1, 0, 0}
        };
        int pinguRow2 = 0;
        int pinguColumn2 = 0;

        System.out.println("\nWorld 2:");
        Penguin.printWorld(world2, pinguRow2, pinguColumn2);
        System.out.println("Fish reachable: " + Penguin.isFishReachable(world2, pinguRow2, pinguColumn2));
        // Expected output: false

        // Test case 3: World with multiple fish
        int[][] world3 = {
                {0, 0, 4, 0},
                {0, 3, 1, 0},
                {0, 0, 0, 4},
                {1, 1, 0, 0}
        };
        int pinguRow3 = 0;
        int pinguColumn3 = 0;

        System.out.println("\nWorld 3:");
        Penguin.printWorld(world3, pinguRow3, pinguColumn3);
        System.out.println("Fish reachable: " + Penguin.isFishReachable(world3, pinguRow3, pinguColumn3));
        // Expected output: true

        // Test case 4: World with no fish
        int[][] world4 = {
                {0, 0, 0, 0},
                {0, 3, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 0}
        };
        int pinguRow4 = 0;
        int pinguColumn4 = 0;

        System.out.println("\nWorld 4:");
        Penguin.printWorld(world4, pinguRow4, pinguColumn4);
        System.out.println("Fish reachable: " + Penguin.isFishReachable(world4, pinguRow4, pinguColumn4));
        // Expected output: false
    }
}
