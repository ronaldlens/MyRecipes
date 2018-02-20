package rfl.spring.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rfl.spring.recipe.command.UnitOfMeasureCommand;
import rfl.spring.recipe.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) {
            return null;
        }

        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(unitOfMeasure.getId());
        uomc.setName(unitOfMeasure.getName());
        return uomc;
    }
}
