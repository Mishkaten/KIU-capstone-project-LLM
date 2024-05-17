from functools import reduce

def oddCounter(numbers):
    odd_lambda = lambda x: x % 2 != 0
    odd_count = lambda acc, x: acc + 1 if odd_lambda(x) else acc
    return reduce(odd_count, numbers, 0)


numbers = [1, 2, 3, 11, 12, 5, 5, 101, 200]
result = oddCounter(numbers)
print(result)  # Output: 6
