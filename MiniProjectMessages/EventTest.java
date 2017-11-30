import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMessage() {
        Event event = new Event("Hello there", 10000000000L);
        assertEquals("Hello there", event.getMessage(), "getMessage method in Event class couldn't work properly.");
    }

    @Test
    void getTime() {
        Event event = new Event("Hello there", 1000000000L);
        assertEquals(1000000000L, event.getTime(), "getTime method in Event class couldn't work properly.");
    }

}