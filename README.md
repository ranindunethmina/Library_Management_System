# Online Library Management System - "BookWorm"

## Introduction
"BookWorm" is an advanced online library management system designed to streamline the processes of managing books, users, transactions, and library branches. This system utilizes the Hibernate framework and JavaFX to offer an efficient and user-friendly interface for both administrators and users.

## Features
1. **User Registration**: Users can register by providing their name, email, and password.
2. **Book Management**: Admins can add, update, and remove books, including details like title, author, genre, and availability.
3. **Borrowing Books**: Users can search for, borrow, and return books.
4. **Transaction Management**: All transactions are recorded with timestamps and user details.
5. **Library Branch Management**: Admins can manage library branches, including adding, updating, and closing branches.
6. **View User Transactions**: Users can view their transaction history.
7. **Advanced Query**: Retrieve users who have not returned borrowed books within the due date.
8. **Transaction Handling**: Ensures data consistency and integrity, with validation of foreign keys to prevent null values.

## Technologies Used
- **Hibernate**: For ORM (Object-Relational Mapping) to handle database interactions.
- **JavaFX**: For creating the user interface.
- **Layered Architecture**: Implements design patterns such as Singleton, Facade, and Factory.

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone <
   https://github.com/ranindunethmina/Library_Management_System>
   ```
2. **Configure Hibernate**:
   Use a property file configuration for Hibernate as described in the references.
3. **Build and Run**:
   Follow the standard JavaFX application build process to run the system.

## Development Guidelines
- **Validation**: All fields are validated using RegEx.
- **Authentication**: Users must log in with their username and password.
- **Password Management**: Users can change their username and password post-login and view the password on the login screen.
- **Database**: Direct use of MySQL clients or JDBC is not allowed.
- **Cascade Types**: Implement cascade types in the database as referenced.

## GitHub Repository
You can find the source code and contribute to the project on GitHub:
[GitHub Repository](https://github.com/ranindunethmina/Library_Management_System)

## License
This project is licensed under the MIT License - see the LICENSE file for details.

