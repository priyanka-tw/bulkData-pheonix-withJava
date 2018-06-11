package Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataInDb {

    private static Connection dbConnection = HbaseConnection.getDbConnection();

    private static final String CREATE_QUERY = "create table IF NOT EXISTS TEST_DATA (\n" +
            "  ACCOUNT_KEY      integer NOT NULL PRIMARY KEY,\n" +
            "  ACCOUNT_TYPE     VARCHAR(100),\n" +
            "  ACCOUNT_NUMBER   integer,\n" +
            "  ASSET_CLASS_L1      VARCHAR(100),\n" +
            "  ASSET_CLASS_L2      VARCHAR(100),\n" +
            "  ACCOUNT_GROUP_ID VARCHAR(100),\n" +
            "  BALANCE          DECIMAL,\n" +
            "  CURRENCY         VARCHAR(100)\n" +
            ")";

    private static void createTable() {
        Statement statement = null;
        try {

            statement = dbConnection.createStatement();
            statement.executeUpdate(CREATE_QUERY);
            dbConnection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBulkData(String query) {
        Statement statement;

        try {
            createTable();
            statement = dbConnection.createStatement();

            for (String singleQuery : query.split(";")) {
                statement.addBatch(singleQuery);
            }
            statement.executeBatch();
            dbConnection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
