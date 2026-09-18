package com.campus.expense;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ExpenseManager {
    private final List<Expense> expenses;
    private final Map<YearMonth, Budget> budgets = new HashMap<>();
    private final ExpenseRepository repository;
    private final ReportService reportService = new ReportService();

    public ExpenseManager(String dataFile) {
        repository = new ExpenseRepository(dataFile);
        expenses = repository.load();
    }

    public String getDataFile() {
        return repository.getFilename();
    }

    public void addExpense(Scanner scanner) throws ExpenseException {
        LocalDate date = readDate(scanner, "Date (YYYY-MM-DD): ");
        String category = readNonEmpty(scanner, "Category (Food/Travel/Study/etc.): ");
        double amount = readPositiveAmount(scanner, "Amount (₹): ");
        String description = readNonEmpty(scanner, "Description: ");

        expenses.add(new Expense(date, category, amount, description));
        System.out.println("Expense added successfully.");
    }

    public void viewExpenses() {
        System.out.println("\n---------------- EXPENSES ----------------");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .forEach(System.out::println);
        System.out.printf("Total records: %d | Overall spending: ₹%.2f%n",
                expenses.size(), reportService.total(expenses));
    }

    public void deleteExpense(Scanner scanner) throws ExpenseException {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to delete.");
            return;
        }
        int id = readInt(scanner, "Enter expense ID to delete: ");
        boolean removed = expenses.removeIf(e -> e.getId() == id);
        if (!removed) throw new ExpenseException("No expense found with ID " + id + ".");
        System.out.println("Expense deleted.");
    }

    public void setBudget(Scanner scanner) throws ExpenseException {
        YearMonth month = readMonth(scanner, "Month (YYYY-MM): ");
        double limit = readPositiveAmount(scanner, "Monthly budget (₹): ");
        budgets.put(month, new Budget(month, limit));
        System.out.printf("Budget for %s set to ₹%.2f%n", month, limit);
    }

    public void showBudgetStatus() throws ExpenseException {
        YearMonth month = YearMonth.now();
        Budget budget = budgets.get(month);
        double spent = reportService.totalForMonth(expenses, month);

        System.out.println("\n------------- CURRENT BUDGET -------------");
        System.out.println("Month: " + month);
        System.out.printf("Spent: ₹%.2f%n", spent);

        if (budget == null) {
            System.out.println("No budget set for the current month.");
            return;
        }

        double remaining = budget.remaining(spent);
        double usage = budget.usagePercent(spent);
        System.out.printf("Budget: ₹%.2f%n", budget.getLimit());
        System.out.printf("Remaining: ₹%.2f%n", remaining);
        System.out.printf("Usage: %.1f%%%n", usage);

        if (remaining < 0) {
            System.out.println("ALERT: Budget exceeded!");
        } else if (usage >= 80) {
            System.out.println("WARNING: More than 80% of the budget is used.");
        } else {
            System.out.println("Status: Within budget.");
        }
    }

    public void showReports() {
        YearMonth month = YearMonth.now();
        Map<String, Double> totals = reportService.categoryTotals(expenses, month);

        System.out.println("\n------------ MONTHLY REPORT -------------");
        System.out.println("Month: " + month);
        System.out.printf("Transactions: %d%n", reportService.countForMonth(expenses, month));
        System.out.printf("Total spent: ₹%.2f%n", reportService.totalForMonth(expenses, month));

        System.out.println("\nCategory-wise spending:");
        if (totals.isEmpty()) {
            System.out.println("No transactions for this month.");
        } else {
            totals.forEach((category, total) ->
                    System.out.printf("  %-15s ₹%.2f%n", category, total));
        }

        reportService.highestExpense(expenses).ifPresent(e ->
                System.out.printf("%nHighest recorded expense: ₹%.2f (%s)%n",
                        e.getAmount(), e.getDescription()));
    }

    public void save() {
        try {
            repository.save(expenses);
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    private LocalDate readDate(Scanner scanner, String prompt) throws ExpenseException {
        System.out.print(prompt);
        try {
            return LocalDate.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            throw new ExpenseException("Invalid date. Use YYYY-MM-DD.");
        }
    }

    private YearMonth readMonth(Scanner scanner, String prompt) throws ExpenseException {
        System.out.print(prompt);
        try {
            return YearMonth.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            throw new ExpenseException("Invalid month. Use YYYY-MM.");
        }
    }

    private double readPositiveAmount(Scanner scanner, String prompt) throws ExpenseException {
        System.out.print(prompt);
        try {
            double value = Double.parseDouble(scanner.nextLine().trim());
            if (value <= 0 || Double.isNaN(value) || Double.isInfinite(value)) {
                throw new NumberFormatException();
            }
            return value;
        } catch (NumberFormatException e) {
            throw new ExpenseException("Amount must be a positive number.");
        }
    }

    private int readInt(Scanner scanner, String prompt) throws ExpenseException {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new ExpenseException("Please enter a valid integer ID.");
        }
    }

    private String readNonEmpty(Scanner scanner, String prompt) throws ExpenseException {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) throw new ExpenseException("This field cannot be empty.");
        return value;
    }
}
