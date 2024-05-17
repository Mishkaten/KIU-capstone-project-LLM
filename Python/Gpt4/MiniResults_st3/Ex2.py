def my_abs(number):
    # Check if the number is negative
    if number < 0:
        return -number  # Return the negated number if it is negative
    else:
        return number   # Return the number itself if it is not negative

# Example usage
print(my_abs(-3))  # Outputs: 3
print(my_abs(1))   # Outputs: 1
