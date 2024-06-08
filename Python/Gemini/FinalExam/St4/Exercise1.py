def list_operations(list1: list, list2: list) -> list:
    """
    Performs concatenation, sorting, and duplicate removal on two input lists.

    Args:
        list1: The first list of integers.
        list2: The second list of integers.

    Returns:
        A new list containing the concatenated, sorted, and de-duplicated elements.
    """
    
    # 1. Concatenation (2 points)
    concatenated_list = list1 + list2   # Combine the lists

    # 2. Sorting (2 points)
    concatenated_list.sort()             # Sort in ascending order

    # 3. Removing Duplicates (3 points)
    # Convert to a set to automatically remove duplicates, then back to list
    unique_list = list(set(concatenated_list))

    # Alternative:  Iterate and only keep unique elements
    # unique_list = []
    # for item in concatenated_list:
    #     if item not in unique_list:
    #         unique_list.append(item)

    # Return the Result (0.5 points)
    return unique_list


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