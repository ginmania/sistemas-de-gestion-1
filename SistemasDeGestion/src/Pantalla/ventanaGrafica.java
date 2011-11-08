/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla;

/**
 *
 * @author natalia
 */
import org.jfree.data.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import java.awt.image.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ventanaGrafica extends java.awt.Frame {

    BufferedImage grafica;
    BufferedImage grafica2;

    /** Creates new form ventanaGrafica */
    public ventanaGrafica(double[] demReal, double[] demPronosticada, double[] señalRastreo, int L, String nombreProducto) {
        initComponents();
        this.setSize(700, 700);
        this.setTitle(nombreProducto);
        this.show();

        grafica = creaImagen(demReal, demPronosticada);
        grafica2 = creaImagen2(señalRastreo, L);
    }

    public ventanaGrafica(double[][] curvaABC) {
        initComponents();
        this.setSize(650, 350);
        this.setTitle("Curva ABC");
        this.show();
        grafica = creaImagen(curvaABC);
    }

    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {
        dispose();
    }

    public BufferedImage creaImagen(double[] demReal, double[] demPronosticada) {
        XYSeries series = new XYSeries("Demanda Real");
        for (int i = 1; i <= 13; i++) {
            series.add(i, demReal[i - 1]);
        }

        XYSeries series2 = new XYSeries("Demanda Pronosticada");
        for (int i = 1; i <= 13; i++) {
            series2.add(i, demPronosticada[i - 1]);
        }

        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(series);
        xyseriescollection.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart("Demanda Real vs Demanda Pronosticada",
                "Periodo", "Demanda", xyseriescollection, PlotOrientation.VERTICAL,
                true, true, true // Show legend
                );

        BufferedImage image = chart.createBufferedImage(600, 300);
        return image;
    }

    public BufferedImage creaImagen2(double[] señalRastreo, int L) {
        XYSeries series = new XYSeries("Señal Rastreo");
        for (int i = 1; i <= 13; i++) {
            series.add(i, señalRastreo[i - 1]);
        }

        XYSeries series2 = new XYSeries("Lmin");
        for (int i = 1; i <= 13; i++) {
            series2.add(i, -L);
        }

        XYSeries series3 = new XYSeries("Lmax");
        for (int i = 1; i <= 13; i++) {
            series3.add(i, L);
        }

        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(series);
        xyseriescollection.addSeries(series2);
        xyseriescollection.addSeries(series3);
        JFreeChart chart = ChartFactory.createXYLineChart("Señal de Rastreo",
                "Periodo", "Nivel", xyseriescollection, PlotOrientation.VERTICAL,
                true, true, true // Show legend
                );

        BufferedImage image = chart.createBufferedImage(600, 300);
        return image;
    }

    public BufferedImage creaImagen(double[][] curvaABC) {
        XYSeries series = new XYSeries("Curva ABC");
        series.add(0, 0);
        for (int i = 0; i < curvaABC.length; i++) {
            series.add(curvaABC[i][0], curvaABC[i][1]);
        }
        series.add(100, 100);

        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("Curva ABC",
                "Porcentaje de Artículos", "Porcentaje del Capital", xyseriescollection, PlotOrientation.VERTICAL,
                true, true, true // Show legend
                );

        BufferedImage image = chart.createBufferedImage(600, 300);
        return image;
    }

    @Override
    public void paint(java.awt.Graphics g) {

        g.drawImage(grafica, 30, 30, null);
        g.drawImage(grafica2, 30, 331, null);
    }
}