# Student Result Management System (Back-End)


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)

## Introduction

The Student Result Management System is a backend web application developed using the Spring Boot framework and Java, which manage student data, courses, and their results efficiently. It provides functionalities for adding, updating, and deleting student records, managing courses, and recording student results.

## Features

- **Course Management:** Add, update, and delete courses.
- **Student Management:** Add, update, and delete student records.
- **Result Management:** Record and manage student results for various courses.

## Technologies Used

- Java
- Spring Boot
- Junit
- Mockito
- Hibernate
- Spring Data JPA
- H2 Database (for development)
- postgresql (for production)
- Gradle

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Gradle
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your_username/student-result-management-system.git
   
2. Open the project in your IDE.
   
3. Build the project using Gradle:
   ```bash
   gradle clean build
   
4. Run the application
    ```bash
    gradle bootRun

### Usage

- Access the application through the provided endpoints in the controller classes (e.g., /courses/allcourses, /students, /results).
- Use API clients like Postman to interact with the application.
- The application provides CRUD operations for managing courses, students, and their results.
