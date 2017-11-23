import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Creating an object and ArrayList<Event> for the RepositoryDb.
        RepositoryDb repositoryDb = new RepositoryDb();
        ArrayList<Event> repositoryDbList = new ArrayList<>();


        // TODO NA DW AN ONTWS XREIAZOMAI TO LOAD TWN EVENTS EDW(sto ksekinhma tou programmatos).

        // Starting a Connection with the DB.
        repositoryDb.open();

        // Calling the method that loads the Events from the DB.
        repositoryDb.queryLoadDb();

        // Closing the Connection with the DB since i finished with the query that loads the Events.
        repositoryDb.close();

        // The requests from the user start through a new Controller object.
        Controller controller = new Controller();
        controller.handleRequestedActivity();
    }
}
