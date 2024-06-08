def abs(num):
  """
  This function calculates the absolute value of a given number.

  Args:
    num: The number to calculate the absolute value of.

  Returns:
    The absolute value of the input number.
  """
  if num >= 0:
    return num
  else:
    return -num


# Examples
print(abs(-3))  # Output: 3
print(abs(1))   # Output: 1
print(abs(0))   # Output: 0
print(abs(-5.7))  # Output: 5.7