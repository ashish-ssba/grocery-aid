package com.github.prdobby.grocery.aid.csv;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.github.prdobby.grocery.aid.models.Amount;
import com.github.prdobby.grocery.aid.models.Amounts;
import com.github.prdobby.grocery.aid.models.GroceryList;
import com.github.prdobby.grocery.aid.models.Ingredient;

public class CsvConverter implements Function<String, GroceryList> {
    private static final String SEPARATOR = ",";

    @Override
    public GroceryList apply(final String input) {
        List<String> lines = convertInputToLines(input);
        
        List<String> recipeNames = getRecipeNames(lines);
        List<Ingredient> ingredients = getIngredients(lines);

        System.out.println(recipeNames.toString());
        System.out.println(ingredients.toString());

        return new GroceryList(recipeNames, ingredients);
    }

    private List<String> convertInputToLines(final String input) {
        return Arrays.asList(input.split("\n"));
    }

    private List<String> getRecipeNames(final List<String> lines) {
        return lines.stream()
            .map(this::getRecipeNameFromLine)
            .collect(Collectors.toList());
    }

    private String getRecipeNameFromLine(final String line) {
        // The recipe name should be the first column in each line
        return line.substring(0, line.indexOf(SEPARATOR));
    }

    private List<Ingredient> getIngredients(final List<String> lines) {
        Map<String, List<Ingredient>> ingredients = lines.stream()
            .map(this::splitLineIntoValues)
            .map(this::removeRecipeNamesFromValues)
            .flatMap(List::stream)
            .map(String::trim)
            .filter(value -> !value.isEmpty())
            .map(this::convertToIngredient)
            .collect(Collectors.groupingBy(Ingredient::getName));


        return ingredients.values().stream()
            .map(this::combineAmounts)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private List<String> splitLineIntoValues(final String line) {
        return Arrays.asList(line.split(SEPARATOR));
    }

    private List<String> removeRecipeNamesFromValues(final List<String> values) {
        return values.subList(1, values.size());
    }

    private Ingredient convertToIngredient(final String value) {
        final String amount = value.substring(0, value.indexOf(' '));
        final int number = Integer.valueOf(amount.replaceAll("[^0-9]",""));
        final String unit = amount.replaceAll("[0-9]","");

        final Amount ingredientAmount = Amounts.of(unit, number);
        final String ingredientName = value.substring(amount.length()).trim();
        return new Ingredient(ingredientName, ingredientAmount);
    }

    private Ingredient combineAmounts(final List<Ingredient> ingredients) {
        if (ingredients.isEmpty()) {
            return null;
        }

        final List<Amount> amounts = ingredients.stream()
            .map(Ingredient::getAmount)
            .collect(Collectors.toList());

        final Amount combinedAmount = Amounts.combine(amounts);
        final String name = ingredients.get(0).getName();
        return new Ingredient(name, combinedAmount);
    }
}
