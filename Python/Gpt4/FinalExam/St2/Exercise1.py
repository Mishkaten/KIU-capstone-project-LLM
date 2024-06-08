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
    # Step 1: Concatenate the two lists
    concatenated_list = list1 + list2

    # Step 2: Sort the concatenated list
    sorted_list = sorted(concatenated_list)

    # Step 3: Remove duplicates by converting to a set, then back to a list
    unique_sorted_list = list(set(sorted_list))

    # Step 4: Sort again to ensure the final list is sorted after removing duplicates
    result_list = sorted(unique_sorted_list)

    # Return the processed list
    return result_list


# Example usage
list1 = [3, 6, 1, 4]
list2 = [4, 2, 5, 3]
print(list_operations(list1, list2))  # Expected Output: [1, 2, 3, 4, 5, 6]
