import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException {
        // Database credentials
        String jdbcUrl = "jdbc:mysql://localhost:3306/api_portal";  
        String jdbcUser = "root";
        String jdbcPass = "Admin@1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
            // Do your work
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // Handle missing driver class
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL errors
        }

        // Open a connection
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
    }
}