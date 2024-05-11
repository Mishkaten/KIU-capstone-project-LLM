def oddCounter(numbers):
    # Filter odd numbers and count them
    return sum(1 for _ in filter(lambda x: x % 2 != 0, numbers))

# Example usage
example_list = [1, 2, 3, 11, 12, 5, 5, 101, 200]
print(oddCounter(example_list))  # Outputs: 6
