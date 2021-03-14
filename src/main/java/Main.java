import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CheckInputInterval checkInputInterval = new CheckInputInterval();
        Scanner inConsole = new Scanner(System.in);

        double a, b, e;

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

                a = Double.parseDouble(scan.nextLine().trim().replaceAll(",", "\\."));
                b = Double.parseDouble(scan.nextLine().trim().replaceAll(",", "\\."));
                e = Double.parseDouble(scan.nextLine().trim().replaceAll(",", "\\."));

                int num_method = Integer.parseInt(scan.nextLine().trim());
                boolean file_flag = Boolean.parseBoolean(scan.nextLine().trim());

                if (Math.abs(a - b) < 0.1) {
                    printParams(a, b, e);
                    System.out.println("Значения должны быть на расстоянии 0,1 и более");
                } else {
                    if (checkInputInterval.checkInterval(a, b)) {
                        if (checkInputInterval.checkIntervalRoots(a, b) > 1) {
                            printParams(a, b, e);
                            System.out.println("На интервале должен быть только один корень");
                            System.out.println("Количество корней на интервале: " + checkInputInterval.checkIntervalRoots(a, b));
                        } else {
                            System.out.println("Начато выполнение...");
                            switch (num_method) {
                                case 2 -> {
                                    Method2 method2 = new Method2(a, b, e);
                                    if (!file_flag) {
                                        System.out.println("Запущен метод хорд");
                                        printParams(a, b, e);
                                        method2.startCalculations(false);
                                    } else {
                                        method2.startCalculations(true);
                                        System.out.println("Результаты записаны в файл out2.txt");
                                    }
                                }
                                case 4 -> {
                                    Method4 method4 = new Method4(a, b, e);
                                    if (!file_flag) {
                                        System.out.println("Запущен метод секущих");
                                        printParams(a, b, e);
                                        method4.startCalculations(false);
                                    } else {
                                        method4.startCalculations(true);
                                        System.out.println("Результаты записаны в файл out4.txt");
                                    }
                                }
                                case 5 -> {
                                    Method5 method5 = new Method5(a, b, e);
                                    if (!file_flag) {
                                        System.out.println("Запущен метод простых итераций");
                                        printParams(a, b, e);
                                        method5.startCalculations(false);
                                    } else {
                                        method5.startCalculations(true);
                                        System.out.println("Результаты записаны в файл out5.txt");
                                    }
                                }
                                case 1, 3 -> System.out.println("Программа не поддерживает этот метод решения");
                            }
                        }
                    } else {
                        printParams(a, b, e);
                        System.out.println("В выбранном интервале нет корней");
                    }
                }

            }


            case 2 -> {
                while (true) {
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
                        if (checkInputInterval.checkInterval(a, b)) {
                            if (checkInputInterval.checkIntervalRoots(a, b) > 1) {
                                System.out.println("На интервале должен быть только один корень");
                                System.out.println("Количество корней на интервале: " +
                                        checkInputInterval.checkIntervalRoots(a, b));
                            } else {
                                System.out.println("""
                                        Выберите метод решения:
                                        2. Метод хорд
                                        4. Метод секущих
                                        5. Метод простой итерации""");
                                int answer = inConsole.nextInt();
                                do {
                                    switch (answer) {
                                        case 2 -> {
                                            System.out.println("Введите: 1 - для вывода в файл; 2 - для вывода в консоль");
                                            Method2 method2 = new Method2(a, b, e);
                                            int flag = inConsole.nextInt();
                                            if (flag == 1) {
                                                method2.startCalculations(true);
                                                System.out.println("Результаты записаны в файл out1.txt");
                                            } else if (flag == 2) {
                                                method2.startCalculations(false);
                                            }
                                        }
                                        case 4 -> {
                                            System.out.println("Введите: 1 - для вывода в файл; 2 - для вывода в консоль");
                                            Method4 method4 = new Method4(a, b, e);
                                            int flag = inConsole.nextInt();
                                            if (flag == 1) {
                                                method4.startCalculations(true);
                                                System.out.println("Результаты записаны в файл out2.txt");
                                            } else if (flag == 2) {
                                                method4.startCalculations(false);
                                            }
                                        }
                                        case 5 -> {
                                            System.out.println("Введите: 1 - для вывода в файл; 2 - для вывода в консоль");
                                            Method5 method5 = new Method5(a, b, e);
                                            int flag = inConsole.nextInt();
                                            if (flag == 1) {
                                                method5.startCalculations(true);
                                                System.out.println("Результаты записаны в файл out3.txt");
                                            } else if (flag == 2) {
                                                method5.startCalculations(false);
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
                }
            }
        }
    }

    private static void printParams(double a, double b, double e) {
        System.out.println("Решение уравнения на интервале [ " + a + "; " + b + " ]");
        System.out.println("Точность решения: " + e);
    }
}