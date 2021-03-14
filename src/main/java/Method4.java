import java.io.FileWriter;
import java.io.IOException;

public class Method4 { //Метод секущих
    DrawChart drawChart = new DrawChart();
    PrintTable printTable = new PrintTable();

    double a, b, e, start_X, next_X, last_x;
    int iterations = 0;

    private double f(double x) {
        return 4.45 * Math.pow(x, 3) + 7.81 * Math.pow(x, 2) - 9.62 * x - 8.17;
    }

    private double f_(double x) {
        return 13.35 * Math.pow(x, 2) + 15.62 * x - 9.62;
    }

    private double f__(double x) {
        return 26.7 * x + 15.62;
    }

    private double setNextX(double x1, double x2) {
        return x1 - (x1 - x2) * f(x1) / (f(x1) - f(x2));
    }

    public Method4(double a, double b, double e) {
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
            writer.write("Запущен метод секущих\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n");
        }

        next_X = setStartX();
        last_x = next_X;

        if (!flag_file)
            printTable.printHeaderFor4inConsole();
        else
            writer.write(printTable.printHeaderFor4inFile());


        while (Math.abs(f(start_X)) > e) {
            start_X = next_X;
            if (iterations == 0)
                next_X = start_X - f(start_X) / f_(start_X);
            else
                next_X = setNextX(start_X, last_x);

            if (iterations == 0) {
                if (!flag_file)
                    printTable.printLineFor4InConsole(iterations + 1, 0, 0, start_X, f(start_X), next_X, f(next_X), 0);
                else
                    writer.write(printTable.printLineFor4inFile(iterations + 1, 0, 0, start_X, f(start_X), next_X, f(next_X), 0));
            } else {
                if (!flag_file)
                    printTable.printLineFor4InConsole(iterations + 1, last_x, f(last_x), start_X, f(start_X), next_X, f(next_X), Math.abs(next_X - start_X));
                else
                    writer.write(printTable.printLineFor4inFile(iterations + 1, last_x, f(last_x), start_X, f(start_X), next_X, f(next_X), Math.abs(next_X - start_X)));
            }
            last_x = start_X;
            iterations++;
        }

        if (!flag_file) {
            System.out.println("Корень уравнения:");
            System.out.printf("x* = %.10f%n", start_X);
            System.out.printf("f(x*) = %.10f%n", f(start_X));
        } else {
            writer.write("Корень уравнения:\n");
            writer.write(String.format("x* = %.10f%n", start_X));
            writer.write(String.format("f(x*) = %.10f%n", f(start_X)));
            writer.flush();
        }

        drawChart.draw(Math.round(a), Math.round(b));
    }
}
