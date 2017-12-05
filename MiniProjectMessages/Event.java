import java.util.Comparator;

public class Event {
    private String message;
    private long time;

    public Event(String message, long time) {
        this.message = message;
        this.time = time;
    }

    // Comparator-Earliest for the two Repositories.
    static final Comparator<Event> EARLIEST_ORDER = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            // Kanei ta 3 if gia 1 , -1, 0 se mia grammh.
            return Long.compare(e2.getTime(), e1.getTime());
        }
    };

    // Comparator-Latest for the two Repositories.
    static final Comparator<Event> LATEST_ORDER = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            // Kanei ta 3 if gia 1 , -1, 0 se mia grammh.
            return Long.compare(e1.getTime(), e2.getTime());
        }
    };

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

}