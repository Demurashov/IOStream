import java.io.File;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    private static String arrName[] = {"Кура", "Яйцо", "Молоко", "Хлеб", "Рыба"};//массив названий
    private static int arrPrice[] = {100, 50, 20, 10, 70};//массив цен
    public static Basket basket;
    public static File fileBasket = new File("basket.txt");

    public static void main(String args[]) {

        if (fileBasket.exists()) {
            System.out.println("Обнаружена ранее сформированная корзина:");
            basket = Basket.loadFromTxtFile(fileBasket);
            basket.printCart();
            System.out.println("Собрать заново -Y. Использовать загруженную из файла - любой символ");
            String promVar=sc.nextLine();
            if (promVar.equals("Y") || promVar.equals("y")) {
                menu();
            }else {
                System.out.println("Ваша корзина:");
                basket.printCart();
            }
        } else {
            menu();
        }
        System.out.println(("Работа программы завершена"));
    }
    //метод ввода и проверки пользовательских  данных
    public static boolean input(String str, Basket basket) {
        System.out.println(str);
        if (!sc.hasNext()) {//проверка на наличие данных в  потоке ввода
            System.out.println("Вы ничего не ввели!");
            return true;
        }
        String var = sc.nextLine();
        //проверка на ввод "end"
        if (var.equals("end")) {
            return false;
        }
        String[] promArr = var.split(" ");
        //проверка, что кол-во введенных чисел равно 2
        if (promArr.length != 2) {
            System.out.println("Нужно ввести два числа через пробел!");
            return true;
        }
        if (!Character.isDigit(promArr[0].charAt(0)) || !Character.isDigit(promArr[1].charAt(0))) {
            System.out.println("Вы ввели недопустимые символы! Введите ТОЛЬКО ЧИСЛА через пробел!");
            return true;
        }
        //проверка, что введенные номера товаров в нужном диапазоне чисел
        if (Integer.parseInt(promArr[0]) - 1 >= 0 && Integer.parseInt(promArr[0]) - 1 < arrName.length) {
            basket.addToCart(Integer.parseInt(promArr[0]) - 1, Integer.parseInt(promArr[1]));//ввод количества товаров в массив количества
        } else {
            System.out.println("Отсутсвует товар с таким номером");
        }
        return true;
    }

    //вывод меню
    public static void menu() {
        System.out.println("------------Меню----------");
        basket = new Basket(arrName, arrPrice);
        for (int i = 0; i < arrName.length; i++) {
            System.out.println("|N товара: " + (i + 1) + "| Товар: " + arrName[i] + "| Цена: " + arrPrice[i] + "|");

        }
        while (input("Для заказа введите номер товара и количество через пробел:", basket)) {
            basket.printCart();
            basket.saveTxt(fileBasket);
        }

    }
}
