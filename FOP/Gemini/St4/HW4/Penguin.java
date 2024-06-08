package St4.HW4;

import java.util.HashSet;
import java.util.Set;
public class Penguin extends MiniJava {

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[0].length; col++) {
                String terrain = "";
                switch (world[row][col]) {
                    case 0:
                        terrain = "L";
                        break;
                    case 1:
                        terrain = "I";
                        break;
                    case 2:
                        terrain = "W";
                        break;
                    case 3:
                        terrain = "S";
                        break;
                    case 4:
                        terrain = "F";
                        break;
                }
                if (row == pinguRow && col == pinguColumn) {
                    terrain += "(P)";
                }
                writeConsole(terrain + "\t"); // Add a tab after each element
            }
            writeLineConsole(); // Move to the next line after each row
        }
    }

    // Check if the fish is reachable
    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        int rows = world.length;
        int cols = world[0].length;
        Set<String> visited = new HashSet<>();

        // Check if Pingu starts on a shark (immediate failure)
        if (world[pinguRow][pinguColumn] == 3) {
            return false;
        }

        return isFishReachableRec(world, pinguRow, pinguColumn, visited, rows, cols);
    }

    // Recursive helper for isFishReachable
    private static boolean isFishReachableRec(int[][] world, int row, int col, Set<String> visited, int rows, int cols) {
        String position = row + "," + col;

        // Base cases
        if (row < 0 || col < 0 || row >= rows || col >= cols || world[row][col] == 3 || visited.contains(position)) {
            return false; // Out of bounds, shark, or already visited
        }
        if (world[row][col] == 4) {
            return true; // Fish found
        }

        visited.add(position); // Mark as visited

        // Recursive exploration for each terrain type, with shark check in each call
        if (world[row][col] == 0) {
            return isFishReachableRec(world, row - 1, col, visited, rows, cols) ||
                    isFishReachableRec(world, row + 1, col, visited, rows, cols) ||
                    isFishReachableRec(world, row, col - 1, visited, rows, cols) ||
                    isFishReachableRec(world, row, col + 1, visited, rows, cols);
        } else if (world[row][col] == 1) {
            return isFishReachableRec(world, row - 1, col - 1, visited, rows, cols) ||
                    isFishReachableRec(world, row - 1, col + 1, visited, rows, cols) ||
                    isFishReachableRec(world, row + 1, col - 1, visited, rows, cols) ||
                    isFishReachableRec(world, row + 1, col + 1, visited, rows, cols);
        } else { // Water
            return isFishReachableRec(world, row - 3, col - 3, visited, rows, cols) ||
                    isFishReachableRec(world, row - 3, col + 3, visited, rows, cols) ||
                    isFishReachableRec(world, row + 3, col - 3, visited, rows, cols) ||
                    isFishReachableRec(world, row + 3, col + 3, visited, rows, cols);
        }
    }
    /**
     * returns the example world 1.
     * Do not modify this method.
     * @return An example world
     */
    public static int[][] generateExampleWorldOne(){
        return new int[][] {{2,3,3,3,3,3}, {3,0,3,3,4,3}, {3,3,3,3,3,1}, {3,3,3,0,1,3}, {3,3,3,3,3,3}};
    }

    /**
     * returns the example world 1.
     * Do not modify this method.
     * @return An example world
     */
    public static int[][] generateExampleWorldTwo(){
        return new int[][] {{0,0,0,2}, {0,0,0,1}, {0,1,3,4}, {3,4,3,3}};
    }

    /**
     *  You may use the main method for testing your program.
     */
    public static void main(String[] args){
        int pinguRow = 0;
        int pinguColumn = 0;
        int[][] world = generateExampleWorldOne();
        printWorld(world, pinguRow, pinguColumn);
        write(""+isFishReachable(world, pinguRow, pinguColumn));
    }
}