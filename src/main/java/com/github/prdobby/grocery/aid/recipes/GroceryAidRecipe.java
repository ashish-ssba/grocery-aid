package com.github.prdobby.grocery.aid.recipes;

import java.util.ArrayList;
import java.util.List;

public class GroceryAidRecipe {
    private final String name;

    private final List<Ingredient> ingredients;

    public GroceryAidRecipe(final String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(final Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public List<Ingredient> getIngredients() {
        final List<Ingredient> returnList = new ArrayList<>();
        returnList.addAll(this.ingredients);
        return returnList;
    }

    public String getName() {
        return this.name;
    }
}