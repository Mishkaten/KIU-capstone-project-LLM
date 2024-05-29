package fop.w4pick;

public class PickSix extends MiniJava {
    // sorting method from the lecture
    public static int[] sort(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            // begin of insert
            int j = 0;
            while (j < i && a[i] > b[j]) ++j;
            // end of locate
            for (int k = i - 1; k >= j; --k) b[k + 1] = b[k];
            // end of shift
            b[j] = a[i];
            // end of insert
        }
        return b;
    } // end of sort

    public static void main(String[] args) throws IllegalAccessException {
        // TODO
    }

    public static void outputStapel(int[][] stapel) {
        // TODO
    }

    public static int playerSelectCard(int player, int[][] playerCards) {
        // TODO
        return 0;
    }

    public static int calculatePoints(int[] lostCards) {
        // TODO
        return 0;
    }

    public static void outputResult(int[] playerPoints) {
        // TODO
    }

    public static int getValueOfCard(int card) {
        // TODO
        return 0;
    }

    public static void givePlayerCards(int[][] playerCards) throws IllegalAccessException {
        // TODO
    }
}
