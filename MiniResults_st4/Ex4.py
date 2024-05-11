def isSecondBitOn(num):
    # Check if the second bit from the right (4's place) is on
    # We use bitwise AND with 4 (binary 100) and check if the result is non-zero
    return (num & 4) != 0

# Example usage
print(isSecondBitOn(6))  # Output: True, because 6 in binary is 110
print(isSecondBitOn(10)) # Output: False, because 10 in binary is 1010
