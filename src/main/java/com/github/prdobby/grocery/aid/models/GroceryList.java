package com.github.prdobby.grocery.aid.models;

import java.util.List;

import com.github.prdobby.grocery.aid.recipes.Ingredient;

public class GroceryList {
    private List<String> recipes;

    private List<Ingredient> ingredients;

    public GroceryList(final List<String> recipes, final List<Ingredient> ingredients) {
        this.recipes = recipes;
        this.ingredients = ingredients;
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
