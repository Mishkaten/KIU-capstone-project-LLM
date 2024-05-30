public class IntDoubleList {
    private IntDoubleListElement head;

    public IntDoubleList() {
        this.head = null;
    }

    public IntDoubleList(IntDoubleListElement head) {
        this.head = head;
    }

    public IntDoubleListElement getFirst() {
        return head;
    }

    public void append(int value) {
        if (head == null) {
            head = new IntDoubleListElement(value);
        } else {
            IntDoubleListElement current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new IntDoubleListElement(value));
        }
    }
}

public class IntDoubleListElement {
    private int key;
    private IntDoubleListElement next;

    public IntDoubleListElement(int key) {
        this.key = key;
        this.next = null;
    }

    public int getKey() {
        return key;
    }

    public IntDoubleListElement getNext() {
        return next;
    }

    public void setNext(IntDoubleListElement next) {
        this.next = next;
    }
}
