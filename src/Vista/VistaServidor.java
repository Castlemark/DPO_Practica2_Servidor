package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de la vista del menú principal del servidor
 * Created by Grup 6 on 11/04/2017.
 */
public class VistaServidor extends JFrame {

    Color blau_cel = new Color(135, 206, 250);

    private Configuracio config;
    private Gestionar gest;
    private Registrar reg;
    private Graphic graf;

    private  CardLayout layout;

    private JToolBar toolBar;
    private JButton jbRegistrar;
    private JButton jbConfiguracio;
    private JButton jbGestionar;
    private JButton jbRanquing;
    private JButton jbGrafic;

    private JMenuBar jmbbarraMenu;
    private JMenu jmRegistrar;
    private JMenu jmGestionar;
    private JMenu jmConfiguracio;
    private JMenu jmRanquing;
    private JMenu jmGrafic;

    private JTextField jtproba;

    /**
     * Constructor de la vista del menú del servidor. Inicialitza els elements a mostrar
     */
    public VistaServidor (){
        this.setTitle("Servidor Troner");
        this.setSize (350, 350);
        this.setResizable(true);

        layout = new CardLayout();
        this.getContentPane().setLayout(layout);

        config = new Configuracio();
        gest = new Gestionar();
        reg = new Registrar();
        graf = new Graphic();


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

        this.getContentPane().add("CONFIGURACIO", config);
        this.getContentPane().add("REGISTRAR", reg);
        this.getContentPane().add("GESTIONAR", gest);
        this.getContentPane().add("GRAFIC", graf);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void registerController(Controlador c){

        jmConfiguracio.setActionCommand("CONFIGURACIO");
        jmRegistrar.setActionCommand("REGISTRAR");
        jmGestionar.setActionCommand("GESTIONAR");
        jmRanquing.setActionCommand("RANQUING");
        jmGrafic.setActionCommand("GRAFIC");

        jmConfiguracio.addMenuListener(c);
        jmRegistrar.addMenuListener(c);
        jmGestionar.addMenuListener(c);
        jmRanquing.addMenuListener(c);
        jmGrafic.addMenuListener(c);

        reg.registerController(c);
        config.registerController(c);
        gest.registerController(c);
        graf.registerController(c);
    }

    public void changePanel(String which){
        layout.show(this.getContentPane(), which);
    }
}
