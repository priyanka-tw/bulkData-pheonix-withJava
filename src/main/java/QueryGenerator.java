public class QueryGenerator {

    public static final String QUERY_PREFIX = "UPSERT INTO TEST_DATA VALUES (";

    private static String generateQuery() {
        return QUERY_PREFIX + new DataPojo().toString() + ");";
    }

    public static String generateNQueries(int count) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(generateQuery());
        }
        return sb.toString();
    }

}
