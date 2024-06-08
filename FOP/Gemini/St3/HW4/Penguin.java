package St1.HW4;

public class Penguin extends MiniJava {

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                String terrain = "";
                switch (world[i][j]) {
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
                if (i == pinguRow && j == pinguColumn) {
                    terrain += "(P)";
                }
                System.out.print(terrain);
                if (j < world[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        if (pinguRow < 0 || pinguRow >= world.length || pinguColumn < 0 || pinguColumn >= world[pinguRow].length) {
            return false;
        }
        if (world[pinguRow][pinguColumn] == 3) {
            return false;
        }
        if (world[pinguRow][pinguColumn] == 4) {
            return true;
        }
        world[pinguRow][pinguColumn] = -1; // Mark the current position as visited
        boolean isFishFound = false;
        switch (world[pinguRow][pinguColumn]) {
            case 0:
                isFishFound = isFishReachable(world, pinguRow - 1, pinguColumn) ||
                        isFishReachable(world, pinguRow + 1, pinguColumn) ||
                        isFishReachable(world, pinguRow, pinguColumn - 1) ||
                        isFishReachable(world, pinguRow, pinguColumn + 1);
                break;
            case 1:
                isFishFound = isFishReachable(world, pinguRow - 1, pinguColumn - 1) ||
                        isFishReachable(world, pinguRow - 1, pinguColumn + 1) ||
                        isFishReachable(world, pinguRow + 1, pinguColumn - 1) ||
                        isFishReachable(world, pinguRow + 1, pinguColumn + 1);
                break;
            case 2:
                isFishFound = isFishReachable(world, pinguRow - 3, pinguColumn - 3) ||
                        isFishReachable(world, pinguRow - 3, pinguColumn + 3) ||
                        isFishReachable(world, pinguRow + 3, pinguColumn - 3) ||
                        isFishReachable(world, pinguRow + 3, pinguColumn + 3);
                break;
        }
        world[pinguRow][pinguColumn] = 0; // Unmark the current position
        return isFishFound;
    }

    // Rest of the code...
}