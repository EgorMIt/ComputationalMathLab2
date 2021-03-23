import java.io.FileWriter;
import java.io.IOException;

public class Method4 { //Метод секущих
    DrawChart drawChart = new DrawChart();
    PrintTable printTable = new PrintTable();
    Functions functions = new Functions();

    double a, b, e, start_X, next_X, last_x;
    int iterations = 0, number;
    boolean flag_file;


    public Method4(double a, double b, double e, int number, boolean flag_file) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.number = number;
        this.flag_file = flag_file;
    }


    public void startCalculations() throws IOException {
        FileWriter writer = null;

        if (flag_file) {
            writer = new FileWriter("src/main/res/out4.txt", false);
            writer.write("Запущен метод секущих\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n\n");
        } else {
            System.out.println("Запущен метод секущих\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n\n");
        }

        next_X = functions.setStartX(a, b, number);
        last_x = next_X;

        if (!flag_file)
            printTable.printHeaderFor4inConsole();
        else
            writer.write(printTable.printHeaderFor4inFile());


        while (Math.abs(functions.f(start_X, number)) > e) {
            start_X = next_X;
            if (iterations == 0)
                next_X = start_X - functions.f(start_X, number) / functions.f_(start_X, number);
            else
                next_X = functions.setNextX(start_X, last_x, number);

            if (iterations == 0) {
                if (!flag_file)
                    printTable.printLineFor4InConsole(iterations + 1, 0, 0, start_X,
                            functions.f(start_X, number), next_X, functions.f(next_X, number), 0);
                else
                    writer.write(printTable.printLineFor4inFile(iterations + 1, 0, 0, start_X,
                            functions.f(start_X, number), next_X, functions.f(next_X, number), 0));
            } else {
                if (!flag_file)
                    printTable.printLineFor4InConsole(iterations + 1, last_x, functions.f(last_x, number), start_X,
                            functions.f(start_X, number), next_X, functions.f(next_X, number), Math.abs(next_X - start_X));
                else
                    writer.write(printTable.printLineFor4inFile(iterations + 1, last_x, functions.f(last_x, number),
                            start_X, functions.f(start_X, number), next_X, functions.f(next_X, number), Math.abs(next_X - start_X)));
            }
            last_x = start_X;
            iterations++;
        }

        if (!flag_file) {
            System.out.println("\nКорень уравнения:");
            System.out.printf("x* = %.10f%n", start_X);
            System.out.printf("f(x*) = %.10f%n", functions.f(start_X, number));
        } else {
            writer.write("\nКорень уравнения:\n");
            writer.write(String.format("x* = %.10f%n", start_X));
            writer.write(String.format("f(x*) = %.10f%n", functions.f(start_X, number)));
            writer.flush();
        }
        drawChart.draw(Math.round(a), Math.round(b), number);
    }
}