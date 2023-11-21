package Project.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigOrUtil {
    public static final String url = "jdbc:postgresql://localhost:5432/postgres";
    public static final String username = "postgres";
    public static final String password = "qwerty";

    public static Connection getConnection (){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection succeed");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
