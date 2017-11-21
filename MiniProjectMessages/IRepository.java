import java.util.ArrayList;

/*
    addMessage : insert enos new Event sthn repositoryList.
    getLatestMessages(int)         : epistrofh twn messages me vash ton xrono(earliest). Analoga to readNumberOfMessages stelnontai ta epithumhta messages.
    getOldestMessages(int)         : epistrofh twn messages me vash ton xrono(oldest). Analoga to readNumberOfMessages stelnontai ta epithumhta messages.
    getLastHourMessages(int)       : epistrofh twn messages ths teleutaias wras.
    getLastThreeHoursMessages(int) : epistrofh twn messages twn 3 teleutaiwn wrwn..
    getLastOneDayMessages(int)     : epistrofh twn messages ths teleutaias hmeras.
    getLastThreeDaysMessages(int)  : epistrofh twn messages apo tis teleutaies 3 hmeres.
    getLastTenDaysMessages(int)    : epistrofh twn messages apo tis teleutaies 10 hmeres.
    getLastMonthMessages(int)      : epistrofh twn messages apo ton teleutaio mhna.
    getAllMessages(int)            : epistrofh olwn twn messages.
*/

public interface IRepository {
    void addMessage(String messageEvent , long timeEvent);

    ArrayList<Event> getLatestMessages(int readNumberOfMessages);
    ArrayList<Event> getOldestMessages(int readNumberOfMessages);
    ArrayList<Event> getLastHourMessages(long time);
    ArrayList<Event> getLastThreeHoursMessages(long time);
    ArrayList<Event> getLastOneDayMessages(long time);
    ArrayList<Event> getLastThreeDaysMessages(long time);
    ArrayList<Event> getLastTenDaysMessages(long time);
    ArrayList<Event> getLastMonthMessages(long time);
    ArrayList<Event> getAllTheMessages();
}