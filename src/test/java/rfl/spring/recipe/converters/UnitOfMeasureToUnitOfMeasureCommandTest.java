package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.UnitOfMeasureCommand;
import rfl.spring.recipe.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID = new Long(1L);
    public static final String NAME = "Name";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullValue() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setName(NAME);

        // when
        UnitOfMeasureCommand uomc = converter.convert(uom);

        // then
        assertEquals(ID, uomc.getId());
        assertEquals(NAME, uomc.getName());
    }
}