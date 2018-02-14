package rfl.spring.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import rfl.spring.recipe.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findUnitOfMeasureByName(String name);
}
