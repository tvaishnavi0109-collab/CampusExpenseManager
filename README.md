# Campus Expense & Budget Manager

A command-line Java application for recording student/campus expenses, setting monthly budgets, and generating category-wise spending reports.

## 1. Project Overview

Students often make small daily payments for food, travel, study materials, subscriptions, and other campus needs. When these transactions are tracked manually, it becomes difficult to know where money is being spent or whether a monthly budget is being exceeded.

**Campus Expense & Budget Manager** provides a lightweight terminal-based solution with persistent CSV storage.

## 2. Major Functional Modules

1. **Expense Management** – add, view, and delete expense records.
2. **Budget Management** – define a monthly budget and monitor current-month usage.
3. **Reports & Analytics** – calculate monthly totals, category-wise totals, transaction count, and highest expense.
4. **Persistent Storage** – save and reload expense records using a CSV file.
5. **Validation & Error Handling** – validate dates, amounts, IDs, and required fields.

## 3. Technologies Used

- Java 17 or later
- Java Collections Framework (`List`, `Map`)
- Java Streams API
- Java File I/O (`java.nio.file`, buffered readers/writers)
- `java.time` API
- Object-Oriented Programming
- Git/GitHub

No external libraries or database are required.

## 4. Project Structure

```text
CampusExpenseManager/
├── README.md
├── statement.md
├── data/
│   └── expenses.csv
├── docs/
│   └── diagrams/
│       ├── architecture.mmd
│       ├── workflow.mmd
│       ├── use-case.mmd
│       ├── class-diagram.mmd
│       ├── sequence.mmd
│       └── er-diagram.mmd
├── scripts/
│   ├── run.sh
│   └── run.bat
├── src/
│   └── com/campus/expense/
│       ├── Main.java
│       ├── Expense.java
│       ├── Budget.java
│       ├── ExpenseException.java
│       ├── ExpenseRepository.java
│       ├── ReportService.java
│       └── ExpenseManager.java
└── test/
    └── ExpenseManagerTest.java
```

## 5. Requirements

Install **JDK 17+** and verify:

```bash
java -version
javac -version
```

## 6. Run on Windows

From the project root:

```bat
scripts\run.bat
```

Or manually:

```bat
if not exist out mkdir out
javac -d out src\com\campus\expense\*.java
java -cp out com.campus.expense.Main
```

## 7. Run on Linux/macOS

Make the script executable once:

```bash
chmod +x scripts/run.sh
```

Then:

```bash
./scripts/run.sh
```

Or manually:

```bash
mkdir -p out
javac -d out src/com/campus/expense/*.java
java -cp out com.campus.expense.Main
```

## 8. Run Tests

Windows:

```bat
if not exist out mkdir out
javac -d out src\com\campus\expense\*.java test\ExpenseManagerTest.java
java -cp out ExpenseManagerTest
```

Linux/macOS:

```bash
mkdir -p out
javac -d out src/com/campus/expense/*.java test/ExpenseManagerTest.java
java -cp out ExpenseManagerTest
```

Expected output:

```text
ALL TESTS PASSED
```

## 9. How to Use

Start the application and choose:

```text
1. Add Expense
2. View Expenses
3. Delete Expense
4. Set Monthly Budget
5. View Budget Status
6. Reports & Analytics
7. Save & Exit
```

Dates use `YYYY-MM-DD`, for example `2026-09-18`.
Monthly budgets use `YYYY-MM`, for example `2026-09`.

## 10. Data Persistence

Expenses are stored in:

```text
data/expenses.csv
```

The file is created automatically if it does not exist. Existing records are loaded when the program starts.

## 11. Error Handling

The application handles:
- Invalid dates
- Invalid month format
- Negative/zero/non-numeric amounts
- Empty categories/descriptions
- Invalid expense IDs
- Missing or malformed CSV records
- File read/write errors

## 12. Academic Concepts Demonstrated

- Classes and objects
- Encapsulation using private fields and methods
- Constructor overloading
- Static members
- Exception handling
- Collections
- Generics
- Streams and lambda expressions
- File handling
- Date/time API
- Modular design
- Input validation
- Separation of responsibilities

## 13. Sample Workflow

```text
Launch
  ↓
Load CSV data
  ↓
Display menu
  ↓
Choose operation
  ↓
Validate input
  ↓
Process expense/budget/report
  ↓
Display result
  ↓
Save & Exit
```

## 14. GitHub Submission

The repository must be public. Submit only the repository root URL in the required format:

```text
https://github.com/<github-username>/<repo-name>
```

Do not submit a `/tree/main/` or `/blob/` URL.

## 15. Notes

This project is intentionally dependency-free so that an evaluator can compile and run it directly from a terminal without a GUI or external service.
