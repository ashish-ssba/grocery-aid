package com.github.prfisher.grocery.aid.recipes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class RecipeManager {

    private final RecipeRepository repository;

    public RecipeManager(final RecipeRepository recipeRepository) {
        this.repository = recipeRepository;
    }

    public GroceryAidRecipe saveRecipeForUser(final GroceryAidRecipe recipe, final String userId) {
        final Recipe recipeToSave = new Recipe(null, userId, recipe.getRecipeName(), recipe.getIngredients());

        return transformRecipe(this.repository.save(recipeToSave));
    }

    public List<GroceryAidRecipe> getRecipesForUser(final String userId) {
        return transform(this.repository.findByUserId(userId)).collect(Collectors.toList());
    }

    private Stream<GroceryAidRecipe> transform(final Stream<Recipe> dbRecipes) {
        return dbRecipes.map(this::transformRecipe);
    }

    private GroceryAidRecipe transformRecipe(final Recipe recipe) {
        return new GroceryAidRecipe(recipe.getId(), recipe.getRecipeName(), recipe.getIngredients());
    }
}