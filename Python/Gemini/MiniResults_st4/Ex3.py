def arg_sum(*args):
  """
  Calculates the sum of an arbitrary number of arguments.

  Args:
    *args: A variable number of positional arguments (assumed to be numbers).

  Returns:
    The sum of the arguments, or 0 if no arguments are provided.
  """
  total = 0
  for num in args:
    total += num
  return total


print(arg_sum(1))  # Output: 1
print(arg_sum(1, 2, 10))  # Output: 13
print(arg_sum())  # Output: 0
