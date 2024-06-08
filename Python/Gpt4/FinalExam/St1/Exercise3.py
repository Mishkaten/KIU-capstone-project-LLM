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

def process_log_file(file_path: str) -> dict:
    from datetime import datetime

    # Initialize variables for log counts and timestamps
    log_counts = {"INFO": 0, "ERROR": 0, "WARNING": 0}
    earliest_timestamp = None
    latest_timestamp = None

    # Step 1: Read the log file
    with open(file_path, 'r') as file:
        for line in file:
            # Split each line into its components
            timestamp_str, log_level, message = line.strip().split(', ', 2)

            # Step 2: Count the number of log entries for each log level
            if log_level in log_counts:
                log_counts[log_level] += 1

            # Convert timestamp string to a datetime object
            timestamp = datetime.strptime(timestamp_str, "%Y-%m-%d %H:%M:%S")

            # Step 3: Identify the earliest and latest timestamps
            if earliest_timestamp is None or timestamp < earliest_timestamp:
                earliest_timestamp = timestamp
            if latest_timestamp is None or timestamp > latest_timestamp:
                latest_timestamp = timestamp

    # Convert datetime objects back to strings
    earliest_timestamp_str = earliest_timestamp.strftime("%Y-%m-%d %H:%M:%S") if earliest_timestamp else None
    latest_timestamp_str = latest_timestamp.strftime("%Y-%m-%d %H:%M:%S") if latest_timestamp else None

    # Step 4: Return the result dictionary
    result = {
        "log_counts": log_counts,
        "earliest_timestamp": earliest_timestamp_str,
        "latest_timestamp": latest_timestamp_str
    }

    return result

# Example usage
# Assuming log.txt contains the example log entries
file_path = "log.txt"
print(process_log_file(file_path))
