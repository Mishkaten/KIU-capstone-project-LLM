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
    # Step 1: Initialize the first two values of the Fibonacci sequence
    if n <= 0:
        return []
    elif n == 1:
        return [0]
    elif n == 2:
        return [0, 1]

    # Initialize the list with the first two values
    fib_sequence = [0, 1]

    # Step 2: Use a loop to generate the remaining values
    for i in range(2, n):
        next_value = fib_sequence[-1] + fib_sequence[-2]
        # Step 3: Append the computed value to the list
        fib_sequence.append(next_value)

    # Step 4: Return the list containing the Fibonacci sequence
    return fib_sequence


# Example usage
n = 6
print(fibonacci_sequence(n))  # Expected Output: [0, 1, 1, 2, 3, 5]

