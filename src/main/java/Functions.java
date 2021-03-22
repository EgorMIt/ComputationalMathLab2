public class Functions {
    public double f(double x, int number) { //Базовая функция
        if (number == 1)
            return 4.45 * Math.pow(x, 3) + 7.81 * Math.pow(x, 2) - 9.62 * x - 8.1;
        else if (number == 2)
            return Math.sin(x) + 0.5;
        else if (number == 3)
            return Math.pow(x, 3) - x + 4;
        else
            return 1;
    }

    public double f_(double x, int number) { //Первая производная
        if (number == 1)
            return 13.35 * Math.pow(x, 2) + 15.62 * x - 9.62;
        else if (number == 2)
            return Math.cos(x);
        else if (number == 3)
            return 3 * Math.pow(x, 2) - 1;
        else
            return 1;
    }

    public double f__(double x, int number) { //Вторая производная
        if (number == 1)
            return 26.7 * x + 15.62;
        else if (number == 2)
            return -Math.sin(x);
        else if (number == 3)
            return 6 * x;
        else
            return 1;
    }

    public double x0(double a, double b, int number) { //Начальный элемент
        return a - ((b - a) / (f(b, number) - f(a, number))) * f(a, number);
    }

    public double Fi(double x, double lambda, int number) {
        return x + lambda * f(x, number);
    }

    public double Fi_(double x, double lambda, int number) {
        return 1 + lambda * f_(x, number);
    }

    public double getMaxF_(double a, double b, int number) {
        return Math.max(f_(a, number), f_(b, number));
    }

    public double getMaxQ(double a, double b, double lambda, int number) {
        return Math.max(Math.abs(Fi_(a, lambda, number)), Math.abs(Fi_(b, lambda, number)));
    }

    public double setNextX(double x1, double x2, int number) {
        return x1 - (x1 - x2) * f(x1, number) / (f(x1, number) - f(x2, number));
    }

    public double setStartX(double a, double b, int number) {
        if (f(a, number) * f__(a, number) > 0) {
            return a;
        } else if (f(b, number) * f__(b, number) > 0) {
            return b;
        } else {
            return 1;
        }
    }
}
