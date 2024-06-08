package St1.HW4;

public class Penguin extends MiniJava {
    private static final char[] terrainChars = {'L', 'I', 'W', 'S', 'F'};

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[0].length; col++) {
                writeConsole(terrainChars[world[row][col]]); // Print terrain character
                if (row == pinguRow && col == pinguColumn) {
                    writeConsole("(P)"); // Mark Pingu's position
                }
                writeConsole("\t"); // Tab separation
            }
            writeLineConsole(); // New line for next row
        }
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        return isFishReachableHelper(world, pinguRow, pinguColumn, new boolean[world.length][world[0].length]);
    }

    private static boolean isFishReachableHelper(int[][] world, int row, int col, boolean[][] visited) {
        // Base cases
        if (row < 0 || row >= world.length || col < 0 || col >= world[0].length) {
            return false; // Out of bounds
        }
        if (world[row][col] == 3) {
            return false; // Shark!
        }
        if (world[row][col] == 4) {
            return true; // Found fish!
        }
        if (visited[row][col]) {
            return false; // Already visited
        }

        // Mark current cell as visited
        visited[row][col] = true;

        // Recursive moves for each terrain type
        if (world[row][col] == 0) {
            // Land: Check all directions
            return isFishReachableHelper(world, row - 1, col, visited) ||
                    isFishReachableHelper(world, row + 1, col, visited) ||
                    isFishReachableHelper(world, row, col - 1, visited) ||
                    isFishReachableHelper(world, row, col + 1, visited);
        } else if (world[row][col] == 1) {
            // Ice: Check diagonals
            return isFishReachableHelper(world, row - 1, col - 1, visited) ||
                    isFishReachableHelper(world, row + 1, col + 1, visited) ||
                    isFishReachableHelper(world, row - 1, col + 1, visited) ||
                    isFishReachableHelper(world, row + 1, col - 1, visited);
        } else {
            // Water: Jump 3 fields diagonally
            return isFishReachableHelper(world, row - 3, col - 3, visited) ||
                    isFishReachableHelper(world, row + 3, col + 3, visited) ||
                    isFishReachableHelper(world, row - 3, col + 3, visited) ||
                    isFishReachableHelper(world, row + 3, col - 3, visited);
        }
    }
}
