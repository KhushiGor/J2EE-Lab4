import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    // Handle POST request for login
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate login with UserRepository
        UserRepository userRepository = new UserRepository();
        User user = null;
        try {
            user = userRepository.authenticateUser(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the user exists and credentials are valid
        if (user != null) {
            // Login successful, create a session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect to the dashboard or any protected page
            response.sendRedirect("dashboard.html");
        } else {
            // Login failed, redirect back to the login page with an error message
            response.sendRedirect("login.html");
        }
    }

    // Optionally handle GET request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
