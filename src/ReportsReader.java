import java.io.*;
import java.util.*;

public class ReportsReader {

    public static HashMap<String, String[]> monthFiles = new HashMap<>();
    public static HashMap<String, String[]> yearFile = new HashMap<>();
    public static File folder = new File("./resources");
    public static File[] listOfFiles = folder.listFiles();
    public static String fileName;
    public static String[] fileData;

    public void rowMonthsDataReader() throws IOException {
        System.out.println("Считать все месячные отчёты.");  // TODO TODO TODO

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {

            if (listOfFiles[i].isFile() && listOfFiles[i].getName().charAt(0) == 'm') {

                FileInputStream fis = new FileInputStream(listOfFiles[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                fileName = listOfFiles[i].getName();
                String strLine;
                int keyNamePrefix = 0;

                while ((strLine = br.readLine()) != null) {
                    String keyName = fileName.substring(2, fileName.length() - 4) + "_" + keyNamePrefix;
                    keyNamePrefix++;

                    if(!keyName.contains("_0")) {
                        fileData = new String[]{};
                        fileData = strLine.split(",");
                        monthFiles.put(keyName, fileData);
                    }
                    System.out.println(Arrays.toString(monthFiles.get(keyName))); // TODO TODO TODO
                }

                fis.close();
            }
        }
       // System.out.println(monthFiles); // TODO TODO TODO
    }

    public void rowYearDataReader() throws IOException {
        System.out.println("Считать годовой отчёт.");            // TODO TODO TODO

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {

            if (listOfFiles[i].isFile() && listOfFiles[i].getName().charAt(0) == 'y') {

                FileInputStream fis = new FileInputStream(listOfFiles[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                fileName = listOfFiles[i].getName();
                String strLine;

                while ((strLine = br.readLine()) != null) {
                    fileData = strLine.split(", ");
                    yearFile.put(fileName, fileData);
                }

                fis.close();
            }
        }

        System.out.println(Arrays.toString(yearFile.get("y.2021.csv")));  // TODO TODO TODO
    }

}


