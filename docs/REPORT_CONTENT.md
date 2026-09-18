# Project Report Content

## 1. Cover Page
**Campus Expense & Budget Manager**  
Programming in Java  
VITyarthi – Build Your Own Project  
Student: ____________________  
Registration No.: ____________________  
Faculty: ____________________  
Date: ____________________

## 2. Introduction
Campus Expense & Budget Manager is a terminal-based Java application designed to help students record and analyse everyday expenses. The application demonstrates object-oriented programming, collections, file handling, exception handling, streams, and modular software design.

## 3. Problem Statement
Students frequently make small payments for food, travel, study materials, subscriptions, and other campus needs. Manual tracking makes it difficult to understand monthly spending and identify whether spending is within a planned allowance. The project addresses this problem using a lightweight command-line Java system.

## 4. Functional Requirements
### FR1 – Expense Management
The user can add, view, and delete expenses.

### FR2 – Budget Management
The user can set a monthly budget and monitor spending against it.

### FR3 – Reporting and Analytics
The system calculates monthly total spending, category-wise totals, transaction count, and highest expense.

### FR4 – Persistent Storage
Expense records are stored in a CSV file and loaded when the application starts.

### FR5 – Validation
The system validates dates, amounts, IDs, and required text fields.

## 5. Non-Functional Requirements
1. **Usability:** Menu-driven commands and clear prompts make the application simple to operate.
2. **Reliability:** Invalid inputs are rejected instead of silently creating invalid records.
3. **Maintainability:** Responsibilities are separated into model, repository, manager, and reporting classes.
4. **Performance:** In-memory collection processing keeps operations responsive for typical student-scale datasets.
5. **Resource Efficiency:** No external server or database is required.
6. **Error Handling:** Application-specific `ExpenseException` and I/O handling provide controlled failure messages.

## 6. System Architecture
The system follows a simple layered structure:
- Presentation: `Main`
- Application logic: `ExpenseManager`
- Domain models: `Expense`, `Budget`
- Persistence: `ExpenseRepository`
- Analytics: `ReportService`
- Storage: CSV file

Refer to `docs/diagrams/architecture.mmd`.

## 7. Design Diagrams
The repository contains:
- Use Case Diagram
- Workflow Diagram
- Sequence Diagram
- Class Diagram
- ER Diagram
- Architecture Diagram

These are stored as Mermaid source files under `docs/diagrams/`.

## 8. Design Decisions and Rationale
### Java Collections
`ArrayList` is used for expense records because the application primarily performs sequential traversal and filtering.

### HashMap
Monthly budgets are stored in a `HashMap<YearMonth, Budget>` so a budget can be associated directly with a month.

### Streams
Java Streams simplify aggregation tasks such as total spending and category-wise grouping.

### CSV Storage
CSV was selected because the project is intended to be executable without installing or configuring a database.

### Separation of Responsibilities
`ExpenseRepository` handles persistence, `ReportService` handles analytics, and `ExpenseManager` coordinates application operations. This improves maintainability.

## 9. Implementation Details
The application begins by loading saved expenses. `Main` displays the menu and forwards operations to `ExpenseManager`.

`Expense` represents one transaction and encapsulates its data. `Budget` represents a monthly spending limit. `ExpenseRepository` performs CSV read/write operations. `ReportService` performs calculations using streams and collectors.

Input validation is centralized in helper methods inside `ExpenseManager`. Invalid data generates meaningful error messages rather than terminating the program.

## 10. Results / Screenshots
Run the program using the commands in `README.md` and capture screenshots of:
1. Main menu
2. Adding an expense
3. Viewing expenses
4. Budget status
5. Monthly report
6. Invalid input handling
7. Test result

Insert these screenshots into the final submitted PDF report if required.

## 11. Testing Approach
Testing combines:
- Manual functional testing through the CLI
- Input validation testing
- Persistence testing
- Automated logic checks in `ExpenseManagerTest.java`

The automated tests verify overall total, monthly total, category aggregation, transaction count, and highest expense.

## 12. Challenges Faced
- Designing a useful application while keeping it dependency-free
- Maintaining persistent data through a simple CSV structure
- Handling malformed user input without terminating the application
- Separating application logic into meaningful Java classes

## 13. Learnings and Key Takeaways
The project provided practical experience with Java classes, constructors, encapsulation, collections, maps, streams, lambda expressions, file I/O, date APIs, exception handling, modularity, and testing.

## 14. Future Enhancements
- User authentication
- SQLite/MySQL database integration
- GUI using JavaFX
- Exportable PDF/CSV reports
- Recurring expenses
- Savings goals
- Charts and visual analytics
- Multiple user profiles

## 15. References
- Oracle Java Documentation – Java SE APIs
- Java Platform documentation for Collections, Streams, `java.time`, and NIO file APIs
- VITyarthi Build Your Own Project – General Project Instructions & Submission Guidelines
