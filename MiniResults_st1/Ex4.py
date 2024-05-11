def isSecondBitOn(number):
    # Check if the second bit is on
    # We do this by using a bitwise AND with 4 (which is 100 in binary)
    return (number & 4) != 0

# Examples
print(isSecondBitOn(6))   # Output: True  (binary 110, second bit is 1)
print(isSecondBitOn(10))  # Output: False (binary 1010, second bit is 0)
