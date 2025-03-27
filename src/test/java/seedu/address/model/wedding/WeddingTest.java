package seedu.address.model.wedding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class WeddingTest {

    @Test
    public void constructor_autoGeneratedId_success() throws ParseException {
        // Creates a Wedding with an auto-generated ID
        Wedding w = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );

        // Check that the ID starts with 'W'
        assertTrue(w.getWeddingId().value.startsWith("W"), "Expected ID to start with 'W'");

        // Verify basic fields
        assertEquals(new WeddingName("Alice & Bob"), w.getWeddingName());
        assertEquals(new WeddingDate("01-Dec-2025"), w.getWeddingDate());
        assertEquals(new WeddingLocation("Paris"), w.getWeddingLocation());
    }

    @Test
    public void constructor_withSpecifiedId_success() throws ParseException {
        // Suppose we have an existing ID "W10" from saved data
        Wedding w = new Wedding(
                new WeddingId("W10"),
                new WeddingName("Charlie & Diana"),
                new WeddingDate("01-Jan-2026"),
                new WeddingLocation("Hawaii")
        );
        assertEquals("W10", w.getWeddingId().value);
        assertEquals(new WeddingName("Charlie & Diana"), w.getWeddingName());
        assertEquals(new WeddingDate("01-Jan-2026"), w.getWeddingDate());
        assertEquals(new WeddingLocation("Hawaii"), w.getWeddingLocation());
    }

    @Test
    public void isSameWedding() throws ParseException {
        // Wedding #1
        Wedding w1 = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );

        // same object -> returns true
        assertTrue(w1.isSameWedding(w1));

        // null -> returns false
        assertFalse(w1.isSameWedding(null));

        // different name -> returns false
        Wedding w2 = new Wedding(
                new WeddingName("Eve & Frank"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertFalse(w1.isSameWedding(w2));

        // same name, date, location => returns true
        Wedding w3 = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertTrue(w1.isSameWedding(w3));
    }

    @Test
    public void equals_sameObject_returnsTrue() throws ParseException {
        Wedding w1 = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        // same object reference
        assertEquals(w1, w1);
    }

    @Test
    public void equals_differentType_returnsFalse() throws ParseException {
        Wedding w1 = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertNotEquals(w1, "some string");
    }

    @Test
    public void equals_sameIdSameFields_returnsTrue() throws ParseException {
        // Construct two weddings with the same ID and identical fields
        Wedding w1 = new Wedding(
                new WeddingId("W15"),
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        Wedding w2 = new Wedding(
                new WeddingId("W15"),
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertEquals(w1, w2);
        assertEquals(w1.hashCode(), w2.hashCode());
    }

    @Test
    public void equals_differentId_returnsFalse() throws ParseException {
        // w1 uses auto-generated ID, w2 has a specified ID
        Wedding w1 = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        Wedding w2 = new Wedding(
                new WeddingId("W999"),
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertNotEquals(w1, w2);
    }

    @Test
    public void equals_differentFields_returnsFalse() throws ParseException {
        Wedding w1 = new Wedding(
                new WeddingId("W10"),
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        // Different name
        Wedding w2 = new Wedding(
                new WeddingId("W10"),
                new WeddingName("Eve & Frank"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertNotEquals(w1, w2);

        // Different date
        Wedding w3 = new Wedding(
                new WeddingId("W10"),
                new WeddingName("Alice & Bob"),
                new WeddingDate("31-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertNotEquals(w1, w3);

        // Different location
        Wedding w4 = new Wedding(
                new WeddingId("W10"),
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("London")
        );
        assertNotEquals(w1, w4);
    }

    @Test
    public void toString_correctFormat() throws ParseException {
        Wedding w = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        String expected = String.format("\nWedding: %s (ID: %s)\nDate: %s\nLocation: %s",
                w.getWeddingName(), w.getWeddingId(), w.getWeddingDate(), w.getWeddingLocation());
        assertEquals(expected, w.toString());
    }

    @Test
    public void getTasksString_noTask() {
        Wedding w = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        assertEquals("No tasks found for this wedding", w.getTasksString());
    }

    @Test
    public void getTasksString_oneTask() {
        Wedding w = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        w.addTask(new WeddingTask("Task 1"));
        assertEquals("There is 1 task for this wedding", w.getTasksString());
    }

    @Test
    public void getTasksString_multipleTasks() {
        Wedding w = new Wedding(
                new WeddingName("Alice & Bob"),
                new WeddingDate("01-Dec-2025"),
                new WeddingLocation("Paris")
        );
        w.addTask(new WeddingTask("Task 1"));
        w.addTask(new WeddingTask("Task 2"));
        w.addTask(new WeddingTask("Task 3"));
        assertEquals("There are 3 tasks for this wedding", w.getTasksString());
    }
}
