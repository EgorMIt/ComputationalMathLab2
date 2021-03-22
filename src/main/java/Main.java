import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CheckInputInterval checkInputInterval = new CheckInputInterval();
        Scanner inConsole = new Scanner(System.in);

        double a, b, e;
        int number;
        boolean file_flag;

        System.out.println("""
                Формат входного файла:
                1. Номер выбранной функции: 1-3
                2. Коэффициент а
                3. Коэффициент b
                4. Точность e
                5. Метод решения: 2/4/5
                6. Вывод результата в файл/консоль: true/false
                """);

        System.out.println("Введите: 1 - для чтения файла; 2 - для ввода с консоли");
        int num = inConsole.nextInt();

        while (!(num == 1 || num == 2)) {
            System.out.println("Ошибка ввода!");
            System.out.println("Введите: 1 - для чтения файла; 2 - для ввода с консоли");
            num = inConsole.nextInt();
        }

        switch (num) {
            case 1 -> {
                FileReader fr = new FileReader("src/main/res/input.txt");
                Scanner scan = new Scanner(fr);

                number = Integer.parseInt(scan.nextLine().trim());
                a = Double.parseDouble(scan.nextLine().trim().replaceAll(",", "\\."));
                b = Double.parseDouble(scan.nextLine().trim().replaceAll(",", "\\."));
                e = Double.parseDouble(scan.nextLine().trim().replaceAll(",", "\\."));

                int num_method = Integer.parseInt(scan.nextLine().trim());
                file_flag = Boolean.parseBoolean(scan.nextLine().trim());

                if (Math.abs(a - b) < 0.1) {
                    System.out.println("Значения должны быть на расстоянии 0,1 и более");
                } else {
                    if (checkInputInterval.checkInterval(a, b, number)) {
                        if (checkInputInterval.checkIntervalRoots(a, b, number) > 1) {
                            System.out.println("На интервале должен быть только один корень");
                            System.out.println("Количество корней на интервале: " + checkInputInterval.checkIntervalRoots(a, b, number));
                        } else {
                            System.out.println("Начато выполнение...");
                            switch (num_method) {
                                case 2 -> {
                                    Method2 method2 = new Method2(a, b, e, number, file_flag);
                                    method2.startCalculations();

                                    if (file_flag) {
                                        System.out.println("Результаты записаны в файл out2.txt");
                                    }
                                }
                                case 4 -> {
                                    Method4 method4 = new Method4(a, b, e, number, file_flag);
                                    method4.startCalculations();

                                    if (file_flag) {
                                        System.out.println("Результаты записаны в файл out4.txt");
                                    }
                                }
                                case 5 -> {
                                    Method5 method5 = new Method5(a, b, e, number, file_flag);
                                    method5.startCalculations();

                                    if (file_flag) {
                                        System.out.println("Результаты записаны в файл out5.txt");
                                    }
                                }
                                case 1, 3 -> System.out.println("Программа не поддерживает этот метод решения");
                            }
                        }
                    } else {
                        System.out.println("В выбранном интервале нет корней");
                    }
                }
            }


            case 2 -> {
                while (true) {
                    int answer = 0;
                    while (true) {
                        System.out.println("Введите номер исследуемой функции (1-3): ");
                        try {
                            number = Integer.parseInt(inConsole.next().trim().replaceAll(",", "\\."));
                            break;
                        } catch (NumberFormatException ignored) {
                            System.out.println("Повторите ввод");
                        }
                    }
                    while (true) {
                        System.out.println("Введите a: ");
                        try {
                            a = Double.parseDouble(inConsole.next().trim().replaceAll(",", "\\."));
                            break;
                        } catch (NumberFormatException ignored) {
                            System.out.println("Повторите ввод");
                        }
                    }
                    while (true) {
                        System.out.println("Введите b: ");
                        try {
                            b = Double.parseDouble(inConsole.next().trim().replaceAll(",", "\\."));
                            break;
                        } catch (NumberFormatException ignored) {
                            System.out.println("Повторите ввод");
                        }
                    }
                    while (true) {
                        System.out.println("Введите точность e:");
                        try {
                            e = Double.parseDouble(inConsole.next().trim().replaceAll(",", "\\."));
                            break;
                        } catch (NumberFormatException ignored) {
                            System.out.println("Повторите ввод");
                        }
                    }

                    if (Math.abs(a - b) < 0.1) {
                        System.out.println("Значения должны быть на расстоянии 0,1 и более");
                    } else {
                        if (checkInputInterval.checkInterval(a, b, number)) {
                            if (checkInputInterval.checkIntervalRoots(a, b, number) > 1) {
                                System.out.println("На интервале должен быть только один корень");
                                System.out.println("Количество корней на интервале: " +
                                        checkInputInterval.checkIntervalRoots(a, b, number));
                            } else {
                                System.out.println("""
                                        Выберите метод решения:
                                        2. Метод хорд
                                        4. Метод секущих
                                        5. Метод простой итерации""");
                                answer = inConsole.nextInt();
                                do {
                                    switch (answer) {

                                        case 2 -> {
                                            System.out.println("Введите: 1 - для вывода в файл; 2 - для вывода в консоль");
                                            int flag = inConsole.nextInt();
                                            file_flag = flag == 1;

                                            Method2 method2 = new Method2(a, b, e, number, file_flag);
                                            method2.startCalculations();

                                            if (file_flag) {
                                                System.out.println("Результаты записаны в файл out2.txt");
                                            }
                                        }
                                        case 4 -> {
                                            System.out.println("Введите: 1 - для вывода в файл; 2 - для вывода в консоль");
                                            int flag = inConsole.nextInt();
                                            file_flag = flag == 1;

                                            Method4 method4 = new Method4(a, b, e, number, file_flag);
                                            method4.startCalculations();

                                            if (file_flag) {
                                                System.out.println("Результаты записаны в файл out4.txt");
                                            }
                                        }
                                        case 5 -> {
                                            System.out.println("Введите: 1 - для вывода в файл; 2 - для вывода в консоль");
                                            int flag = inConsole.nextInt();
                                            file_flag = flag == 1;

                                            Method5 method5 = new Method5(a, b, e, number, file_flag);
                                            method5.startCalculations();

                                            if (file_flag) {
                                                System.out.println("Результаты записаны в файл out5.txt");
                                            }
                                        }
                                        case 1, 3 -> System.out.println("Программа не поддерживает этот метод решения");
                                    }
                                    System.out.println("Если хотите запустить другой метод решения, введите его номер, иначе введите ноль: ");
                                    answer = inConsole.nextInt();
                                } while (answer == 2 || answer == 4 || answer == 5);

                            }
                        } else {
                            System.out.println("В выбранном интервале нет корней");
                            break;
                        }
                    }
                    if (answer == 0)
                        break;
                }
            }
        }
    }
}