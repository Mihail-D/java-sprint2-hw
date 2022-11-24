public class MenuOutput {
    public void menuPrint() {
        System.out.println("Вас приветствует программа финансовой отчетности.");
        System.out.println("Годовые и месячные отчеты подразделений загружены.");
        System.out.println("Что должна сделать программа?");
        System.out.println("Считать все месячные отчёты.");
        System.out.println("Считать годовой отчёт.");
        System.out.println("Сверить отчёты.");
        System.out.println("Вывести информацию о всех месячных отчётах.");
        System.out.println("Вывести информацию о годовом отчёте.");
        System.out.println("Выход.");
    }

    public boolean menuValidator(int item) {
        boolean valid = true;

        if (item < 0 || item > 5 ) {
            valid = false;
        }
        return valid;
    }
}
