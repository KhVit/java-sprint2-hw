public class MonthlyReportEntry { //одна запись из месячного отчета
    String itemName; //название товара
    Integer quantity; //количество закупленного или проданного товара
    Integer sumOfOne; //стоимость одной единицы товара. Целое число
    Boolean isExpense; //одно из двух значений: TRUE или FALSE. Обозначает, является ли запись тратой (TRUE) или доходом (FALSE)


    MonthlyReportEntry(String itemName, Integer quantity, Integer sumOfOne, Boolean isExpense) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
