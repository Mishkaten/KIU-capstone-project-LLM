class BankAccount:
    """Represents a bank account with basic functionality."""

    def __init__(self, account_number: str, account_holder: str, initial_balance: float = 0.0):
        """Initializes the bank account.

        Args:
            account_number (str): The account number.
            account_holder (str): The name of the account holder.
            initial_balance (float, optional): The initial balance. Defaults to 0.0.
        """
        self.account_number = account_number
        self.account_holder = account_holder
        self.balance = initial_balance
        self.transaction_history = []  # Empty list to store transactions

    def deposit(self, amount: float):
        """Deposits the given amount into the account.

        Args:
            amount (float): The amount to deposit.
        """
        if amount > 0:
            self.balance += amount
            self.transaction_history.append(
                {"type": "deposit", "amount": amount, "balance_after": self.balance}
            )
        else:
            raise ValueError("Deposit amount must be positive.")

    def withdraw(self, amount: float):
        """Withdraws the given amount from the account if sufficient funds are available.

        Args:
            amount (float): The amount to withdraw.

        Raises:
            ValueError: If the withdrawal amount is not positive or if there are insufficient funds.
        """
        if amount > 0:
            if amount <= self.balance:
                self.balance -= amount
                self.transaction_history.append(
                    {"type": "withdraw", "amount": amount, "balance_after": self.balance}
                )
            else:
                raise ValueError("Insufficient funds.")
        else:
            raise ValueError("Withdrawal amount must be positive.")

    def get_balance(self) -> float:
        """Gets the current balance of the account.

        Returns:
            float: The current balance.
        """
        return self.balance

    def get_transaction_history(self) -> list:
        """Gets the transaction history of the account.

        Returns:
            list: A list of transaction dictionaries.
        """
        return self.transaction_history


import unittest

class BankAccount:
    """Represents a bank account with basic functionality."""

    def __init__(self, account_number: str, account_holder: str, initial_balance: float = 0.0):
        """Initializes the bank account.

        Args:
            account_number (str): The account number.
            account_holder (str): The name of the account holder.
            initial_balance (float, optional): The initial balance. Defaults to 0.0.
        """
        self.account_number = account_number
        self.account_holder = account_holder
        self.balance = initial_balance
        self.transaction_history = []  # Empty list to store transactions

    def deposit(self, amount: float):
        """Deposits the given amount into the account.

        Args:
            amount (float): The amount to deposit.
        """
        if amount > 0:
            self.balance += amount
            self.transaction_history.append(
                {"type": "deposit", "amount": amount, "balance_after": self.balance}
            )
        else:
            raise ValueError("Deposit amount must be positive.")

    def withdraw(self, amount: float):
        """Withdraws the given amount from the account if sufficient funds are available.

        Args:
            amount (float): The amount to withdraw.

        Raises:
            ValueError: If the withdrawal amount is not positive or if there are insufficient funds.
        """
        if amount > 0:
            if amount <= self.balance:
                self.balance -= amount
                self.transaction_history.append(
                    {"type": "withdraw", "amount": amount, "balance_after": self.balance}
                )
            else:
                raise ValueError("Insufficient funds.")
        else:
            raise ValueError("Withdrawal amount must be positive.")

    def get_balance(self) -> float:
        """Gets the current balance of the account.

        Returns:
            float: The current balance.
        """
        return self.balance

    def get_transaction_history(self) -> list:
        """Gets the transaction history of the account.

        Returns:
            list: A list of transaction dictionaries.
        """
        return self.transaction_history


class TestBankAccount(unittest.TestCase):

    def test_initialization(self):
        account = BankAccount("123456", "John Doe", 100.0)
        self.assertEqual(account.account_number, "123456")
        self.assertEqual(account.account_holder, "John Doe")
        self.assertEqual(account.get_balance(), 100.0)
        self.assertEqual(account.get_transaction_history(), [])

    def test_deposit(self):
        account = BankAccount("123456", "John Doe", 100.0)
        account.deposit(50.0)
        self.assertEqual(account.get_balance(), 150.0)
        self.assertEqual(account.get_transaction_history(), [
            {"type": "deposit", "amount": 50.0, "balance_after": 150.0}
        ])

    def test_withdraw(self):
        account = BankAccount("123456", "John Doe", 100.0)
        account.withdraw(30.0)
        self.assertEqual(account.get_balance(), 70.0)
        self.assertEqual(account.get_transaction_history(), [
            {"type": "withdraw", "amount": 30.0, "balance_after": 70.0}
        ])

    def test_deposit_negative_amount(self):
        account = BankAccount("123456", "John Doe", 100.0)
        with self.assertRaises(ValueError):
            account.deposit(-50.0)

    def test_withdraw_negative_amount(self):
        account = BankAccount("123456", "John Doe", 100.0)
        with self.assertRaises(ValueError):
            account.withdraw(-30.0)

    def test_withdraw_insufficient_funds(self):
        account = BankAccount("123456", "John Doe", 100.0)
        with self.assertRaises(ValueError):
            account.withdraw(150.0)

    def test_transaction_history(self):
        account = BankAccount("123456", "John Doe", 100.0)
        account.deposit(50.0)
        account.withdraw(30.0)
        self.assertEqual(account.get_transaction_history(), [
            {"type": "deposit", "amount": 50.0, "balance_after": 150.0},
            {"type": "withdraw", "amount": 30.0, "balance_after": 120.0}
        ])

if __name__ == "__main__":
    unittest.main()
