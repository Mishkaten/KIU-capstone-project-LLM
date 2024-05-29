public class GamesWithArrays {

    public static int[] otherSort(int[] arr1, int[] arr2) {
        Map<Integer, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            orderMap.put(arr2[i], i);
        }

        List<Integer> notInArr2 = new ArrayList<>();
        Arrays.sort(arr1, (a, b) -> {
            if (orderMap.containsKey(a) && orderMap.containsKey(b)) {
                return orderMap.get(a) - orderMap.get(b);
            } else if (orderMap.containsKey(a)) {
                return -1;
            } else if (orderMap.containsKey(b)) {
                return 1;
            } else {
                notInArr2.add(a);
                return 0;
            }
        });

        for (int i = 0; i < notInArr2.size(); i++) {
            arr1[arr2.length + i] = notInArr2.get(i);
        }

        return arr1;
    }

    public static int[] pinguMerchant(int[] arr) {
        int n = arr.length;
        if (n == 0) return new int[]{0, 0, 0};

        int minPrice = arr[0], maxProfit = 0;
        int buyDay = 0, sellDay = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] < minPrice) {
                minPrice = arr[i];
                buyDay = i;
            }
            if (arr[i] - minPrice > maxProfit) {
                maxProfit = arr[i] - minPrice;
                sellDay = i;
            }
        }

        if (maxProfit > 0) {
            return new int[]{buyDay, sellDay, maxProfit};
        } else {
            return new int[]{0, 0, 0};
        }
    }

    public static int[] fairFriends(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumB - sumA) / 2;

        Set<Integer> setB = Arrays.stream(B).boxed().collect(Collectors.toSet());

        for (int x : A) {
            if (setB.contains(x + delta)) {
                return new int[]{x, x + delta};
            }
        }

        return new int[0]; // No valid swap found
    }

    public static boolean theAlps(int[] arr) {
        if (arr.length < 3) return false;

        int i = 0;
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) i++;
        if (i == 0 || i == arr.length - 1) return false;

        while (i < arr.length - 1 && arr[i] > arr[i + 1]) i++;
        return i == arr.length - 1;
    }

    public static int pinguFriends(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxGroupSize = 0;
        for (int groupSize = 2; groupSize <= arr.length; groupSize++) {
            boolean validGroupSize = true;
            for (int freq : freqMap.values()) {
                if (freq % groupSize != 0) {
                    validGroupSize = false;
                    break;
                }
            }
            if (validGroupSize) {
                maxGroupSize = groupSize;
            }
        }

        return maxGroupSize;
    }

    public static void main(String[] args) {
        // Example calls for testing
        System.out.println(Arrays.toString(otherSort(
                new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19},
                new int[]{2, 1, 4, 3, 9, 6}
        )));

        System.out.println(Arrays.toString(pinguMerchant(
                new int[]{7, 1, 5, 3, 6, 4}
        )));

        System.out.println(Arrays.toString(fairFriends(
                new int[]{1, 2, 5},
                new int[]{2, 4}
        )));

        System.out.println(theAlps(
                new int[]{3, 5, 6, 7, 5, 3, 1}
        ));

        System.out.println(pinguFriends(
                new int[]{1, 2, 3, 4, 4, 3, 2, 1}
        ));
    }
}
