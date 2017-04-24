
package Vista;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de la vista del Gràfic a mostrar en l'apartat de gràfic del servidor
 *
 * Created by Grup 6 on 19/04/2017.
*/
public class Grafic_usuari extends JFrame {
    private JPanel jpGrafic;
    private JLabel jlTitle;
    private JFreeChart jfPuntacio;

    private String login;

    public Grafic_usuari() {
        this.setTitle ("Graphic usuari");
        this.setSize (350,350);
        this.setResizable(true);

        jpGrafic = new JPanel();
        jpGrafic.setLayout(new GridLayout(2,1));
        jlTitle = new JLabel("Login");
        //jfPuntacio = ;


        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        line_chart_dataset.addValue(80, "visitas", "Julio");
        line_chart_dataset.addValue(300, "visitas", "Agosto");
        line_chart_dataset.addValue(600, "visitas", "Septiembre");
        line_chart_dataset.addValue(1200, "visitas", "Octubre");
        line_chart_dataset.addValue(2400, "visitas", "Noviembre");

        // Creando el Grafico
        JFreeChart chart= ChartFactory.createLineChart("Trafico en el Blog",
                "Mes","Visitas",line_chart_dataset, PlotOrientation.VERTICAL,
                true,true,false);

        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);

        jpGrafic.add(jlTitle, BorderLayout.CENTER);

        jpGrafic.add(chartPanel);

        this.getContentPane().add(jpGrafic, BorderLayout.CENTER);
    }
}
