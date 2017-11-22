// TODO A list with 2 values where one is the number that the user pressed while using the handlerActivityRequested and the second one is the method that the activity requests. There should be switch in the runAction method called by the controller.
public class Action {

    private int actionMethod;
    private RepositoryInMemory repositoryInMemory;
    Controller controller = new Controller();
    ScannerImport scannerImport = new ScannerImport();

    Action(int actionMethod, RepositoryInMemory repositoryInMemory) {
        this.actionMethod = actionMethod;
        this.repositoryInMemory = repositoryInMemory;
    }

    int getActionMethod() {
        return actionMethod;
    }

    void setActionMethod(int actionMethod) {
        this.actionMethod = actionMethod;
    }

    void runAction(int actionMethod) {

        switch (actionMethod) {
            case 1: {
                controller.showInstructions(actionMethod);

                scannerImport.readNewMessage();
                String messageOfEvent = scannerImport.getNewMessage();

                long timeOfEvent = controller.calcNewTime();

                Event event = new Event(messageOfEvent, timeOfEvent);
                event.newEvent(messageOfEvent, timeOfEvent);

                repositoryInMemory.addMessage(event.getMessage(), event.getTime());

                controller.showCongratulations();
                break;
            }
            case 2: {
                controller.showInstructions(actionMethod);

                int numOfMessages = scannerImport.readNumOfMessages();
                controller.showCongratulationsInner(actionMethod, numOfMessages);

                controller.printData(repositoryInMemory.getLatestMessages(numOfMessages));

                break;
            }
            case 3: {
                controller.showInstructions(actionMethod);

                ScannerImport scannerNumOfMessages = new ScannerImport();
                int numOfMessages = scannerNumOfMessages.readNumOfMessages();
                controller.showCongratulationsInner(actionMethod, numOfMessages);

                controller.printData(repositoryInMemory.getOldestMessages(numOfMessages));

                break;
            }
            case 4: {
                controller.showInstructions(actionMethod);
                controller.printData(repositoryInMemory.getLastHourMessages());
                break;
            }
            case 5: {
                controller.showInstructions(actionMethod);
                controller.printData(repositoryInMemory.getLastThreeHoursMessages());
                break;
            }
            case 6: {
                controller.showInstructions(actionMethod);
                controller.printData(repositoryInMemory.getLastOneDayMessages());
                break;
            }
            case 7: {
                controller.showInstructions(actionMethod);
                controller.printData(repositoryInMemory.getLastThreeDaysMessages());
                break;
            }
            case 8: {
                controller.showInstructions(actionMethod);
                controller.printData(repositoryInMemory.getLastTenDaysMessages());
                break;
            }
            case 9: {
                controller.showInstructions(actionMethod);
                controller.printData(repositoryInMemory.getLastMonthMessages());
                break;
            }
            case 10: {
                controller.showInstructions(actionMethod);
                controller.printData(repositoryInMemory.getAllTheMessages());
                break;
            }
        }
    }
}