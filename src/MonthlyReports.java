import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReports { //объект для хранения всех месячных отчетов
    public ArrayList<MonthData> allMonthlyReports = new ArrayList<>();

    public Boolean addMonth (String path) { // загрузка одного месяца
        List<String> lines = readFileContents(path); //считываем в коллекцию файл, в коллекции каждый элемент это строка из загруженного файла

        if (!lines.isEmpty()) { // проверка - удалось получить данные из скачиваемого файла
            System.out.println("Скачан файл " + Path.of(path));
        } else {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            System.out.println("Ожидаемый путь " + Path.of(path));
            return false;
        }

        MonthData monthData = new MonthData();

        for (int i = 1; i < lines.size(); i++) {
            String[] item = lines.get(i).split(","); //получил массив из строк, которые представляют собой отдельные значения в загруженном файле

            //создаем запись месячного отчета
            MonthlyReportEntry monthlyReportEntry = new MonthlyReportEntry(item[0],
                    Integer.parseInt(item[2]),
                    Integer.parseInt(item[3]),
                    Boolean.parseBoolean(item[1]));

            monthData.data.add(monthlyReportEntry); //добавляем запись (строку) в месячный отчет
        }
        allMonthlyReports.add(monthData);
        return true;
    }

    public void addAllMonthlyReports (String basePath) { //добавление всех отчетов
        for (int i = 1; i<=3; i++) {
            String path = basePath + "m.20210" + i + ".csv";

            //проверка - загрузка удалась, если нет, то прерываем цикл загрузки
            Boolean result = addMonth(path);
            if (!result) {
                break;
            }
        }
    }

    List <String> readFileContents (String path) { //вызывая метод по переданному пути в ответ получаю коллекцию строк
        try {
            return Files.readAllLines(Path.of(path)); //получаем коллекцию строк, строки разделены в исходнике переводом каретки
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public void printAllMonth() {
        if (allMonthlyReports.isEmpty()) {
            System.out.println("Нет загруженных отчетов за месяц");
        } else {
            System.out.println("Категория записи;  Количество;  Цена за единицу;  Признак расхода");
            for (int j = 0; j < allMonthlyReports.size(); j++) {
                System.out.println("Месяц " + (j+1));
                for (int i = 0; i < allMonthlyReports.get(j).data.size(); i++) {
                    System.out.print  (allMonthlyReports.get(j).data.get(i).itemName + ";  ");
                    System.out.print  (allMonthlyReports.get(j).data.get(i).quantity + ";  ");
                    System.out.print  (allMonthlyReports.get(j).data.get(i).sumOfOne + ";  ");
                    System.out.println(allMonthlyReports.get(j).data.get(i).isExpense);
                }
                System.out.println();
            }
        }
    }

    public HashMap<Integer,Integer> getMonthExpenses() {
        //HashMap хранит номер месяца + сумма
        HashMap<Integer,Integer> monthExpenses = new HashMap<>();
        for (int i = 0; i < allMonthlyReports.size(); i++) {
            int index = i + 1;
            int amount = allMonthlyReports.get(i).monthlyExpenses();
            monthExpenses.put(index,amount);
        }
        return monthExpenses;
    }

    public HashMap<Integer,Integer> getMonthIncome() {
        //HashMap хранит номер месяца + сумма
        HashMap<Integer,Integer> monthIncome = new HashMap<>();
        for (int i = 0; i < allMonthlyReports.size(); i++) {
            int index = i + 1;
            int amount = allMonthlyReports.get(i).monthlyIncome();
            monthIncome.put(index,amount);
        }
        return monthIncome;
    }


}
