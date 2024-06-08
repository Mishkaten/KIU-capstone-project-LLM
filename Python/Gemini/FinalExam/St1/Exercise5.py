def knapsack(items: list, max_weight: int) -> int:
    """Solves the 0/1 Knapsack problem using dynamic programming.

    Args:
        items (list): A list of tuples, where each tuple represents an item with its (value, weight).
        max_weight (int): The maximum weight the knapsack can carry.

    Returns:
        int: The maximum value that can be achieved without exceeding the weight limit.
    """

    n = len(items)  # Number of items

    # Dynamic programming table initialization
    dp = [[0 for _ in range(max_weight + 1)] for _ in range(n + 1)]

    # Table population
    for i in range(1, n + 1):
        for w in range(1, max_weight + 1):
            value, weight = items[i - 1]

            # If the current item is heavier than the current weight limit, skip it
            if weight > w:
                dp[i][w] = dp[i - 1][w]
            else:
                # Choose the maximum between including the current item or excluding it
                dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - weight] + value)

    # Optimal value calculation
    return dp[n][max_weight]  # The maximum value is in the bottom-right corner of the table


import unittest
import tempfile
import os
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

class TestProcessLogFile(unittest.TestCase):

    def setUp(self):
        """Create a temporary log file for testing."""
        self.test_log_file = tempfile.NamedTemporaryFile(delete=False, mode='w+')
        log_entries = [
            "2024-06-01 12:00:00,INFO,Started process",
            "2024-06-01 12:05:00,ERROR,Failed to open file",
            "2024-06-01 12:10:00,INFO,Retrying operation",
            "2024-06-01 12:15:00,WARNING,Low memory",
            "2024-06-01 12:20:00,INFO,Process completed"
        ]
        self.test_log_file.writelines(f"{entry}\n" for entry in log_entries)
        self.test_log_file.close()

    def tearDown(self):
        """Remove the temporary log file after testing."""
        os.remove(self.test_log_file.name)

    def test_process_log_file(self):
        result = process_log_file(self.test_log_file.name)
        expected_log_counts = {
            "INFO": 3,
            "ERROR": 1,
            "WARNING": 1
        }
        expected_earliest_timestamp = "2024-06-01 12:00:00"
        expected_latest_timestamp = "2024-06-01 12:20:00"

        self.assertEqual(result["log_counts"], expected_log_counts)
        self.assertEqual(result["earliest_timestamp"], expected_earliest_timestamp)
        self.assertEqual(result["latest_timestamp"], expected_latest_timestamp)

if __name__ == "__main__":
    unittest.main()

