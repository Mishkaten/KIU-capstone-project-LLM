def list_operations(list1: list, list2: list) -> list:
    """
    Performs operations on two lists of integers: concatenation, sorting, and duplicate removal.

    Args:
        list1: The first list of integers.
        list2: The second list of integers.

    Returns:
        A new list containing the concatenated, sorted, and de-duplicated elements.
    """

    # 1. Concatenate the lists
    concatenated_list = list1 + list2

    # 2. Sort the concatenated list in ascending order
    concatenated_list.sort()

    # 3. Remove duplicates by converting to a set and back to a list
    result_list = list(set(concatenated_list))

    # 4. Return the processed list
    return result_list




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