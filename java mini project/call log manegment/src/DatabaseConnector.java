import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/call_log_db"; // MySQL DB name
        String user = "root";  // MySQL username
        String password = "password";  // MySQL password

        // Return the connection object
        return DriverManager.getConnection(url, user, password);
    }
}
