package rfl.spring.recipe.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rfl.spring.recipe.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findUnitOfMeasureByName() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findUnitOfMeasureByName("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getName());
    }

    @Test
    public void findUnitOfMeasureByNameCup() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findUnitOfMeasureByName("Cup");
        assertEquals("Cup", uomOptional.get().getName());
    }

}