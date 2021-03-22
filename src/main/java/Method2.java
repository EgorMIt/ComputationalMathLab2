import java.io.FileWriter;
import java.io.IOException;

public class Method2 { //Метод хорд
    DrawChart drawChart = new DrawChart();
    PrintTable printTable = new PrintTable();
    Functions functions = new Functions();

    double a, b, e, start_X, end_X;
    int iterations = 0, number;
    double current_x = 1, last_x = 1;
    boolean flag_file;

    public Method2(double a, double b, double e, int number, boolean flag_file) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.number = number;
        this.flag_file = flag_file;
    }

    public void startCalculations() throws IOException {
        FileWriter writer = null;

        if (flag_file) {
            writer = new FileWriter("src/main/res/out2.txt", false);
            writer.write("Запущен метод хорд\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n\n");
        } else {
            System.out.println("Запущен метод хорд\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n\n");
        }

        start_X = functions.setStartX(a, b, number);
        if (start_X == a)
            end_X = b;
        else
            end_X = a;

        if (!flag_file)
            printTable.printHeaderFor2inConsole();
        else
            writer.write(printTable.printHeaderFor2inFile());

        while (Math.abs(functions.f(current_x, number)) > e) {
            current_x = functions.x0(start_X, end_X, number);
            if (iterations == 0) {
                if (!flag_file)
                    printTable.printLineFor2inConsole(iterations + 1, start_X, end_X, current_x, functions.f(start_X, number),
                            functions.f(end_X, number), functions.f(current_x, number), 0);
                else
                    writer.write(printTable.printLineFor2inFile(iterations + 1, start_X, end_X, current_x,
                            functions.f(start_X, number), functions.f(end_X, number), functions.f(current_x, number), 0));
            } else {
                if (!flag_file)
                    printTable.printLineFor2inConsole(iterations + 1, start_X, end_X, current_x, functions.f(start_X, number),
                            functions.f(end_X, number), functions.f(current_x, number), Math.abs(last_x - current_x));
                else
                    writer.write(printTable.printLineFor2inFile(iterations + 1, start_X, end_X, current_x,
                            functions.f(start_X, number), functions.f(end_X, number), functions.f(current_x, number),
                            Math.abs(last_x - current_x)));
            }

            if (functions.f(start_X, number) > 0 && functions.f(current_x, number) < 0 || functions.f(start_X, number) < 0
                    && functions.f(current_x, number) > 0) {
                end_X = current_x;
            } else if (functions.f(end_X, number) > 0 && functions.f(current_x, number) < 0 || functions.f(end_X, number) < 0
                    && functions.f(current_x, number) > 0) {
                start_X = current_x;
            }

            last_x = current_x;
            iterations++;
        }

        if (!flag_file) {
            System.out.println("\nКорень уравнения:");
            System.out.printf("x* = %.13f%n", current_x);
            System.out.printf("f(x*) = %.13f%n", functions.f(current_x, number));
        } else {
            writer.write("\nКорень уравнения:\n");
            writer.write(String.format("x* = %.13f%n\n", current_x));
            writer.write(String.format("f(x*) = %.13f%n", functions.f(current_x, number)));
            writer.flush();
        }

        drawChart.draw(Math.round(a), Math.round(b));
    }
}