package Claude.St3.HW6;

public class Main {
    public static void main(String[] args) {
        // Create an IntDoubleList and append some elements
        IntDoubleList list = new IntDoubleList();
        list.append(10);
        list.append(30);
        list.append(20);
        list.append(40);
        list.append(50);

        // Test size
        System.out.println("Size: " + list.size());
        // Expected output: Size: 5

        // Test get
        System.out.println("Element at position 2: " + list.get(2));
        // Expected output: Element at position 2: 20

        // Test remove
        list.remove(2);
        System.out.println("Size after removing element at position 2: " + list.size());
        // Expected output: Size after removing element at position 2: 4

        // Test remove head
        list.remove(0);
        System.out.println("Size after removing head: " + list.size());
        // Expected output: Size after removing head: 3

        // Test mergeSort
        IntDoubleList sortedList = OpsOnLists.mixedList(list);
        System.out.println("Sorted list: ");
        printList(sortedList);
        // Expected output: Sorted list: 10, 30, 40, 50

        // Test reorderList
        IntDoubleList reorderedList = OpsOnLists.partTheList(list, 1);
        System.out.println("Reordered list: ");
        printList(reorderedList);
        // Expected output: Reordered list: 10, 50, 30, 40
    }

    // Helper method to print elements of the list
    public static void printList(IntDoubleList list) {
        IntDoubleListElement element = list.getFirstElement();
        while (element != null) {
            System.out.print(element.getInfo() + " ");
            element = element.next;
        }
        System.out.println();
    }
}
