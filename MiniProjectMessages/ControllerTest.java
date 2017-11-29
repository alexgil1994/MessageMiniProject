import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO Problem with the BeforeAll and AfterAll classes.

class ControllerTest {

    // For the output of System.cout in the printData method.
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void cleanUpStreams() {
        System.setOut(null);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handleRequestedActivity() {
    }

    @Test
    void showInstructions() {
    }

    @Test
    void calcNewTime() {
        Controller controller = new Controller();

        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();

        assertEquals(time, controller.calcNewTime(), 10000,  "calcNewTime method in Controller couldnt work correctly");
    }

    @Test
    void printData(ArrayList<Event> tempList) {

        // Getting the current time.
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();

        // Initialising new Events for the arrayList.
        Event event = new Event("Hello there this is a new message to test" , time);
        Event event1 = new Event("This is another message to test", time + 20000);

        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event, event1));

        // Formating the existed time in milliseconds.
        SimpleDateFormat formatAs = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
        Date eventTimeFormatted = new Date(event.getTime());
        Date event1TimeFormatted = new Date(event1.getTime());

        // Preparing the String for print.
        StringBuilder builder = new StringBuilder();
        builder.append("Message ").append(1).append(" : ").append(event.getMessage()).append("   || Time of message : ").append(formatAs.format(eventTimeFormatted)).append("\n").append("Message ").append(2).append(" : ").append(event1.getMessage()).append("   || Time of message : ").append(formatAs.format(event1TimeFormatted));

        // Calling the printData method that we are testing.
        printData(arrayList);

        assertEquals(builder, outContent.toString(), "printData method in Calendar could not work properly.");
    }

    @Test
    void showCongratulationsInner() {
    }

    @Test
    void showCongratulations() {
    }

}