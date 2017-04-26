package Vista;

import Controlador.Controlador;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by sullivan on 19/04/2017.
 */
public class Graphic extends JPanel {
    private JPanel jpGraphic;
    private JPanel jpLogin;
    private JPanel jpMode;

    private JButton jbAtras;
    private JLabel jlTitle;
    private JLabel jlLogin;
    private JLabel jlMode;

    private JComboBox jcLogin;
    private JComboBox jcMode;

    private String[] mode = {"2", "4", "Torneig"};

    public Graphic(){

        this.setSize (350,350);

      //  mode = new String[];

        //mode ;

        jpGraphic = new JPanel();
        jpLogin = new JPanel(new BorderLayout());
        jpMode = new JPanel(new BorderLayout());


        jlTitle = new JLabel("            Graphic");
        jlLogin = new JLabel("Login          ");
        jlMode = new JLabel("Mode de joc        ");
        jcLogin = new JComboBox();
        jcMode = new JComboBox(mode);

        jpGraphic.setLayout(new GridLayout(3,1));
        jpLogin.add(jlLogin, BorderLayout.LINE_START);
        jpLogin.add(jcLogin, BorderLayout.CENTER);
        jpMode.add(jlMode, BorderLayout.LINE_START);
        jpMode.add(jcMode, BorderLayout.CENTER);

        jpGraphic.add(jlTitle, BorderLayout.CENTER);
        jpGraphic.add(jpLogin);
        jpGraphic.add(jpMode);

        this.add(jpGraphic, BorderLayout.LINE_START);
        //this.getContentPane().add(jpGraphic, BorderLayout.PAGE_START);
    }

    public void registerController(Controlador c){

    }
}
