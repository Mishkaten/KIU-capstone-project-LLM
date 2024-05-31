//package GPT.St4.HW6;
//
//import java.util.PriorityQueue;
//
//public class OpsOnLists {
//    public static IntDoubleList reuniteLists(IntDoubleList[] arrayOfLists) {
//        PriorityQueue<IntDoubleListElement> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
//        for (IntDoubleList list : arrayOfLists) {
//            if (list.getHead() != null) {
//                pq.add(list.getHead());
//            }
//        }
//
//        IntDoubleList mergedList = new IntDoubleList();
//        while (!pq.isEmpty()) {
//            IntDoubleListElement smallest = pq.poll();
//            mergedList.add(smallest.value);
//            if (smallest.next != null) {
//                pq.add(smallest.next);
//            }
//        }
//        return mergedList;
//    }
//
//    public static IntDoubleList partTheList(IntDoubleList list, int value) {
//        IntDoubleList lessThan = new IntDoubleList();
//        IntDoubleList greaterOrEqual = new IntDoubleList();
//
//        IntDoubleListElement current = list.getHead();
//        while (current != null) {
//            if (current.value < value) {
//                lessThan.add(current.value);
//            } else {
//                greaterOrEqual.add(current.value);
//            }
//            current = current.next;
//        }
//
//        if (lessThan.getTail() != null) {
//            lessThan.getTail().next = greaterOrEqual.getHead();
//        }
//        if (greaterOrEqual.getHead() != null) {
//            greaterOrEqual.getHead().prev = lessThan.getTail();
//        }
//
//        return lessThan.getHead() != null ? lessThan : greaterOrEqual;
//    }
//
//    public static IntDoubleList mixedList(IntDoubleList list) {
//        if (list.getHead() == null || list.getHead().next == null) {
//            return list;
//        }
//
//        IntDoubleListElement mid = getMiddleElement(list);
//        IntDoubleListElement secondHalf = mid.next;
//        mid.next = null;
//        secondHalf.prev = null;
//
//        IntDoubleList mixed = new IntDoubleList();
//        IntDoubleListElement firstHalf = list.getHead();
//
//        while (firstHalf != null || secondHalf != null) {
//            if (firstHalf != null) {
//                mixed.add(firstHalf.value);
//                firstHalf = firstHalf.next;
//            }
//            if (secondHalf != null) {
//                mixed.add(secondHalf.value);
//                secondHalf = secondHalf.next;
//            }
//        }
//
//        return mixed;
//    }
//
//    private static IntDoubleListElement getMiddleElement(IntDoubleList list) {
//        IntDoubleListElement slow = list.getHead();
//        IntDoubleListElement fast = list.getHead();
//
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
//    }
//}
