package GPT.St2.HW4;

import java.util.Scanner;

public class Penguin {
    public static void main(String[] args) {
        // Example usage
        int[][] worldOne = generateExampleWorldOne();
        printWorld(worldOne, 0, 0);
        System.out.println(isFishReachable(worldOne, 0, 0)); // Expected: true

        int[][] worldTwo = generateExampleWorldTwo();
        printWorld(worldTwo, 0, 1);
        System.out.println(isFishReachable(worldTwo, 0, 1)); // Expected: false
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

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[row].length; col++) {
                if (row == pinguRow && col == pinguColumn) {
                    System.out.print(getTerrainSymbol(world[row][col]) + "(P)");
                } else {
                    System.out.print(getTerrainSymbol(world[row][col]));
                }
                if (col < world[row].length - 1) {
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
        boolean[][] visited = new boolean[world.length][world[0].length];
        return isFishReachableHelper(world, pinguRow, pinguColumn, visited);
    }

    private static boolean isFishReachableHelper(int[][] world, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= world.length || col < 0 || col >= world[0].length || visited[row][col] || world[row][col] == 3) {
            return false;
        }
        if (world[row][col] == 4) {
            return true;
        }

        visited[row][col] = true;

        switch (world[row][col]) {
            case 0: // Land
                return isFishReachableHelper(world, row - 1, col, visited) ||
                        isFishReachableHelper(world, row + 1, col, visited) ||
                        isFishReachableHelper(world, row, col - 1, visited) ||
                        isFishReachableHelper(world, row, col + 1, visited);
            case 1: // Ice
                return isFishReachableHelper(world, row - 1, col - 1, visited) ||
                        isFishReachableHelper(world, row - 1, col + 1, visited) ||
                        isFishReachableHelper(world, row + 1, col - 1, visited) ||
                        isFishReachableHelper(world, row + 1, col + 1, visited);
            case 2: // Water
                return isFishReachableHelper(world, row - 3, col - 3, visited) ||
                        isFishReachableHelper(world, row - 3, col + 3, visited) ||
                        isFishReachableHelper(world, row + 3, col - 3, visited) ||
                        isFishReachableHelper(world, row + 3, col + 3, visited);
            default:
                return false;
        }
    }
}
