package com.github.prfisher.grocery.aid.recipes;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.prfisher.grocery.aid.users.GroceryAidUser;
import com.github.prfisher.grocery.aid.users.UserManager;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeManager recipeManager;

    private final UserManager userManager;

    public RecipeController(final RecipeManager recipeManager, final UserManager userManager) {
        this.recipeManager = recipeManager;
        this.userManager = userManager;
    }

    @PostMapping
    public GroceryAidRecipe saveRecipe(@RequestBody final GroceryAidRecipe recipe, final Authentication authentication) {
        final String authId = Objects.toString(authentication.getPrincipal());
        final GroceryAidUser user = this.userManager.getUser(authId);
        
        return this.recipeManager.saveRecipeForUser(recipe, user.getId());
    }
}
