package St1.HW2;

import java.util.Arrays;
import java.util.HashMap;

public class GamesWithArrays {

    // Alternative Sorting
    public static int[] otherSort(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        int resultIndex = 0;
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        // Count occurrences in arr1
        for (int num : arr1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Place elements from arr2 in order
        for (int num : arr2) {
            int count = freqMap.get(num);
            for (int i = 0; i < count; i++) {
                result[resultIndex++] = num;
            }
            freqMap.remove(num); // Remove after placing
        }

        // Place remaining elements from arr1
        for (int num : arr1) {
            if (freqMap.containsKey(num)) {
                result[resultIndex++] = num;
                freqMap.remove(num);
            }
        }

        return result;
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
    public static int[] fairFriends(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int diff = sumA - sumB;

        HashMap<Integer, Integer> mapB = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            mapB.put(B[i], i);
        }

        for (int i = 0; i < A.length; i++) {
            int complement = diff + A[i];
            if (mapB.containsKey(complement)) {
                return new int[]{A[i], complement};
            }
        }
        return null; // No solution found
    }

    // The Alps
    public static boolean alps(int[] arr) {
        int i = 1;
        while (i < arr.length && arr[i] > arr[i - 1]) {
            i++;
        }

        if (i == 1 || i == arr.length) return false; // No ascent or descent

        while (i < arr.length && arr[i] < arr[i - 1]) {
            i++;
        }
        return i == arr.length; // Reached the end during descent
    }


    // Pingu Friends
    public static int pinguFriends(int[] arr) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxGroupSize = 0;
        for (int count : freqMap.values()) {
            if (count >= 2 && arr.length % count == 0) {
                maxGroupSize = Math.max(maxGroupSize, count);
            }
        }
        return maxGroupSize;
    }
}
