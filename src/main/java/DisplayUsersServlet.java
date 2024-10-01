
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class DisplayUsersServlet
 */
public class DisplayUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Use the UserRepository to fetch all users from the database
        UserRepository userRepository = new UserRepository();
        try {
            List<User> users = userRepository.getAllUsers();
            // Set the list of users as a request attribute
            request.setAttribute("users", users);
            // Forward the request to the JSP page for rendering
            request.getRequestDispatcher("display-users.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
