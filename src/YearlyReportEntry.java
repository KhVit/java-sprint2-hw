public class YearlyReportEntry { //класс для хранения одной строки годового отчета
    Integer month; //месяц. Целое число
    Integer amount; // сумма
    Boolean isExpense; // одно из двух значений: true или false. Обозначает, является ли запись тратой (true) или доходом (false)

    YearlyReportEntry(Integer month, Integer amount, Boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
