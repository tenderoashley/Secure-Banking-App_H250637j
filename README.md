# Secure Banking Application

## Description
A console-based banking application built in Java that lets users register, log in, open savings or checking accounts, and deposit or withdraw funds. All data (users, accounts, transaction history) is persisted to local text files so it survives between runs, with no database required.

## Student Details
- **Name:** [Your Name]
- **Registration Number:** [Your Reg Number]

## Features
- User registration and login, with passwords stored as SHA-256 hashes (never in plain text)
- Create multiple bank accounts per user: **Savings** (minimum balance enforced) or **Checking** (overdraft allowed up to a limit)
- View account balances for the logged-in user
- Deposit and withdraw funds, with validation against each account type's rules
- Full transaction history per account, with timestamps
- Data persistence to text files (`users.txt`, `accounts.txt`, `transactions.txt`) — automatically loaded on startup and saved after every change

## Object-Oriented Design
- **Encapsulation:** all fields are private/protected with controlled access through getters and validated setters/methods
- **Abstraction & Inheritance:** `BankAccount` is an abstract class; `SavingsAccount` and `CheckingAccount` extend it and implement their own withdrawal rules
- **Polymorphism:** the `Bank` class works with accounts purely through the `BankAccount` reference, regardless of the concrete subclass
- **Single Responsibility:** `FileHandler` isolates all file I/O from `Bank`'s business logic, and `Main` only handles the console UI

## Project Structure
```
SecureBankApp/
├── src/
│   ├── Main.java            # Console UI / entry point
│   ├── Bank.java            # Core business logic controller
│   ├── User.java            # User authentication model
│   ├── BankAccount.java     # Abstract account base class
│   ├── SavingsAccount.java  # Savings account rules
│   ├── CheckingAccount.java # Checking account rules
│   ├── Transaction.java     # Transaction record model
│   └── FileHandler.java     # Text-file persistence layer
└── README.md
```

## How to Run
1. Make sure you have a JDK installed (Java 11+).
2. Compile:
   ```
   cd SecureBankApp
   javac -d bin src/*.java
   ```
3. Run:
   ```
   java -cp bin Main
   ```
4. Register a user, log in, then create an account and start banking. Data files (`users.txt`, `accounts.txt`, `transactions.txt`) will be created automatically in the folder you ran it from.

## Notes
- No external libraries are required — only core Java (`java.io`, `java.security`, `java.time`, `java.util`).
- This project was built to demonstrate OOP principles and secure coding practices for ISS 2101 Mini Project (2).
