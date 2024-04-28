from functools import reduce

def oddCounter(numbers):
    # Filter the list to include only odd numbers
    odd_numbers = filter(lambda x: x % 2 != 0, numbers)
    # Use sum with a map to count the number of true values (each odd number contributes 1)
    return sum(map(lambda x: 1, odd_numbers))

# Example
print(oddCounter([1, 2, 3, 11, 12, 5, 5, 101, 200]))  # Output: 6
