from datetime import datetime
from typing import Dict

def process_log_file(file_path: str) -> Dict[str, any]:
    """Processes a log file, counting log levels and identifying earliest/latest timestamps.

    Args:
        file_path (str): The path to the log file.

    Returns:
        Dict[str, any]: A dictionary containing log level counts, earliest timestamp, and latest timestamp.
    """
    log_counts = {}
    earliest_timestamp = None
    latest_timestamp = None

    with open(file_path, "r") as f:
        for line in f:
            line = line.strip()
            if not line:  # Skip empty lines
                continue

            # Parse log entry
            timestamp_str, log_level, _ = line.split(",", 2)  # Ignore message

            # Count log level
            log_counts[log_level] = log_counts.get(log_level, 0) + 1

            # Convert timestamp string to datetime object
            timestamp = datetime.strptime(timestamp_str, "%Y-%m-%d %H:%M:%S")

            # Update earliest and latest timestamps
            if earliest_timestamp is None or timestamp < earliest_timestamp:
                earliest_timestamp = timestamp
            if latest_timestamp is None or timestamp > latest_timestamp:
                latest_timestamp = timestamp

    return {
        "log_counts": log_counts,
        "earliest_timestamp": earliest_timestamp.strftime("%Y-%m-%d %H:%M:%S"),
        "latest_timestamp": latest_timestamp.strftime("%Y-%m-%d %H:%M:%S"),
    }


import tempfile
import os
from datetime import datetime

# Sample log data
log_data = """\
2024-06-08 12:34:56,INFO,This is an info message
2024-06-08 12:35:56,ERROR,This is an error message
2024-06-08 12:36:56,WARN,This is a warning message
2024-06-08 12:37:56,INFO,Another info message
2024-06-08 12:38:56,DEBUG,This is a debug message
2024-06-08 12:39:56,ERROR,Another error message
"""

# Write sample log data to a temporary file
temp_log_file = tempfile.NamedTemporaryFile(delete=False, mode='w', suffix='.log')
temp_log_file.write(log_data)
temp_log_file.close()

# Define the function to process the log file
def process_log_file(file_path: str) -> Dict[str, any]:
    """Processes a log file, counting log levels and identifying earliest/latest timestamps.

    Args:
        file_path (str): The path to the log file.

    Returns:
        Dict[str, any]: A dictionary containing log level counts, earliest timestamp, and latest timestamp.
    """
    log_counts = {}
    earliest_timestamp = None
    latest_timestamp = None

    with open(file_path, "r") as f:
        for line in f:
            line = line.strip()
            if not line:  # Skip empty lines
                continue

            # Parse log entry
            timestamp_str, log_level, _ = line.split(",", 2)  # Ignore message

            # Count log level
            log_counts[log_level] = log_counts.get(log_level, 0) + 1

            # Convert timestamp string to datetime object
            timestamp = datetime.strptime(timestamp_str, "%Y-%m-%d %H:%M:%S")

            # Update earliest and latest timestamps
            if earliest_timestamp is None or timestamp < earliest_timestamp:
                earliest_timestamp = timestamp
            if latest_timestamp is None or timestamp > latest_timestamp:
                latest_timestamp = timestamp

    return {
        "log_counts": log_counts,
        "earliest_timestamp": earliest_timestamp.strftime("%Y-%m-%d %H:%M:%S"),
        "latest_timestamp": latest_timestamp.strftime("%Y-%m-%d %H:%M:%S"),
    }

# Test the process_log_file function
result = process_log_file(temp_log_file.name)

# Print the results
print("Log Counts:", result["log_counts"])
print("Earliest Timestamp:", result["earliest_timestamp"])
print("Latest Timestamp:", result["latest_timestamp"])

# Clean up the temporary file
os.remove(temp_log_file.name)
