package Repository;

import java.sql.*;

public class HbaseConnection {

    private volatile static Connection connection;

    public static Connection getDbConnection() throws ClassNotFoundException {
        try {
            if (connection == null) {
                synchronized (Connection.class) {
                    if (connection == null) {

                        Class.forName("org.apache.phoenix.queryserver.client.Driver");
                        connection = DriverManager.getConnection
                                ("jdbc:phoenix:thin:url=http://ec2-18-191-185-255.us-east-2.compute.amazonaws.com:8765;serialization=PROTOBUF");

//                        connection = DriverManager.getConnection
//                                ("jdbc:phoenix:ec2-18-191-185-255.us-east-2.compute.amazonaws.com:");
                    }
                }
            }

            System.out.println("=========connection::" + connection);
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
