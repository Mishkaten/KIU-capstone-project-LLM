package St1.HW6;

import java.util.PriorityQueue;

public class OpsOnLists {

    // Merging of multiple lists
    public static IntDoubleList reuniteLists(IntDoubleList[] arrayOfLists) {
        IntDoubleList result = new IntDoubleList();
        PriorityQueue<IntDoubleListElement> minHeap = new PriorityQueue<>((a, b) -> a.getInfo() - b.getInfo());

        // Add the first element of each list to the minHeap
        for (IntDoubleList list : arrayOfLists) {
            if (list.getFirstElement() != null) {
                minHeap.offer(list.getFirstElement());
            }
        }

        while (!minHeap.isEmpty()) {
            IntDoubleListElement minElement = minHeap.poll();
            result.append(minElement.getInfo());

            // Add the next element from the same list if it exists
            if (minElement.next != null) {
                minHeap.offer(minElement.next);
            }
        }

        return result;
    }

    // Re-arranging lists
    public static IntDoubleList partTheList(IntDoubleList list, int value) {
        IntDoubleList before = new IntDoubleList();
        IntDoubleList after = new IntDoubleList();
        IntDoubleListElement current = list.getFirstElement();

        while (current != null) {
            if (current.getInfo() < value) {
                before.append(current.getInfo());
            } else {
                after.append(current.getInfo());
            }
            current = current.next;
        }

        before.getLastElement().next = after.getFirstElement(); // Connect the two lists
        if (after.getFirstElement() != null) {
            after.getFirstElement().prev = before.getLastElement();
        }
        return before; // Return the head of the combined list
    }

    // Permuting Lists
    public static IntDoubleList mixedList(IntDoubleList list) {
        if (list.size() <= 2) {
            return list.copy(); // No permutation needed for small lists
        }

        IntDoubleListElement slow = list.getFirstElement();
        IntDoubleListElement fast = list.getFirstElement().next;

        // Find the middle of the list using slow and fast pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        IntDoubleListElement middle = slow;
        IntDoubleListElement secondHalf = middle.next;
        middle.next = null; // Break the list into two halves

        // Reverse the second half
        secondHalf = reverseList(secondHalf);

        // Merge the first half and the reversed second half
        return mergeLists(list.getFirstElement(), secondHalf);
    }

    private static IntDoubleListElement reverseList(IntDoubleListElement head) {
        IntDoubleListElement prev = null;
        IntDoubleListElement current = head;
        while (current != null) {
            IntDoubleListElement next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    private static IntDoubleList mergeLists(IntDoubleListElement first, IntDoubleListElement second) {
        IntDoubleList result = new IntDoubleList();
        IntDoubleListElement current = result.getFirstElement();

        while (first != null || second != null) {
            if (second == null || (first != null && first.getInfo() < second.getInfo())) {
                if (current == null) {
                    result.append(first.getInfo());
                    current = result.getFirstElement();
                } else {
                    current.next = new IntDoubleListElement(first.getInfo());
                    current.next.prev = current;
                    current = current.next;
                }
                first = first.next;
            } else {
                if (current == null) {
                    result.append(second.getInfo());
                    current = result.getFirstElement();
                } else {
                    current.next = new IntDoubleListElement(second.getInfo());
                    current.next.prev = current;
                    current = current.next;
                }
                second = second.next;
            }
        }
        return result;
    }
}
