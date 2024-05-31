package Claude.St1.HW4;

public class Penguin extends MiniJava {

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[row].length; col++) {
                char terrainChar = getTerrainChar(world[row][col]);
                if (row == pinguRow && col == pinguColumn) {
                    System.out.print(terrainChar + "(P)");
                } else {
                    System.out.print(terrainChar);
                }
                if (col < world[row].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private static char getTerrainChar(int terrain) {
        switch (terrain) {
            case 0:
                return 'L';
            case 1:
                return 'I';
            case 2:
                return 'W';
            case 3:
                return 'S';
            case 4:
                return 'F';
            default:
                throw new IllegalArgumentException("Invalid terrain value: " + terrain);
        }
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn) {
        if (pinguRow < 0 || pinguRow >= world.length || pinguColumn < 0 || pinguColumn >= world[0].length) {
            return false;
        }
        int terrain = world[pinguRow][pinguColumn];
        if (terrain == 3) {
            return false;
        }
        if (terrain == 4) {
            return true;
        }
        world[pinguRow][pinguColumn] = -1; // Mark as visited
        boolean fishReachable = false;
        switch (terrain) {
            case 0:
                fishReachable = isFishReachable(world, pinguRow - 1, pinguColumn)
                        || isFishReachable(world, pinguRow + 1, pinguColumn)
                        || isFishReachable(world, pinguRow, pinguColumn - 1)
                        || isFishReachable(world, pinguRow, pinguColumn + 1);
                break;
            case 1:
                fishReachable = isFishReachable(world, pinguRow - 1, pinguColumn - 1)
                        || isFishReachable(world, pinguRow - 1, pinguColumn + 1)
                        || isFishReachable(world, pinguRow + 1, pinguColumn - 1)
                        || isFishReachable(world, pinguRow + 1, pinguColumn + 1);
                break;
            case 2:
                fishReachable = isFishReachable(world, pinguRow - 3, pinguColumn - 3)
                        || isFishReachable(world, pinguRow - 3, pinguColumn + 3)
                        || isFishReachable(world, pinguRow + 3, pinguColumn - 3)
                        || isFishReachable(world, pinguRow + 3, pinguColumn + 3);
                break;
        }
        world[pinguRow][pinguColumn] = terrain; // Restore terrain
        return fishReachable;
    }

    // ... existing code ...

}