from functools import reduce

def oddCounter(numbers):
    """
    Counts the number of odd numbers in a list using lambda functions and HOFs.

    Args:
        numbers: A list of integers.

    Returns:
        The count of odd numbers in the list.
    """
    is_odd = lambda x: x % 2 == 1
    return reduce(lambda count, x: count + is_odd(x), numbers, 0)


numbers = [1, 2, 3, 11, 12, 5, 5, 101, 200]
result = oddCounter(numbers)
print(result)  # Output: 6
