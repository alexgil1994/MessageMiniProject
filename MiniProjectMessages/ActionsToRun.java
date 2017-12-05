// TODO A list with 2 values where one is the number that the user pressed while using the handlerActivityRequested and the second one is the method that the activity requests.
// TODO There should be switch in the runAction method called by the controller.

public class ActionsToRun {

    private int actionMethod;

    Controller controller = new Controller();
    ScannerImport scannerImport = new ScannerImport();

    ActionsToRun(Action action) {
        this.actionMethod = action.getMethod();
    }

    int getActionMethod() {
        return actionMethod;
    }


    private void checkDb(RepositoryDb repositoryDb) {
        if (!repositoryDb.open()) {
            System.out.println("Cant open the repositoryDb");
            return;
        }
    }

    private void closeDb(RepositoryDb repositoryDb) {
        repositoryDb.close();
    }

    // TODO We need an if-else to know the type of repository the calledIRepository is in order to disable the open-close db connections for the repositoryInMemory (otherwise there is a conflict).
    // It gets the requested method to run by calling it through the new ActionsToRun object created before the call.
    void runAction(IRepository calledIRepository) {
        IRepository iRepository = calledIRepository;
        switch (actionMethod) {
            case 1: {
                controller.showInstructions(actionMethod);

                scannerImport.readNewMessage();
                String messageOfEvent = scannerImport.getNewMessage();

                long timeOfEvent = controller.calcNewTime();

                Event event = new Event(messageOfEvent, timeOfEvent);

                // Opens the db connection.
                ((RepositoryDb) iRepository).open();

                // Calling for data from the IRepository object that passed as an argument so that both implementations can work.
                boolean success = iRepository.addMessage(event);

                // Checks if the addMessage was a success or not and prints the appropriate message for the user.
                if (success == true){
                    controller.showCongratulations();
                }else {
                    System.out.println("We apologize for the problem that occurred, the message could not be added.");
                }

                // Closes the db connection.
                ((RepositoryDb) iRepository).close();
                break;
            }
            case 2: {
                controller.showInstructions(actionMethod);

                int numOfMessages = scannerImport.readNumOfMessages();
                controller.showCongratulationsInner(actionMethod, numOfMessages);

                ((RepositoryDb) iRepository).open();

                // Calling for data from the IRepository object that passed as an argument so that both implementations can work.
                controller.printData(iRepository.getLatestMessages(numOfMessages));

                ((RepositoryDb) iRepository).close();
                break;
            }
            case 3: {
                controller.showInstructions(actionMethod);

                ScannerImport scannerNumOfMessages = new ScannerImport();
                int numOfMessages = scannerNumOfMessages.readNumOfMessages();
                controller.showCongratulationsInner(actionMethod, numOfMessages);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getOldestMessages(numOfMessages));

                ((RepositoryDb) iRepository).close();
                break;
            }
            case 4: {
                controller.showInstructions(actionMethod);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getLastHourMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
            case 5: {
                controller.showInstructions(actionMethod);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getLastThreeHoursMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
            case 6: {
                controller.showInstructions(actionMethod);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getLastOneDayMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
            case 7: {
                controller.showInstructions(actionMethod);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getLastThreeDaysMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
            case 8: {
                controller.showInstructions(actionMethod);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getLastTenDaysMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
            case 9: {
                controller.showInstructions(actionMethod);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getLastMonthMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
            case 10: {
                controller.showInstructions(actionMethod);

                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getAllTheMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
        }
    }
}