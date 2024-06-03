"""
Exercise 3: File Handling and Data Processing

Problem Description:
Write a Python function `process_log_file(file_path)` that reads a log file, processes the data, and returns a dictionary with statistics. The log file contains multiple lines, each representing a log entry with the format:
timestamp, log_level, message
- timestamp: A string representing the date and time of the log entry (e.g., "2024-06-02 14:22:10")
- log_level: A string representing the log level (e.g., "INFO", "ERROR", "WARNING")
- message: A string representing the log message

The function should:
1. Read the log file.
2. Count the number of log entries for each log level.
3. Identify the earliest and latest timestamp in the log file.
4. Return a dictionary with the following structure:
{
    "log_counts": {"INFO": x, "ERROR": y, "WARNING": z},
    "earliest_timestamp": "timestamp",
    "latest_timestamp": "timestamp"
}

Function Signature:
def process_log_file(file_path: str) -> dict:
    pass

Example:
Assume the content of `log.txt` is:
2024-06-02 14:22:10, INFO, System started
2024-06-02 14:23:15, ERROR, An error occurred
2024-06-02 14:24:20, WARNING, Low disk space
2024-06-02 14:25:25, INFO, User logged in
Calling `process_log_file("log.txt")` should return:
{
    "log_counts": {"INFO": 2, "ERROR": 1, "WARNING": 1},
    "earliest_timestamp": "2024-06-02 14:22:10",
    "latest_timestamp": "2024-06-02 14:25:25"
}

Grading Scheme:
- File Reading (2 points):
  - Correctly reads the log file.
- Log Level Counting (3 points):
  - Accurately counts the number of log entries for each log level.
- Timestamp Identification (3 points):
  - Correctly identifies the earliest and latest timestamps in the log file.
- Return the Result (1 point):
  - Returns the dictionary with the correct structure and data.
- Code Readability and Comments (1 point):
  - Code is well-structured and includes appropriate comments explaining each step.

Total: 10 points
"""

import datetime


def process_log_file(file_path: str) -> dict:
    """
    This function reads a log file, processes the data, and returns statistics
    including log entry counts for each log level and the earliest and latest timestamps.

    Parameters:
    file_path (str): The path to the log file.

    Returns:
    dict: A dictionary containing the log counts and the earliest and latest timestamps.
    """

    # Initialize the data structures for counting log levels and storing timestamps
    log_counts = {"INFO": 0, "ERROR": 0, "WARNING": 0}
    timestamps = []

    # Step 1: Read the log file
    with open(file_path, 'r') as file:
        for line in file:
            # Split the line into timestamp, log level, and message
            parts = line.strip().split(', ')
            timestamp = parts[0]
            log_level = parts[1]
            message = parts[2]

            # Step 2: Count the log entries for each log level
            if log_level in log_counts:
                log_counts[log_level] += 1

            # Collect the timestamp for later processing
            timestamps.append(timestamp)

    # Step 3: Identify the earliest and latest timestamps
    earliest_timestamp = min(timestamps)
    latest_timestamp = max(timestamps)

    # Step 4: Return the dictionary with the processed data
    return {
        "log_counts": log_counts,
        "earliest_timestamp": earliest_timestamp,
        "latest_timestamp": latest_timestamp
    }


# Example usage:
log_content = """2024-06-02 14:22:10, INFO, System started
2024-06-02 14:23:15, ERROR, An error occurred
2024-06-02 14:24:20, WARNING, Low disk space
2024-06-02 14:25:25, INFO, User logged in"""

# Writing example content to a log file for demonstration
with open("log.txt", "w") as log_file:
    log_file.write(log_content)

# Calling the function with the example log file
result = process_log_file("log.txt")
print(
    result)  # Expected Output: {'log_counts': {'INFO': 2, 'ERROR': 1, 'WARNING': 1}, 'earliest_timestamp': '2024-06-02 14:22:10', 'latest_timestamp': '2024-06-02 14:25:25'}
