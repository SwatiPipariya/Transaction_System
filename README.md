# Banking System with Hibernate

This project is a simple banking system implemented in Java using Hibernate with JPQL (Java Persistence Query Language) and Persistence Annotations. It allows users to perform basic banking operations such as registration, login, account management, deposit, withdrawal, money transfer, and balance inquiry.

## Features

- User Registration: Users can register with their full name, email, and password.
- User Login: Registered users can log in using their email and password.
- Account Management: Users can open accounts, and each account is associated with a unique account number.
- Deposit: Users can deposit money into their accounts.
- Withdrawal: Users can withdraw money from their accounts.
- Money Transfer: Users can transfer money between their own accounts or to other users' accounts.
- Balance Inquiry: Users can check the balance of their accounts.

## Technologies Used

- Java
- Hibernate
- MySQL
- JPQL
- Persistence Annotations

## Setup Instructions

1. Clone the repository: `git clone <repository-url>`
2. Import the project into your preferred IDE.
3. Ensure you have MySQL installed and running.
4. Create a MySQL database named `banking_system`.
5. Update the database connection details in `persistence.xml` file located in `src/main/resources/META-INF/`.
6. Run the `BankingApp.java` class to start the application.

## Project Structure

- `Accounts`: Contains entity class and repository for account management.
- `AccountManager`: Contains classes for managing account operations.
- `User`: Contains entity class and repository for user management.
- `BankingSystem`: Main package containing the `BankingApp` class to run the application.
- `META-INF`: Contains `persistence.xml` for Hibernate configuration.

## Contributors

- [Swati Pipariya]([https://github.com/your-username](https://github.com/SwatiPipariya))

## License

This project is licensed under the [MIT License](LICENSE).
