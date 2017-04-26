package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private Ranquing rank;

    private  CardLayout layout;

    private JMenuBar jmbbarraMenu;
    private JMenuItem jmRegistrar;
    private JMenuItem jmGestionar;
    private JMenuItem jmConfiguracio;
    private JMenuItem jmRanquing;
    private JMenuItem jmGrafic;

    private JTextField jtproba;

    /**
     * Constructor de la vista del menú del servidor. Inicialitza els elements a mostrar
     */
    public VistaServidor (){
        this.setTitle("Servidor Troner");
        this.setSize (700, 600);
        this.setResizable(true);

        layout = new CardLayout();
        this.getContentPane().setLayout(layout);

        config = new Configuracio();
        gest = new Gestionar();
        reg = new Registrar();
        graf = new Graphic();
        rank = new Ranquing();


        jmbbarraMenu = new JMenuBar();
        jmRegistrar = new JMenuItem("Registrar");
        jmGestionar = new JMenuItem("Gestionar");
        jmConfiguracio = new JMenuItem("Configuració");
        jmRanquing = new JMenuItem("Ranquing");
        jmGrafic = new JMenuItem("Gràfic");

        jmRegistrar.setBackground(blau_cel);
        jmGestionar.setBackground(blau_cel);
        jmConfiguracio.setBackground(blau_cel);
        jmRanquing.setBackground(blau_cel);
        jmGrafic.setBackground(blau_cel);

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
        this.getContentPane().add("RANQUING", rank);

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

        reg.registerController(c);
        config.registerController(c);
        gest.registerController(c);
        graf.registerController(c);
        rank.registerController(c);
    }

    public void changePanel(String which){
        layout.show(this.getContentPane(), which);
    }

    public String getLogin(){
        return reg.getLogin();
    }

    public String getMail(){
        return reg.getMail();
    }

    public String getPassword(){
        return reg.getPassword();
    }

    public String getPort(){
        return config.getPort();
    }

    public void updateLoginList(ArrayList<String> logins){
        graf.setLlistaLogin(logins);
    }
}
