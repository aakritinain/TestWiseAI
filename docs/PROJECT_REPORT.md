# TestWise AI
## AI-Powered Software Testing and Bug Tracking Platform
### B.Tech Computer Science & Technology — Major Project Report Draft

**Student:** [Your Name]  
**Roll Number:** [Your Roll Number]  
**Department:** Computer Science & Technology  
**College:** [College Name]  
**University:** [University Name]  
**Academic Year:** [Academic Year]  
**Project Guide:** [Guide Name]

> Submission note: Replace all bracketed fields, add your institution's required front matter, and include screenshots/results from your own execution. Do not claim tests were run until you have run them.

## Abstract
Software testing is essential for improving software quality, but manually organizing test cases and defect reports can be repetitive and time-consuming. TestWise AI is a web-based software quality-assurance support platform built with Java and Spring Boot. It allows testers to create and manage test cases, record defects, update execution and issue statuses, and review dashboard metrics. An AI-assistant module can draft candidate test scenarios from natural-language requirements and suggest debugging and regression-checking steps for bug reports. When a compatible language-model endpoint is configured, the assistant sends prompts to that endpoint; otherwise, a clearly labeled rule-based fallback provides generic suggestions. The platform uses Spring Data JPA for persistence and supports an embedded H2 database by default, with configuration for MySQL. The goal is to provide a demonstrable foundation for test management and AI-assisted QA while keeping a human tester responsible for reviewing suggestions and confirming defects.

**Keywords:** Java, Spring Boot, Software Testing, Quality Assurance, Bug Tracking, Artificial Intelligence, REST API, MySQL, H2.

## 1. Introduction
Software teams need repeatable ways to document expected behaviour, execute test cases, report defects, and monitor quality. Spreadsheets can be useful for small projects but often separate the test cases, issue records, and progress summaries. TestWise AI brings these activities into a single web application and adds an assistant for drafting test scenarios and analysing defect descriptions.

### 1.1 Problem statement
A tester may need to write similar test cases repeatedly, track test status in separate documents, and manually prepare defect summaries. The project addresses this organizational problem through a small central web application and optional AI-assisted drafting.

### 1.2 Objectives
- Provide a browser-based interface for test case management.
- Record defects with expected and actual results, reproduction steps, severity, and status.
- Display basic test and bug metrics on a dashboard.
- Generate draft test scenarios from natural-language requirements.
- Suggest possible debugging and regression checks for reported defects.
- Persist records in a relational database.
- Expose REST endpoints that can be tested independently.

### 1.3 Scope
The current implementation includes test case CRUD/status updates, bug report CRUD/status updates, dashboard counts, AI-assistant endpoints, and H2/MySQL configuration. It is an academic prototype. Authentication, multi-user authorization, project/team separation, file attachments, a real Selenium execution engine, and comprehensive test-run history are not implemented in this version and should be treated as future work.

## 2. Existing System and Proposed System
### Existing approach
Small projects may use separate spreadsheets or documents for test cases and bugs. This can lead to duplicated information and manual status summaries.

### Proposed approach
TestWise AI combines test records, defect records, dashboard metrics, and AI-assisted drafting in one web interface. The backend validates requests and stores records through Spring Data JPA. The assistant's output is advisory and must be reviewed by a tester.

## 3. System Requirements
### Hardware
- Laptop or desktop computer
- 4 GB RAM minimum recommended; 8 GB or more preferred
- Internet access for downloading Maven dependencies and optionally contacting an AI provider

### Software
- JDK 17 or newer
- Maven 3.9+ or a Java IDE with Maven support
- VS Code, IntelliJ IDEA, or another Java IDE
- Modern browser
- Optional: MySQL Server
- Optional: compatible AI chat-completions endpoint and API key

## 4. Technology Stack
| Layer | Technology | Purpose |
|---|---|---|
| Frontend | HTML, CSS, JavaScript | User interface and API calls |
| Backend | Java, Spring Boot | REST services and application logic |
| Persistence | Spring Data JPA | Database access |
| Database | H2 / MySQL | Store test cases and bug reports |
| AI integration | HTTP chat-completions API | Optional generation and analysis |
| Testing | Spring Boot Test / JUnit | Application context and future unit/API tests |
| Version control | Git / GitHub | Source history and collaboration |

## 5. Architecture
```text
Browser UI (HTML/CSS/JavaScript)
             |
             | HTTP / JSON
             v
Spring Boot REST Controller (/api)
      |                |
      v                v
Repositories       AI Assistant Service
(Spring Data JPA)  (optional model API)
      |
      v
H2 or MySQL Database
```

### Module descriptions
1. **Dashboard:** shows test totals, pass/fail counts, and open bugs.
2. **Test case management:** create, list, update status, and delete test cases.
3. **Bug management:** record issue details and update status.
4. **AI assistant:** draft test ideas and provide possible debugging and regression suggestions.
5. **Persistence:** stores records using JPA entities and repositories.

## 6. Database Design
### TestCase
| Field | Description |
|---|---|
| id | Primary key |
| title | Short test case name |
| steps | Steps to execute |
| expectedResult | Expected behaviour |
| status | NOT_RUN, PASS, FAIL, or BLOCKED |
| priority | LOW, MEDIUM, HIGH, or CRITICAL |
| createdAt | Record creation timestamp |

### BugReport
| Field | Description |
|---|---|
| id | Primary key |
| title | Defect summary |
| description | Defect details |
| stepsToReproduce | Steps for reproduction |
| expectedResult | Expected behaviour |
| actualResult | Observed behaviour |
| severity | LOW, MEDIUM, HIGH, or CRITICAL |
| status | OPEN, IN_PROGRESS, RESOLVED, or CLOSED |
| aiAnalysis | Optional analysis field |
| createdAt | Record creation timestamp |

## 7. Implementation
The Spring Boot application exposes REST endpoints under `/api`. The controller validates required inputs and delegates database operations to Spring Data JPA repositories. The frontend uses `fetch()` to communicate with the API and updates the page with returned data.

The AI assistant first checks whether an API URL and API key are configured. If configured and reachable, it sends a prompt to a compatible chat-completions endpoint. If no configuration is present or the provider call fails, it returns a rule-based fallback. The interface and README distinguish this fallback from model-generated content. AI-generated root causes are hypotheses, not confirmed findings.

## 8. Testing Plan
Complete this section with actual results from your laptop. Suggested tests:

| Test ID | Test | Expected result | Actual result |
|---|---|---|---|
| TC-01 | Open the home page | Dashboard loads | [Fill after execution] |
| TC-02 | Create a test case with required fields | Record is saved and listed | [Fill after execution] |
| TC-03 | Submit a test case without a title | Request is rejected by validation | [Fill after execution] |
| TC-04 | Change test status to PASS | Status and dashboard update | [Fill after execution] |
| TC-05 | Create a bug report | Bug appears in the bug list | [Fill after execution] |
| TC-06 | Change bug status to RESOLVED | Updated status is shown | [Fill after execution] |
| TC-07 | Generate suggestions without AI credentials | Rule-based fallback is labelled | [Fill after execution] |
| TC-08 | Call dashboard endpoint | Correct counts are returned | [Fill after execution] |
| TC-09 | Delete a test case | Record is removed | [Fill after execution] |
| TC-10 | Restart app with H2 file database | Stored records persist | [Fill after execution] |

### How to capture evidence
Take screenshots of the overview dashboard, a saved test case, a saved bug report, test status updates, and the AI assistant. Add captions and refer to them in the report. Record any failures and fixes honestly.

## 9. Advantages
- Centralizes basic test case and bug records.
- Provides a simple interface for a student demonstration.
- Uses Java and Spring Boot for backend development.
- Offers an optional AI integration without requiring a model to be trained locally.
- Supports H2 for easy setup and MySQL for a relational database demonstration.
- REST endpoints make API testing straightforward.

## 10. Limitations
- AI output may be incomplete or incorrect and needs human review.
- The fallback is rule-based, not a trained machine-learning model.
- The starter does not automatically run Selenium tests.
- Authentication and role-based access are not included.
- No project/team isolation or file/screenshot attachment support is included.
- The AI response parser is intentionally lightweight and may need changes for different providers.

## 11. Future Enhancements
- Add login, user roles, and per-project data isolation.
- Add test runs, execution history, and trend charts.
- Add Selenium WebDriver integration for controlled demo websites.
- Add screenshot and log attachments to defects.
- Add CSV/PDF report export.
- Add AI output validation and structured JSON responses.
- Add integration tests for REST endpoints and security review.

## 12. Conclusion
TestWise AI demonstrates how a Java web application can organize test cases and bug reports while incorporating optional AI assistance. It provides a base for practicing Spring Boot, REST APIs, database persistence, and software testing. The system is intended to assist testers rather than replace manual validation or claim that suggested defects have been confirmed.

## References
1. Spring Boot Reference Documentation — https://docs.spring.io/spring-boot/
2. Spring Data JPA Reference Documentation — https://docs.spring.io/spring-data/jpa/reference/
3. Java Documentation — https://docs.oracle.com/en/java/
4. Selenium Documentation (for future extension) — https://www.selenium.dev/documentation/
5. JUnit 5 User Guide — https://junit.org/junit5/docs/current/user-guide/
