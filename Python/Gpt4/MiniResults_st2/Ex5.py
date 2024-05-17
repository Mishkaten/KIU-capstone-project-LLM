from functools import reduce

def oddCounter(numbers):
    # Filter the list to keep only odd numbers using a lambda function
    odd_numbers = filter(lambda x: x % 2 != 0, numbers)
    # Use the reduce function to count the number of odd numbers
    count = reduce(lambda acc, _: acc + 1, odd_numbers, 0)
    return count

# Example usage:
print(oddCounter([1, 2, 3, 11, 12, 5, 5, 101, 200]))  # Output: 6
