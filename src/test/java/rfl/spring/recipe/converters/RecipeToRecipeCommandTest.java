package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.RecipeCommand;
import rfl.spring.recipe.domain.*;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
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



    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void testConvert() throws Exception {
        Recipe rec = new Recipe();
        rec.setId(ID);
        rec.setCookTime(COOKTIME);
        rec.setPrepTime(PREPTIME);
        rec.setDescription(DESCRIPTION);
        rec.setDirections(DIRECTIONS);
        rec.setDifficulty(DIFFICULTY);
        rec.setServings(SERVINGS);
        rec.setSource(SOURCE);
        rec.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTESID);
        rec.setNotes(notes);

        Category cat1 = new Category();
        cat1.setId(CAT1ID);
        rec.getCategories().add(cat1);

        Category cat2 = new Category();
        cat2.setId(CAT2ID);
        rec.getCategories().add(cat2);

        Ingredient ing1 = new Ingredient();
        ing1.setId(ING1ID);
        rec.getIngredients().add(ing1);

        Ingredient ing2 = new Ingredient();
        ing2.setId(ING2ID);
        rec.getIngredients().add(ing2);

        RecipeCommand recc = converter.convert(rec);

        assertEquals(ID, recc.getId());
        assertEquals(COOKTIME, recc.getCookTime());
        assertEquals(PREPTIME, recc.getPrepTime());
        assertEquals(DESCRIPTION, recc.getDescription());
        assertEquals(DIRECTIONS, recc.getDirections());
        assertEquals(DIFFICULTY, recc.getDifficulty());
        assertEquals(SERVINGS, recc.getServings());
        assertEquals(SOURCE, recc.getSource());
        assertEquals("bla", recc.getUrl());
        assertEquals(NOTESID, recc.getNotes().getId());
        assertEquals(2, recc.getCategories().size());
        assertEquals(2, recc.getIngredients().size());

    }
}