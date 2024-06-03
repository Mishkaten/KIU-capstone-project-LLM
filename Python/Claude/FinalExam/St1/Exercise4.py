"""
Exercise 4: Classes and Object-Oriented Programming

Problem Description:
Create a Python class `BankAccount` that simulates a bank account with the following attributes and methods:

1. Attributes:
   - `account_number` (str): The account number.
   - `account_holder` (str): The name of the account holder.
   - `balance` (float): The current balance of the account.
   - `transaction_history` (list): A list to store transaction history, where each transaction is represented as a dictionary with keys `type` (either "deposit" or "withdraw"), `amount` (float), and `balance_after` (float).

2. Methods:
   - `__init__(self, account_number: str, account_holder: str, initial_balance: float = 0.0)`: Initializes the account with the given account number, account holder name, and an optional initial balance (default is 0.0).
   - `deposit(self, amount: float)`: Adds the specified amount to the account balance and records the transaction in the history.
   - `withdraw(self, amount: float)`: Deducts the specified amount from the account balance (if sufficient funds are available) and records the transaction in the history. If insufficient funds, raises an exception.
   - `get_balance(self) -> float`: Returns the current account balance.
   - `get_transaction_history(self) -> list`: Returns the transaction history as a list of dictionaries.

Class Signature:
class BankAccount:
    def __init__(self, account_number: str, account_holder: str, initial_balance: float = 0.0):
        pass

    def deposit(self, amount: float):
        pass

    def withdraw(self, amount: float):
        pass

    def get_balance(self) -> float:
        pass

    def get_transaction_history(self) -> list:
        pass

Example:
account = BankAccount("123456789", "John Doe", 100.0)
account.deposit(50.0)
account.withdraw(20.0)
print(account.get_balance())  # Expected Output: 130.0
print(account.get_transaction_history())
# Expected Output: [
#     {"type": "deposit", "amount": 50.0, "balance_after": 150.0},
#     {"type": "withdraw", "amount": 20.0, "balance_after": 130.0}
# ]

Grading Scheme:
- Initialization (2 points):
  - Correctly initializes the account with given details and sets up an empty transaction history.
- Deposit Method (3 points):
  - Correctly updates the balance and records the transaction.
- Withdraw Method (3 points):
  - Correctly updates the balance if sufficient funds are available and records the transaction. Raises an exception for insufficient funds.
- Get Balance (1 point):
  - Correctly returns the current balance.
- Transaction History (2 point):
  - Correctly returns the transaction history.
- Code Readability and Comments (1 point):
  - Code is well-structured and includes appropriate comments explaining each step.

Total: 12 points
"""

class BankAccount:
    def __init__(self, account_number: str, account_holder: str, initial_balance: float = 0.0):
        self.account_number = account_number
        self.account_holder = account_holder
        self.balance = initial_balance
        self.transaction_history = []

    def deposit(self, amount: float):
        self.balance += amount
        transaction = {
            "type": "deposit",
            "amount": amount,
            "balance_after": self.balance
        }
        self.transaction_history.append(transaction)

    def withdraw(self, amount: float):
        if amount > self.balance:
            raise ValueError("Insufficient funds")
        self.balance -= amount
        transaction = {
            "type": "withdraw",
            "amount": amount,
            "balance_after": self.balance
        }
        self.transaction_history.append(transaction)

    def get_balance(self) -> float:
        return self.balance

    def get_transaction_history(self) -> list:
        return self.transaction_history


def test_bank_account():
    # Create a new bank account
    account = BankAccount("123456789", "John Doe", 100.0)

    # Test initial balance
    assert account.get_balance() == 100.0, "Initial balance is incorrect"

    # Test deposit
    account.deposit(50.0)
    assert account.get_balance() == 150.0, "Balance after deposit is incorrect"

    # Test withdraw
    account.withdraw(20.0)
    assert account.get_balance() == 130.0, "Balance after withdraw is incorrect"

    # Test transaction history
    history = account.get_transaction_history()
    assert len(history) == 2, "Transaction history length is incorrect"
    assert history[0]["type"] == "deposit", "Transaction type is incorrect"
    assert history[0]["amount"] == 50.0, "Transaction amount is incorrect"
    assert history[0]["balance_after"] == 150.0, "Balance after transaction is incorrect"
    assert history[1]["type"] == "withdraw", "Transaction type is incorrect"
    assert history[1]["amount"] == 20.0, "Transaction amount is incorrect"
    assert history[1]["balance_after"] == 130.0, "Balance after transaction is incorrect"

    # Test insufficient funds exception
    try:
        account.withdraw(200.0)
        assert False, "Expected an exception for insufficient funds"
    except ValueError as e:
        assert str(e) == "Insufficient funds", "Incorrect exception message"

    print("All tests passed!")

# Run the test function
test_bank_account()