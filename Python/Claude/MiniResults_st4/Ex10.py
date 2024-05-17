import matplotlib.pyplot as plt


def plot_grade_distribution(grades):
    # Count the frequency of each grade
    grade_counts = {}
    for grade in grades:
        if grade in grade_counts:
            grade_counts[grade] += 1
        else:
            grade_counts[grade] = 1

    # Extract the grades and their frequencies
    letters = list(grade_counts.keys())
    frequencies = list(grade_counts.values())

    # Create the bar chart
    plt.figure(figsize=(8, 6))
    plt.bar(letters, frequencies)

    # Customize the chart
    plt.title("Grade Distribution")
    plt.xlabel("Grade")
    plt.ylabel("Frequency")
    plt.xticks(letters)
    plt.yticks(range(max(frequencies) + 1))

    # Display the chart
    plt.show()



grades = ['A', 'B', 'C', 'A', 'F', 'B', 'A', 'D', 'C', 'B', 'A']
plot_grade_distribution(grades)
