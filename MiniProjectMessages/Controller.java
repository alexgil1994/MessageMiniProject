import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Controller {

    private void showInstructions(){
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
        // repositoryInMemory object ftiaxnetai mono me kathe arxh tou programmatos edw sthn handleRequestedActivity. H Action to dexetai apo edw.
        RepositoryInMemory repositoryInMemory = new RepositoryInMemory();

        boolean quit = false;
        while (!quit){

            // Starting of the loop by showing the menu of requests.
            showInstructions();

            // Reading the user's request.
            ScannerImport scanner = new ScannerImport();
            scanner.readActivity();
            int numPressed = scanner.getReadActivity();

            if (numPressed > 0 && numPressed <= 10){

                // DONE : Sthn metavash se repository db tha prepei na allaksei to na pairnei ws parametro repository to action. tha kalei ekeinh thn stigmh apo thn db ta dedomena pou xreiazetai.
                // Creating a new Action object for the new request from the user.
//                Action action = new Action(numPressed , repositoryInMemory);
//                action.newAction(numPressed , repositoryInMemory);
//
//                // Creating a new ActionsToRun object that gets the new action object that was created for the new request.
//                ActionsToRun actionsToRun = new ActionsToRun(action);

                // Creating a new Action object for the new request from the user.
                Action action = new Action(numPressed);
                action.newAction(numPressed);

                // Creating a new ActionsToRun object that gets the new action object that was created for the new request.
                ActionsToRun actionsToRun = new ActionsToRun(action);

                // Running the action that was requested by getting the action object to analyze what method to run inside the ActionsToRun class.
                actionsToRun.runAction();


                // When the user presses 0 the program exits from the loop -> program closes.
            } else if (numPressed == 0){
                this.showInstructions(numPressed);
                quit = true;
            }
        }
    }

    void showInstructions(int numPressed){
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
    long calcNewTime(){
        //Initializing Callendar and passing in the String with the full time.
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }
    //Formats and prints the data that are given from the repositoryInMem to the controller's handleRequestedActivity.
    void printData(ArrayList<Event> tempRepoList) {

        if (tempRepoList == null){
            System.out.println("There were no messages to show.");
        }else {


            int i = 0;
            for (Event event : tempRepoList) {
                i = i + 1;

                // Formating the existed time in milliseconds.
                SimpleDateFormat formatAs = new SimpleDateFormat("dd/MMM/yyyy - HH:mm");
                Date eventTimeFormatted = new Date(event.getTime());

                // Preparing the String for print.
                StringBuilder builder = new StringBuilder();
                builder.append("Message ").append(i).append(" : ").append(event.getMessage()).append("   || Time of message : ").append(formatAs.format(eventTimeFormatted));

                System.out.println(builder);
            }
        }
    }
    void showCongratulationsInner(int numPressed, int numOfMessages){
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
    void showCongratulations(){
        System.out.println("\nThe new message has been inserted.");
    }
}