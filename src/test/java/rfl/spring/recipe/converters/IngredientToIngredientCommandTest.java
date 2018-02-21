package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.IngredientCommand;
import rfl.spring.recipe.domain.Ingredient;
import rfl.spring.recipe.domain.Recipe;
import rfl.spring.recipe.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final String DESCRIPTION = "description";
    public static final Long UOMID = new Long(1L);
    public static final Long ID = new Long(2L);

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullValue() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertWithNullUom() throws Exception {
        // given
        Ingredient ing = new Ingredient();
        ing.setId(ID);
        ing.setDescription(DESCRIPTION);
        ing.setRecipe(RECIPE);
        ing.setAmount(AMOUNT);
        ing.setUnitOfMeasure(null);

        // when
        IngredientCommand ingc = converter.convert(ing);

        // then
        assertNull(ingc.getUnitOfMeasure());
        assertEquals(ID, ingc.getId());
        assertEquals(AMOUNT, ingc.getAmount());
        assertEquals(DESCRIPTION, ingc.getDescription());
    }

    @Test
    public void testConvertWithUom() throws Exception {
        // given
        Ingredient ing = new Ingredient();
        ing.setId(ID);
        ing.setDescription(DESCRIPTION);
        ing.setRecipe(RECIPE);
        ing.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOMID);
        ing.setUnitOfMeasure(uom);

        // when
        IngredientCommand ingc = converter.convert(ing);

        // then
        assertNotNull(ingc.getUnitOfMeasure());
        assertEquals(UOMID, ingc.getUnitOfMeasure().getId());
        assertEquals(ID, ingc.getId());
        assertEquals(AMOUNT, ingc.getAmount());
        assertEquals(DESCRIPTION, ingc.getDescription());

    }
}