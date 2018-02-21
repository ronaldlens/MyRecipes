package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.IngredientCommand;
import rfl.spring.recipe.command.UnitOfMeasureCommand;
import rfl.spring.recipe.domain.Ingredient;
import rfl.spring.recipe.domain.Recipe;
import rfl.spring.recipe.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
    public static final Recipe RECIPE = new Recipe();
    public static final Long RECIPEID = new Long(3L);
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final String DESCRIPTION = "description";
    public static final Long UOMID = new Long(1L);
    public static final Long ID = new Long(2L);

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        RECIPE.setId(RECIPEID);
    }

    @Test
    public void testNullValue() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void testConvertWithNullUom() throws Exception {
        // given
        IngredientCommand ingc = new IngredientCommand();
        ingc.setId(ID);
        ingc.setDescription(DESCRIPTION);
        ingc.setRecipeId(RECIPE.getId());
        ingc.setAmount(AMOUNT);
        ingc.setUnitOfMeasure(null);

        // when
        Ingredient ing = converter.convert(ingc);

        // then
        assertNull(ing.getUnitOfMeasure());
        assertEquals(ID, ing.getId());
        assertEquals(AMOUNT, ing.getAmount());
        assertEquals(DESCRIPTION, ing.getDescription());
    }

    @Test
    public void testConvertWithUom() throws Exception {
        // given
        IngredientCommand ingc = new IngredientCommand();
        ingc.setId(ID);
        ingc.setDescription(DESCRIPTION);
        ingc.setRecipeId(RECIPE.getId());
        ingc.setAmount(AMOUNT);
        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(UOMID);
        ingc.setUnitOfMeasure(uomc);

        // when
        Ingredient ing = converter.convert(ingc);

        // then
        assertNotNull(ing.getUnitOfMeasure());
        assertEquals(UOMID, ing.getUnitOfMeasure().getId());
        assertEquals(ID, ing.getId());
        assertEquals(AMOUNT, ing.getAmount());
        assertEquals(DESCRIPTION, ing.getDescription());

    }
}