package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.nio.file.FileSystemLoopException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe de la vista del menú principal del servidor
 * Created by Grup 6 on 11/04/2017.
 */
public class VistaServidor extends JFrame {

    private Color blau_cel = new Color(135, 206, 250);

    private Configuracio config;
    private Gestionar gest;
    private Registrar reg;
    private Graphic graf;
    private Ranquing rank;
    private Gest gs;

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
        gs = new Gest();



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
        this.getContentPane().add("GEST", gs);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void registerController(Controlador c){

        jmConfiguracio.setActionCommand("CONFIGURACIO");
        jmRegistrar.setActionCommand("REGISTRAR");
        jmGestionar.setActionCommand("GEST");
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
        gs.registerController(c);
    }

    public void changePanel(String which){
        layout.show(this.getContentPane(), which);
    }

    //registre
    public String getLogin(){
        return reg.getLogin();
    }

    //registre
    public String getMail(){
        return reg.getMail();
    }

    //registre
    public String getPassword(){
        return reg.getPassword();
    }

    //registre
    public String getPassword2(){
        return reg.getPassword2();
    }

    //Registre
    public void showMessage(boolean correcte){

        if (correcte){
            JOptionPane.showMessageDialog(null, "Registre Correcte");
        }
        else {
            JOptionPane.showMessageDialog(null, "No s'ha pogut realitzar el registre");
        }
    }

    //configuració
    public String getPort(){
        return config.getPort();
    }

    //pestaña grafics
    public void grUupdateLoginList(ArrayList<String> logins){
        graf.setLlistaLogin(logins);
    }

    //pestanya gestionar
    public void gsUpdateList(ArrayList<String> logins){
        gs.setLlistaLogin(logins);
    }

    //pestanya gestionar
    public void gsUpdateInfo(String text){
        gs.updateInfo(text);
    }

    //pestanya gestionar
    public String gsGetSelectedLogin(){
        return gs.getSelectedLogin();
    }

    public void actualitzaPort(int port){
        config.actualitzaPort(port);
    }
}
