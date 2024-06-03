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

from datetime import datetime

def process_log_file(file_path: str) -> dict:
    log_counts = {}
    earliest_timestamp = None
    latest_timestamp = None

    with open(file_path, 'r') as file:
        for line in file:
            # Parse the log entry
            timestamp, log_level, _ = line.strip().split(', ', 2)

            # Update log level count
            log_counts[log_level] = log_counts.get(log_level, 0) + 1

            # Update earliest and latest timestamps
            current_timestamp = datetime.strptime(timestamp, '%Y-%m-%d %H:%M:%S')
            if earliest_timestamp is None or current_timestamp < earliest_timestamp:
                earliest_timestamp = current_timestamp
            if latest_timestamp is None or current_timestamp > latest_timestamp:
                latest_timestamp = current_timestamp

    # Format timestamps as strings
    earliest_timestamp_str = earliest_timestamp.strftime('%Y-%m-%d %H:%M:%S')
    latest_timestamp_str = latest_timestamp.strftime('%Y-%m-%d %H:%M:%S')

    return {
        'log_counts': log_counts,
        'earliest_timestamp': earliest_timestamp_str,
        'latest_timestamp': latest_timestamp_str
    }



import os

def test_process_log_file():
    # Create a sample log file
    sample_log_file = "sample_log.txt"
    with open(sample_log_file, "w") as file:
        file.write("2024-06-02 14:22:10, INFO, System started\n")
        file.write("2024-06-02 14:23:15, ERROR, An error occurred\n")
        file.write("2024-06-02 14:24:20, WARNING, Low disk space\n")
        file.write("2024-06-02 14:25:25, INFO, User logged in\n")

    # Call the process_log_file function
    result = process_log_file(sample_log_file)

    # Assert the expected results
    assert result["log_counts"]["INFO"] == 2
    assert result["log_counts"]["ERROR"] == 1
    assert result["log_counts"]["WARNING"] == 1
    assert result["earliest_timestamp"] == "2024-06-02 14:22:10"
    assert result["latest_timestamp"] == "2024-06-02 14:25:25"

    # Clean up the sample log file
    os.remove(sample_log_file)

    print("Test passed!")

test_process_log_file()