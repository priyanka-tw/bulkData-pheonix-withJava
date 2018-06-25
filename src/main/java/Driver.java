import Repository.InsertDataInDb;

public class Driver {

    public static void main(String args[]) {

        final int QUERY_COUNT = 4999;

//        String nQueries = QueryGenerator.generateNQueries(QUERY_COUNT);
//        System.out.println("queries generated");
//
//        InsertDataInDb.insertBulkData(nQueries);
//        System.out.println("data inserted");

//        FileGenerator.writePhoenixData(nQueries);
//
        new PerformanceCheck().doPerfCheck();

    }
}
