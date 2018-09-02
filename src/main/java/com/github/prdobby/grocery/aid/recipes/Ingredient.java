package com.github.prdobby.grocery.aid.recipes;

public class Ingredient {
    private final String name;
    private final Unit unit;
    private final Number amount;

    public Ingredient(final String name, final Unit unit, final Number amount) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public Number getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        if (Unit.NONE.equals(this.unit)) {
            return this.amount + " " + this.name;
        } else {
            return this.amount + " " + this.unit.getAbbreviation() + " " + this.name;
        }
    }
}
