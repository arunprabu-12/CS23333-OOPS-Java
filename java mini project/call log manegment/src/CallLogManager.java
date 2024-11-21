import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CallLogManager {

    // Method to add a new call log
    public void addCallLog(CallLog callLog) {
        String query = "INSERT INTO CallLog (caller_number, call_duration, call_type, call_timestamp) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, callLog.getCallerNumber());
            stmt.setInt(2, callLog.getCallDuration());
            stmt.setString(3, callLog.getCallType());
            stmt.setTimestamp(4, new Timestamp(callLog.getCallTimestamp().getTime()));

            stmt.executeUpdate();
            System.out.println("Call log added successfully!");

        } catch (SQLException e) {
        }
    }

    // Method to get all call logs
    public List<CallLog> getAllCallLogs() {
        List<CallLog> callLogs = new ArrayList<>();
        String query = "SELECT * FROM CallLog";
        
        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                CallLog callLog = new CallLog();
                callLog.setCallId(rs.getInt("call_id"));
                callLog.setCallerNumber(rs.getString("caller_number"));
                callLog.setCallDuration(rs.getInt("call_duration"));
                callLog.setCallType(rs.getString("call_type"));
                callLog.setCallTimestamp(rs.getTimestamp("call_timestamp"));
                
                callLogs.add(callLog);
            }
        } catch (SQLException e) {
           
        }
        return callLogs;
    }
}

