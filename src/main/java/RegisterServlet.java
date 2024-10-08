import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    
    // Database connection settings
    private String jdbcUrl = "jdbc:mysql://localhost:3306/api_portal";  // Your database URL
    private String dbUser = "khushi";  // Database username
    private String dbPassword = "Admin@1234";  // Database password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        // Validate input
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            response.getWriter().println("All fields are required!");
            return;
        }

        // Connect to the database and insert user data
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        
        try {
            
        	  Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			// Create a PreparedStatement to insert the user data
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);  // Username from the form
                ps.setString(2, password);  // Password from the form
                ps.setString(3, email);     // Email from the form
                int rowsInserted = ps.executeUpdate();
                
                if (rowsInserted > 0) {
                    response.getWriter().println("A new user was inserted successfully!");
                } else {
                    response.getWriter().println("Failed to insert user.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log any SQL exceptions
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
