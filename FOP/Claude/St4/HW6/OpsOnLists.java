package Claude.St4.HW6;

public class OpsOnLists {
    public static IntDoubleList reuniteLists(IntDoubleList[] arrayOfLists) {
        IntDoubleList result = new IntDoubleList();

        // Create an array to store the current elements of each list
        IntDoubleListElement[] currentElements = new IntDoubleListElement[arrayOfLists.length];
        for (int i = 0; i < arrayOfLists.length; i++) {
            currentElements[i] = arrayOfLists[i].getFirstElement();
        }

        while (true) {
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            // Find the minimum value among the current elements
            for (int i = 0; i < currentElements.length; i++) {
                if (currentElements[i] != null && currentElements[i].getInfo() < minValue) {
                    minValue = currentElements[i].getInfo();
                    minIndex = i;
                }
            }

            if (minIndex == -1) {
                // All lists are exhausted
                break;
            }

            // Append the minimum value to the result list
            result.append(minValue);

            // Move to the next element in the list with the minimum value
            currentElements[minIndex] = currentElements[minIndex].next;
        }

        return result;
    }

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

        // Concatenate the two lists
        if (lessThan.getLastElement() != null) {
            lessThan.getLastElement().next = greaterEqual.getFirstElement();
        } else {
            lessThan = greaterEqual;
        }

        return lessThan;
    }

    public static IntDoubleList mixedList(IntDoubleList list) {
        IntDoubleList result = new IntDoubleList();

        IntDoubleListElement current = list.getFirstElement();
        while (current != null) {
            result.append(current.getInfo());
            current = current.next;
            if (current != null) {
                result.append(current.getInfo());
                current = current.prev;
            }
            current = current != null ? current.next : null;
        }

        return result;
    }
}