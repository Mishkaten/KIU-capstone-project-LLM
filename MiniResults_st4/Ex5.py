def oddCounter(numbers):
    # Use filter to keep only odd numbers
    # Convert the results to 1s and 0s using map, then sum them
    return sum(map(lambda x: x % 2 == 1, numbers))

# Example usage
print(oddCounter([1, 2, 3, 11, 12, 5, 5, 101, 200]))  # Output: 6
