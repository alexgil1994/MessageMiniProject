import java.util.InputMismatchException;
import java.util.Scanner;


class ScannerImport {
    private Scanner scanner = new Scanner(System.in);
    private int chosenActivity;
    private String newMessage;
    private int numOfMessages = 0;

    public ScannerImport(Scanner scanner, int chosenActivity, String newMessage, int numOfMessages) {
        this.scanner = scanner;
        this.chosenActivity = chosenActivity;
        this.newMessage = newMessage;
        this.numOfMessages = numOfMessages;
    }

    public ScannerImport() {
        //Kenos constructor giati alliws vgazei sfalma sthn Controller pou kalei ScannerImport scanner = new ScannerImport(); epeidh to kalw keno.
    }

    public int getReadActivity() {
        return chosenActivity;
    }

    public String getNewMessage() {
        return newMessage;
    }

    //The use of while(true) in the methods is in order to navigate the client into another attempt in case he made a mistake.
    int readActivity(){
        while (true){
            try{
                chosenActivity = scanner.nextInt();
                if (chosenActivity >=0 & chosenActivity <= 10) {
                    return chosenActivity;
                }
            }catch (InputMismatchException e){
                System.out.println("You have typed the parameters wrong. Please try again to continue.\n");
                scanner.nextLine();
            }
        }
    }
    String readNewMessage(){
        while (true){
            try{
                newMessage = scanner.next();
                return newMessage;
            }catch (InputMismatchException e){
                System.out.println("You have typed the parameters wrong. Please try again to continue.\n");
                scanner.nextLine();
            }
        }
    }
    public int readNumOfMessages(){
        while (true){
            try{
                numOfMessages = scanner.nextInt();
                if (numOfMessages < 1){
                    throw new InputMismatchException();
                }
                else {
                    return numOfMessages;
                }
            }catch (InputMismatchException e){
                System.out.println("You have typed the parameters wrong. Please try again to continue.\n");
                scanner.nextLine();
            }
        }
    }
}