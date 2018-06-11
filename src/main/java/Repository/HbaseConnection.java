package Repository;

import java.sql.*;

public class HbaseConnection {

    private volatile static Connection connection;

    public static Connection getDbConnection() {
        try {
            if (connection == null) {
                synchronized (Connection.class) {
                    if (connection == null) {
                        connection = DriverManager.getConnection("jdbc:phoenix:localhost:");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection closeDbConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        return null; // need to work on this
    }
}
