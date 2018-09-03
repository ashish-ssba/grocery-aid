package com.github.prfisher.grocery.aid.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GroceryAidRecipe {
    @Nullable
    private final String id;

    @Nonnull
    private String recipeName;

    @Nonnull
    private List<Ingredient> ingredients;

    public GroceryAidRecipe(final String id, final String recipeName, final List<Ingredient> ingredients) {
        this.id = id;
        this.setRecipeName(recipeName);
        this.setIngredients(ingredients);
    }

    public GroceryAidRecipe(final String name) {
        this.id = null;
        this.setRecipeName(name);
        this.setIngredients(new ArrayList<>());
    }

    public String getId() {
        return this.id;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public List<Ingredient> getIngredients() {
        final List<Ingredient> returnList = new ArrayList<>();
        returnList.addAll(this.ingredients);
        return returnList;
    }

    public void addIngredient(final Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void setRecipeName(final String newName) {
        if (newName == null) {
            throw new NullPointerException("Recipe name cannot be null");
        }
        this.recipeName = newName;
    }

    public void setIngredients(final List<Ingredient> ingredients) {
        if (ingredients == null) {
            throw new NullPointerException("Ingredients cannot be null");
        }
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ingredients);
    }

    @Override
    public String toString() {
        return this.recipeName + " (" + this.id + "): " + this.ingredients.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GroceryAidRecipe)) {
            return false;
        }

        GroceryAidRecipe otherRecipe = (GroceryAidRecipe) other;

        return otherRecipe.getId().equals(this.id);
    }
}
