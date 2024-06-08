package Claude.St3.HW2;

import java.util.HashMap;
import java.util.Map;

public class GamesWithArrays {

    // Alternative Sorting
    public static int[] otherSort(int[] arr1, int[] arr2) {
        Map<Integer, Integer> elementIndex = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            elementIndex.put(arr2[i], i);
        }
        int nextIndex = arr2.length;
        for (int i = 0; i < arr1.length; i++) {
            if (!elementIndex.containsKey(arr1[i])) {
                elementIndex.put(arr1[i], nextIndex++);
            }
        }
        return java.util.Arrays.stream(arr1).boxed().sorted((a, b) -> Integer.compare(elementIndex.get(a), elementIndex.get(b))).mapToInt(Integer::intValue).toArray();
    }

    // Pingu Merchant
    public static int[] plancton(int[] arr) {
        int buyDay = 0, sellDay = 0, maxProfit = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int profit = arr[j] - arr[i];
                if (profit > maxProfit) {
                    buyDay = i;
                    sellDay = j;
                    maxProfit = profit;
                }
            }
        }
        return new int[]{buyDay, sellDay, maxProfit};
    }

    // Fair Friends
    public static int[] fairFriends(int[] arr1, int[] arr2) {
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum1 += arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            sum2 += arr2[i];
        }
        int diff = sum1 - sum2;
        if (diff % 2 == 1) {
            return null;
        }
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] - arr2[j] == diff / 2) {
                    return new int[]{arr1[i], arr2[j]};
                }
            }
        }
        return null;
    }

    // The Alps
    public static boolean alps(int[] arr) {
        if (arr.length < 3) return false;
        int i = 1;
        while (i < arr.length && arr[i - 1] < arr[i]) i++;
        while (i < arr.length && arr[i - 1] > arr[i]) i++;
        return i == arr.length;
    }

    // Pingu Friends
    public static int pinguFriends(int[] arr) {
        int[] counts = new int[100];
        for (int num : arr) {
            counts[num]++;
        }

        for (int size = arr.length / 2; size >= 2; size--) { // Iterate from larger to smaller group sizes
            if (arr.length % size == 0) { // Check if the array length is divisible by this group size
                boolean possible = true;
                for (int num = 0; num < counts.length; num++) {
                    if (counts[num] > 0 && counts[num] != size) {
                        possible = false;
                        break;
                    }
                }
                if (possible) return size; // If a valid group size is found, return it immediately
            }
        }
        return 0; // No valid group size found
    }

}
