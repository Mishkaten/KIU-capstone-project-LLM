package GPT.St3.HW4;

public class Penguin {
    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                char terrain;
                switch (world[i][j]) {
                    case 0:
                        terrain = 'L';
                        break;
                    case 1:
                        terrain = 'I';
                        break;
                    case 2:
                        terrain = 'W';
                        break;
                    case 3:
                        terrain = 'S';
                        break;
                    case 4:
                        terrain = 'F';
                        break;
                    default:
                        terrain = ' ';
                }

                if (i == pinguRow && j == pinguColumn) {
                    System.out.print(terrain + "(P)");
                } else {
                    System.out.print(terrain);
                }

                if (j < world[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        return isFishReachable(world, pinguRow, pinguColumn, new boolean[world.length][world[0].length]);
    }

    private static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn, boolean[][] visited) {
        if (pinguRow < 0 || pinguRow >= world.length || pinguColumn < 0 || pinguColumn >= world[0].length || visited[pinguRow][pinguColumn]) {
            return false;
        }

        if (world[pinguRow][pinguColumn] == 3) {
            return false;
        }

        if (world[pinguRow][pinguColumn] == 4) {
            return true;
        }

        visited[pinguRow][pinguColumn] = true;

        switch (world[pinguRow][pinguColumn]) {
            case 0: // Land
                return isFishReachable(world, pinguRow - 1, pinguColumn, visited) || // up
                        isFishReachable(world, pinguRow + 1, pinguColumn, visited) || // down
                        isFishReachable(world, pinguRow, pinguColumn - 1, visited) || // left
                        isFishReachable(world, pinguRow, pinguColumn + 1, visited);   // right

            case 1: // Ice
                return isFishReachable(world, pinguRow - 1, pinguColumn - 1, visited) || // top-left
                        isFishReachable(world, pinguRow - 1, pinguColumn + 1, visited) || // top-right
                        isFishReachable(world, pinguRow + 1, pinguColumn - 1, visited) || // bottom-left
                        isFishReachable(world, pinguRow + 1, pinguColumn + 1, visited);   // bottom-right

            case 2: // Water
                return isFishReachable(world, pinguRow - 3, pinguColumn - 3, visited) || // top-left
                        isFishReachable(world, pinguRow - 3, pinguColumn + 3, visited) || // top-right
                        isFishReachable(world, pinguRow + 3, pinguColumn - 3, visited) || // bottom-left
                        isFishReachable(world, pinguRow + 3, pinguColumn + 3, visited);   // bottom-right
        }

        return false;
    }

    // You can add the main method for testing
    public static void main(String[] args) {
        int[][] exampleWorldOne = generateExampleWorldOne();
        int[][] exampleWorldTwo = generateExampleWorldTwo();

        System.out.println("Example World One:");
        printWorld(exampleWorldOne, 0, 0);

        System.out.println("\nIs Fish Reachable from (0, 0) in Example World One: " + isFishReachable(exampleWorldOne, 0, 0));

        System.out.println("\nExample World Two:");
        printWorld(exampleWorldTwo, 0, 0);

        System.out.println("\nIs Fish Reachable from (0, 0) in Example World Two: " + isFishReachable(exampleWorldTwo, 0, 0));
    }

    public static int[][] generateExampleWorldOne() {
        return new int[][]{
                {2, 3, 3, 3, 3, 3},
                {3, 0, 3, 3, 4, 3},
                {3, 3, 3, 3, 3, 1},
                {3, 3, 3, 0, 1, 3},
                {3, 3, 3, 3, 3, 3}
        };
    }

    public static int[][] generateExampleWorldTwo() {
        return new int[][]{
                {0, 0, 0, 2},
                {0, 0, 0, 1},
                {0, 1, 3, 4},
                {3, 4, 3, 3}
        };
    }
}
