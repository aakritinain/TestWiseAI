# TestWise AI — Viva Preparation

## Project basics
**1. What is TestWise AI?**  
A web application for managing test cases and bug reports, showing QA metrics, and drafting test ideas with an optional AI assistant.

**2. Why did you choose this project?**  
It combines Java backend development, REST APIs, database persistence, and software quality assurance, with an optional AI feature.

**3. What problem does it solve?**  
It brings basic test case and defect tracking into one interface and reduces repetitive drafting work.

**4. Does it automatically detect every bug?**  
No. It records defects reported by a tester and offers advisory analysis. It does not guarantee bug detection.

## Java and Spring Boot
**5. What is Spring Boot?**  
A framework that simplifies creating stand-alone Spring applications with auto-configuration and embedded servers.

**6. What is a REST API?**  
An HTTP interface through which clients access resources using methods such as GET, POST, PATCH, and DELETE.

**7. What does `@RestController` do?**  
It marks a class as a web controller whose method return values are written to the HTTP response body, commonly as JSON.

**8. What is dependency injection?**  
A design approach where a framework provides a class with the objects it depends on instead of having it construct them directly.

**9. What is Spring Data JPA?**  
A Spring project that simplifies data access through repository interfaces and JPA.

**10. What is an entity?**  
A Java class mapped to a database table or persistent entity.

## Database and API
**11. Why use H2?**  
It is convenient for local development and demonstrations without installing a separate database server.

**12. Why support MySQL?**  
MySQL provides a conventional relational database option for deployment and database coursework.

**13. What is CRUD?**  
Create, Read, Update, and Delete operations.

**14. Why validate inputs?**  
To reject missing or invalid data and provide more predictable API behaviour.

**15. What does HTTP 404 mean?**  
The requested resource was not found.

## Testing and QA
**16. What is a test case?**  
A documented set of preconditions or steps, inputs, and expected results used to verify behaviour.

**17. What is the difference between severity and priority?**  
Severity describes the impact of a defect; priority describes how urgently it should be addressed.

**18. What is regression testing?**  
Testing previously working functionality after a change to check that the change did not introduce unintended problems.

**19. What is the difference between expected and actual result?**  
The expected result is the specified behaviour; the actual result is what happened during execution.

**20. What is a smoke test?**  
A small set of basic checks to see whether a build's key functionality works.

## AI component
**21. How is AI integrated?**  
The backend can send prompts to a compatible chat-completions API when its URL and key are configured.

**22. Does the project train its own AI model?**  
No. This version integrates an optional existing model API. The fallback is rule-based and is not a trained ML model.

**23. What is a hallucination?**  
An AI-generated statement that may sound plausible but is unsupported or incorrect.

**24. Why must a tester review AI output?**  
Generated scenarios may miss requirements or contain incorrect assumptions; human review is needed before execution.

**25. What happens without API credentials?**  
The application uses a clearly labelled rule-based fallback for generic suggestions.

## Honest limitations / future work
**26. Does this version run Selenium tests automatically?**  
No. Selenium automation is a proposed future extension, not a completed feature in this version.

**27. What would you add next?**  
Authentication, per-project records, test execution history, Selenium integration, attachments, exportable reports, and more automated tests.

**28. How would you test the API?**  
Use Postman or automated integration tests to check valid inputs, invalid inputs, status codes, database changes, and error cases.

**29. How can the application be secured?**  
Add authentication, authorization, request validation, HTTPS, secret management, rate limiting, and avoid exposing database consoles in production.

**30. What did you learn?**  
Describe only what you personally implemented and tested: Spring Boot APIs, persistence, frontend-to-backend integration, QA workflows, and AI integration.
