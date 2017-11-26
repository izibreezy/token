package ru.sbt.bit.tokenring;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

public class Plot {
    public void makeAPlot(double[] x, double[] y) {
        XYSeries array = new XYSeries("Throughput");
        for (int i = 0; i < x.length; i++) {
            array.add(x[i], y[i]);
        }

        XYSeriesCollection xyseries = new XYSeriesCollection();
        xyseries.addSeries(array);

        JFreeChart chart = ChartFactory.createXYLineChart("Throughput",
                "time",
                "messages",
                xyseries,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        try {
            ChartUtilities.saveChartAsPNG(new File("result/test_throughput.png"), chart, 800, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
