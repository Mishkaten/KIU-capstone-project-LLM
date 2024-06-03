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
    Concatenate two lists, sort the resulting list in ascending order,
    remove duplicate elements, and return the processed list.

    Args:
        list1 (list): The first list of integers.
        list2 (list): The second list of integers.

    Returns:
        list: The processed list after concatenation, sorting, and removing duplicates.
    """
    # Concatenate the two lists
    concatenated_list = list1 + list2

    # Sort the concatenated list in ascending order
    sorted_list = sorted(concatenated_list)

    # Remove duplicate elements from the sorted list
    processed_list = list(set(sorted_list))

    return processed_list


def test_list_operations():
    # Test Case 1
    list1 = [3, 6, 1, 4]
    list2 = [4, 2, 5, 3]
    expected_output = [1, 2, 3, 4, 5, 6]
    result = list_operations(list1, list2)
    assert result == expected_output, f"Test Case 1 Failed: Expected {expected_output}, but got {result}"
    print("Test Case 1 Passed")

    # Test Case 2
    list1 = [1, 2, 3]
    list2 = [4, 5, 6]
    expected_output = [1, 2, 3, 4, 5, 6]
    result = list_operations(list1, list2)
    assert result == expected_output, f"Test Case 2 Failed: Expected {expected_output}, but got {result}"
    print("Test Case 2 Passed")

    # Test Case 3
    list1 = [1, 2, 3, 4, 5]
    list2 = [3, 4, 5, 6, 7]
    expected_output = [1, 2, 3, 4, 5, 6, 7]
    result = list_operations(list1, list2)
    assert result == expected_output, f"Test Case 3 Failed: Expected {expected_output}, but got {result}"
    print("Test Case 3 Passed")

    # Test Case 4
    list1 = []
    list2 = [1, 2, 3]
    expected_output = [1, 2, 3]
    result = list_operations(list1, list2)
    assert result == expected_output, f"Test Case 4 Failed: Expected {expected_output}, but got {result}"
    print("Test Case 4 Passed")

    # Test Case 5
    list1 = [1, 2, 3]
    list2 = []
    expected_output = [1, 2, 3]
    result = list_operations(list1, list2)
    assert result == expected_output, f"Test Case 5 Failed: Expected {expected_output}, but got {result}"
    print("Test Case 5 Passed")

    print("All test cases passed!")

test_list_operations()