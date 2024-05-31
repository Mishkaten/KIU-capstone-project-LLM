package GPT.St3.HW2;
import java.util.*;


public class GamesWithArrays {
    public static void main(String[] args) {
        // You can add test cases here to test the methods
    }

    // Method for OtherSort
    public static int[] otherSort(int[] arr1, int[] arr2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> remaining = new ArrayList<>();

        for (int num : arr1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : arr2) {
            int count = countMap.getOrDefault(num, 0);
            for (int i = 0; i < count; i++) {
                result.add(num);
            }
            countMap.remove(num);
        }

        for (int num : arr1) {
            if (countMap.containsKey(num)) {
                remaining.add(num);
            }
        }

        result.addAll(remaining);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Method for Pingu Merchant
    public static int[] pinguMerchant(int[] arr) {
        int buyDay = 0, sellDay = 0, maxProfit = 0;
        int minPriceIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minPriceIndex]) {
                minPriceIndex = i;
            }
            int profit = arr[i] - arr[minPriceIndex];
            if (profit > maxProfit) {
                maxProfit = profit;
                buyDay = minPriceIndex;
                sellDay = i;
            }
        }

        if (maxProfit <= 0) {
            return new int[]{0, 0, 0};
        }

        return new int[]{buyDay, sellDay, maxProfit};
    }

    // Method for Fair Friends
    public static int[] fairFriends(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumB - sumA) / 2;
        Set<Integer> setB = new HashSet<>();
        for (int b : B) {
            setB.add(b);
        }
        for (int a : A) {
            if (setB.contains(a + delta)) {
                return new int[]{a, a + delta};
            }
        }
        return null; // Should never reach here if input guarantees a solution
    }

    // Method for The Alps
    public static boolean theAlps(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        int i = 0;
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        if (i == 0 || i == arr.length - 1) {
            return false;
        }

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
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
