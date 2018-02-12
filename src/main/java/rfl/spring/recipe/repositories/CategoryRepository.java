package rfl.spring.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import rfl.spring.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
