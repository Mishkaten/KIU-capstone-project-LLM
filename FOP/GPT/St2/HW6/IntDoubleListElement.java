package GPT.St2.HW6;

public class IntDoubleListElement {
    public int value;
    private int info;

    public void setInfo(int inf) {
        info = inf;
    }

    public int getInfo() {
        return info;
    }

    public IntDoubleListElement next;
    public IntDoubleListElement prev;

    public IntDoubleListElement(int startInfo) {
        info = startInfo;
        next = null;
        prev = null;
    }

    public boolean isEqual(IntDoubleListElement other) {
        return other != null && info == other.info;
    }
}
