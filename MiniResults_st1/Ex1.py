def fibonacci(n):
    # Initializing the first two Fibonacci numbers
    a, b = 0, 1
    result = []
    
    # Generating the Fibonacci sequence up to n numbers
    for _ in range(n):
        result.append(a)
        a, b = b, a + b  # Update the values of a and b
    
    return result

# Reading the number of terms from the user
N = int(input("Enter the number of Fibonacci numbers to generate: "))

# Getting the Fibonacci sequence
fib_sequence = fibonacci(N)

# Printing the Fibonacci sequence
print("The first", N, "numbers of the Fibonacci sequence are:", fib_sequence)
