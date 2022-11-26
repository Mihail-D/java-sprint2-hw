import java.util.HashMap;

public class ReportsComparator {

    public static void getRowBalance() {
        HashMap<Integer, Integer> monthsBalanceStorage = new HashMap<>();

        if (ReportsReader.monthFiles.size() == 0 || ReportsReader.yearFile.size() == 0) {
            System.out.println("Месячные и годовой отчеты загружены, но не обработаны." + "/n");
            MenuOutput.menuPrint();
        }

        for (Integer j : ReportsReader.keysChunks) {
            monthsBalanceStorage.put(j, 0);
            monthsBalanceStorage.put(-j, 0);
        }

        for (String value : ReportsReader.monthFiles.keySet()) {
            int operationsSum =
                    ReportsReader.monthFiles.get(value).sumOfOne * ReportsReader.monthFiles.get(value).quantity;
            int valueId = Integer.parseInt(value.substring(0, 2));

            if (monthsBalanceStorage.containsKey(valueId) && ReportsReader.monthFiles.get(value).isExpense) {
                monthsBalanceStorage.merge(-valueId, operationsSum, Integer::sum);
            }
            else if (monthsBalanceStorage.containsKey(valueId) && !(ReportsReader.monthFiles.get(value).isExpense)) {
                monthsBalanceStorage.merge(valueId, operationsSum, Integer::sum);
            }
        }

    }

}
