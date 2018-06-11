import java.io.FileWriter;

public class FileGenerator {

    static void writePhoenixData(String query) {
        try {
            FileWriter fw = new FileWriter("phoenixData.sql");
            fw.write(query);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

