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

        IntDoubleListElement current = list.getFirstElement();
        while (current != null) {
            IntDoubleListElement next = current.next; // Store next before modification
            if (current.getInfo() < value) {
                smallerList.append(current.getInfo());
            } else {
                greaterOrEqualList.append(current.getInfo());
            }
            current = next;
        }

        // Concatenate lists (corrected)
        if (smallerList.head == null) {
            return greaterOrEqualList; // If no smaller elements
        } else if (greaterOrEqualList.head == null) {
            return smallerList;      // If no greater/equal elements
        } else {
            smallerList.getLastElement().next = greaterOrEqualList.head;
            greaterOrEqualList.head.prev = smallerList.getLastElement();
            return smallerList;
        }
    }

    public static IntDoubleList mixedList(IntDoubleList list) {
        IntDoubleList permutedList = new IntDoubleList();
        int size = list.size();

        IntDoubleListElement front = list.getFirstElement();
        IntDoubleListElement back = list.getLastElement();

        // Ensure the correct number of iterations
        for (int i = 0; i < size; i++) {
            if (front != null) {
                permutedList.append(front.getInfo());
                front = front.next;
            }

            if (back != null && back != front) { // Avoid duplicates if the list is odd-sized
                permutedList.append(back.getInfo());
                back = back.prev;
            }
        }

        return permutedList;
    }

}