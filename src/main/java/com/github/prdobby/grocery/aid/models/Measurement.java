package com.github.prdobby.grocery.aid.models;

public class Measurement implements Amount {
    private final double number;
    private final String unit;

    public Measurement(final String unit, final double number)  {
        this.number = number;
        this.unit = unit;
    }

    double getNumber() {
        return this.number;
    }

    String getUnit() {
        return this.unit;
    }

    @Override
    public String getAmount() {
        return this.number + " " + this.unit;
    }

    @Override
    public String toString() {
        return this.getAmount();
    }
}
