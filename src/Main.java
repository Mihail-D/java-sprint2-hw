import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        MenuOutput menuPrint = new MenuOutput();
        ReportsReader reportsReader = new ReportsReader();
        MenuOutput.greetingsMenu();
        MenuOutput.menuPrint();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int item = scanner.nextInt();
            if (!menuPrint.menuValidator(item)) {
                continue;
            }

            if (item == 1) {
                reportsReader.rowMonthsDataReader();
            }
            else if (item == 2) {
                reportsReader.rowYearDataReader();
            }
            else if (item == 3) {
                System.out.println("Сверить отчёты.");
                ReportsComparator.getRowBalance();
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

