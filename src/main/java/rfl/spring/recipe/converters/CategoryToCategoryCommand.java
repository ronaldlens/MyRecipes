package rfl.spring.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rfl.spring.recipe.command.CategoryCommand;
import rfl.spring.recipe.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }

        CategoryCommand cm = new CategoryCommand();
        cm.setId(category.getId());
        cm.setDescription(category.getDescription());
        return cm;
    }
}
