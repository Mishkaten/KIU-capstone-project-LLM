package St1.HW2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Test otherSort
        int[] arr1 = {5, 3, 9, 7, 1};
        int[] arr2 = {3, 1, 9};
        int[] sortedArr = GamesWithArrays.otherSort(arr1, arr2);
        System.out.println("otherSort result: " + Arrays.toString(sortedArr));
        // Expected output: otherSort result: [3, 1, 9, 5, 7]

        // Test plancton
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] planctonResult = GamesWithArrays.plancton(prices);
        System.out.println("plancton result: " + Arrays.toString(planctonResult));
        // Expected output: plancton result: [1, 4, 5]

        // Test fairFriends
        int[] A = {1, 2, 3};
        int[] B = {4, 5, 6};
        int[] fairFriendsResult = GamesWithArrays.fairFriends(A, B);
        System.out.println("fairFriends result: " + Arrays.toString(fairFriendsResult));
        // Expected output: fairFriends result: null (or [1, 5] or another valid pair)

        // Test alps
        int[] mountainArr = {1, 3, 2};
        boolean isMountain = GamesWithArrays.alps(mountainArr);
        System.out.println("alps result: " + isMountain);
        // Expected output: alps result: true

        int[] nonMountainArr = {1, 2, 2};
        boolean isNotMountain = GamesWithArrays.alps(nonMountainArr);
        System.out.println("alps result: " + isNotMountain);
        // Expected output: alps result: false

        // Test pinguFriends
        int[] pinguArr = {2, 2, 3, 3, 4, 4, 4};
        int maxGroupSize = GamesWithArrays.pinguFriends(pinguArr);
        System.out.println("pinguFriends result: " + maxGroupSize);
        // Expected output: pinguFriends result: 3
    }
}
