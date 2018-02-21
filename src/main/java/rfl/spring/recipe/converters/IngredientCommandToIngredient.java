package rfl.spring.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rfl.spring.recipe.command.IngredientCommand;
import rfl.spring.recipe.domain.Ingredient;
import rfl.spring.recipe.domain.Recipe;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingc) {
        if (ingc == null) {
            return null;
        }

        final Ingredient ing = new Ingredient();
        ing.setId(ingc.getId());
        ing.setDescription(ingc.getDescription());
        ing.setAmount(ingc.getAmount());
        ing.setUnitOfMeasure(uomConverter.convert(ingc.getUnitOfMeasure()));
        if (ingc.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(ingc.getRecipeId());
            ing.setRecipe(recipe);
            recipe.addIngredient(ing);
        }
        return ing;
    }
}
