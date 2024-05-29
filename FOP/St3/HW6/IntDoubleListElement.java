public class IntDoubleListElement {
    int value;
    IntDoubleListElement next;
    IntDoubleListElement prev;

    public IntDoubleListElement(int value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public int getValue() {
        return value;
    }

    public IntDoubleListElement getNext() {
        return next;
    }

    public IntDoubleListElement getPrev() {
        return prev;
    }
}
