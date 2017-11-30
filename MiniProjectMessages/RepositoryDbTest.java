import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryDbTest {

    final String DB_NAME = "repositoryDbTest.db";
    final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\amarkovits\\IdeaProjects\\Test\\" + DB_NAME;

    final String TABLE_EVENTS = "event";
    final String COLUMN_EVENT_ID = "_id";
    final String COLUMN_EVENT_MESSAGE = "eventMessage";
    final String COLUMN_EVENT_TIME_MILLIS = "eventTimeMillis";

    public static Connection connection;

    private static RepositoryDb repositoryDbTest = new RepositoryDb();

    @BeforeAll
    static void setUpAllStart() {
        repositoryDbTest.open();
        connection = repositoryDbTest.getConnection();
    }
    @AfterAll
    static void closeAll() {
        repositoryDbTest.close();
    }

    @BeforeEach
    void setUp() {
        // TODO NA ginei sundesh me tis methodous wste na ellatwthoun ta dedomena ekei.
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void open() {
    }

    @Test
    void close() {
    }

    @Test
    void queryLoadDb() {
        // TODO Find a way to use the openTest instead of the .open which is from the main class to connect to the main Database. I am trying to connect the tests with the dbTest while using the class methods from the class that i am testing.

        // Initialising an Event for the other db.
        Event event = new Event("New message ", 10000000000L);
        Event event1 = new Event("New message1 ", 20000000000L);
        Event event2 = new Event("New message2 ", 30000000000L);
        Event event3 = new Event("New message3 ", 40000000000L);
        Event event4 = new Event("New message4 ", 50000000000L);

        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3,event4));

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        assertEquals(arrayList , repositoryDbTest.queryLoadDb(), "queryLoadDb method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void addMessage() {
        // Initialising an Event for the other db.
        Event eventExpected = new Event("Db Test", 10000000000000L);

        Event event = new Event("Db Test", 1000000000000L);
        repositoryDbTest.addMessage(event);


        // TODO Mallon den mporw an den to pairnw etoimo apo etoimh klash. An kai tha eprepe na douleuei pisteuw. Den anagnwrizei to lastNewMessage.
//        ArrayList<Event> lastNewMessageList = repositoryDbTest.getLatestMessages(1);
//        for(Event eventAdded: lastNewMessageList){
//            Event lastNewMessage = new Event(eventAdded.getMessage(), eventAdded.getTime());
//        }
//
//            assertEquals(eventExpected, lastNewMessage, "addMessage method in RepositoryDb Class didn't work correctly.");

        repositoryDbTest.close();
    }

    @Test
    void getLatestMessages() throws SQLException {

        // Initialising an Event for the other db.
        Event event = new Event("New message ", 10000000000L);
        Event event1 = new Event("New message1 ", 20000000000L);
        Event event2 = new Event("New message2 ", 30000000000L);
        Event event3 = new Event("New message3 ", 40000000000L);
        Event event4 = new Event("New message4 ", 50000000000L);

        // Adding 3 / 5 Events in the arraylist to test if the repositoryDb will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event,event1,event2));

        // Because the repositoryDbTest WILL do a sort before it brings the data so we need to do as well.
        Collections.sort(arrayList,RepositoryInMemory.LATEST_ORDER);

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        // TODO Provlhma, den mporw na sugkrinw arrays apo events gt h getLatest dhmiourgei nea events meta to load  twn data.
        // TODO Prospatheia na parw ta events apo thn DB kai na sugkrinw duo arrays mono me ta messages. Den petuxe.
//        ArrayList<Event> loadList = repositoryDbTest.getLatestMessages(3);
//        ArrayList<String> repoList = new ArrayList<>();
//        for(Event object: loadList){
//            repoList.add(object.getMessage());
//        }
//        ArrayList<String> test = new ArrayList<>();
//        test.add(event.getMessage());
//        test.add(event1.getMessage());
//        test.add(event2.getMessage());
//
//        assertEquals(test , repoList, "getLatestMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getOldestMessages() throws SQLException {
        // Initialising an Event for the other db.
        Event event = new Event("New message ", 10000000000L);
        Event event1 = new Event("New message1 ", 20000000000L);
        Event event2 = new Event("New message2 ", 30000000000L);
        Event event3 = new Event("New message3 ", 40000000000L);
        Event event4 = new Event("New message4 ", 50000000000L);

        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event4,event3,event2));

        // Because the repositoryDbTest WILL do a sort before it brings the data so we need to do as well.
        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        assertEquals(arrayList , repositoryDbTest.getLatestMessages(3), "getOldestMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastHourMessages() throws SQLException {
        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        // Initialising Events for the other db.
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

        // Adding 4 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3));

        // Adding all the Events (16 / 16) in the repositoryDbTest.
        repositoryDbTest.addMessage(event0);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);
        repositoryDbTest.addMessage(event5);
        repositoryDbTest.addMessage(event6);
        repositoryDbTest.addMessage(event7);
        repositoryDbTest.addMessage(event8);
        repositoryDbTest.addMessage(event9);
        repositoryDbTest.addMessage(event10);
        repositoryDbTest.addMessage(event11);
        repositoryDbTest.addMessage(event12);
        repositoryDbTest.addMessage(event13);
        repositoryDbTest.addMessage(event14);
        repositoryDbTest.addMessage(event15);

        assertEquals(arrayList , repositoryDbTest.getLastHourMessages(), "getLastHourMesages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastThreeHoursMessages() throws SQLException {
        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        // Initialising Events for the other db.
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

        // Adding 8 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7));

        // Adding all the Events (16 / 16) in the repositoryDbTest.
        repositoryDbTest.addMessage(event0);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);
        repositoryDbTest.addMessage(event5);
        repositoryDbTest.addMessage(event6);
        repositoryDbTest.addMessage(event7);
        repositoryDbTest.addMessage(event8);
        repositoryDbTest.addMessage(event9);
        repositoryDbTest.addMessage(event10);
        repositoryDbTest.addMessage(event11);
        repositoryDbTest.addMessage(event12);
        repositoryDbTest.addMessage(event13);
        repositoryDbTest.addMessage(event14);
        repositoryDbTest.addMessage(event15);

        assertEquals(arrayList , repositoryDbTest.getLastThreeDaysMessages(), "getLastThreeHoursMesages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastOneDayMessages() throws SQLException {
        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        // Initialising Events for the other db.
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

        // Adding 9 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8));

        // Adding all the Events (16 / 16) in the repositoryDbTest.
        repositoryDbTest.addMessage(event0);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);
        repositoryDbTest.addMessage(event5);
        repositoryDbTest.addMessage(event6);
        repositoryDbTest.addMessage(event7);
        repositoryDbTest.addMessage(event8);
        repositoryDbTest.addMessage(event9);
        repositoryDbTest.addMessage(event10);
        repositoryDbTest.addMessage(event11);
        repositoryDbTest.addMessage(event12);
        repositoryDbTest.addMessage(event13);
        repositoryDbTest.addMessage(event14);
        repositoryDbTest.addMessage(event15);

        assertEquals(arrayList , repositoryDbTest.getLastOneDayMessages(), "getLastOneDayMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastThreeDaysMessages() throws SQLException {
        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        // Initialising Events for the other db.
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

        // Adding 11 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10));

        // Adding all the Events (16 / 16) in the repositoryDbTest.
        repositoryDbTest.addMessage(event0);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);
        repositoryDbTest.addMessage(event5);
        repositoryDbTest.addMessage(event6);
        repositoryDbTest.addMessage(event7);
        repositoryDbTest.addMessage(event8);
        repositoryDbTest.addMessage(event9);
        repositoryDbTest.addMessage(event10);
        repositoryDbTest.addMessage(event11);
        repositoryDbTest.addMessage(event12);
        repositoryDbTest.addMessage(event13);
        repositoryDbTest.addMessage(event14);
        repositoryDbTest.addMessage(event15);

        assertEquals(arrayList , repositoryDbTest.getLastThreeDaysMessages(), "getLastThreeDaysMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastTenDaysMessages() throws SQLException {
        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        // Initialising Events for the other db.
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

        // Adding 13 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10,event11,event12));

        // Adding all the Events (16 / 16) in the repositoryDbTest.
        repositoryDbTest.addMessage(event0);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);
        repositoryDbTest.addMessage(event5);
        repositoryDbTest.addMessage(event6);
        repositoryDbTest.addMessage(event7);
        repositoryDbTest.addMessage(event8);
        repositoryDbTest.addMessage(event9);
        repositoryDbTest.addMessage(event10);
        repositoryDbTest.addMessage(event11);
        repositoryDbTest.addMessage(event12);
        repositoryDbTest.addMessage(event13);
        repositoryDbTest.addMessage(event14);
        repositoryDbTest.addMessage(event15);

        assertEquals(arrayList , repositoryDbTest.getLastTenDaysMessages(), "getLastTenDaysMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastMonthMessages() throws SQLException {
        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        // Initialising Events for the other db.
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

        // Adding 15 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10,event11,event12,event13,event14));

        // Adding all the Events (16 / 16) in the repositoryDbTest.
        repositoryDbTest.addMessage(event0);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);
        repositoryDbTest.addMessage(event5);
        repositoryDbTest.addMessage(event6);
        repositoryDbTest.addMessage(event7);
        repositoryDbTest.addMessage(event8);
        repositoryDbTest.addMessage(event9);
        repositoryDbTest.addMessage(event10);
        repositoryDbTest.addMessage(event11);
        repositoryDbTest.addMessage(event12);
        repositoryDbTest.addMessage(event13);
        repositoryDbTest.addMessage(event14);
        repositoryDbTest.addMessage(event15);

        assertEquals(arrayList , repositoryDbTest.getLastMonthMessages(), "getLastMonthMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getAllTheMessages() throws SQLException {
        // Initialising an Event for the other db.
        Event event = new Event("New message ", 10000000000L);
        Event event1 = new Event("New message1 ", 20000000000L);
        Event event2 = new Event("New message2 ", 30000000000L);
        Event event3 = new Event("New message3 ", 40000000000L);
        Event event4 = new Event("New message4 ", 50000000000L);

        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3,event4));

        // Because the repositoryDbTest WILL do a sort before it brings the data so we need to do as well.
        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        assertEquals(arrayList , repositoryDbTest.getAllTheMessages(), "getOldestMessages method in RepositoryDb Class didn't work correctly.");
    }
}