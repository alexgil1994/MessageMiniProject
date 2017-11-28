// TODO A list with 2 values where one is the number that the user pressed while using the handlerActivityRequested and the second one is the method that the activity requests. There should be switch in the runAction method called by the controller.

// TODO Na dexetai interface object apo thn Controller wste na ektelei ton antistoixo tropo
// TODO uloipoihshs (RepositoryInMemory h antistoixa RepositoryDb) kai apla na trexei thn methodo aneksarthtws
// TODO ti repository einai.

public class ActionsToRun {

    private int actionMethod;
//    private RepositoryInMemory repositoryInMemory;
//    private RepositoryDb repositoryDb = new RepositoryDb();
//    private IRepository iRepository = new RepositoryDb();

    Controller controller = new Controller();
    ScannerImport scannerImport = new ScannerImport();

//    ActionsToRun(Action action) {
//        this.actionMethod = action.getMethod();
//        this.repositoryInMemory = action.getRepositoryInMem();
//    }

    ActionsToRun(Action action) {
        this.actionMethod = action.getMethod();
    }

    int getActionMethod() {
        return actionMethod;
    }

    void setActionMethod(int actionMethod) {
        this.actionMethod = actionMethod;
    }

    private void checkDb(RepositoryDb repositoryDb){
        if (!repositoryDb.open()){
            System.out.println("Cant open the repositoryDb");
            return;
        }
    }

    private void closeDb(RepositoryDb repositoryDb){
        repositoryDb.close();
    }

    // It gets the requested method to run by calling it through the new ActionsToRun object created before the call.
    void runAction(IRepository getIRepository) {
        IRepository iRepository = getIRepository;
        switch (actionMethod) {
            case 1: {
                controller.showInstructions(actionMethod);

                scannerImport.readNewMessage();
                String messageOfEvent = scannerImport.getNewMessage();

                long timeOfEvent = controller.calcNewTime();

                Event event = new Event(messageOfEvent, timeOfEvent);
                event.newEvent(messageOfEvent, timeOfEvent);

                // Opens the db connection.
                ((RepositoryDb) iRepository).open();

                // Calling for data from the IRepository object that passed as an argument so that both implementations can work.
                iRepository.addMessage(event);

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
//
                ((RepositoryDb) iRepository).open();
                controller.printData(iRepository.getAllTheMessages());
                ((RepositoryDb) iRepository).close();
                break;
            }
        }
    }




//    // It gets the requested method to run by calling it through the new ActionsToRun object created before the call.
//    void runAction() {
//        switch (actionMethod) {
//            case 1: {
//                controller.showInstructions(actionMethod);
//
//                scannerImport.readNewMessage();
//                String messageOfEvent = scannerImport.getNewMessage();
//
//                long timeOfEvent = controller.calcNewTime();
//
//                Event event = new Event(messageOfEvent, timeOfEvent);
//                event.newEvent(messageOfEvent, timeOfEvent);
//
//                repositoryInMemory.addMessage(event);
//
//                controller.showCongratulations();
//                break;
//            }
//            case 2: {
//                controller.showInstructions(actionMethod);
//
//                int numOfMessages = scannerImport.readNumOfMessages();
//                controller.showCongratulationsInner(actionMethod, numOfMessages);
//
//                controller.printData(repositoryInMemory.getLatestMessages(numOfMessages));
//
//                break;
//            }
//            case 3: {
//                controller.showInstructions(actionMethod);
//
//                ScannerImport scannerNumOfMessages = new ScannerImport();
//                int numOfMessages = scannerNumOfMessages.readNumOfMessages();
//                controller.showCongratulationsInner(actionMethod, numOfMessages);
//
//                controller.printData(repositoryInMemory.getOldestMessages(numOfMessages));
//
//                break;
//            }
//            case 4: {
//                controller.showInstructions(actionMethod);
//                controller.printData(repositoryInMemory.getLastHourMessages());
//                break;
//            }
//            case 5: {
//                controller.showInstructions(actionMethod);
//                controller.printData(repositoryInMemory.getLastThreeHoursMessages());
//                break;
//            }
//            case 6: {
//                controller.showInstructions(actionMethod);
//                controller.printData(repositoryInMemory.getLastOneDayMessages());
//                break;
//            }
//            case 7: {
//                controller.showInstructions(actionMethod);
//                controller.printData(repositoryInMemory.getLastThreeDaysMessages());
//                break;
//            }
//            case 8: {
//                controller.showInstructions(actionMethod);
//                controller.printData(repositoryInMemory.getLastTenDaysMessages());
//                break;
//            }
//            case 9: {
//                controller.showInstructions(actionMethod);
//                controller.printData(repositoryInMemory.getLastMonthMessages());
//                break;
//            }
//            case 10: {
//                controller.showInstructions(actionMethod);
//                controller.printData(repositoryInMemory.getAllTheMessages());
//                break;
//            }
//        }
//    }
}