Quiz Application
This is a simple Quiz application built using Spring Boot, H2 Database, and Java. It allows users to start a new quiz session, fetch random questions, submit answers, and track their progress.

Features
Start a new session: Allows users to start a new quiz session.
Get random questions: Fetches random quiz questions from the database.
Submit answers: Users can submit answers and the application tracks correct answers.
Track session progress: Users can view the number of questions answered and the number of correct answers.
Technologies Used
Spring Boot: The main framework used to build the application.
H2 Database: In-memory database used to store quiz questions and session data.
JPA (Java Persistence API): To interact with the database.
Thymeleaf: For templating (if used for front-end rendering).
Spring Data JPA: To interact with H2 database.
Setup
Prerequisites
Java 11 or above
Maven
IDE (e.g., IntelliJ IDEA, Eclipse)
Installation
Clone this repository:

bash
Copy code
git clone https://github.com/yourusername/quiz-app.git
Navigate to the project directory:

bash
Copy code
cd quiz-app
Build the project using Maven:

bash
Copy code
mvn clean install
Run the Spring Boot application:

bash
Copy code
mvn spring-boot:run
The application will start on the default port 8080. Open the browser and navigate to:

arduino
Copy code
http://localhost:8080
Database Initialization
The H2 database will automatically be created on application startup, and sample data will be inserted into the question table from the data.sql file.

You can view the H2 database console at:

bash
Copy code
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: password
Endpoints
POST /api/start-session/{userId}: Starts a new quiz session for the user with the given userId.
GET /api/random-question: Fetches a random question for the user to answer.
POST /api/submit-answer: Submits the answer for a question. Requires userId, questionId, and answer.
GET /api/session/{userId}: Fetches the session details for the given userId (total answered questions and correct answers).
Example Requests
Start a new session
bash
Copy code
curl -X POST http://localhost:8080/api/start-session/1
Get a random question
bash
Copy code
curl -X GET http://localhost:8080/api/random-question
Submit an answer
bash
Copy code
curl -X POST http://localhost:8080/api/submit-answer -d '{"userId": 1, "questionId": 2, "answer": "A"}' -H "Content-Type: application/json"
Get session details
bash
Copy code
curl -X GET http://localhost:8080/api/session/1
Application Structure
com.example.quiz.quizapp
controller: Contains REST controllers handling HTTP requests.
service: Contains business logic (e.g., fetching random questions, submitting answers).
repository: Contains JPA repositories to interact with the database.
entity: Contains the JPA entities (Question, QuizSession) representing database tables.
