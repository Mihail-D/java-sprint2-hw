import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MenuOutput menuPrint = new MenuOutput();
        menuPrint.menuPrint();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int item = scanner.nextInt();
            if (!menuPrint.menuValidator(item)) {
                System.out.println("Указаны неправильные значения. Введите от 1 до 5 и 0 для выхода.");
                continue;
            }

            if (item == 1) {
                System.out.println("Считать все месячные отчёты.");
            }
            else if (item == 2) {
                System.out.println("Считать годовой отчёт.");
            }
            else if (item == 3) {
                System.out.println("Сверить отчёты.");
            }
            else if (item == 4) {
                System.out.println("Вывести информацию о всех месячных отчётах.");
            }
            else if (item == 5) {
                System.out.println("Вывести информацию о годовом отчёте.");
            }
            else if (item == 0) {
                System.out.println("Выход.");
                break;
            }


        }
    }

}

