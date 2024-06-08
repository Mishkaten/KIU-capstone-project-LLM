def isSecondBitOn(num):
  """
  Checks if the second bit (corresponding to 4) of a number is on.

  Args:
    num: The integer to check.

  Returns:
    True if the second bit is on (1), False otherwise (0).
  """
  mask = 4  # Binary: 0100
  return (num & mask) != 0




print(isSecondBitOn(6))  # Output: True
# Explanation: 6 in binary is 110, where the second bit is 1.

print(isSecondBitOn(10))  # Output: False
# Explanation: 10 in binary is 1010, where the second bit is 0.

print(isSecondBitOn(1))  # Output: False
# Explanation: 1 in binary is 001, where the second bit is 0.

print(isSecondBitOn(4))  # Output: True
# Explanation: 4 in binary is 100, where the second bit is 1.