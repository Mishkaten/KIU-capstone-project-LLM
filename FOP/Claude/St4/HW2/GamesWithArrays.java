package fop.w3array;

import java.util.HashMap;
import java.util.Map;

public class GamesWithArrays {

    public static int[] otherSort(int[] arr1, int[] arr2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int[] result = new int[arr1.length];
        int index = 0;

        for (int num : arr2) {
            int count = countMap.getOrDefault(num, 0);
            for (int i = 0; i < count; i++) {
                result[index++] = num;
            }
            countMap.remove(num);
        }

        for (int num : arr1) {
            if (countMap.containsKey(num)) {
                result[index++] = num;
            }
        }

        return result;
    }

    public static int[] fairFriends(int[] A, int[] B) {
        int sumA = sum(A);
        int sumB = sum(B);

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int newSumA = sumA - A[i] + B[j];
                int newSumB = sumB - B[j] + A[i];
                if (newSumA == newSumB) {
                    return new int[]{A[i], B[j]};
                }
            }
        }

        return new int[]{0, 0};
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static boolean alps(int[] arr) {
        if (arr.length < 2) {
            return false;
        }

        int peakIndex = findPeakIndex(arr);
        if (peakIndex == -1) {
            return false;
        }

        return isAscending(arr, 0, peakIndex) && isDescending(arr, peakIndex, arr.length - 1);
    }

    private static int findPeakIndex(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isAscending(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] >= arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDescending(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] <= arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] plancton(int[] arr) {
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minPrice) {
                minPrice = arr[i];
                buyDay = i;
            } else if (arr[i] - minPrice > maxProfit) {
                maxProfit = arr[i] - minPrice;
                sellDay = i;
            }
        }

        if (maxProfit == 0) {
            return new int[]{0, 0, 0};
        }

        return new int[]{buyDay, sellDay, maxProfit};
    }

    public static int pinguFriends(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int maxGroupSize = 0;

        for (int count : countMap.values()) {
            if (count >= 2) {
                maxGroupSize = Math.max(maxGroupSize, count);
            }
        }

        for (int count : countMap.values()) {
            if (count > 0 && count != maxGroupSize) {
                return 0;
            }
        }

        return maxGroupSize;
    }
}