# TestWise AI — Intelligent Software Testing & Bug Tracking Platform

A B.Tech major-project starter built with Java, Spring Boot, Spring Data JPA, a browser-based frontend, and H2/MySQL. It supports test-case management, bug reports, dashboard metrics, and AI-assisted test generation/bug analysis.

## Current features
- Create, list, update status, and delete test cases.
- Create, list, update status, and delete bug reports.
- Dashboard counts for total/passed/failed/not-run tests and open bugs.
- AI-assisted test-case suggestions and bug analysis.
- Without AI configuration, transparent local rule-based suggestions keep the app usable.
- Optional OpenAI-compatible chat-completions endpoint integration.
- H2 file database by default; environment-based MySQL configuration is supported.

## Requirements
- JDK 17 or newer (JDK 23 should work)
- Maven 3.9+ (or open the project in IntelliJ/VS Code and use its Maven support)
- Internet connection for the first Maven dependency download

## Run on Windows
1. Extract `TestWiseAI.zip`.
2. Open the extracted `TestWiseAI` folder in VS Code.
3. Open Terminal → New Terminal.
4. Run `mvn spring-boot:run`.
5. Open `http://localhost:8080`.

If `mvn` is not recognized, install Maven and add its `bin` folder to PATH, or use the Maven wrapper if you add one later. You can also import `pom.xml` as a Maven project in IntelliJ IDEA.

## Use MySQL (optional)
1. Create a database named `testwise_ai` in MySQL.
2. Set environment variables before starting the app:
   - `DB_URL=jdbc:mysql://localhost:3306/testwise_ai`
   - `DB_USER=your_mysql_user`
   - `DB_PASSWORD=your_mysql_password`
   - `DB_DRIVER=com.mysql.cj.jdbc.Driver`
3. Restart the application. Do not commit passwords to GitHub.

PowerShell example for one terminal session:
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/testwise_ai"
$env:DB_USER="root"
$env:DB_PASSWORD="YOUR_PASSWORD"
$env:DB_DRIVER="com.mysql.cj.jdbc.Driver"
mvn spring-boot:run
```

## Configure a real AI provider (optional)
The application accepts an OpenAI-compatible chat-completions URL:
- `AI_API_URL` e.g. `https://api.openai.com/v1/chat/completions`
- `AI_API_KEY` your private API key
- `AI_MODEL` a model name supported by that endpoint

PowerShell:
```powershell
$env:AI_API_URL="https://api.openai.com/v1/chat/completions"
$env:AI_API_KEY="YOUR_PRIVATE_KEY"
$env:AI_MODEL="gpt-4o-mini"
mvn spring-boot:run
```
The API key is optional and must never be committed or pasted into source files. If omitted or the provider is unavailable, the app falls back to rule-based suggestions; the UI labels that behavior clearly. Provider response parsing is intentionally lightweight for this starter and may need adjustment for providers with different response formats.

## REST API
- `GET /api/dashboard`
- `GET /api/tests`
- `POST /api/tests`
- `PATCH /api/tests/{id}/status` with `{"status":"PASS"}` (`NOT_RUN`, `PASS`, `FAIL`, `BLOCKED`)
- `DELETE /api/tests/{id}`
- `GET /api/bugs`
- `POST /api/bugs`
- `PATCH /api/bugs/{id}/status` with `{"status":"IN_PROGRESS"}` (`OPEN`, `IN_PROGRESS`, `RESOLVED`, `CLOSED`)
- `DELETE /api/bugs/{id}`
- `POST /api/ai/test-cases` with `{"requirement":"A user can reset a password"}`
- `POST /api/ai/analyze-bug` with `{"title":"Login fails","description":"...","expectedResult":"...","actualResult":"..."}`

## Important scope notes
This is a working starter, not a finished production QA platform. It does not yet include authentication/roles, project/team entities, file uploads/screenshots, Selenium execution orchestration, or a full test-run history. Add and test those modules before presenting them as completed features. AI analysis is advisory and should be reviewed by a human. Never use real secrets or sensitive customer data.

## Suggested next milestones
1. Add project/team entities and authentication.
2. Add test-run history and execution records.
3. Add Selenium runner service for a safe, controlled demo website.
4. Add evidence/screenshot attachment support for bug reports.
5. Add charts and exportable test reports.
6. Add integration/unit tests for REST endpoints and validate AI output.


## College submission resources
See `docs/PROJECT_REPORT.md`, `docs/PRESENTATION_OUTLINE.md`, and `docs/VIVA_QUESTIONS.md`.
These are drafts/templates: fill in your details and add screenshots and actual test results from your own run.
