//package GPT.St2.HW6;
//
//public class IntDoubleList {
//    private IntDoubleListElement head;
//
//    public IntDoubleList() {
//        this.head = null;
//    }
//
//    public IntDoubleList(IntDoubleListElement head) {
//        this.head = head;
//    }
//
//    public IntDoubleListElement getFirst() {
//        return head;
//    }
//
//    public void append(int value) {
//        if (head == null) {
//            head = new IntDoubleListElement(value);
//        } else {
//            IntDoubleListElement current = head;
//            while (current.getNext() != null) {
//                current = current.getNext();
//            }
//            current.setNext(new IntDoubleListElement(value));
//        }
//    }
//}
//
