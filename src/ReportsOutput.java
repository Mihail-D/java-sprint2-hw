import java.util.HashMap;
import java.util.TreeMap;

public class ReportsOutput {

    public static MenuOutput menuOutput = new MenuOutput();

    public TreeMap<Integer, MonthlyReport> profitableProducts = new TreeMap<>();
    public TreeMap<Integer, AnnualReport> yearExpensesList = new TreeMap<>();
    public TreeMap<Integer, AnnualReport> yearIncomesList = new TreeMap<>();

    HashMap<String, AnnualReport> report = ReportsReader.yearFile;

    public void getMostProfitableProduct() {

        profitableProductsReset();

        for (String i : ReportsReader.monthFiles.keySet()) {
            int key = Integer.parseInt(i.substring(0, 2));
            MonthlyReport report = ReportsReader.monthFiles.get(i);
            int reportMaxProfit = report.quantity * report.sumOfOne;

            if (!report.isExpense) {
                if (reportMaxProfit > profitableProducts.get(key).sumOfOne * profitableProducts.get(key).quantity) {
                    profitableProducts.put(key, report);
                }
            }
        }

        if (ReportsReader.monthFiles.size() == 0) {
            System.out.println("Месячный отчет загружен, но не обработан.");
        }
        else {
            System.out.println("Самые большие доходы по каждому месяцу составили:" + "\n");
            for (Integer i : profitableProducts.keySet()) {
                int key = Integer.parseInt(profitableProducts.get(i).keyName.substring(0, 2));
                System.out.print(menuOutput.months[key - 1] + ": ");
                System.out.print(profitableProducts.get(i).itemName + " ");
                System.out.println(profitableProducts.get(i).quantity * profitableProducts.get(i).sumOfOne + "руб.");
            }
        }

    }

    public void getMostExpensiveProduct() {

        profitableProductsReset();

        for (String i : ReportsReader.monthFiles.keySet()) {
            int key = Integer.parseInt(i.substring(0, 2));
            MonthlyReport report = ReportsReader.monthFiles.get(i);
            int reportMaxProfit = report.quantity * report.sumOfOne;

            if (report.isExpense) {
                if (reportMaxProfit > profitableProducts.get(key).sumOfOne * profitableProducts.get(key).quantity) {
                    profitableProducts.put(key, report);
                }
            }
        }

        if (ReportsReader.yearFile.size() == 0) {
            System.out.println("Годовой отчет загружен, но не обработан." + "\n");
        }
        else {

            System.out.println();
            System.out.println("Самые большие траты по каждому месяцу составили:");

            for (Integer i : profitableProducts.keySet()) {
                int key = Integer.parseInt(profitableProducts.get(i).keyName.substring(0, 2));
                System.out.print(menuOutput.months[key - 1] + ": ");
                System.out.print(profitableProducts.get(i).itemName + " ");
                System.out.println(profitableProducts.get(i).quantity * profitableProducts.get(i).sumOfOne + " руб. ");
            }
        }
    }

    public void getAnnualReport() {

        if (report.size() == 0) {
            System.out.println("Годовой отчет загружен, но не обработан." + "\n");
        }
        else {
            System.out.println("Отчет за " + ReportsReader.yearPointer + " год.");

            getAnnualReportSplit();

            getAnnualBalanceOut();

            getAnnualAverageIncome();

            getAnnualAverageExpenses();

        }

    }

    public void getAnnualAverageIncome() {
        int total = 0;

        for (Integer i : yearIncomesList.keySet()) {
            total += yearIncomesList.get(i).amount;
        }

        System.out.println("Среднегодовые доходы составили " + (total / 12) + "руб. в месяц" + "\n");
    }

    public void getAnnualAverageExpenses() {
        int total = 0;

        for (Integer i : yearExpensesList.keySet()) {
            total += yearExpensesList.get(i).amount;
        }

        System.out.println("Среднегодовые расходы составили " + (total / 12) + "руб. в месяц" + "\n");
    }

    public void getAnnualBalanceOut() {
        for (Integer key : yearIncomesList.keySet()) {
            int balance = yearIncomesList.get(key).amount - yearExpensesList.get(key).amount;
            String month = menuOutput.months[yearIncomesList.get(key).month - 1];
            System.out.print("За " + month + " ");

            if (balance > 0) {
                System.out.println("получен " + balance + "руб. доход.");
            }
            else {
                System.out.println("получены " + balance + "руб. убытки.");
            }
        }
        System.out.println();
    }
//разделение по доходам и расходам в симметричные коллекции для расчетов
    public void getAnnualReportSplit() {
        for (String i : report.keySet()) {
            if (report.get(i).isExpense) {
                yearExpensesList.put(report.get(i).month, report.get(i));
            }
            else {
                yearIncomesList.put(report.get(i).month, report.get(i));
            }
        }
    }
// сброс данных коллекции при временных расчетах
    public void profitableProductsReset() {
        profitableProducts.clear();
        for (int i = 0; i < ReportsReader.keysChunks.size(); i++) {
            profitableProducts.put(ReportsReader.keysChunks.get(i), new MonthlyReport("itemName", true,
                    1, 1, ""
            ));
        }
    }
}

