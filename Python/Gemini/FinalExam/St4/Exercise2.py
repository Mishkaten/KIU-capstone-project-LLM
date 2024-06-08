"""
Exercise 2: Functions and Control Flow

Problem Description:
Write a Python function `fibonacci_sequence(n)` that generates the first `n` numbers of the Fibonacci sequence. The Fibonacci sequence is defined as:
- F0 = 0
- F1 = 1
- Fn = Fn-1 + Fn-2 for n >= 2

The function should return a list containing the first `n` Fibonacci numbers.

Function Signature:
def fibonacci_sequence(n: int) -> list:
    pass

Example:
n = 6
# Expected Output: [0, 1, 1, 2, 3, 5]

Grading Scheme:
- Initial Values (2 points):
  - Correctly initializes the first two values of the Fibonacci sequence.
- Loop Logic (4 points):
  - Correctly implements the loop to generate the sequence.
- Appending Values (2 points):
  - Correctly appends the computed values to the list.
- Return the Result (1 point):
  - Returns the list containing the Fibonacci sequence.
- Code Readability and Comments (1 point):
  - Code is well-structured and includes appropriate comments explaining each step.

Total: 10 points
"""

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
    for i in range(2, n):
        next_number = fibonacci_numbers[i - 1] + fibonacci_numbers[i - 2]  # Calculate the next number
        fibonacci_numbers.append(next_number)  # Appending Values (2 points)

    # Return the Result (1 point)
    return fibonacci_numbers



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