def fibonacci(n):
    """
    Generates the Fibonacci sequence up to the nth number.
    """
    if n <= 0:
        print("Please enter a positive integer.")
        return

    a, b = 0, 1
    print("Fibonacci sequence:")
    for _ in range(n):
        print(a, end=" ")
        a, b = b, a + b
    print()  # Add a newline for better formatting

# Get the number of terms from the user
while True:
    try:
        n = int(input("Enter the number of Fibonacci terms you want: "))
        if n > 0:
            break
        else:
            print("Please enter a positive integer.")
    except ValueError:
        print("Invalid input. Please enter an integer.")

# Generate and print the sequence
fibonacci(n)
