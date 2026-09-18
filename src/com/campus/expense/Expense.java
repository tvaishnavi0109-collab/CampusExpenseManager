package com.campus.expense;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Expense {
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);

    private final int id;
    private final LocalDate date;
    private final String category;
    private final double amount;
    private final String description;

    public Expense(LocalDate date, String category, double amount, String description) {
        this.id = NEXT_ID.getAndIncrement();
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public Expense(int id, LocalDate date, String category, double amount, String description) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
        NEXT_ID.updateAndGet(current -> Math.max(current, id + 1));
    }

    public int getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }

    public String toCsv() {
        return id + "," + date + "," + escape(category) + "," + amount + "," + escape(description);
    }

    private String escape(String value) {
        return value.replace(",", " ");
    }

    @Override
    public String toString() {
        return String.format("#%-3d %-12s %-15s ₹%10.2f  %s",
                id, date, category, amount, description);
    }
}
