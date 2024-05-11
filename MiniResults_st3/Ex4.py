def isSecondBitOn(number):
    # Check if the second bit is on (bit corresponding to 4)
    return (number & 4) != 0

# Example usage
print(isSecondBitOn(6))  # Outputs: True, because binary of 6 is 110 and second bit is 1
print(isSecondBitOn(10)) # Outputs: False, because binary of 10 is 1010 and second bit is 0
