def isSecondBitOn(num):
    # Check if the second bit from the right (bit 2, which represents 4 in binary) is set to 1
    return (num & 4) != 0

# Example usage:
print(isSecondBitOn(6))  # Output: True (binary 110, third bit is 1)
print(isSecondBitOn(10)) # Output: False (binary 1010, third bit is 0)
print(isSecondBitOn(4))  # Output: True (binary 100, third bit is 1)
