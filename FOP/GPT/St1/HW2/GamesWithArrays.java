package fop.intro.pop;

// Reading the content of the file GamesWithArrays.java
import java.util.*;

public class GamesWithArrays {

    // Method for Alternative Sorting
    public static int[] otherSort(int[] arr1, int[] arr2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int index = 0;
        for (int num : arr2) {
            while (countMap.getOrDefault(num, 0) > 0) {
                arr1[index++] = num;
                countMap.put(num, countMap.get(num) - 1);
            }
        }

        for (int num : arr1) {
            if (countMap.getOrDefault(num, 0) > 0) {
                while (countMap.getOrDefault(num, 0) > 0) {
                    arr1[index++] = num;
                    countMap.put(num, countMap.get(num) - 1);
                }
            }
        }

        return arr1;
    }

    // Method for Pingu Merchant
    public static int[] pinguMerchant(int[] arr) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minPrice) {
                minPrice = arr[i];
                buyDay = i;
            }

            if (arr[i] - minPrice > maxProfit) {
                maxProfit = arr[i] - minPrice;
                sellDay = i;
            }
        }

        if (maxProfit == 0) {
            return new int[]{0, 0, 0};
        }

        return new int[]{buyDay, sellDay, maxProfit};
    }

    // Method for Fair Friends
    public static int[] fairFriends(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> setA = new HashSet<>();
        for (int num : A) {
            setA.add(num);
        }

        for (int num : B) {
            if (setA.contains(num + delta)) {
                return new int[]{num + delta, num};
            }
        }

        return new int[]{0, 0}; // default return in case no solution found, though the problem guarantees a solution
    }

    // Method for The Alps
    public static boolean theAlps(int[] arr) {
        if (arr.length < 3) return false;

        int i = 0;
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        if (i == 0 || i == arr.length - 1) return false;

        while (i < arr.length - 1 && arr[i] > arr[i + 1]) {
            i++;
        }

        return i == arr.length - 1;
    }

    // Method for Pingu Friends
    public static int pinguFriends(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int gcd = 0;
        for (int count : countMap.values()) {
            gcd = gcd(gcd, count);
        }

        return gcd >= 2 ? gcd : 0;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
