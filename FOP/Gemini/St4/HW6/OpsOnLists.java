package Claude.St3.HW6;

public class OpsOnLists {

    // Merging k sorted lists into one sorted list
    public static IntDoubleList reuniteLists(IntDoubleList[] arrayOfLists) {
        IntDoubleList mergedList = new IntDoubleList();
        IntDoubleListElement[] heads = new IntDoubleListElement[arrayOfLists.length];

        // Initialize heads array with the first elements of each list
        for (int i = 0; i < arrayOfLists.length; i++) {
            heads[i] = arrayOfLists[i].getFirstElement();
        }

        // Merge lists until all heads are null (lists are empty)
        while (true) {
            // Find the head with the smallest value
            int minIndex = -1;
            for (int i = 0; i < heads.length; i++) {
                if (heads[i] != null && (minIndex == -1 || heads[i].getInfo() < heads[minIndex].getInfo())) {
                    minIndex = i;
                }
            }

            // If all heads are null, merging is complete
            if (minIndex == -1) break;

            // Append the smallest element to the merged list
            mergedList.append(heads[minIndex].getInfo());

            // Move the head of the selected list to the next element
            heads[minIndex] = heads[minIndex].next;
        }

        return mergedList;
    }

    // Partitioning a list around a given value
    public static IntDoubleList partTheList(IntDoubleList list, int value) {
        IntDoubleList lessThan = new IntDoubleList();
        IntDoubleList greaterEqual = new IntDoubleList();

        IntDoubleListElement current = list.getFirstElement();
        while (current != null) {
            if (current.getInfo() < value) {
                lessThan.append(current.getInfo());
            } else {
                greaterEqual.append(current.getInfo());
            }
            current = current.next;
        }

        // Check if 'lessThan' is empty before accessing its last element
        if (lessThan.getLastElement() != null) {
            lessThan.getLastElement().next = greaterEqual.getFirstElement();
        }

        // Check if 'greaterEqual' is empty before setting its first element's 'prev'
        if (greaterEqual.getFirstElement() != null) {
            greaterEqual.getFirstElement().prev = lessThan.getLastElement();
        }

        // Handle the case where 'lessThan' is empty more elegantly
        return lessThan.size() > 0 ? lessThan : greaterEqual;
    }
    // Interleaving a list's elements (first, last, second, second-to-last, etc.)
    public static IntDoubleList mixedList(IntDoubleList list) {
        IntDoubleList result = new IntDoubleList();

        // Special case for empty or single-element lists
        if (list.getFirstElement() == null || list.getFirstElement() == list.getLastElement()) {
            return list.copy(); // Return a copy of the original list
        }

        IntDoubleListElement head = list.getFirstElement();
        IntDoubleListElement tail = list.getLastElement();

        while (head != tail && head.prev != tail) {
            result.append(head.getInfo());
            result.append(tail.getInfo());

            // Move both pointers inward
            head = head.next;
            tail = tail.prev;
        }

        // Correctly handle the middle element for odd-length lists
        if (head.prev == tail) {  // Head is just before the tail
            result.append(head.getInfo());
            result.append(tail.getInfo());
        } else if (head == tail) { // Head and tail are the same (middle element)
            result.append(head.getInfo());
        }

        return result;
    }

}
