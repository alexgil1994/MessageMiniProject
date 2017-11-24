// TODO A list with 2 values where one is the number that the user pressed while using the handlerActivityRequested and the second one is the method that the activity requests. There should be switch in the runAction method called by the controller.

// TODO Na dexetai interface object apo thn Controller wste na ektelei ton antistoixo tropo
// TODO uloipoihshs (RepositoryInMemory h antistoixa RepositoryDb) kai apla na trexei thn methodo aneksarthtws
// TODO ti repository einai.

public class ActionsToRun {

    private int actionMethod;
//    private RepositoryInMemory repositoryInMemory;
    private RepositoryDb repositoryDb = new RepositoryDb();
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

    // It gets the requested method to run by calling it through the new ActionsToRun object created before the call.
    void runAction() {
        switch (actionMethod) {
            case 1: {
                controller.showInstructions(actionMethod);

                scannerImport.readNewMessage();
                String messageOfEvent = scannerImport.getNewMessage();

                long timeOfEvent = controller.calcNewTime();

                Event event = new Event(messageOfEvent, timeOfEvent);
                event.newEvent(messageOfEvent, timeOfEvent);

                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }

                repositoryDb.addMessage(event.getMessage(), event.getTime());

                repositoryDb.close();

                break;
            }
            case 2: {
                controller.showInstructions(actionMethod);

                int numOfMessages = scannerImport.readNumOfMessages();
                controller.showCongratulationsInner(actionMethod, numOfMessages);

                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getLatestMessages(numOfMessages));
                repositoryDb.close();

                break;
            }
            case 3: {
                controller.showInstructions(actionMethod);

                ScannerImport scannerNumOfMessages = new ScannerImport();
                int numOfMessages = scannerNumOfMessages.readNumOfMessages();
                controller.showCongratulationsInner(actionMethod, numOfMessages);

                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getOldestMessages(numOfMessages));
                repositoryDb.close();

                break;
            }
            case 4: {
                controller.showInstructions(actionMethod);
                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getLastHourMessages());
                repositoryDb.close();
                break;
            }
            case 5: {
                controller.showInstructions(actionMethod);
                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getLastThreeHoursMessages());
                repositoryDb.close();
                break;
            }
            case 6: {
                controller.showInstructions(actionMethod);
                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getLastOneDayMessages());
                repositoryDb.close();
                break;
            }
            case 7: {
                controller.showInstructions(actionMethod);
                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getLastThreeDaysMessages());
                repositoryDb.close();
                break;
            }
            case 8: {
                controller.showInstructions(actionMethod);
                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getLastTenDaysMessages());
                repositoryDb.close();
                break;
            }
            case 9: {
                controller.showInstructions(actionMethod);
                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getLastMonthMessages());
                repositoryDb.close();
                break;
            }
            case 10: {
                controller.showInstructions(actionMethod);
                if (!repositoryDb.open()){
                    System.out.println("Cant open the repositoryDb");
                    return;
                }
                controller.printData(repositoryDb.getAllTheMessages());
                repositoryDb.close();
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
//                repositoryInMemory.addMessage(event.getMessage(), event.getTime());
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