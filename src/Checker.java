import java.util.HashMap;

public class Checker { // отдельный класс для сверки, поскольку передаю два объекта

    public void checker (MonthlyReports allMonthReport, YearlyReports yearlyReports) {
        if (yearlyReports.yearReport.isEmpty()) { //проверка - загружен годовой отчет
            System.out.println("Годовой отчет не загружен!");
            return;
        }
        if (allMonthReport.allMonthlyReports.isEmpty()) { //проверка - загружены месячные отчеты
            System.out.println("Нет загруженных отчетов за месяц");
            return;
        }

        byte errorCount = 0; // счетчик ошибок

        //чтобы потом не запускать расчеты трат и доходов каждый раз в цикле и на проверках
        //создал отдельные переменные для них
        HashMap<Integer,Integer> monthExpenses = allMonthReport.getMonthExpenses();
        HashMap<Integer,Integer> monthIncome = allMonthReport.getMonthIncome();
        HashMap<Integer,Integer> yearExpenses = yearlyReports.getYearExpenses();
        HashMap<Integer,Integer> yearIncome = yearlyReports.getYearIncome();

        for (int i = 1; i <= monthExpenses.size(); i++) {
            boolean isExpenseEqual = (monthExpenses.get(i).equals(yearExpenses.get(i))); //сравниваю расходы за месяц
            boolean isIncomeEqual = (monthIncome.get(i).equals(yearIncome.get(i))); //сравниваю доходы за месяц
            if (!isExpenseEqual) {
                System.out.println("Обнаружено расхождение по расходам в месяце №" + i);
                errorCount++;
            }
            if (!isIncomeEqual) {
                System.out.println("Обнаружено расхождение по доходам в месяце №" + i);
                errorCount++;
            }
        }
        if (errorCount == 0) {
            System.out.println("Расхождений в данных нет");
        }
    }
}