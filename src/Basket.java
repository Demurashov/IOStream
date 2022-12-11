import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Basket {
    private String[] arrName;
    private int[] arrPrice;
    private int[] arrKol;

    static Basket loadFromTxtFile(File textFile) {
        ArrayList<String> listStr = new ArrayList<>(3);
        Basket basket = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            String promVar;
            while ((promVar = reader.readLine()) != null) {
                listStr.add(promVar);
            }

            String[] promArrName = listStr.get(0).split(",");//промежуточный массив строк наименований
            String[] promArrPrice = listStr.get(1).split(",");//промежуточный массив строк цен
            int[] arrPrice = new int[promArrPrice.length];//интовый массив цен
            //перегонка стрингов в инты
            for (int i = 0; i < promArrPrice.length; i++) {
                arrPrice[i] = Integer.parseInt(promArrPrice[i]);
            }
            String[] promArrKol = listStr.get(2).split(",");//промежуточный массив строк цен
            int[] arrKol = new int[promArrKol.length];//интовый массив кол-ва
            //перегонка стрингов в инты
            for (int i = 0; i < promArrKol.length; i++) {
                arrKol[i] = Integer.parseInt(promArrKol[i]);
            }
            basket = new Basket(promArrName, arrPrice);
            System.out.println(Arrays.toString(promArrName) + "\n" +
                    Arrays.toString(arrPrice) + "\n" +
                    Arrays.toString(arrKol) + "\n");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return basket;
    }

    public Basket(String[] arrName, int[] arrPrice) {
        this.arrName = arrName;
        this.arrPrice = arrPrice;
        arrKol = new int[arrPrice.length];
    }

    public void addToCart(int productNum, int amount) {
        arrKol[productNum] += amount;//ввод количества товаров в массив количества

    }

    //метод обработки и печати массивов
    public void printCart() {
        int summ = 0;
        for (int i = 0; i < arrKol.length; i++) {
            summ += arrKol[i] * arrPrice[i];
        }
        if (summ > 0) {
            System.out.println("____________________Корзина____________________:");
            for (int i = 0; i < arrKol.length; i++) {
                if (arrKol[i] > 0) {
                    System.out.println("|Наименование: " + arrName[i] + "| Цена: " + arrPrice[i] + "| Кол-во: " + arrKol[i] +
                            "| Сумма за позицию: " + (arrKol[i] * arrPrice[i]));
                }
            }
            System.out.println("___________________________________________________Итого к оплате: " + summ);
        }
    }

    @Override
    public String toString() {
        String aN = "", aP = "", aK = "";
        for (String item : arrName) {
            aN += item + ",";
        }
        for (int item : arrPrice) {
            aP += item + ",";
        }
        for (int item : arrKol) {
            aK += item + ",";
        }
        return aN + "\n" + aK + "\n" + aP;
    }

    public void saveTxt(File textFile) {
        try (FileWriter writer = new FileWriter(textFile, false)) {
            writer.write(toString());
            writer.flush();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
