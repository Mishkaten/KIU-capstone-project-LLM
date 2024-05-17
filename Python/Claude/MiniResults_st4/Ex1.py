def fibonacci(n):
    if n <= 0:
        return []
    elif n == 1:
        return [0]
    elif n == 2:
        return [0, 1]
    else:
        fib_sequence = [0, 1]
        for i in range(2, n):
            fib_sequence.append(fib_sequence[i-1] + fib_sequence[i-2])
        return fib_sequence

# Read the value of N from the console
N = int(input("Enter the value of N: "))

# Generate and print the first N numbers of the Fibonacci sequence
fib_numbers = fibonacci(N)
print(f"The first {N} numbers of the Fibonacci sequence are:")
print(", ".join(str(num) for num in fib_numbers))