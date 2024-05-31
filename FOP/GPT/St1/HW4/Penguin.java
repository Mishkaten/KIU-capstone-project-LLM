package GPT.St1.HW4;

public class Penguin extends MiniJava {

    public static void printWorld(int[][] world, int pinguRow, int pinguColumn) {
        //TODO
    }

    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn){
        //TODO
        return false;
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
