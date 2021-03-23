import java.io.FileWriter;
import java.io.IOException;

public class Method5 { //Метод простых итераций
    DrawChart drawChart = new DrawChart();
    PrintTable printTable = new PrintTable();
    Functions functions = new Functions();

    double a, b, e, start_X, last_X, q, abs;
    double lambda = 1;
    int iterations = 0, number;
    boolean flag_file;

    public Method5(double a, double b, double e, int number, boolean flag_file) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.number = number;
        this.flag_file = flag_file;
    }

    public void startCalculations() throws IOException {
        FileWriter writer = null;

        if (flag_file) {
            writer = new FileWriter("src/main/res/out5.txt", false);
            writer.write("Запущен метод простых итераций\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n\n");
            writer.flush();
        }else{
            System.out.println("Запущен метод простых итераций\n" +
                    "Решение уравнения на интервале [ " + a + "; " + b + " ]\nС точностью: " + e + "\n\n");
        }


        lambda = -1 / functions.getMaxF_(a, b, number);
        last_X = functions.setStartX(a, b, number);
        start_X = functions.Fi(last_X, lambda, number);

        q = functions.getMaxQ(a,b,lambda, number);

        if (q<1){
            if(q>0.5 && q < 1){
                e = (1-q) * e / q;
                if (!flag_file) {
                    System.out.println("Критерий окончания:  |Xn - Xn-1| > (1-q)*e/q ");
                } else {
                    writer.write("Критерий окончания:  |Xn - Xn-1| > (1-q)*e/q \n\n");
                    writer.flush();
                }
            }
            else if (q >0 && q <= 0.5) {
                if (!flag_file) {
                    System.out.println("Критерий окончания:  |Xn - Xn-1| > e ");
                } else {
                    writer.write("Критерий окончания:  |Xn - Xn-1| > e ");
                }
            }

            if (!flag_file)
                printTable.printHeaderFor5inConsole();
            else
                writer.write(printTable.printHeaderFor5inFile());


            abs = Math.abs(start_X-last_X);

            while(abs > e)
            {
                if (!flag_file) {
                    printTable.printLineFor5inConsole(iterations+1, last_X, start_X,
                            functions.Fi(start_X, lambda, number), functions.f(start_X, number), Math.abs(start_X - last_X));
                } else {
                    writer.write(printTable.printLineFor5inFile(iterations+1, last_X, start_X,
                            functions.Fi(start_X, lambda, number), functions.f(start_X, number), Math.abs(start_X - last_X)));
                }

                last_X = start_X;
                start_X = functions.Fi(last_X, lambda, number);
                abs = Math.abs(start_X - last_X);
                iterations++;
            }

            if (!flag_file) {
                printTable.printLineFor5inConsole(iterations+1, last_X, start_X, functions.Fi(start_X, lambda, number),
                        functions.f(start_X, number), Math.abs(start_X - last_X));
            } else {
                writer.write(printTable.printLineFor5inFile(iterations+1, last_X, start_X,
                        functions.Fi(start_X, lambda, number), functions.f(start_X, number), Math.abs(start_X - last_X)));
            }

            if (flag_file) {
                writer.write("\nКорень уравнения:\n");
                writer.write(String.format("x* = %.10f%n", last_X));
                writer.write(String.format("f(x*) = %.10f%n", functions.f(last_X, number)));
                writer.flush();
            } else {
                System.out.println("\nКорень уравнения:");
                System.out.printf("x* = %.10f%n", last_X);
                System.out.printf("f(x*) = %.10f%n", functions.f(last_X, number));
            }
        }
        else {
            if (!flag_file)
                System.out.println("\nКоэффициент сходимости превышает 1: q = " + q);
            else {
                writer.write("\nКоэффициент сходимости превышает 1: q = " + q);
                writer.flush();
            }
        }
        drawChart.drawForIteration(Math.round(a), Math.round(b), lambda, number);
    }
}