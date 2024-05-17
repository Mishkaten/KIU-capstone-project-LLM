def isSecondBitOn(num):
    return (num & 4) != 0



print(isSecondBitOn(6))  # Output: True
# Explanation: 6 in binary is 110, where the second bit is 1.

print(isSecondBitOn(10))  # Output: False
# Explanation: 10 in binary is 1010, where the second bit is 0.

print(isSecondBitOn(1))  # Output: False
# Explanation: 1 in binary is 001, where the second bit is 0.

print(isSecondBitOn(4))  # Output: True
# Explanation: 4 in binary is 100, where the second bit is 1.