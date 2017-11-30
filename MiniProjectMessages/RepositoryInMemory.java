import java.util.*;

public class RepositoryInMemory implements IRepository {

    ArrayList<Event> repositoryInMemList = new ArrayList<>();

    static final Comparator<Event> EARLIEST_ORDER = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            // Kanei ta 3 if gia 1 , -1, 0 se mia grammh.
            return Long.compare(e2.getTime(), e1.getTime());
        }
    };

    static final Comparator<Event> LATEST_ORDER = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            // Kanei ta 3 if gia 1 , -1, 0 se mia grammh.
            return Long.compare(e1.getTime(), e2.getTime());
        }
    };

    public RepositoryInMemory(ArrayList<Event> repositoryList) {
        this.repositoryInMemList = repositoryList;
    }

    public RepositoryInMemory() {
        // Xreiazetai kenos constructor gia na mporei na dhmiourgei to object repository pou den pairnei metavlhtes sthn Controller.
    }

    @Override
    public void addMessage(Event event) {
        repositoryInMemList.add(event);
    }

    public ArrayList<Event> getTempListBasedOnQuantity(int readNumberOfMessages) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        for (Event event : repositoryInMemList) {
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
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);

        // Arxikopoihsh new tempMessageList, einai auth pou tha epistrepsw gia print.
        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnQuantity(readNumberOfMessages);

        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getOldestMessages(int readNumberOfMessages) {
        // Kanw sort ths repositoryList me to comparator EARLIEST_ORDER pou exw ftiaksei.
        Collections.sort(repositoryInMemList , RepositoryInMemory.LATEST_ORDER);

        // Arxikopoihsh new tempMessageList einai auth pou tha epistrepsw gia print.
        ArrayList<Event> tempMessageList = new ArrayList<>();
        tempMessageList = getTempListBasedOnQuantity(readNumberOfMessages);

        return tempMessageList;
    }

    public ArrayList<Event> getTempListBasedOnTime(Calendar calendarRequest) {

        ArrayList<Event> tempMessageList = new ArrayList<>();

        for (Event event : repositoryInMemList) {
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
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR , -1);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastThreeHoursMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR , -3);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastOneDayMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH , -1);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastThreeDaysMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH , -3);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastTenDaysMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH , -10);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getLastMonthMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);

        Controller controller = new Controller();
        long timeOfRequest = controller.calcNewTime();

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.MONTH , -1);

        tempMessageList = getTempListBasedOnTime(calendarRequest);
        return tempMessageList;
    }

    @Override
    public ArrayList<Event> getAllTheMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryInMemList , RepositoryInMemory.EARLIEST_ORDER);
        for (Event event: repositoryInMemList) {
            try {
                tempMessageList.add(event);
            } catch (Exception e) {
                System.out.println("There was a problem when we tried to add the message.");
            }
        }
        return tempMessageList;
    }
}