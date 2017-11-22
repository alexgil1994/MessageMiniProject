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

    public static final int ORDER_BY_TIME_NONE = 1;
    public static final int ORDER_BY_TIME_ASC = 2;
    public static final int ORDER_BY_TIME_DESC = 3;

    // Opens the Connection with the DB.
    public boolean open() {
        try {
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
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close the database " + e.getMessage());
        }
    }

    // Method that loads the ArrayList from the db.
    public ArrayList<Event> queryAddAllEventDb() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS)) {

            // Initialising.
            String eventMessage = null;
            long eventTime = 0;

            // Creating a new Event.
            Event event = new Event(eventMessage, eventTime);
            event.newEvent(resultSet.getString(COLUMN_EVENT_MESSAGE), resultSet.getLong(COLUMN_EVENT_TIME_MILLIS));

            // Adding the Events from the DB to the repositoryDbList.
            repositoryDbList.add(new Event(event.getMessage(), event.getTime()));

            return repositoryDbList;
        } catch (SQLException e) {
            System.out.println("Could not execute a query -add new Event from the database-." + e.getMessage());
            return null;
        }
    }

    // Method to add Events from the user, in the repositoryDbList.
    @Override
    public void addMessage(String messageEvent, long timeEvent) {
        repositoryDbList.add(new Event(messageEvent, timeEvent));
    }


    /*
      TODO
      TODO
      TODO  -->  NA SYNDETHOUN ME QUERIES ME CONSTANTS AP THN DB OLES OI METHODOUS POU EPISTREFOUN DATA.
      TODO  --> !!! NA RWTHSW AN THELOUME NA GINONTAI OLA MESW SQL H AN THA ZHTAW ME SQL TA EVENTS KAI META SE LISTA THA TA XEIRIZOMAI OPWS PRIN!...
      TODO
     */


    @Override
    public ArrayList<Event> getTempListBasedOnQuantity(int readNumberOfMessages) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        for (Event event : repositoryDbList) {
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
        // Kanw sort ths repositoryList me to comparator EARLIEST_ORDER pou exw ftiaksei.
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);

        // Arxikopoihsh new tempMessageList, einai auth pou tha epistrepsw gia print.
        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnQuantity(readNumberOfMessages);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getOldestMessages(int readNumberOfMessages) {
        // Kanw sort ths repositoryList me to comparator EARLIEST_ORDER pou exw ftiaksei.
        Collections.sort(repositoryDbList, RepositoryInMemory.LATEST_ORDER);

        // Arxikopoihsh new tempMessageList einai auth pou tha epistrepsw gia print.
        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnQuantity(readNumberOfMessages);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getTempListBasedOnTime(Calendar calendarRequest) {

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
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR, -1);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastThreeHoursMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR, -3);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastOneDayMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH, -1);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastThreeDaysMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH, -3);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastTenDaysMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH, -10);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastMonthMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.MONTH, -1);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getAllTheMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryDbList, RepositoryInMemory.EARLIEST_ORDER);
        for (Event event : repositoryDbList) {
            try {
                tempMessageList.add(event);
            } catch (Exception e) {
                System.out.println("There was a problem when we tried to add the message.");
            }
        }
        return repositoryDbList;

//        try(Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_EVENTS)) {
//
//            // Initialising.
//            String eventMessage = null;
//            long eventTime = 0;
//
//            // Creating a new Event.
//            Event event  = new Event(eventMessage, eventTime);
//            event.newEvent(resultSet.getString(INDEX_EVENT_MESSAGE),resultSet.getLong(INDEX_EVENT_TIME_MILLIS));
//
//            // Adding the Events from the DB to the repositoryDbList.
//            repositoryDbList.add(new Event(event.getMessage(),event.getTime()));
//
//            return repositoryDbList;
//        }catch (SQLException e){
//            System.out.println("Could not execute a query -add new Event from the database-." + e.getMessage());
//            return null;
//        }
//    }
    }
}