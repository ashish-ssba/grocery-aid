package com.github.prdobby.grocery.aid.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Amounts {
    private Amounts() {}

    public static Amount of(final String unit, final double number) {
        if (number < 0) {
            throw new IllegalArgumentException("An amount cannot be less than 0, was: " + number);
        }

        if (unit == null || unit.isEmpty()) {
            return new Quantity(number);
        } else {
            return new Measurement(unit, number);
        }
    }

    public static Amount combine(final List<Amount> amounts) {
        if (amounts == null || amounts.isEmpty()) {
            throw new IllegalArgumentException("Cannot combine 0 amounts together");
        }

        final Map<Class<? extends Amount>, List<Amount>> mappedByClass = amounts.stream()
            .collect(Collectors.groupingBy(Amount::getClass));

        if (mappedByClass.keySet().size() > 2) {
            String unknownClasses = mappedByClass.keySet().stream()
                .filter(cls -> cls != Quantity.class || cls != Measurement.class)
                .map(Class::getName)
                .collect(Collectors.joining(", "));

            throw new IllegalArgumentException("Cannot combine unknown Amount classes: " + unknownClasses);
        }

        final List<Amount> combinedAmounts = new ArrayList<>();
        if (mappedByClass.containsKey(Quantity.class)) {
            final double quantity = mappedByClass.get(Quantity.class).stream()
                .map(quan -> (Quantity) quan)
                .collect(Collectors.summingDouble(Quantity::getNumber));

            combinedAmounts.add(Amounts.of(null, quantity));
        }

        if (mappedByClass.containsKey(Measurement.class)) {
            final Map<String, List<Measurement>> measurementsByUnit = mappedByClass.get(Measurement.class).stream()
                .map(meas -> (Measurement) meas)
                .collect(Collectors.groupingBy(Measurement::getUnit));

            List<Amount> measurements = measurementsByUnit.values().stream()
                .map(Amounts::combineLikeMeasurements)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            combinedAmounts.addAll(measurements);
        }


        if (combinedAmounts.size() == 1) {
            return combinedAmounts.get(0);
        } else {
            return new CombinedAmount(combinedAmounts);
        }
    }

    private static Amount combineLikeMeasurements(final List<Measurement> measurements) {
        if (measurements == null || measurements.isEmpty()) {
            return null;
        }

        final double quantity = measurements.stream().collect(Collectors.summingDouble(Measurement::getNumber));

        return Amounts.of(measurements.get(0).getUnit(), quantity);
    }
}
