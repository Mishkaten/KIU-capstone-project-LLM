import matplotlib.pyplot as plt

def plot_grades(grades):
    # Dictionary to count the frequency of each grade
    grade_count = {grade: 0 for grade in 'ABCDEF'}
    
    # Count each grade in the list
    for grade in grades:
        if grade in grade_count:
            grade_count[grade] += 1
    
    # Data for plotting
    labels = list(grade_count.keys())
    heights = [grade_count[grade] for grade in labels]
    
    # Create bar chart
    plt.bar(labels, heights, color='blue')
    
    # Add title and labels to the chart
    plt.title('Frequency of Grades')
    plt.xlabel('Grades')
    plt.ylabel('Frequency')
    
    # Show the plot
    plt.show()

# Example usage
grades_list = ['A', 'B', 'C', 'D', 'F', 'A', 'B', 'A', 'C', 'D', 'F', 'F', 'A']
plot_grades(grades_list)
