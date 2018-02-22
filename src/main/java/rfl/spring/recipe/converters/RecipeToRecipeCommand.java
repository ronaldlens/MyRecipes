package rfl.spring.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rfl.spring.recipe.command.RecipeCommand;
import rfl.spring.recipe.domain.Category;
import rfl.spring.recipe.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final CategoryToCategoryCommand catConverter;
    private final IngredientToIngredientCommand ingConverter;
    private final NotesToNotesCommand notConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand catConverter, IngredientToIngredientCommand ingConverter,
                                 NotesToNotesCommand notConverter) {
        this.catConverter = catConverter;
        this.ingConverter = ingConverter;
        this.notConverter = notConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        final RecipeCommand recc = new RecipeCommand();
        recc.setId(recipe.getId());
        recc.setCookTime(recipe.getCookTime());
        recc.setPrepTime(recipe.getPrepTime());
        recc.setDescription(recipe.getDescription());
        recc.setDifficulty(recipe.getDifficulty());
        recc.setDirections(recipe.getDirections());
        recc.setServings(recipe.getServings());
        recc.setSource(recipe.getSource());
        recc.setUrl(recipe.getUrl());
        recc.setNotes(notConverter.convert(recipe.getNotes()));
        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories().forEach((Category cat) -> recc.getCategories().add(catConverter.convert(cat)));
        }
        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            recipe.getIngredients().forEach(ing -> recc.getIngredients().add(ingConverter.convert(ing)));
        }
        return recc;
    }
}
