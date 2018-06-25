package Repository;

import java.sql.*;

public class HbaseConnection {

    private volatile static Connection connection;

    public static Connection getDbConnection() {
        try {
            if (connection == null) {
                synchronized (Connection.class) {
                    if (connection == null) {

                        Class.forName("org.apache.phoenix.queryserver.client.Driver");
                        connection = DriverManager.getConnection
                                ("jdbc:phoenix:thin:url=http://ec2-18-191-186-68.us-east-2.compute.amazonaws.com:8765;serialization=PROTOBUF");

                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeDbConnection(Connection innerConnection) {

        if (innerConnection != null) {
            try {
                innerConnection.close();
            } catch (Exception e) {
            }
        }
        connection = null;
    }
}
