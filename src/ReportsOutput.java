import java.util.TreeMap;

public class ReportsOutput {

    public static MenuOutput menuOutput = new MenuOutput();

    public TreeMap<Integer, Integer> profitableProducts = new TreeMap<>();

    public void getMostProfitableProduct() {

        emptyStorageCheck();
        profitableProductsReset();

        for (String i : ReportsReader.monthFiles.keySet()) { //01_4, 02_3, 03_2, 01_5, 02_4, 03_3, 01_2
            int key = Integer.parseInt(i.substring(0, 2));
            MonthlyReport report = ReportsReader.monthFiles.get(i);
            int reportMaxProfit = report.quantity * report.sumOfOne;

            if (!report.isExpense) {
                if (reportMaxProfit > profitableProducts.get(key)) {
                    profitableProducts.put(key, reportMaxProfit);
                }
            }
        }






        System.out.println(profitableProducts);  // TODO TODO TODO
        System.out.println(ReportsReader.monthFiles.keySet()); // TODO TODO TODO

    }


/*    public void getMostExpensiveProduct() {

        emptyStorageCheck();
        profitableProductsReset();

        for (String i : ReportsReader.monthFiles.keySet()) {
            MonthlyReport report = ReportsReader.monthFiles.get(i);
            int key = Integer.parseInt(i.substring(0, 2));

            if (report.quantity * report.sumOfOne > profitableProducts.get(key)) {
                profitableProducts.put(key, report.quantity * report.sumOfOne);
                System.out.println(report.itemName); // TODO TODO TODO
            }
        }

        System.out.println(profitableProducts);  // TODO TODO TODO

    }*/






    public void emptyStorageCheck(){
        if (ReportsReader.monthFiles.size() == 0 || ReportsReader.yearFile.size() == 0) {
            System.out.println("Месячные и годовой отчеты загружены, но не обработаны." + "\n");
            menuOutput.menuPrint();
        }
    }

    public void profitableProductsReset() {
        profitableProducts.clear();

        for (int i = 0; i < ReportsReader.keysChunks.size(); i++) {
            profitableProducts.put(ReportsReader.keysChunks.get(i), 0);
        }
    }
}

