package ru.sbt.bit.tokenring;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
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

        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        r1.setSeriesPaint(0, new Color(0xff, 0xff, 0x00));
        r1.setSeriesPaint(1, new Color(0x00, 0xff, 0xff));
        r1.setSeriesShapesVisible(0,  false);
        r1.setSeriesShapesVisible(1,  false);


        JFreeChart chart = ChartFactory.createXYAreaChart("Throughput",
                "Time",
                "n(count)",
                xyseries,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDataset(0, xyseries);
        plot.setRenderer(0, r1);
        plot.setBackgroundPaint(new Color(0xFF, 0xFF, 0xFF));
        plot.setDomainGridlinePaint(new Color(0x00, 0x00, 0xff));
        plot.setRangeGridlinePaint(new Color(0xff, 0x00, 0x00));
        chart.setBackgroundPaint(Color.white);
        chart.setBorderVisible(false);
        try {
            ChartUtilities.saveChartAsPNG(new File("graph/throughput.png"), chart, 500, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
