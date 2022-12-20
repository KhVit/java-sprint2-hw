import java.util.ArrayList;

public class MonthData { //хранение данных за один месяц
    ArrayList<MonthlyReportEntry> data = new ArrayList<>();

    public Integer monthlyExpenses() { //подсчет расходов за месяц
        int sum = 0; //сразу ставим 0 чтобы не было null
        for (MonthlyReportEntry base : data) { //по всему массиву
            if (base.isExpense) { //проверяем что расход
                sum += (base.sumOfOne * base.quantity);
            }
        }
        return sum;
    }

    public Integer monthlyIncome() { //подсчет доходов за месяц
        int sum = 0; //сразу ставим 0 чтобы не было null
        for (MonthlyReportEntry base : data) { //по всему массиву
            if (!base.isExpense) { //проверяем что НЕрасход
                sum += (base.sumOfOne * base.quantity);
            }
        }
        return sum;
    }
}