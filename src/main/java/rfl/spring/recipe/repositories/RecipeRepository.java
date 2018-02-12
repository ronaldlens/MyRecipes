package rfl.spring.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import rfl.spring.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
