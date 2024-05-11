def abs(num):
    # Check if the number is negative
    if num < 0:
        # If negative, return the number multiplied by -1
        return -num
    # If not negative, return the number as it is
    return num

# Example usage:
print(abs(-3))  # Output: 3
print(abs(1))   # Output: 1
