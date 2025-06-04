Spring Social Media Blog API

This project provides the backend API for a hypothetical social media application, designed to manage user accounts and their submitted messages. It's built using the powerful Spring Boot Framework in Java, leveraging its capabilities for automatic dependency injection, data persistence, and conventional data manipulation (CRUD operations).

🚀 Features

This API supports essential social media functionalities, allowing users to:

Register and log in to their accounts securely.
Create, retrieve, update, and delete messages.
View all messages posted on the platform.
Retrieve messages by a specific user or by message ID.
🛠️ Technologies Used
Java: The core programming language.
Spring Boot: The foundation for building the robust and scalable RESTful API.
Spring MVC: For handling web requests and responses.
Spring Data JPA: For seamless data persistence and interaction with the database.
H2 Database: An in-memory database used for development and testing, initialized with provided SQL scripts.
🗄️ Database Schema
The application uses two primary tables to store account and message information. These tables are automatically initialized upon startup using the configuration in application.properties and the provided SQL script.

Account Table
Stores user registration details.

Column	Type	Constraints
accountId	integer	PRIMARY KEY, AUTO_INCREMENT
username	varchar(255)	NOT NULL, UNIQUE
password	varchar(255)	

Export to Sheets
Message Table
Stores details about messages posted by users.

Column	Type	Constraints
messageId	integer	PRIMARY KEY, AUTO_INCREMENT
postedBy	integer	FOREIGN KEY references Account(accountId)
messageText	varchar(255)	
timePostedEpoch	long	

Export to Sheets
Endpoints & User Stories
This API adheres to the following user stories, implemented as RESTful endpoints:

User Accounts
1. Register a new user
Endpoint: POST /register
Request Body: JSON representation of an Account (without accountId).
Success (200 OK):
Username is not blank.
Password is at least 4 characters long.
Username does not already exist.
Response body contains the new Account JSON, including accountId.
New account is persisted to the database.
Failure (409 Conflict): Duplicate username.
Failure (400 Bad Request): Other validation errors.
2. Log in a user
Endpoint: POST /login
Request Body: JSON representation of an Account (username and password).
Success (200 OK):
Username and password match an existing account.
Response body contains the Account JSON, including accountId.
Failure (401 Unauthorized): Invalid username or password.
Messages
3. Create a new message
Endpoint: POST /messages
Request Body: JSON representation of a Message (without messageId).
Success (200 OK):
messageText is not blank and not over 255 characters.
postedBy refers to an existing user.
Response body contains the new Message JSON, including messageId.
New message is persisted to the database.
Failure (400 Bad Request): Validation errors.
4. Retrieve all messages
Endpoint: GET /messages
Response (200 OK): JSON list containing all messages. (Empty list if no messages).
5. Retrieve a message by ID
Endpoint: GET /messages/{messageId}
Response (200 OK): JSON representation of the message. (Empty if no such message).
6. Delete a message by ID
Endpoint: DELETE /messages/{messageId}
Success (200 OK):
If message existed: Response body contains 1 (rows updated).
If message did not exist: Response body is empty (idempotent).
Message is removed from the database if it existed.
7. Update a message text by ID
Endpoint: PATCH /messages/{messageId}
Request Body: JSON containing new messageText value.
Success (200 OK):
Message ID exists.
New messageText is not blank and not over 255 characters.
Response body contains 1 (rows updated).
Message in database has updated messageText.
Failure (400 Bad Request): Validation errors or message not found.
8. Retrieve all messages by a particular user
Endpoint: GET /accounts/{accountId}/messages
Response (200 OK): JSON list containing all messages posted by the specified user. (Empty list if no messages from that user).

Getting Started

To run this project, ensure you have Java and Maven installed. The application is a Spring Boot project, so you can build and run it using standard Spring Boot commands.

This project comes with pre-configured Spring Boot settings, valid application.properties for database setup, and essential database entities. Your task is to implement the service logic and controllers to fulfill the user stories.

Testing

The project includes functional test cases and "SpringTest" to verify the proper leveraging of the Spring framework, including Spring Boot, Spring MVC, and Spring Data. Specifically, SpringTest will ensure:

Beans for AccountService, MessageService, AccountRepository, MessageRepository, and SocialMediaController classes are properly configured.
AccountRepository and MessageRepository are functional JpaRepositories based on their respective Account and Message entities.
The Spring Boot application correctly leverages MVC, identified by Spring's default error message structure.
