# TestWise AI — Presentation Outline

Use this outline to create your college PPT. Add screenshots from the running project and replace bracketed details.

## Slide 1 — Title
- TestWise AI: AI-Powered Software Testing and Bug Tracking Platform
- Student: [Name] | Roll No.: [Roll Number]
- Department / College / University
- Project Guide: [Name]

## Slide 2 — Introduction
- Software testing helps validate expected behaviour.
- Testers need a consistent way to track test cases and defects.
- AI can assist with drafting scenarios and suggested debugging steps.

## Slide 3 — Problem Statement
- Test cases and bug records can be scattered across documents.
- Status summaries can require manual work.
- Test scenario drafting can be repetitive.

## Slide 4 — Objectives
- Central test case and bug management.
- Dashboard for basic QA metrics.
- AI-assisted test scenario drafting.
- Optional model API integration.
- Persistent storage and REST API.

## Slide 5 — Proposed System
Show the workflow: Tester → Browser UI → Spring Boot API → JPA Repository → H2/MySQL; AI Assistant Service optionally calls an AI provider.

## Slide 6 — Technology Stack
Java, Spring Boot, Spring Data JPA, H2/MySQL, HTML, CSS, JavaScript, HTTP/JSON, JUnit.

## Slide 7 — Architecture
Use the architecture diagram in the project report. Explain the controller, service, repository, and database responsibilities.

## Slide 8 — Main Modules
- Dashboard
- Test case management
- Bug reports
- AI assistant
- Database persistence

## Slide 9 — Database Design
Show TestCase and BugReport entities, their primary keys, and key fields.

## Slide 10 — Screenshots / Demonstration
Insert real screenshots of the running application. Demonstrate creating a test case, updating status, reporting a bug, and generating AI suggestions.

## Slide 11 — Testing and Results
List tests you actually executed and their results. Do not invent successful results. Include at least one failure and its resolution if applicable.

## Slide 12 — Limitations and Future Work
- AI results need human review.
- Current version does not execute Selenium tests automatically.
- Future: authentication, test-run history, Selenium integration, report export.

## Slide 13 — Conclusion
TestWise AI combines Java web development, database management, QA workflows, and optional AI-assisted drafting in a single academic prototype.

## Slide 14 — References / Q&A
List official documentation used and invite questions.
