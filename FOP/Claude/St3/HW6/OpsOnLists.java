package Claude.St3.HW6;

public class OpsOnLists {
    public static IntDoubleList reuniteLists(IntDoubleList[] arrayOfLists) {
        IntDoubleList mergedList = new IntDoubleList();
        IntDoubleListElement[] currentElements = new IntDoubleListElement[arrayOfLists.length];

        // Initialize current elements to the head of each list
        for (int i = 0; i < arrayOfLists.length; i++) {
            currentElements[i] = arrayOfLists[i].getFirstElement();
        }

        while (true) {
            int minIndex = -1;
            int minValue = Integer.MAX_VALUE;

            // Find the minimum value among current elements
            for (int i = 0; i < currentElements.length; i++) {
                if (currentElements[i] != null && currentElements[i].getInfo() < minValue) {
                    minIndex = i;
                    minValue = currentElements[i].getInfo();
                }
            }

            // If no minimum value found, all lists are exhausted
            if (minIndex == -1) {
                break;
            }

            // Append the minimum value to the merged list
            mergedList.append(minValue);

            // Move to the next element in the list containing the minimum value
            currentElements[minIndex] = currentElements[minIndex].next;
        }

        return mergedList;
    }

    public static IntDoubleList partTheList(IntDoubleList list, int value) {
        IntDoubleList smallerList = new IntDoubleList();
        IntDoubleList greaterOrEqualList = new IntDoubleList();

        IntDoubleListElement temp = list.getFirstElement();

        // Partition the list based on the given value
        while (temp != null) {
            if (temp.getInfo() < value) {
                smallerList.append(temp.getInfo());
            } else {
                greaterOrEqualList.append(temp.getInfo());
            }
            temp = temp.next;
        }

        // Concatenate the smaller and greater or equal lists
        IntDoubleListElement smallerTail = smallerList.getLastElement();
        if (smallerTail != null) {
            smallerTail.next = greaterOrEqualList.getFirstElement();
            if (greaterOrEqualList.getFirstElement() != null) {
                greaterOrEqualList.getFirstElement().prev = smallerTail;
            }
        } else {
            smallerList.head = greaterOrEqualList.getFirstElement();
        }

        return smallerList;
    }

    public static IntDoubleList mixedList(IntDoubleList list) {
        IntDoubleList permutedList = new IntDoubleList();
        int size = list.size();
        int mid = (size - 1) / 2;

        IntDoubleListElement frontElement = list.getFirstElement();
        IntDoubleListElement backElement = list.getLastElement();

        // Permute the list according to the given pattern
        for (int i = 0; i <= mid; i++) {
            permutedList.append(frontElement.getInfo());
            frontElement = frontElement.next;

            if (i != mid || size % 2 == 1) {
                permutedList.append(backElement.getInfo());
                backElement = backElement.prev;
            }
        }

        return permutedList;
    }
}