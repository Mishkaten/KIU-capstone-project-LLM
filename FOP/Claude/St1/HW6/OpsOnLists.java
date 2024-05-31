package Claude.St1.HW6;

public class OpsOnLists {
    public static IntDoubleList reuniteLists(IntDoubleList[] arrayOfLists) {
        IntDoubleList result = new IntDoubleList();
        IntDoubleListElement[] currentElements = new IntDoubleListElement[arrayOfLists.length];

        // Initialize current elements to the head of each list
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

            // If no minimum value found, all lists are exhausted
            if (minIndex == -1) {
                break;
            }

            // Append the minimum value to the result list
            result.append(minValue);

            // Move to the next element in the list that had the minimum value
            currentElements[minIndex] = currentElements[minIndex].next;
        }

        return result;
    }

    public static IntDoubleList partTheList(IntDoubleList list, int value) {
        IntDoubleList smallerList = new IntDoubleList();
        IntDoubleList greaterOrEqualList = new IntDoubleList();

        IntDoubleListElement current = list.getFirstElement();

        // Partition the list into smaller and greater or equal elements
        while (current != null) {
            if (current.getInfo() < value) {
                smallerList.append(current.getInfo());
            } else {
                greaterOrEqualList.append(current.getInfo());
            }
            current = current.next;
        }

        // Attach the greater or equal list to the end of the smaller list
        if (smallerList.getLastElement() != null) {
            smallerList.getLastElement().next = greaterOrEqualList.getFirstElement();
        } else {
            smallerList.append(greaterOrEqualList.getFirstElement().getInfo());
        }

        return smallerList;
    }

    public static IntDoubleList mixedList(IntDoubleList list) {
        if (list.getFirstElement() == null || list.getFirstElement().next == null) {
            return list;
        }

        IntDoubleList result = new IntDoubleList();
        IntDoubleListElement current = list.getFirstElement();
        IntDoubleListElement last = list.getLastElement();

        // Permute the elements according to the pattern
        while (current != last && current.next != last) {
            result.append(current.getInfo());
            result.append(last.getInfo());
            current = current.next;
            last = last.prev;
        }

        // Handle the remaining element if the list has an odd number of elements
        if (current == last) {
            result.append(current.getInfo());
        }

        return result;
    }
}