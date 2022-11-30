import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        MenuOutput menuOutput = new MenuOutput();
        ReportsReader reportsReader = new ReportsReader();
        ReportsOutput reportsWriter = new ReportsOutput();
        ReportsComparator reportsComparator = new ReportsComparator();

        menuOutput.greetingsMenu();
        menuOutput.menuPrint();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int item;

            try {
                item = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введенные значения не являются числами.");
                scanner.next();
                continue;
            }

            if (!menuOutput.menuValidator(item)) {
                continue;
            }

            if (item == 1) {
                reportsReader.rowMonthsDataReader();
            }
            else if (item == 2) {
                reportsReader.rowYearDataReader();
            }
            else if (item == 3) {
                reportsComparator.getRowBalance();
                reportsComparator.getFineBalance();
            }
            else if (item == 4) {
                reportsWriter.getMostProfitableProduct();
                reportsWriter.getMostExpensiveProduct();
            }
            else if (item == 5) {
                reportsWriter.getAnnualReport();
            }
            else if (item == 6) {
                menuOutput.menuPrint();
            }
            else if (item == 0) {
                System.out.println("Выход.");
                break;
            }
        }
    }
}

