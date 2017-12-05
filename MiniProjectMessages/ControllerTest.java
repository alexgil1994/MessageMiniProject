import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    // For the output of System.cout in the printData method.
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    // TODO Problem with the BeforeAll and AfterAll classes.
    @BeforeAll
    public static void setUpStreams() {
        // For the printData method.
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void cleanUpStreams() {
        // For the printData method.
        System.setOut(null);
    }

    @Test
    void handleRequestedActivity() {
    }

    @Test
    void calcNewTime() {
        Controller controller = new Controller();

        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();

        assertEquals(time, controller.calcNewTime(), 10000,  "calcNewTime method in Controller couldn't work correctly");
    }

    // We decided not to test this. I am keeping it for now just in case.
//    @Test
//    void printData(ArrayList<Event> tempList) {
//
//        // Getting the current time.
//        Calendar calendar = Calendar.getInstance();
//        long time = calendar.getTimeInMillis();
//
//        // Initialising new Events for the arrayList.
//        Event event = new Event("Hello there this is a new message to test" , time);
//        Event event1 = new Event("This is another message to test", time + 20000);
//
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event, event1));
//
//        // Formating the existed time in milliseconds.
//        SimpleDateFormat formatAs = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
//        Date eventTimeFormatted = new Date(event.getTime());
//        Date event1TimeFormatted = new Date(event1.getTime());
//
//        // Preparing the String for print.
//        StringBuilder builder = new StringBuilder();
//        builder.append("Message ").append(1).append(" : ").append(event.getMessage()).append("   || Time of message : ").append(formatAs.format(eventTimeFormatted)).append("\n").append("Message ").append(2).append(" : ").append(event1.getMessage()).append("   || Time of message : ").append(formatAs.format(event1TimeFormatted));
//
//        // Calling the printData method that we are testing.
//        printData(arrayList);
//
//        assertEquals(builder, outContent.toString(), "printData method in Calendar could not work properly.");
//    }

    @Test
    void showCongratulationsInner() {
    }

    @Test
    void showCongratulations() {
    }

}