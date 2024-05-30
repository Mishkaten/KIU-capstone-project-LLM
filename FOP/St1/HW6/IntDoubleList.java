// Reading the content of the file IntDoubleList.java
public class IntDoubleList {
    private IntDoubleListElement head;
    private IntDoubleListElement tail;

    public IntDoubleList() {
        this.head = null;
        this.tail = null;
    }

    public void add(int value) {
        IntDoubleListElement newElement = new IntDoubleListElement(value);
        if (head == null) {
            head = tail = newElement;
        } else {
            tail.next = newElement;
            newElement.prev = tail;
            tail = newElement;
        }
    }

    public IntDoubleListElement getHead() {
        return head;
    }

    public void setHead(IntDoubleListElement head) {
        this.head = head;
    }

    public IntDoubleListElement getTail() {
        return tail;
    }

    public void setTail(IntDoubleListElement tail) {
        this.tail = tail;
    }

    public void printList() {
        IntDoubleListElement current = head;
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
}
