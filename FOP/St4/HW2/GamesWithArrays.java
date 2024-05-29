public class GamesWithArrays {

    public static int[] otherSort(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        int index = 0;

        // Map element of arr2 to its index
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            indexMap.put(arr2[i], i);
        }

        // Add elements of arr1 that are in arr2, in order
        for (int num : arr2) {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == num) {
                    result[index++] = arr1[i];
                }
            }
        }

        // Add elements of arr1 that are not in arr2
        for (int num : arr1) {
            if (!indexMap.containsKey(num)) {
                result[index++] = num;
            }
        }
        return result;
    }

    public static int[] fairFriends(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int a : A) sumA += a;
        for (int b : B) sumB += b;

        int delta = (sumA - sumB) / 2;

        HashSet<Integer> setA = new HashSet<>();
        for (int a : A) setA.add(a);

        for (int b : B) {
            if (setA.contains(b + delta)) {
                return new int[]{b + delta, b};
            }
        }

        return new int[0];
    }

    public static boolean alps(int[] arr) {
        if (arr.length < 3) return false;

        int i = 0;
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) i++;

        if (i == 0 || i == arr.length - 1) return false;

        while (i < arr.length - 1 && arr[i] > arr[i + 1]) i++;

        return i == arr.length - 1;
    }

    public static int[] plancton(int[] arr) {
        int n = arr.length;
        if (n == 0) return new int[]{0, 0, 0};

        int minPrice = arr[0];
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] < minPrice) {
                minPrice = arr[i];
                buyDay = i;
            } else if (arr[i] - minPrice > maxProfit) {
                maxProfit = arr[i] - minPrice;
                sellDay = i;
            }
        }

        if (maxProfit == 0) return new int[]{0, 0, 0};
        return new int[]{buyDay, sellDay, maxProfit};
    }

    public static int pinguFriends(int[] arr) {
        if (arr.length < 2) return 0;

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int gcd = 0;
        for (int count : countMap.values()) {
            gcd = gcd(gcd, count);
            if (gcd == 1) return 0;
        }

        return gcd;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
