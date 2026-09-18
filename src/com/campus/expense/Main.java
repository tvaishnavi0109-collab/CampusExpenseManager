package com.campus.expense;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager("data/expenses.csv");
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("       CAMPUS EXPENSE & BUDGET MANAGER");
        System.out.println("==============================================");
        System.out.println("Data file: " + manager.getDataFile());

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> manager.addExpense(scanner);
                    case "2" -> manager.viewExpenses();
                    case "3" -> manager.deleteExpense(scanner);
                    case "4" -> manager.setBudget(scanner);
                    case "5" -> manager.showBudgetStatus();
                    case "6" -> manager.showReports();
                    case "7" -> {
                        manager.save();
                        System.out.println("Data saved. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please enter 1-7.");
                }
            } catch (ExpenseException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--------------- MAIN MENU ----------------");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Delete Expense");
        System.out.println("4. Set Monthly Budget");
        System.out.println("5. View Budget Status");
        System.out.println("6. Reports & Analytics");
        System.out.println("7. Save & Exit");
        System.out.print("Enter choice: ");
    }
}
