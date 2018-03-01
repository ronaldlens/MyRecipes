package rfl.spring.recipe.services;

import rfl.spring.recipe.command.RecipeCommand;
import rfl.spring.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}


