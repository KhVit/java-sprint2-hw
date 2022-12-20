import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReports { //объект для хранения годового отчета
    public ArrayList<YearlyReportEntry> yearReport = new ArrayList<>();

    public void addYear(String basePath) { //загрузка годового отчета
        String path = basePath + "y.2021.csv";
        List<String> lines = readFileContents(path); //считываю в коллекцию файл, в коллекции каждый элемент это строка из загруженного файла

        if (!lines.isEmpty()) { // проверка - что удалось получить данные из скачиваемого файла
            System.out.println("Скачан файл " + Path.of(path));
        } else {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            System.out.println("Ожидаемый путь " + Path.of(path));
            return;
        }

        for (int i = 1; i < lines.size(); i++) {
            String[] item = lines.get(i).split(","); //получил массив из строк, которые представляют собой отдельные значения в загруженном файле

            //создал запись месячного отчета
            YearlyReportEntry yearlyReportEntry = new YearlyReportEntry(Integer.parseInt(item[0]),
                    Integer.parseInt(item[1]),
                    Boolean.parseBoolean(item[2]));

            yearReport.add(yearlyReportEntry); //добавляем запись(строку) в годовой отчет
        }
    }

    List<String> readFileContents (String path) { //вызывая метод по переданному пути в ответ получаю коллекцию строк
        try {
            return Files.readAllLines(Path.of(path)); //получаем коллекцию строк, строки разделены в исходнике переводом каретки
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public void printYear() {
        if (yearReport.isEmpty()) {
            System.out.println("Годовой отчет не загружен!");
        } else {
            System.out.println("Месяц №;  Сумма;  Признак расхода");
            for (YearlyReportEntry yearlyReportEntry : yearReport) {
                System.out.print("Месяц " + yearlyReportEntry.month + ";  ");
                System.out.print(yearlyReportEntry.amount + ";  ");
                System.out.println(yearlyReportEntry.isExpense);
            }
        }
    }

    public HashMap<Integer,Integer> getYearExpenses() {
        HashMap<Integer,Integer> yearExpenses = new HashMap<>();
        for (YearlyReportEntry yearlyReportEntry : yearReport) {
            if (yearlyReportEntry.isExpense) { //если расход
                int index = yearlyReportEntry.month;
                int amount = yearlyReportEntry.amount;
                yearExpenses.put(index, amount);
            }
        }
        return yearExpenses;
    }

    public HashMap<Integer,Integer> getYearIncome() {
        HashMap<Integer,Integer> yearIncome = new HashMap<>();
        for (YearlyReportEntry yearlyReportEntry : yearReport) {
            if (!yearlyReportEntry.isExpense) { //если НЕ расход
                int index = yearlyReportEntry.month;
                int amount = yearlyReportEntry.amount;
                yearIncome.put(index, amount);
            }
        }
        return yearIncome;
    }

}