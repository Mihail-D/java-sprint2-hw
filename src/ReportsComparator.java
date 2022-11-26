import java.util.HashMap;

public class ReportsComparator {

    static HashMap<Integer, Integer> monthsBalanceStorage = new HashMap<>();

    public static void getRowBalance() {

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

    public static void getFineBalance() {

        for (String item : ReportsReader.yearFile.keySet()) {
            if (ReportsReader.yearFile.get(item).isExpense) {
                int index = ReportsReader.yearFile.get(item).month;
                System.out.println(monthsBalanceStorage.get(-index));                                  // TODO TODO TODO
                System.out.println(ReportsReader.yearFile.get(item).amount);                          // TODO TODO TODO
                System.out.println(ReportsReader.yearFile.get(item).amount == monthsBalanceStorage.get(-index)); // TODO TODO TODO
            }
            else if (!(ReportsReader.yearFile.get(item).isExpense)) {
                int index = ReportsReader.yearFile.get(item).month;
                System.out.println(monthsBalanceStorage.get(index));                                   // TODO TODO TODO
                System.out.println(ReportsReader.yearFile.get(item).amount);                           // TODO TODO TODO
                System.out.println(ReportsReader.yearFile.get(item).amount == monthsBalanceStorage.get(index));  // TODO TODO TODO
            }
        }
    }
}
