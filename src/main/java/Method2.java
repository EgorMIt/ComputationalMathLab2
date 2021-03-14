import java.io.FileWriter;
import java.io.IOException;

public class Method2 { //Метод хорд
    DrawChart drawChart = new DrawChart();
    PrintTable printTable = new PrintTable();

    double a, b, e, start_X, end_X;
    int iterations = 0;
    double current_x = 1, last_x = 1;

    private double f(double x) {
        return 4.45 * Math.pow(x, 3) + 7.81 * Math.pow(x, 2) - 9.62 * x - 8.17;
    }

    private double x0(double a, double b) {
        return a - ((b - a) / (f(b) - f(a))) * f(a);
    }

    private double f__(double x) {
        return 26.7 * x + 15.62;
    }

    public Method2(double a, double b, double e) {
        this.a = a;
        this.b = b;
        this.e = e;
    }

    private double setStartX() {
        if (f(a) * f__(a) > 0) {
            return a;
        } else if (f(b) * f__(b) > 0) {
            return b;
        } else {
            return 1;
        }
    }

    public void startCalculations(boolean flag_file) throws IOException {
        FileWriter writer = null;

        if (flag_file) {
            writer = new FileWriter("src/main/res/out2.txt", false);
            writer.write("Запущен метод хорд\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n");
        }

        start_X = setStartX();
        if (start_X == a)
            end_X = b;
        else
            end_X = a;

        if (!flag_file)
            printTable.printHeaderFor2inConsole();
        else
            writer.write(printTable.printHeaderFor2inFile());

        while (Math.abs(f(current_x)) > e) {
            current_x = x0(start_X, end_X);
            if (iterations == 0) {
                if (!flag_file)
                    printTable.printLineFor2inConsole(iterations + 1, start_X, end_X, current_x, f(start_X),
                            f(end_X), f(current_x), 0);
                else
                    writer.write(printTable.printLineFor2inFile(iterations + 1, start_X, end_X, current_x,
                            f(start_X), f(end_X), f(current_x), 0));
            } else {
                if (!flag_file)
                    printTable.printLineFor2inConsole(iterations + 1, start_X, end_X, current_x, f(start_X),
                            f(end_X), f(current_x), Math.abs(last_x - current_x));
                else
                    writer.write(printTable.printLineFor2inFile(iterations + 1, start_X, end_X, current_x,
                            f(start_X), f(end_X), f(current_x), Math.abs(last_x - current_x)));
            }

            if (f(start_X) > 0 && f(current_x) < 0 || f(start_X) < 0 && f(current_x) > 0) {
                end_X = current_x;
            } else if (f(end_X) > 0 && f(current_x) < 0 || f(end_X) < 0 && f(current_x) > 0) {
                start_X = current_x;
            }

            last_x = current_x;
            iterations++;
        }

        if (!flag_file) {
            System.out.println("Корень уравнения:");
            System.out.printf("x* = %.13f%n", current_x);
            System.out.printf("f(x*) = %.13f%n", f(current_x));
        } else {
            writer.write("Корень уравнения:\n");
            writer.write(String.format("x* = %.13f%n\n", current_x));
            writer.write(String.format("f(x*) = %.13f%n", f(current_x)));
            writer.flush();
        }

        drawChart.draw(Math.round(a), Math.round(b));
    }

}
