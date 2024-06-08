"""
Exercise 1: Basic Data Structures and Operations

Problem Description:
Write a Python function `list_operations()` that takes two lists of integers as input. The function should:
1. Concatenate the two lists.
2. Sort the resulting list in ascending order.
3. Remove any duplicate elements.
4. Return the processed list.

Function Signature:
def list_operations(list1: list, list2: list) -> list:
    pass

Example:
list1 = [3, 6, 1, 4]
list2 = [4, 2, 5, 3]
# Expected Output: [1, 2, 3, 4, 5, 6]

Grading Scheme:
- Concatenation (2 points):
  - Correctly concatenates the two lists.
- Sorting (2 points):
  - Correctly sorts the concatenated list in ascending order.
- Removing Duplicates (3 points):
  - Correctly removes duplicate elements from the list.
- Return the Result (0.5 point):
  - Returns the processed list correctly.
- Code Readability and Comments (0.5 points):
  - Code is well-structured and includes appropriate comments explaining each step.

Total: 8 points
"""


def list_operations(list1: list, list2: list) -> list:
    """
    This function takes two lists of integers, concatenates them,
    sorts the result in ascending order, removes duplicates, and returns the processed list.

    Parameters:
    list1 (list): The first list of integers.
    list2 (list): The second list of integers.

    Returns:
    list: The processed list with concatenated, sorted, and unique elements.
    """

    # Step 1: Concatenate the two lists
    concatenated_list = list1 + list2

    # Step 2: Sort the resulting list in ascending order
    sorted_list = sorted(concatenated_list)

    # Step 3: Remove duplicates by converting the list to a set, then back to a sorted list
    unique_sorted_list = sorted(set(sorted_list))

    # Return the final processed list
    return unique_sorted_list


# Example usage:
list1 = [3, 6, 1, 4]
list2 = [4, 2, 5, 3]
print(list_operations(list1, list2))  # Expected Output: [1, 2, 3, 4, 5, 6]

