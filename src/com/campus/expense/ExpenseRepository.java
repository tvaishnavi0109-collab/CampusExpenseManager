package com.campus.expense;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private final Path file;

    public ExpenseRepository(String filename) {
        this.file = Paths.get(filename);
    }

    public List<Expense> load() {
        List<Expense> expenses = new ArrayList<>();
        if (!Files.exists(file)) return expenses;

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank() || line.startsWith("id,")) continue;
                String[] p = line.split(",", 5);
                if (p.length < 5) continue;
                try {
                    expenses.add(new Expense(
                            Integer.parseInt(p[0]),
                            LocalDate.parse(p[1]),
                            p[2],
                            Double.parseDouble(p[3]),
                            p[4]
                    ));
                } catch (RuntimeException ignored) {
                    // Ignore malformed records rather than stopping the application.
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load existing data: " + e.getMessage());
        }
        return expenses;
    }

    public void save(List<Expense> expenses) throws IOException {
        Path parent = file.getParent();
        if (parent != null) Files.createDirectories(parent);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write("id,date,category,amount,description");
            writer.newLine();
            for (Expense expense : expenses) {
                writer.write(expense.toCsv());
                writer.newLine();
            }
        }
    }

    public String getFilename() {
        return file.toString();
    }
}
