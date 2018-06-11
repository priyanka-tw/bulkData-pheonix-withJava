import Repository.HbaseConnection;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceCheck {

    private static Connection dbConnection = HbaseConnection.getDbConnection();
    private static final String AGGREGATE_BALANCE_BY_ACCOUNT_GROUP_ID = "SELECT SUM(BALANCE), ACCOUNT_GROUP_ID FROM \n" +
            "TEST_DATA GROUP BY ACCOUNT_GROUP_ID"; // WHERE account_group_id ='111'

    private static final String AGGREGATE_BALANCE_BY_ACCOUNT_TYPE = "SELECT SUM(BALANCE),ACCOUNT_TYPE FROM " +
            "TEST_DATA GROUP BY ACCOUNT_TYPE";

    private static final String AGGREGATE_BALANCE_WITH_L1L2_ACCOUNT_TYPE = "SELECT SUM(BALANCE), ACCOUNT_TYPE FROM " +
            "(SELECT * FROM TEST_DATA WHERE ASSET_CLASS_L1 = 'CASH' AND ASSET_CLASS_L2 ='CASH_EQUIVALANT') " +
            "GROUP BY ACCOUNT_TYPE";

    private static final String ORDER_BY_DATE = "SELECT SUM(BALANCE),TEST_DATE FROM " +
            "TEST_DATA GROUP BY TEST_DATE ORDER BY TEST_DATE";

    public void doPerfCheck() {

        executeQuery(AGGREGATE_BALANCE_BY_ACCOUNT_GROUP_ID);
        executeQuery(AGGREGATE_BALANCE_BY_ACCOUNT_TYPE);
        executeQuery(AGGREGATE_BALANCE_WITH_L1L2_ACCOUNT_TYPE);
        executeQuery(ORDER_BY_DATE);
    }

    private void executeQuery(String query) {

        long startTime = System.currentTimeMillis();
        PreparedStatement ps = null;
        try {
            ps = dbConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("Table Values");

            while (rs.next()) {
                Object myKey = rs.getObject(1);
                Object myColumn = rs.getObject(2);
                System.out.println("\tRow: " + myKey + " : " + myColumn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(query);
        System.out.println("MILLS TAKEN::::" + (endTime - startTime));
    }
}
