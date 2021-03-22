public class CheckInputInterval {
    Functions functions = new Functions();


    public boolean checkInterval(double a, double b, int number) { //Проверка на наличие корней
        boolean rootFlag = false;

        for (double i = a; i <= b - 0.1; i = i + 0.1)
            if (functions.f(i, number) > 0 && functions.f(i + 0.1, number) < 0 || functions.f(i, number) < 0 &&
                    functions.f(i + 0.1, number) > 0) {
                rootFlag = true;
                break;
            }

        if (functions.f(a, number) * functions.f(b, number) < 0 && !rootFlag)
            rootFlag = true;

        return rootFlag;
    }

    public int checkIntervalRoots(double a, double b, int number) { //Подсчет количества корней
        int roots = 0;
        for (double i = a; i <= b; i += 0.1) {
            if (functions.f(i, number) > 0 && functions.f(i + 0.1, number) < 0 || functions.f(i, number) < 0 &&
                    functions.f(i + 0.1, number) > 0) {
                roots++;
            }
        }
        return roots;
    }
}
