import java.io.FileWriter;
import java.io.IOException;

public class Method5 { //Метод простых итераций
    DrawChart drawChart = new DrawChart();
    PrintTable printTable = new PrintTable();

    double a, b, e, start_X, last_X;
    double lambda = 1;
    int iterations = 0;

    public Method5(double a, double b, double e) {
        this.a = a;
        this.b = b;
        this.e = e;
    }

    private double Fi(double x) {
        return x - lambda * f(x);
    }

    private double f(double x) {
        return 4.45 * Math.pow(x, 3) + 7.81 * Math.pow(x, 2) - 9.62 * x - 8.1;
    }

    private double f_(double x) {
        return 13.35 * Math.pow(x, 2) + 15.62 * x - 9.62;
    }

    private double getMaxF_(double a, double b) {
        return Math.max(f_(a), f_(b));
    }

    public void startCalculations(boolean flag_file) throws IOException {
        lambda = lambda / getMaxF_(a, b);
        boolean flag_rejection = false;

        FileWriter writer = null;

        if (flag_file) {
            writer = new FileWriter("src/main/res/out2.txt", false);
            writer.write("Запущен метод простых итераций\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n");
        }

        if (!flag_file)
            printTable.printHeaderFor5inConsole();
        else
            writer.write(printTable.printHeaderFor5inFile());

        start_X = Fi(a);
        last_X = a;
        while (Math.abs(start_X - last_X) > e) {
            if (lambda * f_(start_X) > 0 && lambda * f_(start_X) < 2 && start_X > a) {
                if (!flag_file) {
                    printTable.printLineFor5inConsole(iterations, last_X, start_X, Fi(start_X), f(start_X), Math.abs(start_X - last_X));
                } else {
                    writer.write(printTable.printLineFor5inFile(iterations, last_X, start_X, Fi(start_X), f(start_X), Math.abs(start_X - last_X)));
                }
                last_X = start_X;
                start_X = Fi(start_X);
                iterations++;
                flag_rejection = true;
            } else {
                break;
            }
        }

        if (flag_rejection) {
            if (!flag_file)
                printTable.printLineFor5inConsole(iterations, last_X, start_X, Fi(start_X), f(start_X), Math.abs(start_X - last_X));
            else
                writer.write(printTable.printLineFor5inFile(iterations, last_X, start_X, Fi(start_X), f(start_X), Math.abs(start_X - last_X)));
        }

        if (flag_file) {
            writer.write("Корень уравнения:\n");
            writer.write(String.format("x* = %.10f%n", last_X));
            writer.write(String.format("f(x*) = %.10f%n", f(last_X)));
            writer.flush();
        } else {
            System.out.println("Корень уравнения:");
            System.out.printf("x* = %.10f%n", last_X);
            System.out.printf("f(x*) = %.10f%n", f(last_X));
        }

        drawChart.drawForIt(Math.round(a), Math.round(b), lambda);
    }
}
