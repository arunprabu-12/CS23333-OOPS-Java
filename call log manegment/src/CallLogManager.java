import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CallLogManager {
    private static final String URL = "jdbc:mysql://localhost:3306/CallLogDB"; // DB connection URL
    private static final String USER = "root";  // MySQL username
    private static final String PASSWORD = "SKy@12345";  // MySQL password

    public static void main(String[] args) {
        // Try to connect to the database
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to MySQL database!");

            Scanner scanner = new Scanner(System.in);
            OUTER:
            while (true) {
                System.out.println("1. Add Call Log");
                System.out.println("2. View All Call Logs");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                // Handle user's choice
                switch (option) {
                    case 1 -> addCallLog(conn, scanner);  // Add call log to the database
                    case 2 -> viewAllCallLogs(conn);  // View all call logs
                    case 3 -> {
                        System.out.println("Exited");
                        break OUTER; // Exit the program
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    // Method to add a new call log to the database
    private static void addCallLog(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter caller name: ");
        String callerName = scanner.next();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter call date (YYYY-MM-DD HH:MM:SS): ");
        scanner.nextLine();  // Consume the newline character
        String callDate = scanner.nextLine();  // Read the full date and time
        System.out.print("Enter duration in seconds: ");
        int duration = scanner.nextInt();
        System.out.print("Enter call type (Incoming/Outgoing/Missed): ");
        String callType = scanner.next();

        // SQL query to insert the call log
        String sql = "INSERT INTO call_logs (caller_name, phone_number, call_date, duration, call_type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, callerName);
            pstmt.setString(2, phoneNumber);
            pstmt.setString(3, callDate);
            pstmt.setInt(4, duration);
            pstmt.setString(5, callType);
            pstmt.executeUpdate();
            System.out.println("Call log added successfully!");
        }
    }

    // Method to view all call logs from the database
    private static void viewAllCallLogs(Connection conn) throws SQLException {
        String sql = "SELECT * FROM call_logs";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Caller: %s, Number: %s, Date: %s, Duration: %d sec, Type: %s%n",
                        rs.getInt("call_id"), rs.getString("caller_name"), rs.getString("phone_number"),
                        rs.getTimestamp("call_date"), rs.getInt("duration"), rs.getString("call_type"));
            }
        }
    }
}

