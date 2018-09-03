package com.github.prdobby.grocery.aid.recipes;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
    Stream<Recipe> findByRecipeNameLike(final String recipeName);

    Stream<Recipe> findByUserId(final String userId);
}