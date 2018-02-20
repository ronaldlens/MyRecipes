package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.CategoryCommand;
import rfl.spring.recipe.domain.Category;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {
    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "Description";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullValue() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        // given
        CategoryCommand cm = new CategoryCommand();
        cm.setId(ID);
        cm.setDescription(DESCRIPTION);

        // when
        Category cat = converter.convert(cm);

        // then
        assertEquals(ID, cat.getId());
        assertEquals(DESCRIPTION, cat.getDescription());
    }
}