import java.util.TreeMap;

public class ReportsOutput {

    public static MenuOutput menuOutput = new MenuOutput();

    public TreeMap<Integer, MonthlyReport> profitableProducts = new TreeMap<>();

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
            //menuOutput.menuPrint();
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

    }

    public void emptyStorageCheck() {

        if (ReportsReader.monthFiles.size() == 0 || ReportsReader.yearFile.size() == 0) {
            menuOutput.menuPrint();
        }

    }

    public void profitableProductsReset() {
        profitableProducts.clear();
        for (int i = 0; i < ReportsReader.keysChunks.size(); i++) {
            profitableProducts.put(ReportsReader.keysChunks.get(i), new MonthlyReport("itemName", true,
                    1, 1, ""
            ));
        }
    }
}

