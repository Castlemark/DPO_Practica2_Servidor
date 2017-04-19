package Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de la vista del menú principal del servidor
 * Created by Grup 6 on 11/04/2017.
 */
public class VistaServidor extends JFrame {
    private JButton jbRegistrar;
    private JButton jbGestionar;
    private JButton jbConfiguracio;
    private JButton jbRanquing;
    private JButton jbGrafic;
    private JPanel jpButtons;

    /**
     * Constructor de la vista del menú del servidor. Inicialitza els elements a mostrar
     */
    public VistaServidor (){
        this.setTitle("Servidor Troner");
        this.setSize (350, 350);
        this.setResizable(true);
        jpButtons = new JPanel();
        jpButtons.setLayout(new GridLayout(5,1));

        jbRegistrar = new JButton("Registrar usuaris");
        jbGestionar = new JButton("Gestionar usuaris");
        jbConfiguracio = new JButton("Configurar el sistema");
        jbRanquing = new JButton("Visualitzar ranquing de jugadors");
        jbGrafic = new JButton("Evolució jugador");

        jpButtons.add(jbRegistrar);
        jpButtons.add(jbGestionar);
        jpButtons.add(jbConfiguracio);
        jpButtons.add(jbRanquing);
        jpButtons.add(jbGrafic);

        getContentPane().add(jpButtons, BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
