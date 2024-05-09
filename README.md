# Food Delivery Application

## Project Objective

The objective of the Food Delivery Application is to provide users with a convenient platform to order food from various dishes, track their orders, and manage their accounts.

## Prerequisites

Before running the Food Delivery Application, ensure you have the following prerequisites installed:

- Java 17
- IntelliJ IDEA (for backend development)
- Visual Studio Code (for frontend development)
- MongoDB Compass (for MongoDB database management)
- MySQL Workbench (for MySQL database management)

Make sure all prerequisites are installed and configured properly before running the application.

## Frontend

The frontend of the Food Delivery Application is developed using React. To run the React application, follow these steps:

1. Navigate to the `frontend` directory.
2. Run `npm install` to install the necessary dependencies.
3. Run `npm run dev` to start the development server.

## Backend

The backend of the Food Delivery Application consists of Spring Boot microservices organized into different directories:

### Config Server

The Config Server is responsible for managing configurations for the microservices. To run the Config Server:

1. Navigate to the `config-server` directory.
2. Run `mvn spring-boot:run` to start the server.

### User Login Microservice

The User Login Microservice handles user authentication and login functionalities. To run the User Login Microservice:

1. Navigate to the `userlogin` directory.
2. Run `mvn spring-boot:run` to start the microservice.

### Orders Microservice

The Orders Microservice manages order-related operations, such as placing orders and order tracking. To run the Orders Microservice:

1. Create a database named `orderdetails_db` in your MySQL Workbench to store order details.
2. Navigate to the `orders` directory.
3. Run `mvn spring-boot:run` to start the microservice.

## Running the Application

After starting both frontend and backend servers, follow these steps to access the application:

1. Go to the browser.
2. Run [http://localhost:5173](http://localhost:5173) to access the Food Delivery Application.
