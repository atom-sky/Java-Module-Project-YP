import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> items = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        System.out.println("Здравствуйте! Сколько гостей присутствовало?: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Ошибка! Введите положительное целое число: ");
        }
        int number = scanner.nextInt();
        while (number <= 1) {
            if (number == 1) {
                System.out.println("Ошибка! Введите число больше 1: ");
            } else {
                System.out.println("Ошибка! Вы ввели число меньше 1. Введите количество гостей: ");
            }
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Ошибка! Введите положительное целое число: ");
            }
            number = scanner.nextInt();
        }
        System.out.println("Спасибо! Количество гостей: " + number);

        scanner.nextLine();

        double total = 0.0;
        boolean continueAddingItems = true;
        while (continueAddingItems) {
            System.out.println("Введите название товара (для завершения введите 'Завершить'): ");
            String itemName = scanner.nextLine();

            if (itemName.equalsIgnoreCase("Завершить")) {
                continueAddingItems = false;
            } else if (!itemName.matches("[а-яА-ЯёЁ\\s]+")) {
                System.out.println("Ошибка! Название товара может содержать только кириллицу. Начнём заново.");
                continue;
            } else {
                items.add(itemName);

                System.out.println("Вы успешно добавили товар. \nВведите цену товара с копейками (например, 07.50, 10.05): ");
                String priceString = scanner.nextLine();

                if (!priceString.matches("\\d+(\\.\\d{2})")) {
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



        System.out.println("\nОбщая стоимость товаров: " + formatRubles(total));
        double paymentPerGuest = total / number;

        System.out.println("Каждый гость должен заплатить: " + formatRubles(paymentPerGuest));
    }

    public static String formatRubles(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String amountStr = decimalFormat.format(amount);

        int rubles = (int) amount;
        int lastTwoDigits = rubles % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return amountStr + " рублей";
        }

        int lastDigit = lastTwoDigits % 10;

        String rublesStr;

        if (lastDigit == 1) {
            rublesStr = amountStr + " рубль";
        } else if (lastDigit >= 2 && lastDigit <= 4) {
            rublesStr = amountStr + " рубля";
        } else {
            rublesStr = amountStr + " рублей";
        }

        return rublesStr;
    }
}