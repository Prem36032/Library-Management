import java.sql.*;

public class DatabaseConnection {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USERNAME = "Prem";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
            return null;
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Connected to Oracle database successfully.");
            } else {
                System.out.println("Failed to connect to Oracle database.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}