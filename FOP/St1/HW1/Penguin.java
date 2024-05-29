package fop.intro.pop;

// Reading the content of the file Penguin.java
public class Penguin {
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
            default: return "?";
        }
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        if (world[pinguRow][pinguColumn] == 3) {
            return false;
        }
        return searchFish(world, pinguRow, pinguColumn, new boolean[world.length][world[0].length]);
    }

    private static boolean searchFish(int[][] world, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= world.length || col < 0 || col >= world[0].length) {
            return false;
        }
        if (world[row][col] == 4) {
            return true;
        }
        if (world[row][col] == 3 || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        int[][] directions;
        switch (world[row][col]) {
            case 0:
                directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                break;
            case 1:
                directions = new int[][] {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
                break;
            case 2:
                directions = new int[][] {{3, 3}, {3, -3}, {-3, 3}, {-3, -3}};
                break;
            default:
                directions = new int[0][0];
        }
        for (int[] dir : directions) {
            if (searchFish(world, row + dir[0], col + dir[1], visited)) {
                return true;
            }
        }
        return false;
    }

    public static int[][] generateExampleWorldOne() {
        return new int[][] {
                {2, 3, 3, 3, 3, 3},
                {3, 0, 3, 3, 4, 3},
                {3, 3, 3, 3, 3, 3},
                {3, 3, 3, 0, 1, 3},
                {3, 3, 3, 3, 3, 3}
        };
    }

    public static int[][] generateExampleWorldTwo() {
        return new int[][] {
                {0, 0, 0, 2},
                {0, 0, 0, 1},
                {0, 1, 3, 4},
                {3, 4, 3, 3}
        };
    }

    public static void main(String[] args) {
        int[][] worldOne = generateExampleWorldOne();
        int[][] worldTwo = generateExampleWorldTwo();

        System.out.println("Example World One:");
        printWorld(worldOne, 0, 0);
        System.out.println("Is fish reachable? " + isFishReachable(worldOne, 0, 0));

        System.out.println("\nExample World Two:");
        printWorld(worldTwo, 0, 0);
        System.out.println("Is fish reachable? " + isFishReachable(worldTwo, 0, 0));
    }
}

