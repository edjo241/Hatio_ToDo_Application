# Todo Application

The Todo Application is a web-based application built with Spring Boot for the backend and React for the frontend. It allows users to manage their todo items by creating, reading, updating, and deleting them. The application follows a modern software architecture with a clear separation of concerns between the backend and frontend components.

## Features

- **Create Todo Items**: Users can create new todo items by providing a title and description.
- **View Todo Items**: Users can view a list of all their todo items, including their titles, descriptions, and completion status.
- **Update Todo Items**: Users can update the title, description, and completion status of existing todo items.
- **Delete Todo Items**: Users can delete todo items they no longer need.

## Demo Video

Check out the demo video to see the Todo application in action:

[Todo Application Demo](https://youtu.be/_DVgtr5TOWY)

## Technologies Used

### Backend

- **Spring Boot**: The backend is built using the Spring Boot framework, which provides a robust and scalable foundation for building RESTful APIs.
- **Spring Data JPA**: The application utilizes Spring Data JPA for interacting with the database and managing the persistence layer.
- **MYSQL Database**: An MYSQL database is used for development and testing purposes.
- **Maven**: The project is built and managed using Apache Maven.
- **JWT**: Used JWT to authenticate user.

### Frontend

- **React**: The frontend is built using React, a popular JavaScript library for building user interfaces.
- **React Router**: React Router is used for client-side routing and navigation within the application.
- **Axios**: Axios is used for making HTTP requests from the frontend to the backend API.

## Getting Started

To run the Todo Application locally, follow these steps:

### Prerequisites

Make sure you have the following installed on your system:

- Java Development Kit (JDK) 8 or higher
- Node.js and npm (Node Package Manager)
- Git

### Backend Setup

1. Clone the repository:
2. Navigate to the `backend` directory:
3. **Set up the MySQL database**:

The application uses a MySQL database. Before running the application, you need to create a MySQL database and configure the connection details in the `application.properties` file located in `src/main/resources`.

Open the `application.properties` file and update the following properties with your MySQL credentials:
Replace `your_mysql_username` and `your_mysql_password` with your actual MySQL username and password. You can also modify the `todo_schema` database name if needed.
4. #Note

1. Used Github access token to create gist. It may expires which cause error to generates gist file , if it happens please replace with the new github access token. 
2. Gist file will also save in the directory exporedProjects locally in the backend directory.

5. Build the project using Maven:
6. Run the Spring Boot application:
The backend server will start running on `http://localhost:8080`.

### Frontend Setup

1. Navigate to the `frontend` directory:
2. Install the required dependencies:
3. Start the React development server:
The frontend application will be accessible at `http://localhost:3000`.

## Backend Commands

- `mvn clean install`: Builds the Spring Boot backend project and generates the JAR file.
- `mvn spring-boot:run`: Runs the Spring Boot application.
- `mvnw test`: Runs the backend unit tests and integration tests.

## Frontend Commands

- `npm install`: Installs the required dependencies for the React frontend.
- `npm start`: Starts the React development server and launches the application in the browser.

#Note

1. Used Github access token to create gist. It may expires which cause error to generates gist file. 
2. Gist file will also save in the directory exporedProjects locally.

## Testing


To run the backend tests, navigate to the `backend` directory and execute the following command:
- `mvn test`: Runs the backend unit tests and integration tests.





