# NEXUS — Personal Career Command Center

**A centralized platform for managing job applications, tracking career development, and measuring professional growth.**

NEXUS is a full-stack career management application designed to help individuals organize their job search, monitor recruitment progress, manage technical skills, and build a structured roadmap toward their career goals.

The project is being developed with a focus on clean architecture, secure API design, relational data modeling, and maintainable software engineering practices.

## Overview

Managing a job search across multiple companies, recruitment stages, interviews, and learning goals can quickly become difficult. NEXUS aims to bring these activities together in one centralized workspace.

The platform is designed to provide visibility into the entire career development process—from tracking an application to preparing for interviews and evaluating progress toward long-term goals.

## Core Features

The following capabilities define the project roadmap.

- **Career Dashboard** — A consolidated view of application statistics, recruitment progress, upcoming interviews, and learning goals.
- **Job Application Management** — Create, update, search, filter, and organize job applications.
- **Recruitment Pipeline** — Track applications through stages such as Applied, Screening, Assessment, Interview, Offer, and Rejected.
- **Interview Management** — Organize interview schedules, preparation notes, and interview outcomes.
- **Skills & Learning Goals** — Maintain a skills inventory and track learning objectives and associated tasks.
- **Project Portfolio** — Record projects and map them to relevant technical skills.
- **Career Analytics** — Analyze application trends and recruitment outcomes.
- **Authentication & Authorization** — Planned secure registration, login, protected APIs, and user-specific data access.

> **Development status:** NEXUS is under active development. Features will be marked as implemented as they are completed and tested.

## Technology Stack

| Layer | Technology |
|---|---|
| Frontend | React, TypeScript, Vite |
| Backend | Java 17, Spring Boot |
| API | REST |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL 8 |
| Build Tool | Maven |
| Backend IDE | Eclipse IDE |
| Frontend IDE | Visual Studio Code |
| Version Control | Git and GitHub |

## System Architecture

The application follows a layered architecture that separates presentation, business logic, API handling, and persistence.

```text
React + TypeScript
        |
        | HTTP / JSON
        v
Spring Boot REST API
        |
        v
Controller Layer
        |
        v
Service Layer
        |
        v
Repository Layer
        |
        v
MySQL Database
```

### Architectural Responsibilities

- **Controller Layer:** Handles HTTP requests, validates request input, and returns appropriate responses.
- **Service Layer:** Contains business rules and coordinates application operations.
- **Repository Layer:** Provides database access through Spring Data JPA.
- **Entity Layer:** Represents persistent domain objects and their relationships.
- **Frontend Services:** Encapsulate API communication between React components and the backend.

## Project Structure

The backend is organized to support modular development as the application grows.

```text
nexus-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/nexus/nexus_backend/
│   │   │       └── NexusBackendApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

As features are implemented, packages for controllers, services, repositories, entities, DTOs, configuration, and exception handling will be introduced.

## Getting Started

### Prerequisites

Install the following software before running the backend:

- Java Development Kit (JDK) 17
- MySQL 8 or a compatible MySQL server
- Git
- Eclipse IDE or another Java IDE
- Maven, if not using the included Maven Wrapper

### 1. Clone the Repository

```bash
git clone https://github.com/Jaideeeppp/Nexus-Backend.git
cd Nexus-Backend
```

### 2. Create the Database

Connect to your MySQL server and execute:

```sql
CREATE DATABASE nexus_db;
```

If you have already created a database with a different name, use that name consistently in the application configuration.

### 3. Configure Database Connectivity

Configure the database connection in `src/main/resources/application.properties`.

```properties
spring.application.name=nexus-backend

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3307/nexus_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

server.port=8080
```

Set the `DB_PASSWORD` environment variable to your MySQL password before starting the application. Adjust the port, database name, and credentials to match your local MySQL configuration.

**Security note:** Never commit real database credentials, access tokens, or other secrets to GitHub. Keep local configuration private and provide safe example configuration separately.

### 4. Run the Backend

On Windows, execute the Maven Wrapper from the project root:

```bat
mvnw.cmd spring-boot:run
```

Alternatively, import the project into Eclipse as an existing Maven project and run `NexusBackendApplication.java` as a Java application.

When the application starts successfully, the backend should be available at:

```text
http://localhost:8080
```

Individual API endpoints will become available as their corresponding features are implemented.

## API Roadmap

The following endpoints are planned. They are not necessarily available in the current build.

| Resource | Planned Endpoint |
|---|---|
| Authentication | `/api/auth/register`, `/api/auth/login` |
| Job Applications | `/api/applications` |
| Interviews | `/api/interviews` |
| Skills | `/api/skills` |
| Learning Goals | `/api/goals` |
| Projects | `/api/projects` |
| Dashboard | `/api/dashboard` |
| Analytics | `/api/analytics` |

The API will evolve as domain models, validation rules, and authorization requirements are implemented.

## Engineering Principles

NEXUS is being developed with the following principles in mind:

- Separation of concerns through layered architecture.
- RESTful API design and consistent HTTP response semantics.
- Input validation and centralized exception handling.
- Relational data integrity and appropriate database indexing.
- Secure password handling and server-side authorization.
- User-level data isolation for personal career records.
- Maintainable code, meaningful commits, and version-controlled changes.
- Automated testing as application functionality expands.

## Development Roadmap

- [x] Initialize the Spring Boot backend project.
- [x] Configure Java 17 and the build environment.
- [x] Initialize Git version control.
- [ ] Complete GitHub repository integration.
- [ ] Configure and verify MySQL connectivity.
- [ ] Implement the first job application API.
- [ ] Persist and retrieve application records.
- [ ] Build the React and TypeScript frontend.
- [ ] Integrate frontend forms with backend APIs.
- [ ] Implement authentication and authorization.
- [ ] Add interviews, skills, learning goals, and projects.
- [ ] Build dashboard analytics and reporting.
- [ ] Add automated tests and production-readiness improvements.

*Checklist items should be updated as each milestone is completed and verified.*

## Contributing

NEXUS is currently a personal learning and portfolio project. Suggestions, bug reports, and constructive feedback are welcome.

For proposed changes:

1. Create a feature branch.
2. Make a focused change.
3. Test the affected functionality.
4. Submit a pull request with a clear description of the change.

## License

A license has not yet been selected. Until a license is added to this repository, permission to reuse or redistribute this project's code should not be assumed.

## Author

**Jaideepteja Valluru**

GitHub: [@Jaideeeppp](https://github.com/Jaideeeppp)

---

*NEXUS — Organize your career. Track your progress. Build your future.*# Nexus-Backend