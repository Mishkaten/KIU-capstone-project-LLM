package St3.HW2;

import java.util.HashMap;
import java.util.Map;

public class GamesWithArrays {

    // Alternative Sorting (Corrected)
    public static int[] otherSort(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        int index = 0;

        // Track occurrences of numbers in arr2
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : arr2) {
            occurrences.put(num, 0);
        }

        // Place numbers from arr1 in the order of arr2, handling duplicates
        for (int num : arr2) { // Iterate through arr2 to ensure correct order
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == num && occurrences.get(num) < arr1.length) {
                    result[index++] = num;
                    occurrences.put(num, occurrences.get(num) + 1);
                }
            }
        }

        // Add remaining elements from arr1 that weren't in arr2
        for (int num : arr1) {
            if (!occurrences.containsKey(num) || occurrences.get(num) == 0) {
                result[index++] = num;
            }
        }

        return result;
    }

    // Pingu Merchant (Optimal Plancton Buying/Selling)
    public static int[] plancton(int[] arr) {
        int buyDay = 0, sellDay = 0, maxProfit = 0;
        int minPriceSoFar = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minPriceSoFar) {
                minPriceSoFar = arr[i];
                buyDay = i;
            } else if (arr[i] - minPriceSoFar > maxProfit) {
                maxProfit = arr[i] - minPriceSoFar;
                sellDay = i;
            }
        }

        return maxProfit > 0 ? new int[]{buyDay, sellDay, maxProfit} : new int[]{0, 0, 0};
    }

    // Fair Friends (Chocolate Slab Exchange)
    public static int[] fairFriends(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int a : A) sumA += a;
        for (int b : B) sumB += b;

        int target = (sumA + sumB) / 2;

        // Find the difference needed to reach target
        for (int a : A) {
            for (int b : B) {
                if (sumA - a + b == target) {
                    return new int[]{a, b};
                }
            }
        }

        return new int[]{}; // No solution found
    }


    // The Alps (Checking for Alpine Array)
    public static boolean alps(int[] arr) {
        int n = arr.length;
        int i = 0;

        // Find peak (increasing phase)
        while (i + 1 < n && arr[i] < arr[i + 1]) {
            i++;
        }

        // Peak can't be at the start or end
        if (i == 0 || i == n - 1) {
            return false;
        }

        // Descending phase
        while (i + 1 < n && arr[i] > arr[i + 1]) {
            i++;
        }

        // If we reach the end, it's an alpine array
        return i == n - 1;
    }

    // Pingu Friends (Finding Equally Sized Groups)
    public static int pinguFriends(int[] arr) {
        // Count occurrences of each penguin number
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // Find the greatest common divisor (GCD) of the counts
        int gcd = 0;
        for (int count : counts.values()) {
            if (count < 2) return 0; // Groups must have at least 2 members
            gcd = gcd(gcd, count);
        }

        return gcd;
    }

    // Helper function to find the greatest common divisor (GCD)
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
