import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositoryInMemoryTest {

    @BeforeEach
    void setUp(){

        // TODO Needs to be configured so that the Test methods can use the data.

        // --- For quantity Requests.
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        // --- For time Requests.
        RepositoryInMemory repositoryInMemory1 = new RepositoryInMemory();

        // --- For quantity Requests.
        Event eventA = new Event("A", 1000000000);
        Event eventB = new Event("B", 1000000000);
        Event eventC = new Event("C", 1000000000);
        Event eventD = new Event("D", 1000000000);
        Event eventE = new Event("E", 1000000000);

        // Adding to the list.
        repositoryInMemory.addMessage(eventA);
        repositoryInMemory.addMessage(eventB);
        repositoryInMemory.addMessage(eventC);
        repositoryInMemory.addMessage(eventD);
        repositoryInMemory.addMessage(eventE);


        // --- For time Requests.
        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        // Adding to the list.
        repositoryInMemory1.addMessage(event0);
        repositoryInMemory1.addMessage(event1);
        repositoryInMemory1.addMessage(event2);
        repositoryInMemory1.addMessage(event3);
        repositoryInMemory1.addMessage(event4);
        repositoryInMemory1.addMessage(event5);
        repositoryInMemory1.addMessage(event6);
        repositoryInMemory1.addMessage(event7);
        repositoryInMemory1.addMessage(event8);
        repositoryInMemory1.addMessage(event9);
        repositoryInMemory1.addMessage(event10);
        repositoryInMemory1.addMessage(event11);
        repositoryInMemory1.addMessage(event12);
        repositoryInMemory1.addMessage(event13);
        repositoryInMemory1.addMessage(event14);
        repositoryInMemory1.addMessage(event15);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMessage() {
        // TODO... Problem is that this method is a void so there is no return to compare.

//        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();
//        ArrayList<Event> repositoryInMemList = new ArrayList<>();
//
//        Event event = new Event("Hello", 1000000000);
//
//        repositoryInMemory.addMessage(event);
//
//        // Isws prepei k edw na to valw prwta se Event k meta mesa?
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event));
//        // To 10000 to xw san metavlhth delta gia tous duo xronous.
//        assertEquals(arrayList , repositoryInMemory,"addMessage method in RepositoryInMemory Class didn't work correctly");
    }

    @Test
    void getTempListBasedOnQuantity() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();
        Event event = new Event("A", 1000000000);
        Event event1 = new Event("B", 1000000000);
        Event event2 = new Event("C", 1000000000);
        Event event3 = new Event("D", 1000000000);
        Event event4 = new Event("E", 1000000000);
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3));

        repositoryInMemory.addMessage(event);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);

        assertEquals(arrayList, repositoryInMemory.getTempListBasedOnQuantity(4),"getTempListBasedOnQuantity method in RepositoryInMemory Class didnt work correctly.");
    }

    @Test
    void getLatestMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();
        Event event = new Event("A", 1000000000);
        Event event1 = new Event("B", 1000000000);
        Event event2 = new Event("C", 1000000000);
        Event event3 = new Event("D", 1000000000);
        Event event4 = new Event("E", 1000000000);
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3));

        Collections.sort(arrayList, RepositoryInMemory.EARLIEST_ORDER);

        repositoryInMemory.addMessage(event);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);

        assertEquals(arrayList, repositoryInMemory.getLatestMessages(4),"getLatestMessages method in RepositoryInMemory Class didnt work correctly.");
    }

    @Test
    void getOldestMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();
        Event event = new Event("A", 1000000000);
        Event event1 = new Event("B", 1000000000);
        Event event2 = new Event("C", 1000000000);
        Event event3 = new Event("D", 1000000000);
        Event event4 = new Event("E", 1000000000);
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3));

        Collections.sort(arrayList, RepositoryInMemory.LATEST_ORDER);

        repositoryInMemory.addMessage(event);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);

        assertEquals(arrayList, repositoryInMemory.getOldestMessages(4),"getOldestMessages method in RepositoryInMemory Class didnt work correctly.");
    }

    // Prepei na vgei iso gia ta event2,event3,event4. To event kai event1 einai mikrotera tou calendarRequest pou thetw pio katw, opote den prepei na eisaxthoun sthn lista.
    @Test
    void getTempListBasedOnTime() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        Event event = new Event("A", 1000000000);
        Event event1 = new Event("B", 1000000000);
        Event event2 = new Event("C", 2000000000);
        Event event3 = new Event("D", 2000000000);
        Event event4 = new Event("E", 1666666666);
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event2,event3,event4));

        repositoryInMemory.addMessage(event);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);

        Calendar calendarRequest = Calendar.getInstance();
        calendarRequest.setTimeInMillis(1555555555);

        assertEquals(arrayList, repositoryInMemory.getTempListBasedOnTime(calendarRequest),"getLastTempListBasedOnTime method in RepositoryInMemory Class didnt work correctly.");
    }

    @Test
    void getLastHourMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3));

        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        repositoryInMemory.addMessage(event0);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);
        repositoryInMemory.addMessage(event5);
        repositoryInMemory.addMessage(event6);
        repositoryInMemory.addMessage(event7);
        repositoryInMemory.addMessage(event8);
        repositoryInMemory.addMessage(event9);
        repositoryInMemory.addMessage(event10);
        repositoryInMemory.addMessage(event11);
        repositoryInMemory.addMessage(event12);
        repositoryInMemory.addMessage(event13);
        repositoryInMemory.addMessage(event14);
        repositoryInMemory.addMessage(event15);

        assertEquals(arrayList, repositoryInMemory.getLastHourMessages(),"getLastHourMessages method in RepositoryInMemory Class didn't work correctly.");
    }

    @Test
    void getLastThreeHoursMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7));

        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        repositoryInMemory.addMessage(event0);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);
        repositoryInMemory.addMessage(event5);
        repositoryInMemory.addMessage(event6);
        repositoryInMemory.addMessage(event7);
        repositoryInMemory.addMessage(event8);
        repositoryInMemory.addMessage(event9);
        repositoryInMemory.addMessage(event10);
        repositoryInMemory.addMessage(event11);
        repositoryInMemory.addMessage(event12);
        repositoryInMemory.addMessage(event13);
        repositoryInMemory.addMessage(event14);
        repositoryInMemory.addMessage(event15);

        assertEquals(arrayList, repositoryInMemory.getLastThreeHoursMessages(),"getLastThreeHoursMessages method in RepositoryInMemory Class didn't work correctly.");
    }

    @Test
    void getLastOneDayMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8));

        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        repositoryInMemory.addMessage(event0);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);
        repositoryInMemory.addMessage(event5);
        repositoryInMemory.addMessage(event6);
        repositoryInMemory.addMessage(event7);
        repositoryInMemory.addMessage(event8);
        repositoryInMemory.addMessage(event9);
        repositoryInMemory.addMessage(event10);
        repositoryInMemory.addMessage(event11);
        repositoryInMemory.addMessage(event12);
        repositoryInMemory.addMessage(event13);
        repositoryInMemory.addMessage(event14);
        repositoryInMemory.addMessage(event15);

        assertEquals(arrayList, repositoryInMemory.getLastOneDayMessages(),"getLastOneDayMessages method in RepositoryInMemory Class didn't work correctly.");
    }

    @Test
    void getLastThreeDaysMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10));

        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        repositoryInMemory.addMessage(event0);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);
        repositoryInMemory.addMessage(event5);
        repositoryInMemory.addMessage(event6);
        repositoryInMemory.addMessage(event7);
        repositoryInMemory.addMessage(event8);
        repositoryInMemory.addMessage(event9);
        repositoryInMemory.addMessage(event10);
        repositoryInMemory.addMessage(event11);
        repositoryInMemory.addMessage(event12);
        repositoryInMemory.addMessage(event13);
        repositoryInMemory.addMessage(event14);
        repositoryInMemory.addMessage(event15);

        assertEquals(arrayList, repositoryInMemory.getLastThreeDaysMessages(),"getLastThreeDaysMessages method in RepositoryInMemory Class didn't work correctly.");
    }

    @Test
    void getLastTenDaysMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10,event11,event12));

        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        repositoryInMemory.addMessage(event0);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);
        repositoryInMemory.addMessage(event5);
        repositoryInMemory.addMessage(event6);
        repositoryInMemory.addMessage(event7);
        repositoryInMemory.addMessage(event8);
        repositoryInMemory.addMessage(event9);
        repositoryInMemory.addMessage(event10);
        repositoryInMemory.addMessage(event11);
        repositoryInMemory.addMessage(event12);
        repositoryInMemory.addMessage(event13);
        repositoryInMemory.addMessage(event14);
        repositoryInMemory.addMessage(event15);

        assertEquals(arrayList, repositoryInMemory.getLastTenDaysMessages(),"getLastTenDaysMessages method in RepositoryInMemory Class didn't work correctly.");
    }

    @Test
    void getLastMonthMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10,event11,event12,event13,event14));

        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        repositoryInMemory.addMessage(event0);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);
        repositoryInMemory.addMessage(event5);
        repositoryInMemory.addMessage(event6);
        repositoryInMemory.addMessage(event7);
        repositoryInMemory.addMessage(event8);
        repositoryInMemory.addMessage(event9);
        repositoryInMemory.addMessage(event10);
        repositoryInMemory.addMessage(event11);
        repositoryInMemory.addMessage(event12);
        repositoryInMemory.addMessage(event13);
        repositoryInMemory.addMessage(event14);
        repositoryInMemory.addMessage(event15);

        assertEquals(arrayList, repositoryInMemory.getLastMonthMessages(),"getLatMonthMessages method in RepositoryInMemory Class didn't work correctly.");
    }

    @Test
    void getAllTheMessages() {
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();
        Event event = new Event("A", 1000000000);
        Event event1 = new Event("B", 1000000000);
        Event event2 = new Event("C", 1000000000);
        Event event3 = new Event("D", 1000000000);
        Event event4 = new Event("E", 1000000000);
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3,event4));

        Collections.sort(arrayList, RepositoryInMemory.LATEST_ORDER);

        repositoryInMemory.addMessage(event);
        repositoryInMemory.addMessage(event1);
        repositoryInMemory.addMessage(event2);
        repositoryInMemory.addMessage(event3);
        repositoryInMemory.addMessage(event4);

        assertEquals(arrayList, repositoryInMemory.getAllTheMessages(),"getAllTheMessages method in RepositoryInMemory Class didn't work correctly.");
    }

}