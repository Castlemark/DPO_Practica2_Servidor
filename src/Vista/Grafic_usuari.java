
package Vista;


import Controlador.Controlador;
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
public class Grafic_usuari extends JPanel {
    private JPanel jpGrafic;
    private JLabel jlTitle;
    private JFreeChart jfPuntacio;

    private String login;

    public Grafic_usuari() {


       // jpGrafic = new JPanel();
        //jpGrafic.setLayout(new GridLayout(1,1));
        jlTitle = new JLabel("Login");
        //jfPuntacio = ;


        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        line_chart_dataset.addValue(80, "punts", "Julio");
        line_chart_dataset.addValue(300, "punts", "Agosto");
        line_chart_dataset.addValue(600, "punts", "Septiembre");
        line_chart_dataset.addValue(1200, "punts", "Octubre");
        line_chart_dataset.addValue(2400, "punts", "Noviembre");

        // Creando el Grafico
        JFreeChart chart= ChartFactory.createLineChart("Gràfic de l'usuari",
                "Partida","Punts",line_chart_dataset, PlotOrientation.VERTICAL,
                true,true,false);

        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);

       // jpGrafic.add(jlTitle, BorderLayout.CENTER);

        this.add(chartPanel);

        //this.add(jpGrafic, BorderLayout.CENTER);
    }

    public void registerController(Controlador c){

    }
}
