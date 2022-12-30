import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientLog {
    private ArrayList<String[]> logList = new ArrayList<>();

    public void log(int productNum, int amount) {
        //String strLog=Integer.toString(productNum)+
        String[] logArr = {Integer.toString(productNum), Integer.toString(amount)};
        logList.add(logArr);
        //return logList;
    }

    public void exportAsCSV(File txtFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter((txtFile)))) {
            for (String[] item : logList) {
                writer.writeNext(item);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String print() {
        String arrStr = "";
        for (String[] item : logList) {
            arrStr += Arrays.toString(item);

        }
        //System.out.println(arrStr);
        return arrStr;
    }

}

