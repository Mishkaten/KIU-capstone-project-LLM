import java.util.PriorityQueue;
import java.util.Comparator;

public class OpsOnLists {

    static class ListNodeWrapper {
        IntDoubleListElement node;

        ListNodeWrapper(IntDoubleListElement node) {
            this.node = node;
        }
    }

    public static IntDoubleList mergeLists(IntDoubleList[] lists) {
        PriorityQueue<ListNodeWrapper> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.node.getKey()));

        for (IntDoubleList list : lists) {
            if (list != null && list.getFirst() != null) {
                pq.add(new ListNodeWrapper(list.getFirst()));
            }
        }

        IntDoubleList mergedList = new IntDoubleList();
        while (!pq.isEmpty()) {
            ListNodeWrapper wrapper = pq.poll();
            mergedList.append(wrapper.node.getKey());
            if (wrapper.node.getNext() != null) {
                pq.add(new ListNodeWrapper(wrapper.node.getNext()));
            }
        }

        return mergedList;
    }

    public static IntDoubleList rearrangeList(IntDoubleList list, int x) {
        IntDoubleList less = new IntDoubleList();
        IntDoubleList greaterEqual = new IntDoubleList();
        IntDoubleListElement current = list.getFirst();

        while (current != null) {
            if (current.getKey() < x) {
                less.append(current.getKey());
            } else {
                greaterEqual.append(current.getKey());
            }
            current = current.getNext();
        }

        if (less.getFirst() == null) {
            return greaterEqual;
        }

        IntDoubleListElement lessTail = less.getFirst();
        while (lessTail.getNext() != null) {
            lessTail = lessTail.getNext();
        }
        lessTail.setNext(greaterEqual.getFirst());
        return less;
    }

    public static IntDoubleList permuteList(IntDoubleList list) {
        if (list.getFirst() == null || list.getFirst().getNext() == null) {
            return list;
        }

        IntDoubleListElement slow = list.getFirst();
        IntDoubleListElement fast = list.getFirst();

        while (fast != null && fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        IntDoubleListElement secondHalf = slow.getNext();
        slow.setNext(null);
        secondHalf = reverseList(secondHalf);

        IntDoubleListElement firstHalf = list.getFirst();
        IntDoubleListElement dummy = new IntDoubleListElement(0);
        IntDoubleListElement current = dummy;

        while (firstHalf != null || secondHalf != null) {
            if (firstHalf != null) {
                current.setNext(firstHalf);
                firstHalf = firstHalf.getNext();
                current = current.getNext();
            }

            if (secondHalf != null) {
                current.setNext(secondHalf);
                secondHalf = secondHalf.getNext();
                current = current.getNext();
            }
        }

        return new IntDoubleList(dummy.getNext());
    }

    private static IntDoubleListElement reverseList(IntDoubleListElement head) {
        IntDoubleListElement prev = null;
        IntDoubleListElement current = head;

        while (current != null) {
            IntDoubleListElement next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        // Add test code to demonstrate the methods
    }
}
