public class Main {
    public static void main(String[] args) {
        RepositoryDb repositoryDb = new RepositoryDb();

        // The requests from the user start through a new Controller object.
        Controller controller = new Controller();
        controller.handleRequestedActivity();

        // Closing the Connection with the DB since i finished with the query that loads the Events.
        repositoryDb.close();

    }
}
