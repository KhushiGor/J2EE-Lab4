import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApiKeyRepository {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/api_portal", "root", "Admin@1234");
    }

    public List<ApiKey> getApiKeys(int userId) throws SQLException {
        String sql = "SELECT * FROM api_keys WHERE user_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            List<ApiKey> apiKeys = new ArrayList<>();
            while (rs.next()) {
                ApiKey apiKey = new ApiKey();
                apiKey.setId(rs.getInt("id"));
                apiKey.setUserId(rs.getInt("user_id"));
                apiKey.setApiKey(rs.getString("api_key"));
                apiKey.setStatus(rs.getString("status"));
                apiKeys.add(apiKey);
            }
            return apiKeys;
        }
    }

    public void generateApiKey(int userId) throws SQLException {
        String apiKey = "key_" + System.currentTimeMillis(); // Simple key generation
        String sql = "INSERT INTO api_keys (user_id, api_key) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, apiKey);
            stmt.executeUpdate();
        }
    }

    public void deactivateApiKey(int apiKeyId) throws SQLException {
        String sql = "UPDATE api_keys SET status = 'deactivated' WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, apiKeyId);
            stmt.executeUpdate();
        }
    }
}
