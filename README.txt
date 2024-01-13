# To-Do Service README

## Project Overview

Welcome to the To-Do Service project! This backend service is built on Spring Boot version 3.2.1 and utilizes Java 17. The primary purpose of this service is to provide functionality for managing tasks in a to-do list.

## Technology Stack

- **Spring Boot:** 3.2.1
- **Java:** 17
- **Database:** MariaDB

## Database Schema:
        CREATE TABLE todoapp.users(
            user_id INT AUTO_INCREMENT PRIMARY KEY,
            user_name TEXT
        );

        CREATE TABLE todoapp.tasks(
            task_id INT AUTO_INCREMENT PRIMARY KEY,
            user_id INT,
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