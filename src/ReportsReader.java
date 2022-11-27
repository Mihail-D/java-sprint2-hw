import java.io.*;
import java.util.*;

public class ReportsReader {

    public MenuOutput menuOutput = new MenuOutput();

    public static HashMap<String, MonthlyReport> monthFiles = new HashMap<>();
    public static HashMap<String, AnnualReport> yearFile = new HashMap<>();
    public static ArrayList<Integer> keysChunks = new ArrayList<>();

    public void rowMonthsDataReader() throws IOException {

        File folder = new File("./resources");
        File[] listOfFiles = folder.listFiles();
        String fileName;
        String[] fileData;
        MonthlyReport monthlyReport;

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {

            if (listOfFiles[i].isFile() && listOfFiles[i].getName().charAt(0) == 'm') {

                FileInputStream fis = new FileInputStream(listOfFiles[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                fileName = listOfFiles[i].getName();
                String strLine;
                int keyNamePrefix = 0;

                while ((strLine = br.readLine()) != null && strLine.length() != 0) {
                    String keyName = fileName.substring(6, fileName.length() - 4) + "_" + keyNamePrefix;
                    keyNamePrefix++;

                    if (!keyName.contains("_0")) {
                        fileData = strLine.split(",");
                        monthlyReport = new MonthlyReport(fileData[0], Boolean.parseBoolean(fileData[1]),
                                Integer.parseInt(fileData[2]), Integer.parseInt(fileData[3])
                        );

                        monthFiles.put(keyName, monthlyReport);
                    }
                }
                fis.close();
            }
        }

        getNamesChunks();

        System.out.println("Отчет за " + menuOutput.months[keysChunks.get(0)-1] + " загружен.");
        System.out.println("Отчет за " + menuOutput.months[keysChunks.get(1)-1] + " загружен.");
        System.out.println("Отчет за " + menuOutput.months[keysChunks.get(2)-1] + " загружен.");
        System.out.println("Месячные отчёты готовы к работе." + "\n");

        menuOutput.menuPrint();
    }

    public void rowYearDataReader() throws IOException {
        System.out.println("Считываю годовой отчёт.");

        File folder = new File("./resources");
        File[] listOfFiles = folder.listFiles();
        String fileName;
        String[] fileData;
        AnnualReport annualReport;

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {

            if (listOfFiles[i].isFile() && listOfFiles[i].getName().charAt(0) == 'y') {

                FileInputStream fis = new FileInputStream(listOfFiles[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                fileName = listOfFiles[i].getName();
                String strLine;
                int keyNamePrefix = 0;

                while ((strLine = br.readLine()) != null && strLine.length() != 0) {
                    String keyName = fileName.substring(2, fileName.length() - 4) + "_" + keyNamePrefix;
                    keyNamePrefix++;

                    if (!keyName.contains("_0")) {
                        fileData = strLine.split(",");
                        annualReport = new AnnualReport(Integer.parseInt(fileData[0]), Integer.parseInt(fileData[1]),
                                Boolean.parseBoolean(fileData[2])

                        );
                        yearFile.put(keyName, annualReport);
                    }
                }
                fis.close();
            }
        }
        System.out.println("Годовой отчет загружен в систему и готов к работе." + "\n");
        menuOutput.menuPrint();
    }

    void getNamesChunks() {
        int keyChunk;
        for (String i : monthFiles.keySet()) {
            keyChunk = Integer.parseInt(i.substring(i.length() - 4, i.length() - 2));
            if (!keysChunks.contains(keyChunk)) {
                keysChunks.add(keyChunk);
            }
        }
    }
}


