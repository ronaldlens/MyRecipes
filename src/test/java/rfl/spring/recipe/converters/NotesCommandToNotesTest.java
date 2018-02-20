package rfl.spring.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import rfl.spring.recipe.command.NotesCommand;
import rfl.spring.recipe.domain.Notes;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    public static final Long ID = new Long(1L);
    public static final String NOTES = "notes";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullValue() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        // given
        NotesCommand notesc = new NotesCommand();
        notesc.setId(ID);
        notesc.setRecipeNotes(NOTES);

        // when
        Notes notes = converter.convert(notesc);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }
}