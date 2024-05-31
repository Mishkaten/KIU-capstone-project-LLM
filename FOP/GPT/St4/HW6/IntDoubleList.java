//package GPT.St4.HW6;
//
//public class IntDoubleList {
//    private IntDoubleListElement head;
//    private IntDoubleListElement tail;
//
//    public IntDoubleList() {
//        head = null;
//        tail = null;
//    }
//
//    public void add(int value) {
//        IntDoubleListElement newNode = new IntDoubleListElement(value);
//        if (head == null) {
//            head = newNode;
//            tail = newNode;
//        } else {
//            tail.next = newNode;
//            newNode.prev = tail;
//            tail = newNode;
//        }
//    }
//
//    public IntDoubleListElement getHead() {
//        return head;
//    }
//
//    public IntDoubleListElement getTail() {
//        return tail;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        IntDoubleListElement current = head;
//        while (current != null) {
//            sb.append(current.value).append("->");
//            current = current.next;
//        }
//        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
//    }
//}
