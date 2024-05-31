package Claude.St4.HW4;

public class Penguin extends MiniJava {

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[row].length; col++) {
                String terrain = getTerrainSymbol(world[row][col]);
                if (row == pinguRow && col == pinguColumn) {
                    terrain += "(P)";
                }
                System.out.print(terrain);
                if (col < world[row].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private static String getTerrainSymbol(int terrain) {
        switch (terrain) {
            case 0:
                return "L";
            case 1:
                return "I";
            case 2:
                return "W";
            case 3:
                return "S";
            case 4:
                return "F";
            default:
                return "";
        }
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        if (world[pinguRow][pinguColumn] == 3) {
            return false; // Pingu is on a shark, cannot reach fish
        }
        if (world[pinguRow][pinguColumn] == 4) {
            return true; // Pingu reached a fish
        }

        boolean[][] visited = new boolean[world.length][world[0].length];
        return isFishReachableHelper(world, pinguRow, pinguColumn, visited);
    }

    private static boolean isFishReachableHelper(int[][] world, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= world.length || col < 0 || col >= world[0].length || visited[row][col]) {
            return false; // Out of bounds or already visited
        }

        if (world[row][col] == 3) {
            return false; // Shark, cannot reach fish
        }
        if (world[row][col] == 4) {
            return true; // Found a fish
        }

        visited[row][col] = true; // Mark the current field as visited

        // Check adjacent fields based on the terrain
        switch (world[row][col]) {
            case 0: // Land
                return isFishReachableHelper(world, row - 1, col, visited) || // Up
                        isFishReachableHelper(world, row + 1, col, visited) || // Down
                        isFishReachableHelper(world, row, col - 1, visited) || // Left
                        isFishReachableHelper(world, row, col + 1, visited); // Right
            case 1: // Ice
                return isFishReachableHelper(world, row - 1, col - 1, visited) || // Top-left
                        isFishReachableHelper(world, row - 1, col + 1, visited) || // Top-right
                        isFishReachableHelper(world, row + 1, col - 1, visited) || // Bottom-left
                        isFishReachableHelper(world, row + 1, col + 1, visited); // Bottom-right
            case 2: // Water
                return isFishReachableHelper(world, row - 3, col - 3, visited) || // Top-left
                        isFishReachableHelper(world, row - 3, col + 3, visited) || // Top-right
                        isFishReachableHelper(world, row + 3, col - 3, visited) || // Bottom-left
                        isFishReachableHelper(world, row + 3, col + 3, visited); // Bottom-right
            default:
                return false;
        }
    }

    // Existing code...
    // ...

}