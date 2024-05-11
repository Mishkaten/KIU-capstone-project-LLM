def fibonacci(n):
    # Create an array to store Fibonacci numbers. Starting with 0 and 1.
    fib_sequence = [0, 1]

    # Generate the Fibonacci sequence until we have n numbers
    while len(fib_sequence) < n:
        # The new number is the sum of the last two numbers in the sequence
        next_fib = fib_sequence[-1] + fib_sequence[-2]
        fib_sequence.append(next_fib)

    # Return only the first n numbers
    return fib_sequence[:n]


# Read an integer from the user input
n = int(input("Enter the number of Fibonacci numbers to generate: "))

# Calculate the first n Fibonacci numbers
fib_numbers = fibonacci(n)

# Print the result
print("The first", n, "numbers of the Fibonacci sequence are:", fib_numbers)
