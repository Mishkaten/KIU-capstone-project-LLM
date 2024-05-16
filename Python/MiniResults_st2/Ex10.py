import matplotlib.pyplot as plt


def plot_grade_frequencies(grades):
    # Create a dictionary to count the frequency of each grade
    grade_counts = {grade: 0 for grade in 'ABCDEF'}

    # Count each grade in the list
    for grade in grades:
        if grade in grade_counts:
            grade_counts[grade] += 1

    # Prepare data for plotting
    labels = list(grade_counts.keys())
    heights = list(grade_counts.values())

    # Creating the bar chart
    plt.figure(figsize=(10, 6))  # Set the size of the plot
    plt.bar(labels, heights, color='skyblue')  # Create bars with skyblue color
    plt.xlabel('Grades')  # Label on X axis
    plt.ylabel('Frequency')  # Label on Y axis
    plt.title('Frequency of Grades')  # Title of the plot
    plt.show()  # Display the plot


# Example usage
grades = ['A', 'B', 'A', 'C', 'B', 'A', 'D', 'F', 'B', 'C', 'D', 'E', 'F', 'A']
plot_grade_frequencies(grades)
