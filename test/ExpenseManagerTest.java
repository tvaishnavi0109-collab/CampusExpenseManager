import com.campus.expense.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class ExpenseManagerTest {
    public static void main(String[] args) {
        // Lightweight validation tests for the core data/reporting logic.
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(LocalDate.of(2026, 9, 1), "Food", 100, "Lunch"));
        expenses.add(new Expense(LocalDate.of(2026, 9, 2), "Travel", 200, "Bus"));
        expenses.add(new Expense(LocalDate.of(2026, 8, 20), "Food", 50, "Snack"));

        ReportService reports = new ReportService();

        assertClose(reports.total(expenses), 350, "overall total");
        assertClose(reports.totalForMonth(expenses, YearMonth.of(2026, 9)), 300, "monthly total");
        assertClose(reports.categoryTotals(expenses, YearMonth.of(2026, 9)).get("Food"), 100, "food total");
        assertTrue(reports.countForMonth(expenses, YearMonth.of(2026, 9)) == 2, "transaction count");
        assertTrue(reports.highestExpense(expenses).orElseThrow().getAmount() == 200, "highest expense");

        System.out.println("ALL TESTS PASSED");
    }

    private static void assertClose(double actual, double expected, String name) {
        if (Math.abs(actual - expected) > 0.001)
            throw new AssertionError(name + ": expected " + expected + " but got " + actual);
    }

    private static void assertTrue(boolean condition, String name) {
        if (!condition) throw new AssertionError("Failed: " + name);
    }
}
