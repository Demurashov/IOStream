import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientLog {
    private ArrayList<int[]> logList = new ArrayList<>();

    public void log(int productNum, int amount) {
        //String strLog=Integer.toString(productNum)+
        int[] logArr = {productNum, amount};
        logList.add(logArr);
        //return logList;
    }

    public void exportAsCSV(File txtFile) {
        try (CSVWriter writer=new CSVWriter(new FileWriter((txtFile)))){

        }

    }

    public String print() {
        String arrStr = "";
        for (int[] item : logList) {
            arrStr += Arrays.toString(item);

        }
        //System.out.println(arrStr);
        return arrStr;
    }

}

