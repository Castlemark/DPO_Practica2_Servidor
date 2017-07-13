package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by sullivan on 18/04/2017.
 */
public class Configuracio extends JPanel {
    private JPanel jpConfiguracio;
    private JLabel jlPort;
    private JPanel jpPort;
    private JPanel jpButtons;
    private JButton jbAtras;
    private JLabel jlTitle;
    private JTextField jtPort;
    private JButton jbIniciar;
    private JButton jbAturar;

    public Configuracio () {
        this.setSize (350,350);

        jpConfiguracio = new JPanel();
        jpPort = new JPanel(new BorderLayout());
        jpButtons = new JPanel(new BorderLayout());

        jlTitle = new JLabel("Configuració", SwingConstants.CENTER);
        jlPort = new JLabel("Port  ");
        jtPort = new JTextField();
        jtPort.setHorizontalAlignment(SwingConstants.RIGHT);
        jbIniciar = new JButton("Iniciar");
        jbAturar = new JButton("Aturar");

        jpConfiguracio.setLayout(new GridLayout(3,1));
        jpPort.add (jlPort, BorderLayout.LINE_START);
        jpPort.add (jtPort, BorderLayout.CENTER);
        jpButtons.add (jbIniciar, BorderLayout.CENTER);
        jpButtons.add (jbAturar, BorderLayout.LINE_END);

        jpConfiguracio.add(jlTitle, BorderLayout.CENTER);
        jpConfiguracio.add(jpPort);
        jpConfiguracio.add(jpButtons, BorderLayout.LINE_END);

        this.add(jpConfiguracio, BorderLayout.SOUTH);
    }

    public void actualitzaPort(int port){
        String portStr= Integer.toString(port);
        jtPort.setText(portStr);
    }
    public void registerController(ActionListener c){
        jbIniciar.setActionCommand("INICIAR");
        jbAturar.setActionCommand("ATURAR");

        jbIniciar.addActionListener(c);
        jbAturar.addActionListener(c);
    }

    public String getPort(){
        return jtPort.getText();
    }

}
