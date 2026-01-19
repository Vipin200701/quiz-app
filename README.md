# Quiz Application

A web-based Quiz Application built using **Spring Boot** that allows users to create quizzes, add questions, join quizzes, and view results.

---

## 🚀 Features
- Create a new quiz
- Add multiple questions to a quiz
- Join a quiz using quiz ID
- Automatic evaluation of answers
- Display quiz result
- Simple and clean UI

---

## 🛠 Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- HTML, CSS, JavaScript
- Maven
- H2 / PostgreSQL (configurable)

---

## 📂 Project Structure
src
├── main
│ ├── java
│ │ └── com.vipin.QuizeApp
│ │ ├── controller
│ │ ├── model
│ │ ├── repository
│ │ └── service
│ └── resources
│ ├── templates
│ ├── static
│ └── application.properties

yaml
Copy code

---

## ▶️ How to Run the Project

1. Clone the repository:
```bash
git clone https://github.com/Vipin200701/quiz-app.git
Navigate to project directory:

bash
Copy code
cd quiz-app
Run the application:

bash
Copy code
./mvnw spring-boot:run
Open browser and visit:

arduino
Copy code
http://localhost:8080
