package com.github.prdobby.grocery.aid.models;

import java.util.List;
import java.util.stream.Collectors;

public class CombinedAmount implements Amount {
    private final List<Amount> amounts;

    public CombinedAmount(final List<Amount> amounts) {
        this.amounts = amounts;
    }

    @Override
    public String getAmount() {
        return String.join(", ", amounts.stream().map(Amount::getAmount).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return this.getAmount();
    }
}
