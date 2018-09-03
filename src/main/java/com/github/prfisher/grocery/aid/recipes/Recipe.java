package com.github.prfisher.grocery.aid.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Recipe {
    @Id
    private final String id;

    @Field("user_id")
    private final String userId;

    @Field("recipe_name")
    private final String recipeName;

    @Field("ingredients")
    private final List<Ingredient> ingredients;

    public Recipe(final String id, final String userId, final String recipeName, final List<Ingredient> ingredients) {
        this.id = id;
        this.userId = userId;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public List<Ingredient> getIngredients() {
        final List<Ingredient> returnList = new ArrayList<>();
        returnList.addAll(this.ingredients);
        return returnList;
    }

    @Override
    public String toString() {
        return "[id: " + this.id + ", userId: " + this.userId + ", recipeName: " + this.recipeName +
            ", ingredients: " + this.ingredients.toString() + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userId, this.recipeName, this.ingredients);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;

        return Objects.equals(this.id, otherRecipe.getId()) &&
            Objects.equals(this.userId, otherRecipe.getUserId()) &&
            Objects.equals(this.recipeName, otherRecipe.getRecipeName()) &&
            Objects.equals(this.ingredients, otherRecipe.getIngredients());
    }
}