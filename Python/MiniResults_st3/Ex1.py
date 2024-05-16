def fibonacci(n):
    # Initialize the first two Fibonacci numbers
    a, b = 0, 1
    # Use a loop to generate the Fibonacci sequence up to n numbers
    for _ in range(n):
        print(a, end=' ')
        a, b = b, a + b  # Update values of a and b

# Read an integer from the console
N = int(input("Enter the number of Fibonacci numbers to generate: "))
fibonacci(N)
