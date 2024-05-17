import matplotlib.pyplot as plt

def plot_grade_frequencies(grades):
    # Count the frequency of each grade
    grade_counts = {}
    for grade in ['A', 'B', 'C', 'D', 'E', 'F']:
        grade_counts[grade] = grades.count(grade)
    
    # Data for plotting
    grades_list = list(grade_counts.keys())
    counts = list(grade_counts.values())
    
    # Creating the bar chart
    plt.figure(figsize=(10, 6))
    plt.bar(grades_list, counts, color='blue')
    
    # Adding title and labels
    plt.title('Grade Frequencies')
    plt.xlabel('Grades')
    plt.ylabel('Frequency')
    
    # Display the plot
    plt.show()

# Example usage
grades = ['A', 'B', 'A', 'C', 'B', 'B', 'E', 'F', 'A', 'D', 'B', 'C', 'F', 'A']
plot_grade_frequencies(grades)
