import Repository.HbaseConnection;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PerformanceCheck {

    private static Connection dbConnection = HbaseConnection.getDbConnection();
    private static final String AGGREGATE_BALANCE_BY_ACCOUNT_GROUP_ID = "Select sum(BALANCE),account_group_id from " +
            "test_data group by ACCOUNT_GROUP_ID"; // WHERE account_group_id ='111'
    private static final String AGGREGATE_BALANCE_BY_ACCOUNT_TYPE = "Select sum(BALANCE),ACCOUNT_TYPE from " +
            "test_data group by ACCOUNT_TYPE";

    public void doPerfCheck() {
        executeQuery(AGGREGATE_BALANCE_BY_ACCOUNT_GROUP_ID);
        executeQuery(AGGREGATE_BALANCE_BY_ACCOUNT_TYPE);
        
    }

    private void executeQuery(String query) {

        System.out.println("START:::::" + new Date());
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
        System.out.println("END:::::" + new Date());
    }
}
