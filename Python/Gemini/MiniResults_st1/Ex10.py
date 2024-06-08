import altair as alt
import pandas as pd

def plot_grade_distribution(grades):
    """
    Plots the distribution of grades in a bar chart.

    Args:
        grades: A list of grades (uppercase letters from 'A' to 'F').
    """

    # Data tidying
    grade_counts = {}
    for grade in grades:
        grade_counts[grade] = grade_counts.get(grade, 0) + 1

    grades = list(grade_counts.keys())
    counts = list(grade_counts.values())

    # Create a DataFrame from the grade counts
    df = pd.DataFrame({'Grade': grades, 'Count': counts})

    # Visualization using Altair
    chart = alt.Chart(df).mark_bar().encode(
        x=alt.X('Grade:N', sort='-y', axis=alt.Axis(labelAngle=-45)),
        y=alt.Y('Count:Q'),
        tooltip=['Grade', 'Count']
    ).properties(
        title='Grade Distribution'
    )

    chart.save('grade_distribution_bar_chart.json')

# Sample grades
grades = ['A', 'B', 'A', 'C', 'B', 'A', 'D', 'F', 'B', 'C', 'A']

# Call the function with the sample grades
plot_grade_distribution(grades)