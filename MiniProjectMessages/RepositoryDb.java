import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class RepositoryDb implements IRepository {

    ArrayList<Event> repositoryDbList = new ArrayList<>();
    private Connection connection;

    public RepositoryDb() {
    }

    public static final String DB_NAME = "repositoryDb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\amarkovits\\IdeaProjects\\Test\\" + DB_NAME;

    public static final String TABLE_EVENTS = "event";
    public static final String COLUMN_EVENT_ID = "_id";
    public static final String COLUMN_EVENT_MESSAGE = "eventMessage";
    public static final String COLUMN_EVENT_TIME_MILLIS = "eventTimeMillis";

    // Tha mporousa na to aplopoihsw kai akomh perissotero me INDEX ws:
    public static final String INDEX_EVENT_ID = "1";
    public static final String INDEX_EVENT_MESSAGE = "2";
    public static final String INDEX_EVENT_TIME_MILLIS = "3";

    // Constants for order
    public static final int ORDER_BY_TIME_NONE = 1;
    public static final int ORDER_BY_TIME_ASC = 2;
    public static final int ORDER_BY_TIME_DESC = 3;

    public static final String QUERY_LOAD_DB =
            "SELECT * FROM " + TABLE_EVENTS;

    // TODO Vghke error eksaitias tou "=". Na dw pws mporei na mpei.
//    public static final String QUERY_LOAD_DB_PREP =
//            "SELECT * FROM " + TABLE_EVENTS + " = ?";

    private PreparedStatement queryLoadDb;

    // Opens the Connection with the DB.
    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            // Prepared statemnt for queryLoadDb
//            queryLoadDb = connection.prepareStatement(QUERY_LOAD_DB_PREP);
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect with the database " + e.getMessage());
            return false;
        }
    }

    // Closes the Connection with the DB.
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close the database " + e.getMessage());
        }
    }

    // Method that loads the ArrayList from the db.
    public ArrayList<Event> queryLoadDb() {
        // Statement and ResultSet inside the try to ensure that they will close after the query is finished, in order to not use a "finally" after the catch.
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_LOAD_DB)) {

            // Creating a new repositoryDbList ArrayList<Event>.
            ArrayList<Event> repositoryDbList = new ArrayList<>();

            while (resultSet.next()){
                // Storing the values that come from the DB in variables.
                String eventMessage;
                long eventTime;
                Event event = new Event(eventMessage = resultSet.getString(COLUMN_EVENT_MESSAGE), eventTime = resultSet.getLong(COLUMN_EVENT_TIME_MILLIS));

                // Creating a new Event object passing those values.
                event.newEvent(eventMessage , eventTime);

                // Adding the event objects in the repositoryDbList.
                repositoryDbList.add(event);
            }

            return repositoryDbList;
        }catch (SQLException e){
            System.out.println("Could not load the events from the DB." + e.getMessage());
            return null;
        }
    }

    // Method to add Events from the user, in the repositoryDbList.
    @Override
    public void addMessage(String messageEvent, long timeEvent) {
//        repositoryDbList.add(new Event(messageEvent, timeEvent));

        // Adding an Event to the DB.
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("INSERT INTO " + TABLE_EVENTS + (COLUMN_EVENT_MESSAGE + "," + COLUMN_EVENT_TIME_MILLIS) + " VALUES " + (messageEvent + "," + timeEvent));
        }catch (SQLException e){
            System.out.println("Could not add the event to the DB" + e.getMessage());
        }
    }


    /*
      TODO
      TODO
      TODO  -->  NA SYNDETHOUN TA QUERIES ME CONSTANTS AP THN DB OLES OI METHODOI POU EPISTREFOUN DATA.
      TODO  --> !!! NA RWTHSW AN THELOUME NA GINONTAI OLA MESW SQL H AN THA ZHTAW ME SQL TA EVENTS KAI META SE LISTA THA TA XEIRIZOMAI OPWS PRIN!...
      TODO
      TODO
     */


    public ArrayList<Event> getTempListBasedOnQuantity(int readNumberOfMessages , ArrayList<Event> loadMessageList) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        for (Event event : loadMessageList) {
            if (tempMessageList.size() < readNumberOfMessages) {
                try {
                    tempMessageList.add(event);
                } catch (Exception e) {
                    System.out.println("There was an error trying to load the messages.");
                }
            }
        }
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLatestMessages(int readNumberOfMessages) {

        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        // Kanw sort ths repositoryList me to comparator EARLIEST_ORDER pou exw ftiaksei.
        Collections.sort(loadMessageList , RepositoryInMemory.LATEST_ORDER);


        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_EVENTS);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnQuantity(readNumberOfMessages , loadMessageList);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getOldestMessages(int readNumberOfMessages) {

        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        // Kanw sort ths repositoryList me to comparator EARLIEST_ORDER pou exw ftiaksei.
        Collections.sort(loadMessageList , RepositoryInMemory.EARLIEST_ORDER);


        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_EVENTS);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnQuantity(readNumberOfMessages , loadMessageList);

        return tempMessageList;
    }

    public ArrayList<Event> getTempListBasedOnTime(Calendar calendarRequest , ArrayList<Event> loadMessageList) {

        ArrayList<Event> tempMessageList = new ArrayList<>();

        for (Event event : repositoryDbList) {
            Calendar calendarRepo = Calendar.getInstance();
            calendarRepo.setTimeInMillis(event.getTime());
            try {
                if (calendarRequest.before(calendarRepo)) {
                    try {
                        tempMessageList.add(event);
                    } catch (Exception e) {
                        System.out.println("There was a problem when we tried to add the message.");
                    }
                }
            } catch (ArithmeticException e) {
                System.out.println("Something went wrong with the time calculation.");
            }
        }
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastHourMessages() {

        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR, -1);

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_EVENTS);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnTime(calendarRequest , loadMessageList);

        return tempMessageList;


        // TODO Deuteros tropos pou isxuei gia kathe methodo ksexwrista:
        // TODO Na to kanw olo me sql query h mporw na xrhsimopoihsw methodo eksw ap to query? An thelei olo sql tote tha prepei na
        // TODO apothhkeuw DATE_TIME gia na mporesw na kanw SELECT DATEADD(Year,-1,GETDATE()).
//        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
//        stringBuilder.append(TABLE_EVENTS);
//        stringBuilder.append(" WHERE ");
//        stringBuilder.append(COLUMN_EVENT_TIME_MILLIS);
//        stringBuilder.append(" - ");
//        stringBuilder.append(" INTERVAL ");
//        stringBuilder.append(calendarRequest);

    }



    // TODO Ta ekana comment gia na ginetai compile, tha ginoun sthn poreia.
    @Override
    public ArrayList<Event> getLastThreeHoursMessages() {
        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR, -3);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnTime(calendarRequest , loadMessageList);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastOneDayMessages() {

        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH, -1);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnTime(calendarRequest , loadMessageList);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastThreeDaysMessages() {
        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH, -3);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnTime(calendarRequest , loadMessageList);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastTenDaysMessages() {

        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH, -10);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnTime(calendarRequest , loadMessageList);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastMonthMessages() {

        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.MONTH, -1);

        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnTime(calendarRequest , loadMessageList);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getAllTheMessages() {

        ArrayList<Event> loadMessageList = new ArrayList<>();
        loadMessageList = queryLoadDb();

        return loadMessageList;
    }
}