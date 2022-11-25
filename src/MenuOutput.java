public class MenuOutput {
    public void menuPrint() {
        System.out.println("Вас приветствует программа финансовой отчетности.");
        System.out.println("Годовые и месячные отчеты подразделений загружены.");
        System.out.println("Что должна сделать программа?"+"\n");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("0 - Выход.");
    }

    public boolean menuValidator(int item) {
        boolean valid = true;

        if (item < 0 || item > 5 ) {
            System.out.println("Указаны неправильные значения. Введите от 1 до 5 или 0 для выхода.");
            valid = false;
        }
        return valid;
    }
}
