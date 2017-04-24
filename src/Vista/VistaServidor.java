package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de la vista del menú principal del servidor
 * Created by Grup 6 on 11/04/2017.
 */
public class VistaServidor extends JFrame {
    private JMenuBar jmbbarraMenu;
    private JMenu jmRegistrar;
    private JMenu jmGestionar;
    private JMenu jmConfiguracio;
    private JMenu jmRanquing;
    private JMenu jmGrafic;

    /**
     * Constructor de la vista del menú del servidor. Inicialitza els elements a mostrar
     */
    public VistaServidor (){
        this.setTitle("Servidor Troner");
        this.setSize (350, 350);
        this.setResizable(true);

        Color blau_cel = new Color(135, 206, 250);

        jmbbarraMenu = new JMenuBar();
        jmRegistrar = new JMenu("Registrar");
        jmGestionar = new JMenu("Gestionar");
        jmConfiguracio = new JMenu("Configuració");
        jmRanquing = new JMenu("Ranquing");
        jmGrafic = new JMenu("Gràfic");

        jmbbarraMenu.add(jmRegistrar);
        jmbbarraMenu.add(jmConfiguracio);
        jmbbarraMenu.add(jmGestionar);
        jmbbarraMenu.add(jmRanquing);
        jmbbarraMenu.add(jmGrafic);

        jmbbarraMenu.setBackground(blau_cel);
        this.setJMenuBar(jmbbarraMenu);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void registerController(Controlador c){

        jmConfiguracio.setActionCommand("CONFIGURACIO");
        jmRegistrar.setActionCommand("REGISTRAR");
        jmGestionar.setActionCommand("GESTIONAR");
        jmRanquing.setActionCommand("RANQUING");
        jmGrafic.setActionCommand("GRAFIC");

        jmConfiguracio.addActionListener(c);
        jmRegistrar.addActionListener(c);
        jmGestionar.addActionListener(c);
        jmRanquing.addActionListener(c);
        jmGrafic.addActionListener(c);
    }
}
