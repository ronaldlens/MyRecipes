package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.CategoryCommand;
import rfl.spring.recipe.command.IngredientCommand;
import rfl.spring.recipe.command.NotesCommand;
import rfl.spring.recipe.command.RecipeCommand;
import rfl.spring.recipe.domain.*;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final Long ID = new Long(1L);
    public static final Integer COOKTIME = Integer.valueOf(2);
    public static final Integer PREPTIME = Integer.valueOf(3);
    public static final String DESCRIPTION = "description";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf(4);
    public static final String SOURCE = "source";
    public static final String URL = "url";
    public static final Long CAT1ID = new Long(5L);
    public static final Long CAT2ID = new Long(6L);
    public static final Long ING1ID = new Long(7L);
    public static final Long ING2ID = new Long(8L);
    public static final Long NOTESID = new Long(9L);



    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void testConvert() throws Exception {
        RecipeCommand recc = new RecipeCommand();
        recc.setId(ID);
        recc.setCookTime(COOKTIME);
        recc.setPrepTime(PREPTIME);
        recc.setDescription(DESCRIPTION);
        recc.setDirections(DIRECTIONS);
        recc.setDifficulty(DIFFICULTY);
        recc.setServings(SERVINGS);
        recc.setSource(SOURCE);
        recc.setUrl(URL);

        NotesCommand notesc = new NotesCommand();
        notesc.setId(NOTESID);
        recc.setNotes(notesc);

        CategoryCommand cat1c = new CategoryCommand();
        cat1c.setId(CAT1ID);
        recc.getCategories().add(cat1c);

        CategoryCommand cat2c = new CategoryCommand();
        cat2c.setId(CAT2ID);
        recc.getCategories().add(cat2c);

        IngredientCommand ing1 = new IngredientCommand();
        ing1.setId(ING1ID);
        recc.getIngredients().add(ing1);

        IngredientCommand ing2 = new IngredientCommand();
        ing2.setId(ING2ID);
        recc.getIngredients().add(ing2);

        Recipe rec = converter.convert(recc);

        assertEquals(ID, rec.getId());
        assertEquals(COOKTIME, rec.getCookTime());
        assertEquals(PREPTIME, rec.getPrepTime());
        assertEquals(DESCRIPTION, rec.getDescription());
        assertEquals(DIRECTIONS, rec.getDirections());
        assertEquals(DIFFICULTY, rec.getDifficulty());
        assertEquals(SERVINGS, rec.getServings());
        assertEquals(SOURCE, rec.getSource());
        assertEquals(URL, rec.getUrl());
        assertEquals(NOTESID, rec.getNotes().getId());
        assertEquals(2, rec.getCategories().size());
        assertEquals(2, rec.getIngredients().size());

    }
}