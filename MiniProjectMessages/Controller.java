import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Controller {

    void showInstructions(){
        System.out.println("\nHello there!\n");
        System.out.println("Instructions : \n" +
                "Press 1 and then Enter to add a new message in your list.\n" +
                "Press 2 and then Enter to view the specific amount of messages you want.\n" +
                "Press 3 and then Enter to view the messages of the last hour.\n" +
                "Press 4 and then Enter to view the messages of the last 3 hours.\n" +
                "Press 5 and then Enter to view the messages of the last 24 hours.\n" +
                "Press 6 and then Enter to view the messages of the last 3 days.\n" +
                "Press 7 and then Enter to view the messages of the last 10 days.\n" +
                "Press 8 and then Enter to view the messages of the last 30 days.\n" +
                "Press 9 and then Enter to view all the messages that have been stored.\n" +
                "Press 0 and then Enter if you want to exit from the program.\n");
    }
    void handleRequestedActivity(){
        // repositoryInMemory object ftiaxnetai mono me kathe arxh tou programmatos.
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        // TODO Start for the future database.
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\amarkovits\\IdeaProjects\\Test\\repositoryDb.db");
//            Statement statement = conn.createStatement();
//            statement.execute("CREATE TABLE event ()");
//        }catch (SQLException e){
//            System.out.println("Something went wrong. The connection with the db couldnt be established." + e.getMessage());
//        }

        boolean quit = false;
        while (!quit){

            showInstructions();
            ScannerImport scanner = new ScannerImport();
            scanner.readActivity();
            int numPressed = scanner.getReadActivity();

            switch (numPressed){
                case 1: {
                    this.showInstructions(numPressed);

                    scanner.readNewMessage();
                    String messageOfEvent = scanner.getNewMessage();

                    long timeOfEvent = calcNewTime();

                    Event event = new Event(messageOfEvent , timeOfEvent);
                    event.newEvent(messageOfEvent , timeOfEvent);

                    repositoryInMemory.addMessage(event.getMessage() , event.getTime());

                    this.showCongratulations();
                    break;
                }
                case 2: {
                    this.showInstructions(numPressed);

                    int numOfMessages = scanner.readNumOfMessages();
                    showCongratulationsInner(numPressed,numOfMessages);

                    this.printData(repositoryInMemory.getLatestMessages(numOfMessages));

                    break;
                }
                case 3: {
                    this.showInstructions(numPressed);

                    ScannerImport scannerNumOfMessages = new ScannerImport();
                    int numOfMessages = scannerNumOfMessages.readNumOfMessages();
                    showCongratulationsInner(numPressed,numOfMessages);

                    this.printData(repositoryInMemory.getOldestMessages(numOfMessages));

                    break;
                }
                case 4: {
                    this.showInstructions(numPressed);
                    this.printData(repositoryInMemory.getLastHourMessages());
                    break;
                }
                case 5: {
                    this.showInstructions(numPressed);
                    this.printData(repositoryInMemory.getLastThreeHoursMessages());
                    break;
                }
                case 6: {
                    this.showInstructions(numPressed);
                    this.printData(repositoryInMemory.getLastOneDayMessages());
                    break;
                }
                case 7: {
                    this.showInstructions(numPressed);
                    this.printData(repositoryInMemory.getLastThreeDaysMessages());
                    break;
                }
                case 8: {
                    this.showInstructions(numPressed);
                    this.printData(repositoryInMemory.getLastTenDaysMessages());
                    break;
                }
                case 9: {
                    this.showInstructions(numPressed);
                    this.printData(repositoryInMemory.getLastMonthMessages());
                    break;
                }
                case 10: {
                    this.showInstructions(numPressed);
                    printData(repositoryInMemory.getAllTheMessages());
                    break;
                }
                case 0: {
                    this.showInstructions(numPressed);
                    quit = true;
                    break;
                }
            }
        }
    }
    private void showInstructions(int numPressed){
        switch (numPressed){
            case 1: {
                System.out.println("Type the message you want to insert and then press Enter\n");
                break;
            }
            case 2: {
                System.out.println("Request an x amount of the latest messages\n");
                break;
            }
            case 3: {
                System.out.println("\nRequest an x amount of the oldest messages\n");
                break;
            }
            case 4: {
                System.out.println("\nThe messages from the past 1 hour are :\n");
                break;
            }
            case 5: {
                System.out.println("\nThe messages from the past 3 hours are :\n");
                break;
            }
            case 6: {
                System.out.println("\nThe messages from the past 1 days are :\n");
                break;
            }
            case 7: {
                System.out.println("\nThe messages from the past 3 days are :\n");
                break;
            }
            case 8: {
                System.out.println("\nThe messages from the past 10 days are :\n");
                break;
            }
            case 9: {
                System.out.println("\nThe messages from the past 30 days are :\n");
                break;
            }
            case 10: {
                System.out.println("\nThese are all the stored messages :\n");
                break;
            }
            case 0: {
                System.out.println("\nYou have chosen to exit\n");
                break;
            }
        }
    }
    public long calcNewTime(){
        //Initializing Callendar and passing in the String with the full time.
        Calendar calendar = Calendar.getInstance();
        long timeEvent = calendar.getTimeInMillis();
        return timeEvent;
    }
    //Formats and prints the data that are given from the repositoryInMem to the controller's handleRequestedActivity.
    private void printData(ArrayList<Event> tempRepoList) {
        int i = 0;
        for (Event event: tempRepoList){
            i = i + 1;

            // Formating the existed time in milliseconds.
            SimpleDateFormat formatAs = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
            Date eventTimeFormatted = new Date(event.getTime());

            // Preparing the String for print.
            StringBuilder builder = new StringBuilder();
            builder.append("Message " + i + " : " + event.getMessage() + "   || Time of message : " + formatAs.format(eventTimeFormatted));

            System.out.println(builder);
        }
    }
    private void showCongratulationsInner(int numPressed,int numOfMessages){
        switch (numPressed){
            case 2: {
                System.out.println("\nThe latest amount of " + numOfMessages + " messages is : \n");
                break;
            }
            case 3: {
                System.out.println("\nThe oldest amount of " + numOfMessages + " messages is : \n");
                break;
            }
            case 0: {
                System.out.println("\nYou have chosen to navigate back\n");
                break;
            }
        }
    }
    private void showCongratulations(){
        System.out.println("\nThe new message has been inserted.");
    }
}