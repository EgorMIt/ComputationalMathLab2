import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;

public class DrawChart {
    //Отрисовка графика функции в файл
    public void draw(long a, long b) {
        a -= 1;
        b += 1;

        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        for (long i = a; i <= b; i++) {
            line_chart_dataset.addValue(4.45 * Math.pow(i, 3) + 7.81 * Math.pow(i, 2) - 9.62 * i - 8.17,
                    "function", String.valueOf(i));
        }


        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "fun(x)", "x",
                "y",
                line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1920;    /* Width of the image */
        int height = 1080;   /* Height of the image */
        File lineChart = new File("src/main/res/Chart.jpeg");
        try {
            ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double f(double x) {
        return 4.45 * Math.pow(x, 3) + 7.81 * Math.pow(x, 2) - 9.62 * x - 8.17;
    }

    public void drawForIt(long a, long b, double lambda) {
        a -= 1;
        b += 1;

        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        for (long i = a; i <= b; i++) {
            line_chart_dataset.addValue(i - lambda * f(i),
                    "function", String.valueOf(i));
        }

        for (long i = a; i <= b; i++) {
            line_chart_dataset.addValue(i,
                    "x=y", String.valueOf(i));
        }


        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "fun(x)", "x",
                "y",
                line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1920;    /* Width of the image */
        int height = 1080;   /* Height of the image */
        File lineChart = new File("Chart.jpeg");
        try {
            ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
