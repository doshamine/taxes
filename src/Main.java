import java.util.Scanner;

public class Main {
    public static double INCOME_PERCENT = 0.06;
    public static double INCOME_MINUS_EXPENSE_PERCENT = 0.15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int income = 0;
        int expense = 0;

        do {
            System.out.println("Выберите операцию и введите её номер:");
            System.out.println("1. Добавить новый доход");
            System.out.println("2. Добавить новый расход");
            System.out.println("3. Выбрать систему налогообложения");

            String choice = scanner.nextLine();
            if (choice.equals("end")) {
                break;
            }

            int number = Integer.parseInt(choice);
            switch (number) {
                case 1:
                    System.out.print("Введите сумму дохода: ");
                    income += scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Введите сумму расхода: ");
                    expense += scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 3:
                    int taxIncome = taxIncome(income);
                    int taxIncomeMinusExpense = taxIncomeMinusExpense(income, expense);

                    if (taxIncome > taxIncomeMinusExpense) {
                        System.out.println("Мы советуем Вам УСН доходы минус расходы");
                        System.out.println("Ваш налог составит: " + taxIncomeMinusExpense + " рублей");
                        System.out.println("Налог на другой системе: " + taxIncome + " рублей");
                    } else if (taxIncome < taxIncomeMinusExpense) {
                        System.out.println("Мы советуем Вам УСН доходы");
                        System.out.println("Ваш налог составит: " + taxIncome + " рублей");
                        System.out.println("Налог на другой системе: " + taxIncomeMinusExpense + " рублей");
                    }
                    int saving = Math.abs(taxIncome - taxIncomeMinusExpense);
                    System.out.println("Экономия: " + saving + " рублей");

                    if (taxIncome == taxIncomeMinusExpense) {
                        System.out.println("Можете выбрать любую систему налогообложения");
                        System.out.println("Ваш налог составит: " + taxIncome);
                    }
                    break;
                default:
                    System.out.println("Неверная операция");
            }
        } while (true);

        System.out.println("Программа завершена!");
    }

    public static int taxIncome(int income) {
        return (int) (INCOME_PERCENT * income);
    }

    public static int taxIncomeMinusExpense(int income, int expense) {
        int tax = (int) (INCOME_MINUS_EXPENSE_PERCENT * (income - expense));
        return Math.max(tax, 0);
    }
}