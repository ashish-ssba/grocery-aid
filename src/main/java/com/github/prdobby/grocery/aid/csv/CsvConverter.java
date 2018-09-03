package com.github.prdobby.grocery.aid.csv;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.prdobby.grocery.aid.models.GroceryList;
import com.github.prdobby.grocery.aid.recipes.GroceryAidRecipe;
import com.github.prdobby.grocery.aid.recipes.Ingredient;
import com.github.prdobby.grocery.aid.recipes.Unit;

@Component
public class CsvConverter implements Function<String, GroceryList> {
    private static final String SEPARATOR = ",";

    @Override
    public GroceryList apply(final String input) {
        List<GroceryAidRecipe> recipes = convertInputToLines(input).stream()
            .map(this::convertToRecipe)
            .collect(Collectors.toList());

        List<String> recipeNames = recipes.stream()
            .map(GroceryAidRecipe::getRecipeName)
            .collect(Collectors.toList());

        List<Ingredient> ingredients = recipes.stream()
            .map(GroceryAidRecipe::getIngredients)
            .flatMap(List::stream)
            .collect(Collectors.toList());

        return new GroceryList(recipeNames, ingredients);
    }

    private List<String> convertInputToLines(final String input) {
        return Arrays.asList(input.split("\n"));
    }

    private GroceryAidRecipe convertToRecipe(final String line) {
        List<String> values = splitLineIntoValues(line);
        final GroceryAidRecipe recipe = new GroceryAidRecipe(values.remove(0));

        values.stream()
            .map(String::trim)
            .filter(value -> !value.isEmpty())
            .map(this::convertToIngredient)
            .forEach(recipe::addIngredient);

        return recipe;
    }

    private List<String> splitLineIntoValues(final String line) {
        final List<String> values = new ArrayList<>();
        values.addAll(Arrays.asList(line.split(SEPARATOR)));
        return values;
    }

    private Ingredient convertToIngredient(final String value) {
        String strAmount = value.replaceFirst("^([0-9\\.]+).*","$1");
        Number amount = parseAmount(strAmount);
        String valueWithoutAmount = value.replaceFirst(strAmount, "").trim();

        String strUnit = parseStringUnit(valueWithoutAmount);
        Unit unit = Unit.parse(strUnit);
        
        String ingredientName = valueWithoutAmount.replaceFirst(strUnit, "").trim();

        return new Ingredient(ingredientName, unit, amount);
    }

    private Number parseAmount(final String numericStr) {
        if (numericStr.contains(".")) {
            return Double.valueOf(numericStr);
        } else {
            return Integer.valueOf(numericStr);
        }
    }

    private String parseStringUnit(final String value) {
        int spaceIndex = value.indexOf(" ");
        if (spaceIndex == -1) {
            return "";
        }
        String unitStr = value.substring(0, spaceIndex).trim();
        Unit unit = Unit.parse(unitStr);

        if (Unit.NONE.equals(unit)) {
            spaceIndex = value.indexOf(" ", spaceIndex + 1);
            if (spaceIndex == -1) {
                return "";
            }
            unitStr = value.substring(0, spaceIndex).trim();
            unit = Unit.parse(unitStr);
        }

        if (Unit.NONE.equals(unit)) {
            return "";
        } else {
            return unitStr;
        }
    }
}
