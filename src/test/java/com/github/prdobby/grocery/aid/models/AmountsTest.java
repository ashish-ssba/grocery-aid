package com.github.prdobby.grocery.aid.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AmountsTest {
    @Test(expected = IllegalArgumentException.class)
    public void of_throwsExceptionOnAmountLessThanZero() throws Exception {
        Amounts.of("", -1);
    }

    @Test
    public void of_returnsQuantityWhenNullUnitProvided() throws Exception {
        final Amount result = Amounts.of(null, 1);
        assertThat(result).isInstanceOf(Quantity.class);
        assertThat(result.getAmount()).isEqualTo(Double.valueOf(1).toString());
    }

    @Test
    public void of_returnsQuantityWhenEmptyUnitProvided() throws Exception {
        final Amount result = Amounts.of("", 1);
        assertThat(result).isInstanceOf(Quantity.class);
        assertThat(result.getAmount()).isEqualTo(Double.valueOf(1).toString());
    }

    @Test
    public void of_parsesFloatingPointQuantity() throws Exception {
        final Amount result = Amounts.of(null, 0.25);
        assertThat(result).isInstanceOf(Quantity.class);
        assertThat(result.getAmount()).isEqualTo(Double.valueOf(0.25).toString());
    }

    @Test
    public void of_returnsMeasurementWhenUnitProvided() throws Exception {
        final Amount result = Amounts.of("oz", 3);
        assertThat(result).isInstanceOf(Measurement.class);
        assertThat(result.getAmount()).isEqualTo(Double.valueOf(3).toString() + " oz");
    }

    @Test
    public void combine_combinesQuantitiesIntoOne() throws Exception {
        List<Amount> originalAmounts = listOf(Amounts.of(null, 3), Amounts.of(null, 2), Amounts.of(null, 1));

        final Amount result = Amounts.combine(originalAmounts);

        assertThat(result).isInstanceOf(Quantity.class);
        assertThat(result.getAmount()).isEqualTo(Double.valueOf(6).toString());
    }

    @Test
    public void combine_combiningUnlikeMeasurements() throws Exception {
        List<Amount> originalAmounts = listOf(Amounts.of("oz", 3), Amounts.of("tsp", 2), Amounts.of("g", 1));

        final Amount result = Amounts.combine(originalAmounts);

        assertThat(result).isInstanceOf(CombinedAmount.class);
        assertThat(result.getAmount())
            .contains(Double.valueOf(3).toString() + " oz")
            .contains(Double.valueOf(2).toString() + " tsp")
            .contains(Double.valueOf(1).toString() + " g");
    }

    @Test
    public void combine_combiningLikeMeasurements() throws Exception {
        List<Amount> originalAmounts = listOf(Amounts.of("tsp", 3), Amounts.of("tsp", 2), Amounts.of("tsp", 1));

        final Amount result = Amounts.combine(originalAmounts);

        assertThat(result).isInstanceOf(Measurement.class);
        assertThat(result.getAmount()).isEqualTo(Double.valueOf(6).toString() + " tsp");
    }

    @Test
    public void combine_combiningQuantitiesAndUnlikeMeasurements() throws Exception {
        List<Amount> originalAmounts = listOf(Amounts.of(null, 3), Amounts.of("tsp", 2), Amounts.of("g", 1));

        final Amount result = Amounts.combine(originalAmounts);

        assertThat(result).isInstanceOf(CombinedAmount.class);
        assertThat(result.getAmount())
            .contains(Double.valueOf(3).toString())
            .contains(Double.valueOf(2).toString() + " tsp")
            .contains(Double.valueOf(1).toString() + " g");
    }

    @Test
    public void combine_combiningQuantitiesAndLikeMeasurements() throws Exception {
        List<Amount> originalAmounts = listOf(Amounts.of(null, 3), Amounts.of("tsp", 2), Amounts.of("tsp", 1));

        final Amount result = Amounts.combine(originalAmounts);

        assertThat(result).isInstanceOf(CombinedAmount.class);
        assertThat(result.getAmount())
            .contains(Double.valueOf(3).toString())
            .contains(Double.valueOf(3).toString() + " tsp");
    }

    @Test(expected = IllegalArgumentException.class)
    public void combine_combiningUnknownClass() throws Exception {
        List<Amount> originalAmounts = listOf(Amounts.of(null, 3), Amounts.of("tsp", 2));
        originalAmounts.add(() -> "something");

        Amounts.combine(originalAmounts);
    }

    private List<Amount> listOf(final Amount... amounts) {
        List<Amount> list = new ArrayList<>();

        for (final Amount amount : amounts) {
            list.add(amount);
        }

        return list;
    }
}
