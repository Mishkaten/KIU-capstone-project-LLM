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
    # Number of items
    n = len(items)

    # Initialize the DP table with zeros
    dp = [[0 for _ in range(max_weight + 1)] for _ in range(n + 1)]

    # Populate the DP table
    for i in range(1, n + 1):
        value, weight = items[i - 1]
        for w in range(1, max_weight + 1):
            if weight <= w:
                dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - weight] + value)
            else:
                dp[i][w] = dp[i - 1][w]

    # The optimal value will be in the bottom-right cell of the table
    return dp[n][max_weight]


# Example usage
items = [(60, 10), (100, 20), (120, 30)]
max_weight = 50
print(knapsack(items, max_weight))  # Expected Output: 220

