package Claude.St1.HW2;

public class GamesWithArrays {
    public static int[] otherSort(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        int index = 0;

        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] == arr2[i]) {
                    result[index++] = arr1[j];
                }
            }
        }

        for (int i = 0; i < arr1.length; i++) {
            boolean found = false;
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result[index++] = arr1[i];
            }
        }

        return result;
    }
    public static int[] plancton(int[] arr) {
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int profit = arr[j] - arr[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                    buyDay = i;
                    sellDay = j;
                }
            }
        }

        if (maxProfit > 0) {
            return new int[]{buyDay, sellDay, maxProfit};
        } else {
            return new int[]{0, 0, 0};
        }
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
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static boolean alps(int[] arr) {
        if (arr.length < 2) {
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

    public static int pinguFriends(int[] arr) {
        int[] count = new int[100];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        int maxGroupSize = 0;

        for (int i = 1; i < count.length; i++) {
            if (count[i] >= 2 && count[i] % 2 == 0) {
                maxGroupSize = Math.max(maxGroupSize, count[i]);
            }
        }

        return maxGroupSize;
    }
}
