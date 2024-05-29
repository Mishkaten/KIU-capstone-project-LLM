public class Penguin {
    public static void main(String[] args) {
        int[][] world = generateExampleWorldOne();
        printWorld(world, 0, 0);
        boolean reachable = isFishReachable(world, 0, 0);
        MiniJava.printLine("Is fish reachable: " + reachable);
    }

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (i == pinguRow && j == pinguColumn) {
                    System.out.print(getTerrainSymbol(world[i][j]) + "(P)");
                } else {
                    System.out.print(getTerrainSymbol(world[i][j]));
                }
                if (j < world[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private static String getTerrainSymbol(int terrain) {
        switch (terrain) {
            case 0: return "L";
            case 1: return "I";
            case 2: return "W";
            case 3: return "S";
            case 4: return "F";
            default: return " ";
        }
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        if (world[pinguRow][pinguColumn] == 3) {
            return false; // Pingu starts on a shark field.
        }
        boolean[][] visited = new boolean[world.length][world[0].length];
        return searchFish(world, pinguRow, pinguColumn, visited);
    }

    private static boolean searchFish(int[][] world, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= world.length || col < 0 || col >= world[0].length) {
            return false; // Out of bounds.
        }
        if (visited[row][col] || world[row][col] == 3) {
            return false; // Already visited or shark.
        }
        if (world[row][col] == 4) {
            return true; // Found fish.
        }
        visited[row][col] = true;

        int[][] directions;
        switch (world[row][col]) {
            case 0: // Land
                directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                break;
            case 1: // Ice
                directions = new int[][] {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
                break;
            case 2: // Water
                directions = new int[][] {{3, 3}, {3, -3}, {-3, 3}, {-3, -3}};
                break;
            default:
                directions = new int[0][0]; // Should never reach here.
        }

        for (int[] dir : directions) {
            if (searchFish(world, row + dir[0], col + dir[1], visited)) {
                return true;
            }
        }
        return false;
    }

    private static int[][] generateExampleWorldOne() {
        return new int[][] {
                {2, 3, 3, 3, 3, 3},
                {3, 0, 3, 3, 4, 3},
                {3, 3, 3, 3, 3, 1},
                {3, 3, 3, 0, 1, 3},
                {3, 3, 3, 3, 3, 3}
        };
    }

    private static int[][] generateExampleWorldTwo() {
        return new int[][] {
                {0, 0, 0, 2},
                {0, 0, 0, 1},
                {0, 1, 3, 4},
                {3, 4, 3, 3}
        };
    }
}
