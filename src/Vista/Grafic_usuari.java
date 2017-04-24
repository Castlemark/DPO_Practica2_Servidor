package Vista;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sullivan on 19/04/2017.
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

        jpGrafic.setLayout(new GridLayout(2,1));
        jlTitle = new JLabel(login);
        //jfPuntacio = ;

        jpGrafic.add(jlTitle, BorderLayout.CENTER);


        this.getContentPane().add(jpGrafic, BorderLayout.PAGE_START);
    }
}
