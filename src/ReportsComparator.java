import java.util.HashMap;

public class ReportsComparator {

    public MenuOutput menuOutput = new MenuOutput();
    static HashMap<Integer, Integer> monthsBalanceStorage = new HashMap<>();

    public void getRowBalance() {
// делаем структуру для будущего хранения данных по виду трат/доходов
        for (Integer j : ReportsReader.keysChunks) {
            monthsBalanceStorage.put(j, 0);
            monthsBalanceStorage.put(-j, 0);
        }
// распределение по доходам или расходам
        for (String value : ReportsReader.monthFiles.keySet()) {
            MonthlyReport report = ReportsReader.monthFiles.get(value);
            int operationsSum = report.sumOfOne * report.quantity;
            int valueId = Integer.parseInt(value.substring(0, 2));

            if (monthsBalanceStorage.containsKey(valueId) && report.isExpense) {
                monthsBalanceStorage.merge(-valueId, operationsSum, Integer::sum);
            }
            else if (monthsBalanceStorage.containsKey(valueId) && !(report.isExpense)) {
                monthsBalanceStorage.merge(valueId, operationsSum, Integer::sum);
            }
        }
    }
// сверка отчетов и вывод разницы
    public void getFineBalance() {
        System.out.println("Сверяю отчёты." + "\n");
        boolean isMatches;
        boolean withoutDiscrepancies = true;
        int index;

        for (String item : ReportsReader.yearFile.keySet()) {
            AnnualReport report = ReportsReader.yearFile.get(item);

            if (report.isExpense) {
                index = report.month;
                isMatches = report.amount == monthsBalanceStorage.get(-index);
                if (!isMatches) {
                    getReportDiscrepancy(index, report.amount,
                            monthsBalanceStorage.get(-index)
                    );
                    withoutDiscrepancies = false;
                }
            }
            else {
                index = report.month;

                if (ReportsReader.monthFiles.size() == 0) {
                    System.out.println("Введите значение меню '1' для обработки месячных отчетов.");
                    return;
                }
                isMatches = report.amount == monthsBalanceStorage.get(index);



                if (!isMatches) {
                    getReportDiscrepancy(index, report.amount, monthsBalanceStorage.get(index));
                    withoutDiscrepancies = false;
                }
            }
        }
        if (withoutDiscrepancies) {
            if (ReportsReader.monthFiles.size() == 0) {
                System.out.println("Месячные отчеты загружены, но не обработаны." + "\n");
                menuOutput.menuPrint();
            }
            else if (ReportsReader.yearFile.size() == 0) {
                System.out.println("Годовой отчет загружен, но не обработан." + "\n");
            }

            else {
                System.out.println("Сверка завершена. Расхождений не обнаружено.");
            }
        }
    }

    public void getReportDiscrepancy(int month, int globalBata, int localData) {

        System.out.println("В отчетности за " + menuOutput.months[month] + " обнаружено расхождение " +
                "данных");
        System.out.println("Данные в годовом отчете: " + globalBata + "руб.");
        System.out.println("Данные в отчете за месяц: " + localData + "руб.");
        System.out.println("Разница составляет: " + (globalBata > localData ? globalBata - localData :
                localData - globalBata) + "руб.");
    }
}
