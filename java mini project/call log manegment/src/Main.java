import java.util.List;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // Creating an instance of CallLogManager
        CallLogManager manager = new CallLogManager();

        // Adding a new call log
        CallLog newCall = new CallLog();
        newCall.setCallerNumber("1234567890");
        newCall.setCallDuration(300);  // 5 minutes
        newCall.setCallType("Incoming");
        newCall.setCallTimestamp(new Date());

        manager.addCallLog(newCall);  // Add the log to the database

        // Retrieving all call logs from the database
        List<CallLog> callLogs = manager.getAllCallLogs();
        for (CallLog log : callLogs) {
            System.out.println("Call ID: " + log.getCallId() + ", Caller: " + log.getCallerNumber());
        }
    }
}
