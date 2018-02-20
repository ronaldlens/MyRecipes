package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.CategoryCommand;
import rfl.spring.recipe.domain.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "Description";

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullValue() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        // given
        Category cat = new Category();
        cat.setId(ID);
        cat.setDescription(DESCRIPTION);

        // when
        CategoryCommand catc = converter.convert(cat);

        // then
        assertEquals(ID, catc.getId());
        assertEquals(DESCRIPTION, catc.getDescription());
    }


}