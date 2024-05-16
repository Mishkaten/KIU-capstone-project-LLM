import matplotlib.pyplot as plt
from collections import Counter

def plot_grades(grades):
    # Count the frequency of each grade
    grade_count = Counter(grades)

    # Sort the grades from A to F to ensure they are displayed in order
    categories = ['A', 'B', 'C', 'D', 'E', 'F']
    counts = [grade_count.get(grade, 0) for grade in categories]  # Get counts in A-F order, defaulting to 0

    # Creating the bar chart
    plt.bar(categories, counts, color='blue')

    # Adding titles and labels
    plt.title('Frequency of Grades')
    plt.xlabel('Grades')
    plt.ylabel('Frequency')

    # Display the plot
    plt.show()

# Example usage
grades = ['A', 'B', 'A', 'C', 'B', 'B', 'E', 'D', 'F', 'A', 'D', 'E']
plot_grades(grades)
