import java.util.*;

public class Repository implements IRepository {

    ArrayList<Event> repositoryList = new ArrayList<>();

    static final Comparator<Event> LATEST_ORDER = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            if (e1.getTime() > e2.getTime()){
                return -1;
            }else if (e1.getTime() < e2.getTime()){
                return 1;
            }else {
                return 0;
            }
        }
    };

    static final Comparator<Event> EARLIEST_ORDER = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            if (e1.getTime() > e2.getTime()){
                return 1;
            }else if (e1.getTime() < e2.getTime()){
                return -1;
            }else {
                return 0;
            }
        }
    };

    public Repository(ArrayList<Event> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public Repository() {
        // Xreiazetai keno constructor gia na mporei na dhmiourgei to object repository pou den pairnei metavlhtes sthn Controller.
    }

    @Override
    public void addMessage(String messageEvent, long timeEvent) {
        repositoryList.add(new Event(messageEvent , timeEvent));
    }

    @Override
    public ArrayList<Event> getLatestMessages(int readNumberOfMessages) {
        // Kanw sort ths repositoryList me to comparator EARLIEST_ORDER pou exw ftiaksei.
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);

        // Arxikopoihsh new tempMessageList, einai auth pou tha epistrepsw gia print.
        ArrayList<Event> tempMessageList = new ArrayList<>();

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getOldestMessages(int readNumberOfMessages) {
        // Kanw sort ths repositoryList me to comparator EARLIEST_ORDER pou exw ftiaksei.
        Collections.sort(repositoryList , Repository.LATEST_ORDER);

        // Arxikopoihsh new tempMessageList einai auth pou tha epistrepsw gia print.
        ArrayList<Event> tempMessageList = new ArrayList<>();

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getLastHourMessages(long timeOfRequest) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR , -1);

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getLastThreeHoursMessages(long timeOfRequest) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.HOUR , -3);

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getLastOneDayMessages(long timeOfRequest) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH , -1);

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getLastThreeDaysMessages(long timeOfRequest) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH , -3);

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getLastTenDaysMessages(long timeOfRequest) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.DAY_OF_MONTH , -10);

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getLastMonthMessages(long timeOfRequest) {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);

        Calendar calendarRequest = Calendar.getInstance();

        calendarRequest.setTimeInMillis(timeOfRequest);
        calendarRequest.add(Calendar.MONTH , -1);

        for (Event event : repositoryList) {
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
    public ArrayList<Event> getAllTheMessages() {
        ArrayList<Event> tempMessageList = new ArrayList<>();
        Collections.sort(repositoryList , Repository.EARLIEST_ORDER);
        for (Event event: repositoryList) {
            try {
                tempMessageList.add(event);
            } catch (Exception e) {
                System.out.println("There was a problem when we tried to add the message.");
            }
        }
        return tempMessageList;
    }
}