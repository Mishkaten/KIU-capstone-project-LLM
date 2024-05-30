import java.util.PriorityQueue;

public class OpsOnLists {
    // Method to merge k sorted lists
    public static IntDoubleList mergeKSortedLists(IntDoubleList[] lists) {
        IntDoubleList mergedList = new IntDoubleList();
        PriorityQueue<IntDoubleListElement> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.getValue(), b.getValue()));

        // Initialize the min heap with the head of each list
        for (IntDoubleList list : lists) {
            if (list.getHead() != null) {
                minHeap.offer(list.getHead());
            }
        }

        // Merge lists
        while (!minHeap.isEmpty()) {
            IntDoubleListElement minElement = minHeap.poll();
            mergedList.add(minElement.getValue());

            if (minElement.getNext() != null) {
                minHeap.offer(minElement.getNext());
            }
        }

        return mergedList;
    }

    // Method to rearrange a list based on a pivot value x
    public static IntDoubleList rearrangeList(IntDoubleList list, int x) {
        IntDoubleList lessThanX = new IntDoubleList();
        IntDoubleList greaterOrEqualX = new IntDoubleList();
        IntDoubleListElement current = list.getHead();

        // Split the list into two
        while (current != null) {
            if (current.getValue() < x) {
                lessThanX.add(current.getValue());
            } else {
                greaterOrEqualX.add(current.getValue());
            }
            current = current.getNext();
        }

        // Merge the two lists
        if (lessThanX.getTail() != null) {
            lessThanX.getTail().next = greaterOrEqualX.getHead();
            if (greaterOrEqualX.getHead() != null) {
                greaterOrEqualX.getHead().prev = lessThanX.getTail();
            }
        } else {
            return greaterOrEqualX;
        }

        return lessThanX;
    }

    // Method to permute the list
    public static IntDoubleList permuteList(IntDoubleList list) {
        IntDoubleListElement head = list.getHead();
        IntDoubleListElement tail = list.getTail();
        IntDoubleList permutedList = new IntDoubleList();

        while (head != tail && head.getPrev() != tail) {
            permutedList.add(head.getValue());
            permutedList.add(tail.getValue());

            head = head.getNext();
            tail = tail.getPrev();
        }

        // If the list has odd number of elements
        if (head == tail) {
            permutedList.add(head.getValue());
        }

        return permutedList;
    }
}
