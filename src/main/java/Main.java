import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> items = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        System.out.print("Здравствуйте! Сколько людей откушало?: ");
        int number = scanner.nextInt();
        while (number <= 1) {
            if (number == 1) {
                System.out.println("Введите число больше 1: ");
            } else {
                System.out.println("Вы ввели число меньше 1. Введите количество гостей: ");
            }
            number = scanner.nextInt();
        }
        System.out.println("Спасибо! Количество гостей: " + number);

        scanner.nextLine();

        double total = 0.0;
        boolean continueAddingItems = true;
        while (continueAddingItems) {
            System.out.print("Введите название товара (для завершения введите 'Завершить'): ");
            String itemName = scanner.nextLine();

            if (itemName.equalsIgnoreCase("Завершить")) {
                continueAddingItems = false;
            } else if (!itemName.matches("[а-яА-ЯёЁ\\s]+")) {
                System.out.println("Ошибка! Название товара может содержать только кириллицу. Начнём заново.");
                continue;
            } else {
                items.add(itemName);

                System.out.print("Вы успешно добавили товар. \nВведите цену товара с копейками (например, 07.50, 10.05): ");
                String priceString = scanner.nextLine();

                if (!priceString.matches("\\d+(\\.\\d{2})?")) {
                    System.out.println("Ошибка! Стоимость нужно ввести с копейками (например, 07.50, 10.05). Начнём заново ");
                    continue;
                }

                double price = Double.parseDouble(priceString);
                prices.add(price);

                total += price;
            }
        }

        System.out.println("\nСписок добавленных товаров: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i) + ": " + prices.get(i));
        }

        System.out.println("\nОбщая стоимость товаров: " + String.format("%.2f", total));
        double paymentPerGuest = total / number;

        System.out.println("Каждый гость должен заплатить: " + String.format("%.2f", paymentPerGuest));
    }
}
