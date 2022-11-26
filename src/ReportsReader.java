import java.io.*;
import java.util.*;

public class ReportsReader {

    public static HashMap<String, MonthlyReport> monthFiles = new HashMap<>();
    public static HashMap<String, AnnualReport> yearFile = new HashMap<>();
    public static ArrayList<String> keysChunks = new ArrayList<>();

    public void rowMonthsDataReader() throws IOException {
        System.out.println("Считать все месячные отчёты.");  // TODO TODO TODO

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
        System.out.println(monthFiles); // TODO TODO TODO
        getNamesChunks();
        System.out.println("Месячные отчёты загружены в систему и готовы к работе." + "\n");
        MenuOutput.menuPrint();
    }

    public void rowYearDataReader() throws IOException {
        System.out.println("Считать годовой отчёт.");            // TODO TODO TODO

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
        System.out.println(yearFile); // TODO TODO TODO
        System.out.println("Годовой отчет загружен в систему и готов к работе." + "\n");
        MenuOutput.menuPrint();
    }

      static void getNamesChunks() {
        String keyChunk;
        for (String i : monthFiles.keySet()) {
            keyChunk = i.substring(i.length() - 4, i.length() - 2);
            if (!keysChunks.contains(keyChunk)) {
                keysChunks.add(keyChunk);
            }
        }
        System.out.println(keysChunks); // TODO TODO TODO
    }

}


