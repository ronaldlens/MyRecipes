package rfl.spring.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rfl.spring.recipe.command.CategoryCommand;
import rfl.spring.recipe.command.RecipeCommand;
import rfl.spring.recipe.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final CategoryCommandToCategory catConverter;
    private final IngredientCommandToIngredient ingConverter;
    private final NotesCommandToNotes notConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory catConverter, IngredientCommandToIngredient ingConverter,
                                 NotesCommandToNotes notConverter) {
        this.catConverter = catConverter;
        this.ingConverter = ingConverter;
        this.notConverter = notConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recc) {
        if (recc == null) {
            return null;
        }

        final Recipe rec = new Recipe();
        rec.setId(recc.getId());
        rec.setCookTime(recc.getCookTime());
        rec.setPrepTime(recc.getPrepTime());
        rec.setDescription(recc.getDescription());
        rec.setDifficulty(recc.getDifficulty());
        rec.setDirections(recc.getDirections());
        rec.setServings(recc.getServings());
        rec.setSource(recc.getSource());
        rec.setUrl(recc.getUrl());
        rec.setNotes(notConverter.convert(recc.getNotes()));
        if (recc.getCategories() != null && recc.getCategories().size() > 0) {
            recc.getCategories().forEach((CategoryCommand cat) -> rec.getCategories().add(catConverter.convert(cat)));
        }
        if (recc.getIngredients() != null && recc.getIngredients().size() > 0) {
            recc.getIngredients().forEach(ing -> rec.getIngredients().add(ingConverter.convert(ing)));
        }
        return rec;
    }

}
