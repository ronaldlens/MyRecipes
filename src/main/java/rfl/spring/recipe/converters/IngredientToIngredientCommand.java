package rfl.spring.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rfl.spring.recipe.command.IngredientCommand;
import rfl.spring.recipe.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand ic = new IngredientCommand();
        ic.setId(ingredient.getId());
        ic.setDescription(ingredient.getDescription());
        ic.setAmount(ingredient.getAmount());
        ic.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        if (ingredient.getRecipe() != null) {
            ic.setRecipeId(ingredient.getRecipe().getId());
        }
        return ic;
    }
}
