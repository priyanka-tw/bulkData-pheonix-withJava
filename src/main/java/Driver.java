import Repository.InsertDataInDb;

public class Driver {

    public static void main(String args[]) {

        final int QUERY_COUNT = 10000;

        String nQueries = QueryGenerator.generateNQueries(QUERY_COUNT);
        InsertDataInDb.insertBulkData(nQueries);
        FileGenerator.writePhoenixData(nQueries);
    }
}
