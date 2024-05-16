def fibonacci(n):
    # Initialize the first two Fibonacci numbers
    if n <= 0:
        return []  # Return an empty list if n is less than or equal to 0
    elif n == 1:
        return [0]  # Return the list with only the first Fibonacci number
    elif n == 2:
        return [0, 1]  # Return the list with the first two Fibonacci numbers

    # Start the Fibonacci sequence with the first two numbers
    fib_seq = [0, 1]

    # Compute Fibonacci numbers until n is reached
    while len(fib_seq) < n:
        # Append the sum of the last two elements in the list to extend the Fibonacci sequence
        fib_seq.append(fib_seq[-1] + fib_seq[-2])

    return fib_seq

# Read an integer from the user
n = int(input("Enter the number of Fibonacci numbers to generate: "))

# Generate and print the Fibonacci sequence
fibonacci_numbers = fibonacci(n)
print("The first", n, "numbers of the Fibonacci sequence are:")
print(fibonacci_numbers)
