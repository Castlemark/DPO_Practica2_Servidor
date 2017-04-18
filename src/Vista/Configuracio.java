package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by sullivan on 18/04/2017.
 */
public class Configuracio extends JFrame {
    private JPanel jpConfiguracio;
    private JPanel jpAtras;
    private JLabel jlPort;
    private JPanel jpPort;
    private JPanel jpButtons;
    private JButton jbAtras;
    private JLabel jlTitle;
    private JTextField jtPort;
    private JButton jbIniciar;
    private JButton jbAturar;

    public Configuracio () {
        this.setTitle ("Configuració");
        this.setSize (350,350);
        this.setResizable(true);

        jpConfiguracio = new JPanel();
        jpAtras = new JPanel(new BorderLayout());
        jpPort = new JPanel(new BorderLayout());
        jpButtons = new JPanel(new BorderLayout());

        jbAtras = new JButton("<-");
        jlTitle = new JLabel("            Configuració");
        jlPort = new JLabel("Port         ");
        jtPort = new JTextField();
        jbIniciar = new JButton("Iniciar");
        jbAturar = new JButton("Aturar");

        jpConfiguracio.setLayout(new GridLayout(3,1));
        jpAtras.add (jbAtras, BorderLayout.LINE_START);
        jpAtras.add (jlTitle, BorderLayout.CENTER);
        jpPort.add (jlPort, BorderLayout.LINE_START);
        jpPort.add (jtPort, BorderLayout.CENTER);
        jpButtons.add (jbIniciar, BorderLayout.CENTER);
        jpButtons.add (jbAturar, BorderLayout.LINE_END);

        jpConfiguracio.add(jpAtras, BorderLayout.CENTER);
        jpConfiguracio.add(jpPort);
        jpConfiguracio.add(jpButtons, BorderLayout.LINE_END);

        this.getContentPane().add(jpConfiguracio,BorderLayout.PAGE_START);
    }

}
