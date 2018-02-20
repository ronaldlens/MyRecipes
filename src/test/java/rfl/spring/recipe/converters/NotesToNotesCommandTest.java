package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.NotesCommand;
import rfl.spring.recipe.domain.Notes;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final Long ID = new Long(1L);
    public static final String NOTES = "notes";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullValue() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        // given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(NOTES);

        // when
        NotesCommand notesc = converter.convert(notes);

        //then
        assertEquals(ID, notesc.getId());
        assertEquals(NOTES, notesc.getRecipeNotes());
    }
}
