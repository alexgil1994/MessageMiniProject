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
        // repository object ftiaxnetai mono me kathe arxh tou programmatos.
        Repository repository = new Repository();

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

                    repository.addMessage(event.getMessage() , event.getTime());

                    this.showCongratulations();
                    break;
                }

                case 2: {
                    boolean back = false;
                    while (!back){
                        this.showInstructions(numPressed);

                        scanner.readActivity();
                        int numPressedInner = scanner.getReadActivity();


                        // TODO TO-FIX-BUG: kanei mia epipleon ektupwsh. Afou teleiwsei me thn case 2 ws sunolo, paei automata sthn epomenh k ektelei print gia kapoion logo.

                        switch (numPressedInner){
                            case 1: {
                                System.out.println("Insert the requested amount of the latest messages :");

                                int numOfMessages = scanner.readNumOfMessages();
                                showCongratulationsInner(numPressedInner,numOfMessages);

                                //Kalw thn getRequestedMessages pou mou epistrefei String mesa sthn printData pou zhtaei to String gia na leitourghsei kai to kanei print.
                                this.printData(repository.getLatestMessages(numOfMessages));

                                back = true;
                                break;
                            }
                            case 2: {
                                System.out.println("Insert the requested amount of the oldest messages :");

                                ScannerImport scannerNumOfMessages = new ScannerImport();
                                int numOfMessages = scannerNumOfMessages.readNumOfMessages();
                                showCongratulationsInner(numPressedInner,numOfMessages);

                                this.printData(repository.getOldestMessages(numOfMessages));

                                back = true;
                                break;
                            }
                            case 0: {
                                back = true;
                                break;
                            }
                        }
                    }
                }
                case 3: {
                    this.showInstructions(numPressed);
                    long timeOfRequest = calcNewTime();
                    this.printData(repository.getLastHourMessages(timeOfRequest));
                    break;
                }
                case 4: {
                    this.showInstructions(numPressed);
                    long timeOfRequest = calcNewTime();
                    this.printData(repository.getLastThreeHoursMessages(timeOfRequest));
                    break;
                }
                case 5: {
                    this.showInstructions(numPressed);
                    long timeOfRequest = calcNewTime();
                    this.printData(repository.getLastOneDayMessages(timeOfRequest));
                    break;
                }
                case 6: {
                    this.showInstructions(numPressed);
                    long timeOfRequest = calcNewTime();
                    this.printData(repository.getLastThreeDaysMessages(timeOfRequest));
                    break;
                }
                case 7: {
                    this.showInstructions(numPressed);
                    long timeOfRequest = calcNewTime();
                    this.printData(repository.getLastTenDaysMessages(timeOfRequest));
                    break;
                }
                case 8: {
                    this.showInstructions(numPressed);
                    long timeOfRequest = calcNewTime();
                    this.printData(repository.getLastMonthMessages(timeOfRequest));
                    break;
                }
                case 9: {
                    this.showInstructions(numPressed);
                    printData(repository.getAllTheMessages());
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
                System.out.println("Press 1 to request an x amount of the latest messages\n" +
                                   "Press 2 to request an x amount of the oldest messages\n" +
                                   "Press 0 to navigate back\n");
                break;
            }
            case 3: {
                System.out.println("\nThe messages from the past 1 hour are :\n");
                break;
            }
            case 4: {
                System.out.println("\nThe messages from the past 3 hours are :\n");
                break;
            }
            case 5: {
                System.out.println("\nThe messages from the past 1 days are :\n");
                break;
            }
            case 6: {
                System.out.println("\nThe messages from the past 3 days are :\n");
                break;
            }
            case 7: {
                System.out.println("\nThe messages from the past 10 days are :\n");
                break;
            }
            case 8: {
                System.out.println("\nThe messages from the past 30 days are :\n");
                break;
            }
            case 9: {
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
    //Formats and prints the data that are given from the repository to the controller's handleRequestedActivity.
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
    private void showCongratulationsInner(int numPressedInner,int numOfMessages){
        switch (numPressedInner){
            case 1: {
                System.out.println("\nThe latest amount of " + numOfMessages + " messages is : \n");
                break;
            }
            case 2: {
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