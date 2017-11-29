//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class RepositoryDbTest {
//
//    RepositoryDb repositoryDbTest = new RepositoryDb();
//    ArrayList<Event> arrayList = new ArrayList<>();
//    Connection connection;
//
//    final String DB_NAME = "repositoryDbTest.db";
//    final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\amarkovits\\IdeaProjects\\Test\\" + DB_NAME;
//
//    final String TABLE_EVENTS = "event";
//    final String COLUMN_EVENT_ID = "_id";
//    final String COLUMN_EVENT_MESSAGE = "eventMessage";
//    final String COLUMN_EVENT_TIME_MILLIS = "eventTimeMillis";
//
//
//    public boolean openTest() {
//        try {connection = DriverManager.getConnection(CONNECTION_STRING);
//
//        return true;
//        } catch (SQLException e) {
//            System.out.println("Could not connect with the database " + e.getMessage());
//            return false;
//        }
//    }
//
//    public void closeTest() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("Could not close the database " + e.getMessage());
//        }
//    }
//    @BeforeAll
//    void setUpAllStart() {
//    }
//
//    @BeforeEach
//    void setUp() {
//        // TODO NA ginei sundesh me tis methodous wste na ellatwthoun ta dedomena ekei.
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void open() {
//    }
//
//    @Test
//    void close() {
//    }
//
//    // TODO Ask if i should implement a Test for this private method too. To do so i either have to turn it to public OR create another new class at which i will pass it as a public if it is of such importance.
////    @Test
////    void listFromQuery() {
////        openTest();
////
////        // Creating the DB.
////        try(Statement statement = connection.createStatement();
////            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        // Initialising an Event for the other db.
////        Event event = new Event("New message ", 10000000000l);
////        Event event1 = new Event("New message1 ", 20000000000L);
////        Event event2 = new Event("New message2 ", 30000000000L);
////        Event event3 = new Event("New message3 ", 40000000000L);
////        Event event4 = new Event("New message4 ", 50000000000L);
////
////        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
////        arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3,event4));
////
////        // Adding all the Events (5 / 5) in the repositoryDbTest.
////        repositoryDbTest.addMessage(event);
////        repositoryDbTest.addMessage(event1);
////        repositoryDbTest.addMessage(event2);
////        repositoryDbTest.addMessage(event3);
////        repositoryDbTest.addMessage(event4);
////
////        //
////        try(Statement statement = connection.createStatement();
////            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS)){
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        assertEquals(arrayList , repositoryDbTest.listFromQuery(resultSet), "getOldestMessages method in RepositoryDb Class didn't work correctly.");
////
////        closeTest();
////    }
//
//    @Test
//    void queryLoadDb() {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + " ( " +  COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER " + " )")) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Initialising an Event for the other db.
//        Event event = new Event("New message ", 10000000000l);
//        Event event1 = new Event("New message1 ", 20000000000L);
//        Event event2 = new Event("New message2 ", 30000000000L);
//        Event event3 = new Event("New message3 ", 40000000000L);
//        Event event4 = new Event("New message4 ", 50000000000L);
//
//        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3,event4));
//
//        // Adding all the Events (5 / 5) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//
//        assertEquals(arrayList , repositoryDbTest.queryLoadDb(), "queryLoadDb method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void addMessage() {
//
////        repositoryDbTest.open();
////
////        // Creating the DB.
////        try(Statement statement = connection.createStatement();
////            statement.executeQuery("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        // Inserting in the DB.
////        try(Statement statement = connection.createStatement()){
////            statement.executeUpdate("INSERT INTO " + TABLE_EVENTS + "(" + COLUMN_EVENT_MESSAGE + "," + COLUMN_EVENT_TIME_MILLIS + ")" + " VALUES " + "('" + " New message " + "'," + 10000000000L + ")");
////        }catch (SQLException e){
////            System.out.println("Could not add the event to the DB" + e.getMessage());
////        }
////
////
////        // Initialising an Event for the other db.
////        Event event = new Event(" New message ", 10000000000l);
////
////        assertEquals(  , repositoryDbTest.addMessage(event), "addMessage method in RepositoryDb Class didn't work correctly.");
////
////        repositoryDbTest.close();
//    }
//
//    @Test
//    void getLatestMessages() throws SQLException {
//        // To connect with the dbTest
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            // TODO TO-FIX Solve the problem!
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        // Initialising an Event for the other db.
//        Event event = new Event("New message ", 10000000000l);
//        Event event1 = new Event("New message1 ", 20000000000L);
//        Event event2 = new Event("New message2 ", 30000000000L);
//        Event event3 = new Event("New message3 ", 40000000000L);
//        Event event4 = new Event("New message4 ", 50000000000L);
//
//        // Adding 3 / 5 Events in the arraylist to test if the repositoryDb will bring us back only those asked (3) in the assertEquals.
//        arrayList = new ArrayList<>(Arrays.asList(event,event1,event2));
//
////        // Because the repositoryDbTest WILL do a sort before it brings the data so we need to do as well.
////        Collections.sort(arrayList,RepositoryInMemory.LATEST_ORDER);
//
//        // Adding all the Events (5 / 5) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//
//
//        assertEquals(arrayList , repositoryDbTest.getLatestMessages(3), "getLatestMessages method in RepositoryDb Class didn't work correctly.");
//
//        // To close the dbTest.
//        closeTest();
//    }
//
//    @Test
//    void getOldestMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        // Initialising an Event for the other db.
//        Event event = new Event("New message ", 10000000000l);
//        Event event1 = new Event("New message1 ", 20000000000L);
//        Event event2 = new Event("New message2 ", 30000000000L);
//        Event event3 = new Event("New message3 ", 40000000000L);
//        Event event4 = new Event("New message4 ", 50000000000L);
//
//        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        arrayList = new ArrayList<>(Arrays.asList(event4,event3,event2));
//
////        // Because the repositoryDbTest WILL do a sort before it brings the data so we need to do as well.
////        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);
//
//        // Adding all the Events (5 / 5) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//
//        assertEquals(arrayList , repositoryDbTest.getLatestMessages(3), "getOldestMessages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void getLastHourMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Controller controller = new Controller();
//        long timeOfRequest = controller.calcNewTime();
//
//        // Initialising Events for the other db.
//        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
//        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
//        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
//        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
//        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
//        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
//        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
//        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
//        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
//        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
//        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
//        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
//        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
//        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
//        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
//        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));
//
//        // Adding 4 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3));
//
//        // Adding all the Events (16 / 16) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event0);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//        repositoryDbTest.addMessage(event5);
//        repositoryDbTest.addMessage(event6);
//        repositoryDbTest.addMessage(event7);
//        repositoryDbTest.addMessage(event8);
//        repositoryDbTest.addMessage(event9);
//        repositoryDbTest.addMessage(event10);
//        repositoryDbTest.addMessage(event11);
//        repositoryDbTest.addMessage(event12);
//        repositoryDbTest.addMessage(event13);
//        repositoryDbTest.addMessage(event14);
//        repositoryDbTest.addMessage(event15);
//
//        assertEquals(arrayList , repositoryDbTest.getLastHourMessages(), "getLastHourMesages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void getLastThreeHoursMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Controller controller = new Controller();
//        long timeOfRequest = controller.calcNewTime();
//
//        // Initialising Events for the other db.
//        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
//        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
//        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
//        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
//        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
//        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
//        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
//        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
//        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
//        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
//        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
//        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
//        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
//        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
//        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
//        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));
//
//        // Adding 8 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7));
//
//        // Adding all the Events (16 / 16) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event0);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//        repositoryDbTest.addMessage(event5);
//        repositoryDbTest.addMessage(event6);
//        repositoryDbTest.addMessage(event7);
//        repositoryDbTest.addMessage(event8);
//        repositoryDbTest.addMessage(event9);
//        repositoryDbTest.addMessage(event10);
//        repositoryDbTest.addMessage(event11);
//        repositoryDbTest.addMessage(event12);
//        repositoryDbTest.addMessage(event13);
//        repositoryDbTest.addMessage(event14);
//        repositoryDbTest.addMessage(event15);
//
//        assertEquals(arrayList , repositoryDbTest.getLastThreeDaysMessages(), "getLastThreeHoursMesages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void getLastOneDayMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Controller controller = new Controller();
//        long timeOfRequest = controller.calcNewTime();
//
//        // Initialising Events for the other db.
//        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
//        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
//        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
//        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
//        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
//        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
//        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
//        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
//        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
//        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
//        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
//        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
//        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
//        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
//        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
//        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));
//
//        // Adding 9 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8));
//
//        // Adding all the Events (16 / 16) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event0);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//        repositoryDbTest.addMessage(event5);
//        repositoryDbTest.addMessage(event6);
//        repositoryDbTest.addMessage(event7);
//        repositoryDbTest.addMessage(event8);
//        repositoryDbTest.addMessage(event9);
//        repositoryDbTest.addMessage(event10);
//        repositoryDbTest.addMessage(event11);
//        repositoryDbTest.addMessage(event12);
//        repositoryDbTest.addMessage(event13);
//        repositoryDbTest.addMessage(event14);
//        repositoryDbTest.addMessage(event15);
//
//        assertEquals(arrayList , repositoryDbTest.getLastOneDayMessages(), "getLastOneDayMessages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void getLastThreeDaysMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Controller controller = new Controller();
//        long timeOfRequest = controller.calcNewTime();
//
//        // Initialising Events for the other db.
//        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
//        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
//        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
//        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
//        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
//        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
//        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
//        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
//        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
//        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
//        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
//        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
//        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
//        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
//        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
//        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));
//
//        // Adding 11 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10));
//
//        // Adding all the Events (16 / 16) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event0);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//        repositoryDbTest.addMessage(event5);
//        repositoryDbTest.addMessage(event6);
//        repositoryDbTest.addMessage(event7);
//        repositoryDbTest.addMessage(event8);
//        repositoryDbTest.addMessage(event9);
//        repositoryDbTest.addMessage(event10);
//        repositoryDbTest.addMessage(event11);
//        repositoryDbTest.addMessage(event12);
//        repositoryDbTest.addMessage(event13);
//        repositoryDbTest.addMessage(event14);
//        repositoryDbTest.addMessage(event15);
//
//        assertEquals(arrayList , repositoryDbTest.getLastThreeDaysMessages(), "getLastThreeDaysMessages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void getLastTenDaysMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Controller controller = new Controller();
//        long timeOfRequest = controller.calcNewTime();
//
//        // Initialising Events for the other db.
//        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
//        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
//        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
//        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
//        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
//        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
//        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
//        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
//        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
//        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
//        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
//        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
//        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
//        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
//        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
//        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));
//
//        // Adding 13 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10,event11,event12));
//
//        // Adding all the Events (16 / 16) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event0);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//        repositoryDbTest.addMessage(event5);
//        repositoryDbTest.addMessage(event6);
//        repositoryDbTest.addMessage(event7);
//        repositoryDbTest.addMessage(event8);
//        repositoryDbTest.addMessage(event9);
//        repositoryDbTest.addMessage(event10);
//        repositoryDbTest.addMessage(event11);
//        repositoryDbTest.addMessage(event12);
//        repositoryDbTest.addMessage(event13);
//        repositoryDbTest.addMessage(event14);
//        repositoryDbTest.addMessage(event15);
//
//        assertEquals(arrayList , repositoryDbTest.getLastTenDaysMessages(), "getLastTenDaysMessages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void getLastMonthMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        Controller controller = new Controller();
//        long timeOfRequest = controller.calcNewTime();
//
//        // Initialising Events for the other db.
//        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
//        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
//        Event event2 = new Event("B", timeOfRequest - (1000 * 60 * 45));
//        Event event3 = new Event("B", timeOfRequest - (1000 * 60 * 55));
//        Event event4 = new Event("C", timeOfRequest - (1000 * 60 * 65));
//        Event event5 = new Event("D", timeOfRequest - (1000 * 60 * 100));
//        Event event6 = new Event("F", timeOfRequest - (1000 * 60 * 150));
//        Event event7 = new Event("G", timeOfRequest - (1000 * 60 * 170));
//        Event event8 = new Event("H", timeOfRequest - (1000 * 60 * 60 * 18));
//        Event event9 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 26));
//        Event event10 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 70));
//        Event event11 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 80));
//        Event event12 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 220));
//        Event event13 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 260));
//        Event event14 = new Event("N", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
//        Event event15 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 35));
//
//        // Adding 15 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        ArrayList<Event> arrayList = new ArrayList<>(Arrays.asList(event0,event1,event2,event3,event4,event5,event6,event7,event8,event9,event10,event11,event12,event13,event14));
//
//        // Adding all the Events (16 / 16) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event0);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//        repositoryDbTest.addMessage(event5);
//        repositoryDbTest.addMessage(event6);
//        repositoryDbTest.addMessage(event7);
//        repositoryDbTest.addMessage(event8);
//        repositoryDbTest.addMessage(event9);
//        repositoryDbTest.addMessage(event10);
//        repositoryDbTest.addMessage(event11);
//        repositoryDbTest.addMessage(event12);
//        repositoryDbTest.addMessage(event13);
//        repositoryDbTest.addMessage(event14);
//        repositoryDbTest.addMessage(event15);
//
//        assertEquals(arrayList , repositoryDbTest.getLastMonthMessages(), "getLastMonthMessages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//
//    @Test
//    void getAllTheMessages() throws SQLException {
//        openTest();
//
//        // Creating the DB.
//        try(Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + " VARCHAR(255) , " + COLUMN_EVENT_TIME_MILLIS + " INTEGER "))) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        // Initialising an Event for the other db.
//        Event event = new Event("New message ", 10000000000l);
//        Event event1 = new Event("New message1 ", 20000000000L);
//        Event event2 = new Event("New message2 ", 30000000000L);
//        Event event3 = new Event("New message3 ", 40000000000L);
//        Event event4 = new Event("New message4 ", 50000000000L);
//
//        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
//        arrayList = new ArrayList<>(Arrays.asList(event,event1,event2,event3,event4));
//
////        // Because the repositoryDbTest WILL do a sort before it brings the data so we need to do as well.
////        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);
//
//        // Adding all the Events (5 / 5) in the repositoryDbTest.
//        repositoryDbTest.addMessage(event);
//        repositoryDbTest.addMessage(event1);
//        repositoryDbTest.addMessage(event2);
//        repositoryDbTest.addMessage(event3);
//        repositoryDbTest.addMessage(event4);
//
//        assertEquals(arrayList , repositoryDbTest.getAllTheMessages(), "getOldestMessages method in RepositoryDb Class didn't work correctly.");
//
//        closeTest();
//    }
//}