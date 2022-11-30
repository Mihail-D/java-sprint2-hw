import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportsReader {

    public MenuOutput menuOutput = new MenuOutput();

    public static HashMap<String, MonthlyReport> monthFiles = new HashMap<>();
    public static HashMap<String, AnnualReport> yearFile = new HashMap<>();
    public static ArrayList<Integer> keysChunks = new ArrayList<>();
    public static String yearPointer = "";
    BufferedReader reader;
    public static final String path = "./resources/";

    public void rowMonthsDataReader() throws IOException {
        Set<String> filesList = getListFiles(path);
        MonthlyReport monthlyReport;

        for (String i : filesList) {
            if ((i.charAt(0) == 'm')) {
                int keyNamePrefix = 0;
                reader = new BufferedReader(new FileReader(path + i));
                String line = " ";

                while (reader.ready()) {
                    line = reader.readLine();
                    String keyName = i.substring(6, i.length() - 4) + "_" + keyNamePrefix;
                    keyNamePrefix++;

                    if (!keyName.contains("_0")) {
                        String[] fileData = line.split(",");
                        monthlyReport = new MonthlyReport(fileData[0], Boolean.parseBoolean(fileData[1]),
                                Integer.parseInt(fileData[2]), Integer.parseInt(fileData[3]), keyName
                        );

                        monthFiles.put(keyName, monthlyReport);
                    }
                }
                reader.close();
            }
        }
        getNamesChunks();

        String monthsOfReport = "Загружены отчеты за ";

        for (Integer keysChunk : keysChunks) {
            monthsOfReport += menuOutput.months[keysChunk - 1] + " ";
        }

        System.out.println(monthsOfReport.trim() + ".");
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
                    String yearPoint = fileName.substring(2, fileName.length() - 4);

                    if (!yearPointer.contains(yearPoint)) {
                        yearPointer += yearPoint;
                    }

                    String keyName = yearPoint + "_" + keyNamePrefix;
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

    public Set<String> getListFiles(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}