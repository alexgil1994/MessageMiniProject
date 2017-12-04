import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryDbTest {

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

    // TODO Na sundesw ta queries me string queries g na mikrhnoun.
    // First of all loading the db values that interest me in every method in order to add them to the compare otherwise there will be conflicts when comparing the expected with the actual.
    // After every test the test-values should be deleted.

    @Test
    void queryLoadDb() {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS)){
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Adding Events for the other db.
        Event event = new Event("New message* ", 10000000000L);
        Event event1 = new Event("New message1* ", 10000000001L);
        Event event2 = new Event("New message2* ", 10000000002L);
        Event event3 = new Event("New message3* ", 10000000003L);
        Event event4 = new Event("New message4* ", 10000000004L);

        // Adding 3 / 5 Events (other than those loaded and inserted already) in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);
        arrayList.add(event4);

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: arrayList){
            arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> repoList = new ArrayList<>();
        for(Event object: repositoryDbTest.queryLoadDb()){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('New message* ','New message1* ','New message2* ','New message3* ','New message4* ')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "queryLoadDb method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void addMessage() {
        // Initialising an Event for the other db.
        Event event = new Event("Db Test", 10000000000000L);
        repositoryDbTest.addMessage(event);

        // TODO Mallon den mporw an den to pairnw etoimo apo etoimh klash. An kai tha eprepe na douleuei pisteuw. Den anagnwrizei to lastNewMessage. EPISHS DEN DOULEPSE OUTE ME COUNT. ENW EPISTREFEI AUTO POU VAZW TO COUNT DEN GINETAI 1 MENEI 0.

        int count = 0;
        for(Event eventAdded: repositoryDbTest.getAllTheMessages()){
            if (Objects.equals(eventAdded.getMessage(), "Db Test") && eventAdded.getTime() == 10000000000000L){
                count = count + 1;
            }
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('Db Test')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(1, count, "addMessage method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLatestMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS)){
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Oi xronoi einai ligo diaforetikoi gt alliws to order by xanei logikh kai taksinomei me to onoma afou exoun idio xrono akrivws.
        // Initialising an Events
        Event event = new Event("New message- ", 10000000000000L);
        Event event1 = new Event("New message1- ", 10000000000001L);
        Event event2 = new Event("New message2- ", 10000000000002L);
        Event event3 = new Event("New message3- ", 10000000000003L);
        Event event4 = new Event("New message4- ", 10000000000004L);

        // Adding 3 / 5 Events in the arraylist to test if the repositoryDb will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event4);
        arrayList.add(event3);
        arrayList.add(event2);

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        if (arrayList.size()>=3) {
            arrayListCompare.add(arrayList.get(arrayList.size() - 3).getMessage());
            arrayListCompare.add(arrayList.get(arrayList.size() - 2).getMessage());
            arrayListCompare.add(arrayList.get(arrayList.size() - 1).getMessage());
        }

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> repoList = new ArrayList<>();
        for(Event object: repositoryDbTest.getLatestMessages(3)){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('New message- ','New message1- ','New message2- ','New message3- ','New message4- ')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getLatestMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getOldestMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS)){
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising an Event for the other db.
        Event event = new Event("New message+ ", 10000000000000L);
        Event event1 = new Event("New message1+ ", 10000000000001L);
        Event event2 = new Event("New message2+ ", 10000000000002L);
        Event event3 = new Event("New message3+ ", 10000000000003L);
        Event event4 = new Event("New message4+ ", 10000000000004L);

        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event);
        arrayList.add(event1);
        arrayList.add(event2);

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        // Sorting the list based on a class in the RepositoryInMemory i have in order to make the correct expected list.
        Collections.sort(arrayList,RepositoryInMemory.EARLIEST_ORDER);

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        if (arrayList.size()>=3) {
            arrayListCompare.add(arrayList.get(arrayList.size() - 1).getMessage());
            arrayListCompare.add(arrayList.get(arrayList.size() - 2).getMessage());
            arrayListCompare.add(arrayList.get(arrayList.size() - 3).getMessage());
        }

        // Taking an array with only the messages from the db in order to compare. We use this because the repositoryDb methods return NEW Events so they cant be compared.
        ArrayList<String> repoList = new ArrayList<>();
        for(Event object: repositoryDbTest.getOldestMessages(3)){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('New message+ ','New message1+ ','New message2+ ','New message3+ ','New message4+ ')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getOldestMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastHourMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + " - " + "1000*60*60")) {
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising Events for the other db.
        Event event0 = new Event("A!", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B!", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("C!", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("D!", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("E!", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("F!", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("G!", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("H!", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("I!", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("J!", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("K!", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("L!", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("M!", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("N!", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("O!", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("P!", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        // Adding 4 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event0);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);

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

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: repositoryDbTest.getLastHourMessages()){
            arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare.We use this because the repositoryDb methods return NEW Events so they cant be compared.
        ArrayList<String> repoList = new ArrayList<>();
        for(Event object: arrayList){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('A!','B!','C!','D!','E!','F!','G!','H!','I!','J!','K!','L!','M!','N!','O!','P!')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getLastHourMesages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastThreeHoursMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + " - " + "1000*60*60*3")) {
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising Events for the other db.
        Event event0 = new Event("A@", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B@", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("C@", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("D@", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("E@", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("F@", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("G@", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("H@", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("I@", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("J@", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("K@", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("L@", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("M@", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("N@", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("O@", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("P@", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        // Adding 8 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event0);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);
        arrayList.add(event4);
        arrayList.add(event5);
        arrayList.add(event6);
        arrayList.add(event7);

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

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: arrayList){
            arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare. We use this because the repositoryDb methods return NEW Events so they cant be compared.
        ArrayList<String> repoList = new ArrayList<>();
        for(Event object: repositoryDbTest.getLastThreeHoursMessages()){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('A@','B@','C@','D@','E@','F@','G@','H@','I@','J@','K@','L@','M@','N@','O@','P@')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getLastThreeHoursMesages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastOneDayMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + " - " + "1000*60*60*24")) {
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising Events for the other db.
        Event event0 = new Event("A", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("C", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("D", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("E", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("F", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("G", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("H", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("I", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("J", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("K", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("L", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("M", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("N", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("O", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("P", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        // Adding 9 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event0);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);
        arrayList.add(event4);
        arrayList.add(event5);
        arrayList.add(event6);
        arrayList.add(event7);
        arrayList.add(event8);

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

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: arrayList){
            arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare. We use this because the repositoryDb methods return NEW Events so they cant be compared.
        ArrayList<String> repoList = new ArrayList<>();
        for(Event object: repositoryDbTest.getLastOneDayMessages()){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getLastOneDayMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastThreeDaysMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + " - " + "1000*60*60*24*3")) {
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising Events for the other db.
        Event event0 = new Event("A#", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B#", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("C#", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("D#", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("E#", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("F#", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("G#", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("H#", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("I#", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("J#", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("K#", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("L#", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("M#", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("N#", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("O#", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("P#", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        // Adding 11 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event0);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);
        arrayList.add(event4);
        arrayList.add(event5);
        arrayList.add(event6);
        arrayList.add(event7);
        arrayList.add(event8);
        arrayList.add(event9);
        arrayList.add(event10);

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

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: arrayList){
        arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare. We use this because the repositoryDb methods return NEW Events so they cant be compared.
        ArrayList<String> repoList = new ArrayList<>();
        for (Event object: repositoryDbTest.getLastThreeDaysMessages()){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('A#','B#','C#','D#','E#','F#','G#','H#','I#','J#','K#','L#','M#','N#','O#','P#')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getLastThreeDaysMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastTenDaysMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + " - " + "1000*60*60*24*10")) {
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising Events for the other db.
        Event event0 = new Event("A$", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B$", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("C$", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("D$", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("E$", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("F$", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("G$", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("H$", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("I$", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("J$", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("K$", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("L$", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("M$", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("N$", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("O$", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("P$", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        // Adding 13 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event0);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);
        arrayList.add(event4);
        arrayList.add(event5);
        arrayList.add(event6);
        arrayList.add(event7);
        arrayList.add(event8);
        arrayList.add(event9);
        arrayList.add(event10);
        arrayList.add(event11);
        arrayList.add(event12);

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

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: arrayList){
        arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare. We use this because the repositoryDb methods return NEW Events so they cant be compared.
        ArrayList<String> repoList = new ArrayList<>();
        for (Event object: repositoryDbTest.getLastTenDaysMessages()){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('A$','B$','C$','D$','E$','F$','G$','H$','I$','J$','K$','L$','M$','N$','O$','P$')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getLastTenDaysMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getLastMonthMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + " - " + "1000*60*60*24*30")) {
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising Events for the other db.
        Event event0 = new Event("A^", timeOfRequest - (1000 * 20));
        Event event1 = new Event("B^", timeOfRequest - (1000 * 60 * 30));
        Event event2 = new Event("C^", timeOfRequest - (1000 * 60 * 45));
        Event event3 = new Event("D^", timeOfRequest - (1000 * 60 * 55));
        Event event4 = new Event("E^", timeOfRequest - (1000 * 60 * 65));
        Event event5 = new Event("F^", timeOfRequest - (1000 * 60 * 100));
        Event event6 = new Event("G^", timeOfRequest - (1000 * 60 * 150));
        Event event7 = new Event("H^", timeOfRequest - (1000 * 60 * 170));
        Event event8 = new Event("I^", timeOfRequest - (1000 * 60 * 60 * 18));
        Event event9 = new Event("J^", timeOfRequest - (1000 * 60 * 60 * 26));
        Event event10 = new Event("K^", timeOfRequest - (1000 * 60 * 60 * 70));
        Event event11 = new Event("L^", timeOfRequest - (1000 * 60 * 60 * 80));
        Event event12 = new Event("M^", timeOfRequest - (1000 * 60 * 60 * 220));
        Event event13 = new Event("N^", timeOfRequest - (1000 * 60 * 60 * 260));
        Event event14 = new Event("O^", timeOfRequest - (1000L * 60 * 60 * 24 * 25));
        Event event15 = new Event("P^", timeOfRequest - (1000L * 60 * 60 * 24 * 35));

        // Adding 15 / 16 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event0);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);
        arrayList.add(event4);
        arrayList.add(event5);
        arrayList.add(event6);
        arrayList.add(event7);
        arrayList.add(event8);
        arrayList.add(event9);
        arrayList.add(event10);
        arrayList.add(event11);
        arrayList.add(event12);
        arrayList.add(event13);
        arrayList.add(event14);

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

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: arrayList){
        arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> repoList = new ArrayList<>();
        for (Event object: repositoryDbTest.getLastMonthMessages()){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('A^','B^','C^','D^','E^','F^','G^','H^','I^','J^','K^','L^','M^','N^','O^','P^')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getLastMonthMessages method in RepositoryDb Class didn't work correctly.");
    }

    @Test
    void getAllTheMessages() throws SQLException {
        // Initialising the expected arraylist.
        // Loading the existed values in the db.
        ArrayList<Event> arrayList = new ArrayList<>();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS)){
            while (resultSet.next()){
                arrayList.add(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
//                repositoryDbTest.addMessage(new Event(resultSet.getString(COLUMN_EVENT_MESSAGE),resultSet.getLong(COLUMN_EVENT_TIME_MILLIS)));
            }
        } catch (SQLException e) {
            System.out.println("There was an error when trying t load the db for the queryLoadDb test." + e.getMessage());
        }

        // Initialising an Event for the other db.
        Event event = new Event("New message& ", 10000000000L);
        Event event1 = new Event("New message1& ", 20000000000L);
        Event event2 = new Event("New message2& ", 30000000000L);
        Event event3 = new Event("New message3& ", 40000000000L);
        Event event4 = new Event("New message4& ", 50000000000L);

        // Adding 3 / 5 Events in the arraylist to test if the repositoryDbTest will bring us back only those asked (3) in the assertEquals.
        arrayList.add(event);
        arrayList.add(event1);
        arrayList.add(event2);
        arrayList.add(event3);
        arrayList.add(event4);

        // Adding all the Events (5 / 5) in the repositoryDbTest.
        repositoryDbTest.addMessage(event);
        repositoryDbTest.addMessage(event1);
        repositoryDbTest.addMessage(event2);
        repositoryDbTest.addMessage(event3);
        repositoryDbTest.addMessage(event4);

        //        // Taking an array with only the messages from the db in order to compare.
        ArrayList<String> arrayListCompare = new ArrayList<>();
        for(Event object: arrayList){
        arrayListCompare.add(object.getMessage());
        }

        // Taking an array with only the messages from the db in order to compare. We use this because the repositoryDb methods return NEW Events so they cant be compared.
        ArrayList<String> repoList = new ArrayList<>();
        for(Event object: repositoryDbTest.getAllTheMessages()){
            repoList.add(object.getMessage());
        }

        // Deleting the created messages to test the db.
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_MESSAGE + " IN ('New message& ','New message1& ','New message2& ','New message3& ','New message4& ')");
        }catch (SQLException e){
            System.out.println("There was an error trying to delete the test values that were inserted. " + e.getMessage());
        }

        assertEquals(arrayListCompare , repoList, "getOldestMessages method in RepositoryDb Class didn't work correctly.");
    }
}