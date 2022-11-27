import java.util.TreeMap;

public class ReportsOutput {

    public static MenuOutput menuOutput = new MenuOutput();

    public TreeMap<Integer, Integer> profitableProducts = new TreeMap<>();

    public void getMostProfitableProduct() {

        if (ReportsReader.monthFiles.size() == 0 || ReportsReader.yearFile.size() == 0) {
            System.out.println("Месячные и годовой отчеты загружены, но не обработаны." + "\n");
            menuOutput.menuPrint();
        }

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

    }




    public void profitableProductsReset() {
        profitableProducts.clear();

        for (int i = 0; i < ReportsReader.keysChunks.size(); i++) {
            profitableProducts.put(Integer.valueOf(String.valueOf(ReportsReader.keysChunks.get(i))), 0);
        }
    }
}

