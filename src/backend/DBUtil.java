package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hrms";
        String user = "root";
        String password = "yourpassword";
        return DriverManager.getConnection(url, user, password);
    }
}
