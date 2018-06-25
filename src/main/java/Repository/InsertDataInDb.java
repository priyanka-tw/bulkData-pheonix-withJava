package Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class InsertDataInDb {

    private static Connection dbConnection = HbaseConnection.getDbConnection();


    private static final String CREATE_QUERY = "create table IF NOT EXISTS TEST_DATA (\n" +
            "  ACCOUNT_KEY      integer NOT NULL PRIMARY KEY,\n" +
            "  ACCOUNT_TYPE     VARCHAR(100),\n" +
            "  ACCOUNT_NUMBER   integer,\n" +
            "  ASSET_CLASS_L1   VARCHAR(100),\n" +
            "  ASSET_CLASS_L2   VARCHAR(100),\n" +
            "  ACCOUNT_GROUP_ID VARCHAR(100),\n" +
            "  BALANCE          DECIMAL,\n" +
            "  CURRENCY         VARCHAR(100),\n" +
            "  TEST_DATE        DATE \n" +
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
        final int BATCH_SIZE = 5000;

        try {
            createTable();
            statement = dbConnection.createStatement();
            createQueryBatch(Arrays.asList(query.split(";")), BATCH_SIZE, statement);

            HbaseConnection.closeDbConnection(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createQueryBatch(List<String> queries, int batchSize, Statement statement) throws SQLException {

        if (batchSize > queries.size()) {
            batchSize = queries.size();
        }
        for (String singleQuery : queries.subList(0, batchSize)) {
            statement.addBatch(singleQuery);
        }
        statement.executeBatch();
        dbConnection.commit();

        if (batchSize >= queries.size()) return;
        int lastIndex = queries.size();

        List<String> remainingQueries = queries.subList(batchSize, lastIndex);
        createQueryBatch(remainingQueries, batchSize, statement);
    }

}
