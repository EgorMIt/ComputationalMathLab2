public class CheckInputInterval {
    private double f(double x) {
        return 4.45 * Math.pow(x, 3) + 7.81 * Math.pow(x, 2) - 9.62 * x - 8.17;
    }

    public boolean checkInterval(double a, double b) { //Проверка на наличие корней
        boolean rootFlag = false;

        for (double i = a; i <= b - 0.1; i = i + 0.1)
            if (f(i) > 0 && f(i + 0.1) < 0 || f(i) < 0 && f(i + 0.1) > 0) {
                rootFlag = true;
                break;
            }

        if (f(a) * f(b) < 0 && !rootFlag)
            rootFlag = true;

        return rootFlag;
    }

    public int checkIntervalRoots(double a, double b) { //Подсчет количества корней
        int roots = 0;
        for (double i = a; i <= b; i += 0.1) {
            if (f(i) > 0 && f(i + 0.1) < 0 || f(i) < 0 && f(i + 0.1) > 0) {
                roots++;
            }
        }
        return roots;
    }
}
