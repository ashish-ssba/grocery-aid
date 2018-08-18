package com.github.prdobby.grocery.aid.models;

public class Quantity implements Amount {
    private final double number;

    public Quantity(final double number) {
        this.number = number;
    }

    double getNumber() {
        return this.number;
    }

    @Override
    public String getAmount() {
        return String.valueOf(this.number);
    }

    @Override
    public String toString() {
        return this.getAmount();
    }
}
