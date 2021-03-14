public class PrintTable {

    //Таблица для второго метода
    public void printHeaderFor2inConsole() {
        System.out.println("+-------------------------------------------------" +
                "------------------------------------------------------------------------------+");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", "a", "b", "x", "F(a)", "F(b)", "F(x)", " |xn+1 - xn| ");
        System.out.println("+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------+");
    }

    public String printHeaderFor2inFile() {
        String str = "+-------------------------------------------------" +
                "------------------------------------------------------------------------------+\n";
        str += String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", "a", "b", "x", "F(a)", "F(b)", "F(x)", " |xn+1 - xn| ");
        str += "+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------+\n";
        return str;
    }


    public void printLineFor2inConsole(int i, double a, double b, double x, double f_a, double f_b,
                                       double f_x, double abs) {
        System.out.printf("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                i, a, b, x, f_a, f_b, f_x, abs);
        System.out.println("|---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------|");
    }

    public String printLineFor2inFile(int i, double a, double b, double x, double f_a, double f_b,
                                      double f_x, double abs) {
        String str = String.format("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                i, a, b, x, f_a, f_b, f_x, abs);
        str += "|---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------|\n";
        return str;
    }

    //Таблица для четвертого метода
    public void printHeaderFor4inConsole() {
        System.out.println("+-----------------------------------------------------" +
                "--------------------------------------------------------------------------+");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", " xn-1", " f(xn-1)", " xn", " f(xn)", " xn+1", " f(xn+1)", " |xn+1 - xn| ");
        System.out.println("+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------+");
    }

    public String printHeaderFor4inFile() {
        String str = "+-------------------------------------------------" +
                "----------------------------------------------+\n";
        str += String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", " xn-1", " f(xn-1)", " xn", " f(xn)", " xn+1", " f(xn+1)", " |xn+1 - xn| ");
        str += "+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------+";
        return str;
    }

    public void printLineFor4InConsole(int i, double xn_1, double f_xn_1, double xn, double f_xn, double xn__1, double f_xn__1, double abs) {

        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                i, Math.round(xn_1 * 100.0) / 100.0, Math.round(f_xn_1 * 100.0) / 100.0, Math.round(xn * 100.0) / 100.0,
                Math.round(f_xn * 100.0) / 100.0, Math.round(xn__1 * 100.0) / 100.0,
                Math.round(f_xn__1 * 100.0) / 100.0, Math.round(abs * 100.0) / 100.0);
        System.out.println("+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------+");
    }

    public String printLineFor4inFile(int i, double xn_1, double f_xn_1, double xn, double f_xn, double xn__1, double f_xn__1, double abs) {
        String str = String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                i, Math.round(xn_1 * 100.0) / 100.0, Math.round(f_xn_1 * 100.0) / 100.0, Math.round(xn * 100.0) / 100.0,
                Math.round(f_xn * 100.0) / 100.0, Math.round(xn__1 * 100.0) / 100.0,
                Math.round(f_xn__1 * 100.0) / 100.0, Math.round(abs * 100.0) / 100.0);
        str += "+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+---------------+---------------+\n";
        return str;
    }

    //Таблица для пятого метода
    public void printHeaderFor5inConsole() {
        System.out.println("+-------------------------------------------------" +
                "----------------------------------------------+");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", "xi", "xi+1", "Fi(xi+1)", "f(xi+1)", " |xi+1 - xi| ");
        System.out.println("+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+");
    }

    public String printHeaderFor5inFile() {
        String str = "+-------------------------------------------------" +
                "----------------------------------------------+\n";
        str += String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "№", "xi", "xi+1", "Fi(xi+1)", "f(xi+1)", " |xi+1 - xi| ");
        str += "+---------------+---------------+---------------+" +
                "---------------+---------------+---------------+\n";
        return str;
    }

    public void printLineFor5inConsole(int i, double xi, double xi_1, double Fi, double f, double abs) {
        System.out.printf("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                i, xi, xi_1, Fi, f, abs);
        System.out.println("|---------------+---------------+---------------+" +
                "---------------+---------------+---------------|");
    }

    public String printLineFor5inFile(int i, double xi, double xi_1, double Fi, double f, double abs) {
        String str = String.format("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                i, xi, xi_1, Fi, f, abs);
        str += "|---------------+---------------+---------------+" +
                "---------------+---------------+---------------|\n";
        return str;
    }
}