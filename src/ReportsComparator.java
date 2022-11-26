import java.util.HashMap;

public class ReportsComparator {

    public static int getBalance() {
        HashMap<String, Integer> monthsBalaceStorage = new HashMap<>();

        if (ReportsReader.monthFiles.size() == 0 || ReportsReader.yearFile.size() == 0) {
            System.out.println("Месячные и годовой отчеты загружены, но не обработаны." + "/n");
            MenuOutput.menuPrint();
        }

        for (String j : ReportsReader.keysChunks) {
            monthsBalaceStorage.put(j + "_income",0);
            monthsBalaceStorage.put(j + "_expense",0);
        }

        for (String value : ReportsReader.monthFiles.keySet()) {
            int valueId = Integer.parseInt(value.substring(0,2));
            int
        }

        System.out.println(ReportsReader.keysChunks); // TODO TODO TODO
        System.out.println(monthsBalaceStorage); // TODO TODO TODO
        return 0;
    }

}
