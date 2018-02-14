package rfl.spring.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import rfl.spring.recipe.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String name);
}