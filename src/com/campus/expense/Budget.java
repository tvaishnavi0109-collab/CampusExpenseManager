package com.campus.expense;

import java.time.YearMonth;

public class Budget {
    private final YearMonth month;
    private double limit;

    public Budget(YearMonth month, double limit) {
        this.month = month;
        this.limit = limit;
    }

    public YearMonth getMonth() { return month; }
    public double getLimit() { return limit; }
    public void setLimit(double limit) { this.limit = limit; }

    public double remaining(double spent) {
        return limit - spent;
    }

    public double usagePercent(double spent) {
        if (limit == 0) return 0;
        return (spent / limit) * 100.0;
    }
}
