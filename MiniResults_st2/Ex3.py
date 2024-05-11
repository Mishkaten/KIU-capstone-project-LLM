def arg_sum(*args):
    # Initialize sum to zero
    total = 0
    # Iterate over all arguments provided
    for num in args:
        # Add each number to the total
        total += num
    # Return the total sum of the arguments
    return total

# Example usage:
print(arg_sum(1))           # Output: 1
print(arg_sum(1, 2, 10))    # Output: 13
print(arg_sum())            # Output: 0
