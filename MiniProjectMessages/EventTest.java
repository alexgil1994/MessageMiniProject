import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void getMessage() {
        Event event = new Event("Hello there", 10000000000L);
        assertEquals("Hello there", event.getMessage(), "getMessage method in Event class couldn't work properly.");
    }

    @Test
    void getMessageEmptyStringMessage() {
        Event event = new Event("", 10000000000L);
        assertEquals("", event.getMessage(), "getMessage method in Event class couldn't work properly.");
    }

    @Test
    void getMessageNumbers() {
        Event event = new Event("123456", 10000000000L);
        assertEquals("123456", event.getMessage(), "getMessage method in Event class couldn't work properly.");
    }

    @Test
    void getMessageString() {
        Event event = new Event("Hello there i am a message with numbers 123456", 10000000000L);
        assertEquals("Hello there i am a message with numbers 123456", event.getMessage(), "getMessage method in Event class couldn't work properly.");
    }

    @Test
    void getTime() {
        Event event = new Event("Hello there", 1000000000L);
        assertEquals(1000000000L, event.getTime(), "getTime method in Event class couldn't work properly.");
    }

}