import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Checker check = new Checker();//объект для сверки отчетов
        MonthlyReports monthlyReports = new MonthlyReports(); //новый объект всех месячных отчетов
        YearlyReports yearlyReports = new YearlyReports(); //новый объект всех годовых отчетов

        String basePath = "resources/"; //базовый путь к отчетам

        printMenu();
        String command = scanner.nextLine();

        while (true) {
            //printMenu();
            switch (command) {
                case "1":  //считать все месячные отчеты
                    monthlyReports.addAllMonthlyReports(basePath);
                    printMenu();
                    command = scanner.nextLine();
                    break;
                case "2":  // считать все годовые отчеты
                    yearlyReports.addYear(basePath);
                    printMenu();
                    command = scanner.nextLine();
                    break;
                case "3":  // сверить отчеты
                    check.checker(monthlyReports, yearlyReports);
                    printMenu();
                    command = scanner.nextLine();
                    break;
                case "4":  // вывести информацию обо всех месячных отчетах
                    monthlyReports.printAllMonth();
                    printMenu();
                    command = scanner.nextLine();
                    break;
                case "5":  // вывести информацию о годовом отчете
                    yearlyReports.printYear();
                    printMenu();
                    command = scanner.nextLine();
                    break;
                case "0":  // выход
                    System.out.println("Программа завершена. Good luck!");
                    return;
                default: // проверка введенной команды
                    System.out.println("Неизвестная команда! Try again!");
                    printMenu();
                    command = scanner.nextLine();
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите пункт меню: ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход из программы");
    }
}
