// Reading the content of the file OpsOnLists.java
public class OpsOnLists {

    // Method to merge multiple sorted lists into one sorted list
    public static IntDoubleList mergeLists(IntDoubleList[] lists) {
        IntDoubleList mergedList = new IntDoubleList();
        java.util.PriorityQueue<IntDoubleListElement> pq = new java.util.PriorityQueue<>(lists.length, (a, b) -> a.value - b.value);

        for (IntDoubleList list : lists) {
            if (list.getHead() != null) {
                pq.add(list.getHead());
            }
        }

        while (!pq.isEmpty()) {
            IntDoubleListElement minElement = pq.poll();
            mergedList.add(minElement.value);
            if (minElement.next != null) {
                pq.add(minElement.next);
            }
        }

        return mergedList;
    }

    // Method to rearrange a list such that all elements less than x are before elements greater than or equal to x
    public static IntDoubleList rearrangeList(IntDoubleList list, int x) {
        IntDoubleList lessList = new IntDoubleList();
        IntDoubleList greaterOrEqualList = new IntDoubleList();

        IntDoubleListElement current = list.getHead();
        while (current != null) {
            if (current.value < x) {
                lessList.add(current.value);
            } else {
                greaterOrEqualList.add(current.value);
            }
            current = current.next;
        }

        if (lessList.getHead() == null) {
            return greaterOrEqualList;
        } else {
            lessList.getTail().next = greaterOrEqualList.getHead();
            if (greaterOrEqualList.getHead() != null) {
                greaterOrEqualList.getHead().prev = lessList.getTail();
            }
            return lessList;
        }
    }

    // Method to permute a list in the pattern x0 -> xn -> x1 -> x(n-1) -> ...
    public static IntDoubleList permuteList(IntDoubleList list) {
        if (list.getHead() == null || list.getHead().next == null) {
            return list;
        }

        IntDoubleListElement head = list.getHead();
        IntDoubleListElement tail = list.getTail();
        IntDoubleList permutedList = new IntDoubleList();

        boolean toggle = true;
        while (head != tail && head.prev != tail) {
            if (toggle) {
                permutedList.add(head.value);
                head = head.next;
            } else {
                permutedList.add(tail.value);
                tail = tail.prev;
            }
            toggle = !toggle;
        }
        permutedList.add(head.value);

        return permutedList;
    }

    public static void main(String[] args) {
        // Example of merging lists
        IntDoubleList list1 = new IntDoubleList();
        list1.add(1);
        list1.add(4);
        list1.add(5);

        IntDoubleList list2 = new IntDoubleList();
        list2.add(1);
        list2.add(3);
        list2.add(4);

        IntDoubleList list3 = new IntDoubleList();
        list3.add(2);
        list3.add(6);

        IntDoubleList[] lists = {list1, list2, list3};
        IntDoubleList mergedList = mergeLists(lists);
        System.out.println("Merged list:");
        mergedList.printList();

        // Example of rearranging list
        IntDoubleList listToRearrange = new IntDoubleList();
        listToRearrange.add(1);
        listToRearrange.add(4);
        listToRearrange.add(3);
        listToRearrange.add(2);
        listToRearrange.add(5);
        listToRearrange.add(2);
        IntDoubleList rearrangedList = rearrangeList(listToRearrange, 3);
        System.out.println("Rearranged list:");
        rearrangedList.printList();

        // Example of permuting list
        IntDoubleList listToPermute = new IntDoubleList();
        listToPermute.add(1);
        listToPermute.add(2);
        listToPermute.add(3);
        listToPermute.add(4);
        listToPermute.add(5);
        IntDoubleList permutedList = permuteList(listToPermute);
        System.out.println("Permuted list:");
        permutedList.printList();
    }
}
