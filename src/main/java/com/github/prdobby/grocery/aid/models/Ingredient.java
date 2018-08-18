package com.github.prdobby.grocery.aid.models;

public class Ingredient {
    private final String name;
    private final Amount amount;

    public Ingredient(final String name, final Amount amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public Amount getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return this.amount.getAmount() + " " + this.name;
    }
}
