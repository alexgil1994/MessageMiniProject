import java.sql.*;
import java.util.ArrayList;

public class RepositoryDb implements IRepository {

    private ArrayList<Event> repositoryDbList = new ArrayList<>();
    private Connection connection;

    public RepositoryDb() {
        // Empty Constructor.
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
    public static final String QUERY_LOAD_DB_PREP =
            "SELECT * FROM " + TABLE_EVENTS + " = ?";

    /*
      TODO
      TODO  -->  NA SYNDETHOUN TA QUERIES ME CONSTANTS AP THN DB OLES OI METHODOI POU EPISTREFOUN DATA.
      TODO
     */

    // For a transaction.
    public static final String  INSERT_EVENT_MESSAGE = "INSERT INTO " + TABLE_EVENTS + '(' + COLUMN_EVENT_MESSAGE + ") VALUES(?)";
    public static final String  INSERT_EVENT_TIME = "INSERT INTO " + TABLE_EVENTS + '(' + COLUMN_EVENT_TIME_MILLIS + ") VALUES(?)";

    private PreparedStatement queryLoadDb;
    private PreparedStatement insertEventMessage;
    private PreparedStatement insertEventTime;

    // Opens the Connection with the DB.
    public boolean open() {
        try {
            //TODO fix the problem with prepared statements
//            // Prepared statement for queryLoadDb
//            queryLoadDb = connection.prepareStatement(QUERY_LOAD_DB_PREP);
//
//            // Prepared statement for addMessage
//            insertEventMessage = connection.prepareStatement(INSERT_EVENT_MESSAGE, Statement.RETURN_GENERATED_KEYS);
//
//            // Prepared statement for addMessage
//            insertEventTime = connection.prepareStatement(INSERT_EVENT_TIME, Statement.RETURN_GENERATED_KEYS);

            connection = DriverManager.getConnection(CONNECTION_STRING);

            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect with the database " + e.getMessage());
            return false;
        }
    }

    // Closes the Connection with the DB.
    public void close() {
        try {
            if (queryLoadDb != null) {
                queryLoadDb.close();
            }

            if (insertEventMessage != null) {
                insertEventMessage.close();
            }

            if (insertEventTime != null) {
                insertEventTime.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close the database " + e.getMessage());
        }
    }

    private ArrayList<Event> listFromQuery(ResultSet resultSet) throws SQLException{
        try{
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
        }catch (SQLException e){
            System.out.println("There was a problem when trying to save the data.");
        }
        return repositoryDbList;
    }

    // Method that loads the ArrayList from the db.
    public ArrayList<Event> queryLoadDb() {
        // Statement and ResultSet inside the try to ensure that they will close after the query is finished, in order to not use a "finally" after the catch.
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_LOAD_DB)) {

            // Creating a new repositoryDbList ArrayList<Event>.
            ArrayList<Event> repositoryDbList = new ArrayList<>();

            repositoryDbList = listFromQuery(resultSet);

            return repositoryDbList;
        }catch (SQLException e){
            System.out.println("Could not load the events from the DB." + e.getMessage());
            return null;
        }
    }

    // TODO FIX Vazei mhnuma mono an einai arithmos, an tou dwthei string vgainei den mporei na to valei.
    // Method to add Events from the user, in the repositoryDbList.
    @Override
    public void addMessage(String messageEvent, long timeEvent) {
        Controller controller = new Controller();

        // Adding an Event to the DB.
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("INSERT INTO " + TABLE_EVENTS + "(" + COLUMN_EVENT_MESSAGE + "," + COLUMN_EVENT_TIME_MILLIS + ")" + " VALUES " + "(" + messageEvent + "," + timeEvent + ")");

            // Showing to the user that the message was successfully added
            controller.showCongratulations();
        }catch (SQLException e){
            System.out.println("Could not add the event to the DB" + e.getMessage());
        }
    }

    // TODO Epishs mporw na allaksw thn apothhkeush na einai se DATE_TIME gia na xrhsimopoiw ta : SELECT DATEADD(Year,-1,GETDATE()).

    // TODO Na dw an thelw na kanw ta queries strings, p.x. :
//        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
//        stringBuilder.append(TABLE_EVENTS);
//        stringBuilder.append(" WHERE ");
//        stringBuilder.append(COLUMN_EVENT_TIME_MILLIS);
//        stringBuilder.append(" - ");
//        stringBuilder.append(" INTERVAL ");
//        stringBuilder.append(calendarRequest);

    @Override
    public ArrayList<Event> getLatestMessages(int readNumberOfMessages) {
        ArrayList<Event> repositoryDbList = new ArrayList<>();

        try(Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_ID + " >= ( SELECT MAX( " + COLUMN_EVENT_ID + " ) - " + readNumberOfMessages + " FROM " + TABLE_EVENTS  )) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " ORDER BY " + COLUMN_EVENT_TIME_MILLIS + " ASC " + " LIMIT " + readNumberOfMessages )) {

            // Calling the listFromQuery method to store the data from the db in the list.
            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not load the latest " + readNumberOfMessages + " messages." + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getOldestMessages(int readNumberOfMessages) {

        ArrayList<Event> repositoryDbList = new ArrayList<>();

        try(Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_ID + " >= ( SELECT MAX( " + COLUMN_EVENT_ID + " ) - " + readNumberOfMessages + " FROM " + TABLE_EVENTS  )) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " ORDER BY " + COLUMN_EVENT_TIME_MILLIS + " DESC " + " LIMIT " + readNumberOfMessages)) {

            // Calling the listFromQuery method to store the data from the db in the list.
            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not load the oldest " + readNumberOfMessages + " messages." + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getLastHourMessages() {
        ArrayList<Event> repositoryDbList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + "/1000/60/60")) {

            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not get the last hour messages from the db. " + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getLastThreeHoursMessages() {
        ArrayList<Event> repositoryDbList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + "/1000/60/60/3")) {

            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not get the last hour messages from the db. " + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getLastOneDayMessages() {
        ArrayList<Event> repositoryDbList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + "/1000/60/60/24")) {

            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not get the last hour messages from the db. " + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getLastThreeDaysMessages() {
        ArrayList<Event> repositoryDbList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + "/1000/60/60/24/3")) {

            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not get the last hour messages from the db. " + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getLastTenDaysMessages() {
        ArrayList<Event> repositoryDbList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + "/1000/60/60/24/10")) {

            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not get the last hour messages from the db. " + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getLastMonthMessages() {
        ArrayList<Event> repositoryDbList = new ArrayList<>();

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TIME_MILLIS + " >= " + timeOfRequest + "/1000/60/60/24/30")) {

            repositoryDbList = listFromQuery(resultSet);
        }catch (SQLException e){
            System.out.println("Could not get the last hour messages from the db. " + e.getMessage());
        }

        return repositoryDbList;
    }

    @Override
    public ArrayList<Event> getAllTheMessages() {

        ArrayList<Event> repositoryDbList = new ArrayList<>();
        repositoryDbList = queryLoadDb();

        return repositoryDbList;
    }
}