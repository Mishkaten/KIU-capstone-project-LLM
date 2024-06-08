"""
Exercise 5: Advanced Algorithms and Optimization

Problem Description:
Write a Python function `knapsack(items, max_weight)` that solves the 0/1 Knapsack problem using dynamic programming. The function should take a list of items and the maximum weight the knapsack can carry, and return the maximum value that can be achieved without exceeding the weight limit.

Each item is represented as a tuple (value, weight).

Function Signature:
def knapsack(items: list, max_weight: int) -> int:
    pass

Example:
items = [(60, 10), (100, 20), (120, 30)]
max_weight = 50
# Expected Output: 220

Grading Scheme:
- Dynamic Programming Table Initialization (1 point):
  - Correctly initializes the dynamic programming table.
- Table Population (2 points):
  - Correctly populates the dynamic programming table based on item values and weights.
- Optimal Value Calculation (1 point):
  - Correctly calculates the optimal value that can be achieved without exceeding the weight limit.
- Code Efficiency and Comments (1 point):
  - Code is efficient and includes appropriate comments explaining each step.

Total: 5 points
"""


def knapsack(items: list, max_weight: int) -> int:
    """Solves the 0/1 Knapsack problem using dynamic programming.

    Args:
        items: A list of tuples, where each tuple represents an item with (value, weight).
        max_weight: The maximum weight the knapsack can carry.

    Returns:
        The maximum total value of items that can be carried without exceeding max_weight.
    """

    n = len(items)

    # Create a table to store the maximum value for each subproblem
    dp = [[0 for _ in range(max_weight + 1)] for _ in range(n + 1)]

    # Iterate over items and weights
    for i in range(1, n + 1):
        for w in range(1, max_weight + 1):
            value, weight = items[i - 1]

            # Case 1: Current item's weight exceeds the current weight limit
            if weight > w:
                dp[i][w] = dp[i - 1][w]  # Don't include the current item
            else:
                # Case 2: Choose between including or excluding the current item
                dp[i][w] = max(dp[i - 1][w],  # Don't include the current item
                               dp[i - 1][w - weight] + value)  # Include the current item

    return dp[n][max_weight]  # The result is at the bottom right corner of the table


def test_knapsack():
    # Test Case 1
    items1 = [(60, 10), (100, 20), (120, 30)]
    max_weight1 = 50
    expected_output1 = 220
    result1 = knapsack(items1, max_weight1)
    assert result1 == expected_output1, f"Test Case 1 Failed: Expected {expected_output1}, but got {result1}"

    # Test Case 2
    items2 = [(30, 5), (50, 10), (100, 20), (90, 30)]
    max_weight2 = 40
    expected_output2 = 180
    result2 = knapsack(items2, max_weight2)
    assert result2 == expected_output2, f"Test Case 2 Failed: Expected {expected_output2}, but got {result2}"

    # Test Case 3
    items3 = [(15, 1), (10, 2), (8, 3), (5, 4)]
    max_weight3 = 6
    expected_output3 = 33
    result3 = knapsack(items3, max_weight3)
    assert result3 == expected_output3, f"Test Case 3 Failed: Expected {expected_output3}, but got {result3}"

    # Test Case 4
    items4 = [(1, 1), (2, 2), (3, 3)]
    max_weight4 = 0
    expected_output4 = 0
    result4 = knapsack(items4, max_weight4)
    assert result4 == expected_output4, f"Test Case 4 Failed: Expected {expected_output4}, but got {result4}"

    print("All test cases passed!")

# Run the test function
test_knapsack()
