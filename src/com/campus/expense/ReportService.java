package com.campus.expense;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    public double total(List<Expense> expenses) {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double totalForMonth(List<Expense> expenses, YearMonth month) {
        return expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(month))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public Map<String, Double> categoryTotals(List<Expense> expenses, YearMonth month) {
        return expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(month))
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        TreeMap::new,
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }

    public Optional<Expense> highestExpense(List<Expense> expenses) {
        return expenses.stream()
                .max(Comparator.comparingDouble(Expense::getAmount));
    }

    public long countForMonth(List<Expense> expenses, YearMonth month) {
        return expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(month))
                .count();
    }
}
