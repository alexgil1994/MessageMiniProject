import java.util.ArrayList;
import java.util.Calendar;

/*
    addMessage : insert enos new Event sthn repositoryList.
    getTempListBasedOnMessages     : epistrofh tou tempList gia tis methodous getLatestMessages kai getOldestMessages.
    getLatestMessages(int)         : epistrofh twn messages me vash ton xrono(earliest). Analoga to readNumberOfMessages stelnontai ta epithumhta messages.
    getOldestMessages(int)         : epistrofh twn messages me vash ton xrono(oldest). Analoga to readNumberOfMessages stelnontai ta epithumhta messages.
    getTempListBasedOnTime         : epistrofh tou tempList gia tis methodous getLastHourMessages, getLastThreeHoursMessages, getLastOneDayMessages, getLastThreeDaysMessages, getLastTenDaysMessages, getLastMonthMessages, getAllMessages
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

    ArrayList<Event> getTempListBasedOnQuantity(int readNumberOfMessages);
    ArrayList<Event> getLatestMessages(int readNumberOfMessages);
    ArrayList<Event> getOldestMessages(int readNumberOfMessages);
    ArrayList<Event> getTempListBasedOnTime(Calendar calendarRequest);
    ArrayList<Event> getLastHourMessages();
    ArrayList<Event> getLastThreeHoursMessages();
    ArrayList<Event> getLastOneDayMessages();
    ArrayList<Event> getLastThreeDaysMessages();
    ArrayList<Event> getLastTenDaysMessages();
    ArrayList<Event> getLastMonthMessages();
    ArrayList<Event> getAllTheMessages();
}