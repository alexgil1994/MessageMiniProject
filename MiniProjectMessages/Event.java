public class Event {
    private String message;
    private long time;

    public Event(String message, long time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    public void newEvent(String eventMessage, long eventTime) {
        message = eventMessage;
        time = eventTime;
        setNewEvent(message , time);
    }

    private void setNewEvent(String message , long time){
        this.time = time;
        this.message = message;
    }
}