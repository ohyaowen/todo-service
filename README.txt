# To-Do Service README
# THIS PROJECT IS STILL WORK IN PROGRESS
## Project Overview

Welcome to the To-Do Service project! This backend service is built on Spring Boot version 3.2.1 and utilizes Java 17. The primary purpose of this service is to provide functionality for managing tasks in a to-do list.

## Technology Stack

- **Spring Boot:** 3.2.1
- **Java:** 17
- **Database:** MariaDB

## Database Schema:
        CREATE TABLE IF NOT EXISTS users(
             user_id INT AUTO_INCREMENT PRIMARY KEY,
             user_name VARCHAR(255) NOT NULL UNIQUE,
             user_password VARCHAR(255)
         );

         CREATE TABLE IF NOT EXISTS tasks(
             task_id INT AUTO_INCREMENT PRIMARY KEY,
             user_id INT NOT NULL,
             title VARCHAR(255) NOT NULL,
             DESCRIPTION TEXT,
             due_date DATE,
             complete_status BOOLEAN NOT NULL,
             FOREIGN KEY (user_id) REFERENCES users(user_id)
         );

## Getting Started

Follow these instructions to set up and run the To-Do Service locally.

### Prerequisites

Make sure you have the following installed on your machine:

- [Java 17](https://openjdk.java.net/projects/jdk/17/)
- [Spring Boot 3.2.1](https://spring.io/projects/spring-boot)
- [MariaDB](https://mariadb.org/)

### Database Configuration

1. Create a MariaDB database with the specified schema above.
2. Configure the database connection properties in the application.properties or application.yml file.

## Build and Run
1. Clone the repository:
git clone https://github.com/ohyaowen/todo-service.git
2. Navigate to the project directory
cd todo-service
3. Build and run the application
mvn spring-boot:run

## API Calls
1. Import the Postman collection file located in /postman/.
2. Run the API calls based on functionality.
# Running the API Calls
1. Open Postman.
2. Click on the Import button in the top left corner.
3. Select the Postman collection file (collection.json) located in the /postman/ directory.
4. Once imported, you will see a list of API requests in the collection.
5. Click on the request you want to run to open it.
6. Click on the "Send" button to make the API call.
7. View the response from the API in the Postman interface.
# API Call Notes
Make sure to review the request details, including the endpoint, request body (if applicable), and expected response, before running the API calls.