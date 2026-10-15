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
Service Interface
       |
       v
Service Implementation
       |
       v
Repository Layer
       |
       v
MySQL Database
```

### Architectural Responsibilities

- **Controller Layer:** Handles HTTP requests, validates request input, and returns appropriate responses.
- **Service Layer:** Defines application operations through service interfaces and implements business rules in service implementation classes.
- **Repository Layer:** Provides database access through Spring Data JPA.
- **Entity Layer:** Represents persistent domain objects and their relationships.
- **DTO Layer:** Separates API request and response structures from database entities.
- **Frontend Services:** Encapsulate API communication between React components and the backend.

## Project Structure

The backend is organized to support modular development as the application grows.

```text
nexus-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/nexus/backend/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       │   ├── request/
│   │   │       │   └── response/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   │           └── impl/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

The structure will expand as configuration, exception handling, authentication, and additional domain features are implemented.

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

Alternatively, import the project into Eclipse as an existing Maven project and run `NexusBackendApplication.java` as a Spring Boot application.

When the application starts successfully, the backend should be available at:

`http://localhost:8080`

## Implemented API Endpoints

The following job application endpoints have been implemented and tested.

| HTTP Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/job-applications` | Create a job application |
| `GET` | `/api/v1/job-applications` | Retrieve all job applications |
| `GET` | `/api/v1/job-applications/{id}` | Retrieve a job application by ID |
| `DELETE` | `/api/v1/job-applications/{id}` | Delete a job application by ID |

### API Roadmap

The following endpoints are planned and may not yet be implemented.

| Resource | Planned Endpoint |
|---|---|
| Authentication | `/api/auth/register`, `/api/auth/login` |
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
- DTOs for separating external API contracts from persistence entities.
- Service interfaces and implementations for clear business-layer responsibilities.
- Input validation and centralized exception handling.
- Relational data integrity and appropriate database indexing.
- Secure password handling and server-side authorization.
- User-level data isolation for personal career records.
- Maintainable code, meaningful commits, and version-controlled changes.
- Automated testing as application functionality expands.

## Troubleshooting & Development Challenges

This section documents technical challenges encountered during NEXUS development, the debugging steps performed, the resolutions applied, and the lessons learned.

### 1. Spring Boot Port Conflict

**Problem:** The backend could not start because port `8080` was already in use.

**Resolution:** Freed the occupied port and restarted the intended Spring Boot application.

**Lesson learned:** Check port availability and running processes when a Spring Boot application fails to start.

### 2. GitHub Push Rejected

**Problem:** The initial Git push was rejected because the remote repository already contained an initial commit that was missing from the local repository.

**Resolution:** Pulled the remote changes using `git pull origin main --allow-unrelated-histories`, reconciled the repository histories, and successfully pushed the changes.

**Lesson learned:** Independently initialized local and remote repositories may have unrelated Git histories that need to be reconciled.

### 3. Individual REST Endpoints Returning 404

**Problem:** Requests to the individual job application GET and DELETE endpoints returned `404 Not Found`, even though the controller methods appeared correctly implemented.

**Debugging:** Inspected the controller mappings and enabled Spring MVC request-mapping TRACE logs.

```properties
logging.level.org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping=TRACE
```

**Resolution:** Verified the registered request mappings and restarted the application. The endpoints subsequently worked.

**Lesson learned:** When an endpoint returns 404, verify route registration and the running application before rewriting controller methods that appear correct.

### 4. Verifying Generated Database IDs

**Problem:** Testing an individual job application using an assumed ID can fail if the record does not exist.

**Debugging:** Used the collection endpoint to inspect existing records and their generated IDs.

**Resolution:** Used the actual ID returned by the API when testing individual GET and DELETE requests.

**Lesson learned:** Always verify database-generated identifiers rather than assuming their values.

### 5. Database and JPA Diagnostics

**Problem:** Backend development requires distinguishing application startup issues from database connectivity, persistence, and HTTP request issues.

**Debugging:** Examined Spring Boot startup logs, Hibernate initialization messages, generated SQL statements, and API responses.

**Resolution:** Confirmed successful JPA initialization, observed database insert and select operations, and tested API endpoints individually.

**Lesson learned:** Use application logs and focused API tests to isolate problems before modifying application code.

*This troubleshooting log will be updated as development continues. Each entry should document an issue actually encountered, the diagnostic steps taken, the verified resolution, and the technical lesson learned.*

## Development Roadmap

- [x] Initialize the Spring Boot backend project.
- [x] Configure Java 17 and the build environment.
- [x] Initialize Git version control.
- [x] Integrate the backend with GitHub.
- [x] Configure and verify MySQL connectivity.
- [x] Implement job application creation and retrieval APIs.
- [x] Implement individual job application retrieval and deletion APIs.
- [ ] Complete job application update functionality.
- [ ] Add custom exception handling and consistent API error responses.
- [ ] Complete request validation and automated backend tests.
- [ ] Build the React and TypeScript frontend.
- [ ] Integrate frontend forms with backend APIs.
- [ ] Implement authentication and authorization.
- [ ] Add interviews, skills, learning goals, and projects.
- [ ] Build dashboard analytics and reporting.
- [ ] Improve security and production readiness.

*Checklist items are marked complete only after implementation and verification.*

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

*NEXUS — Organize your career. Track your progress. Build your future.*
