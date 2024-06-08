def fibonacci_sequence(n: int) -> list:
    """
    Generates the first n numbers of the Fibonacci sequence.

    Args:
        n: The number of Fibonacci numbers to generate.

    Returns:
        A list containing the first n Fibonacci numbers.
    """

    # Initial Values (2 points)
    fibonacci_numbers = [0, 1]  # Start with the first two Fibonacci numbers

    # Loop Logic (4 points)
    for i in range(2, n):  # Start from the third number (index 2)
        next_number = fibonacci_numbers[i - 1] + fibonacci_numbers[i - 2]
        fibonacci_numbers.append(next_number)  # Appending Values (2 points)

    # Return the Result (1 point)
    return fibonacci_numbers

# # Example usage
# n = 6
# result = fibonacci_sequence(n)
# print(result)  # Output: [0, 1, 1, 2, 3, 5]



def test_fibonacci_sequence():
    # Test case 1: n = 0
    result1 = fibonacci_sequence(0)
    expected1 = []
    assert result1 == expected1, f"Test case 1 failed: Expected {expected1}, but got {result1}"

    # Test case 2: n = 1
    result2 = fibonacci_sequence(1)
    expected2 = [0]
    assert result2 == expected2, f"Test case 2 failed: Expected {expected2}, but got {result2}"

    # Test case 3: n = 2
    result3 = fibonacci_sequence(2)
    expected3 = [0, 1]
    assert result3 == expected3, f"Test case 3 failed: Expected {expected3}, but got {result3}"

    # Test case 4: n = 6
    result4 = fibonacci_sequence(6)
    expected4 = [0, 1, 1, 2, 3, 5]
    assert result4 == expected4, f"Test case 4 failed: Expected {expected4}, but got {result4}"

    # Test case 5: n = 10
    result5 = fibonacci_sequence(10)
    expected5 = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
    assert result5 == expected5, f"Test case 5 failed: Expected {expected5}, but got {result5}"

    print("All test cases passed!")

test_fibonacci_sequence()