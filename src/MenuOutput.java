public class MenuOutput {

    public String[] months = new String[]{"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август",
            "сентябрь", "октябрь", "ноябрь", "декабрь"};

    public void greetingsMenu() {
        System.out.println("Вас приветствует программа финансовой отчетности.");
        System.out.println("Нажмите 6, если в процессе работы забудете значения меню." + "\n");
    }

    public void menuPrint() {
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("0 - Выход.");
    }

    public boolean menuValidator(int item) {
        boolean valid = true;

        if ((item < 0 || item > 6)) {
            System.out.println("Указаны неправильные значения. Введите от 1 до 5 или 0 для выхода.");
            valid = false;
        }
        return valid;
    }

    public void monthlyReportWarning () {
        System.out.println("Для выполнения действий необходимы месячные отчеты.");
        System.out.println("Введите значение меню '1' для их обработки.");
    }

    public void yearReportWarning () {
        System.out.println("Годовой отчет загружен, но не обработан.");
        System.out.println("Введите значение меню '2' для его обработки." + "\n");
    }
}
