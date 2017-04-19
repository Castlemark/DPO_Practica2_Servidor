package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by sullivan on 19/04/2017.
 */
public class Graphic extends JFrame {
    private JPanel jpGraphic;
    private JPanel jpAtras;
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
        this.setTitle ("Graphic");
        this.setSize (350,350);
        this.setResizable(true);

      //  mode = new String[];

        //mode ;

        jpGraphic = new JPanel();
        jpAtras = new JPanel(new BorderLayout());
        jpLogin = new JPanel(new BorderLayout());
        jpMode = new JPanel(new BorderLayout());


        jbAtras = new JButton("<-");
        jlTitle = new JLabel("            Graphic");
        jlLogin = new JLabel("Login          ");
        jlMode = new JLabel("Mode de joc        ");
        jcLogin = new JComboBox();
        jcMode = new JComboBox(mode);

        jpGraphic.setLayout(new GridLayout(3,1));
        jpAtras.add(jbAtras, BorderLayout.LINE_START);
        jpAtras.add(jlTitle, BorderLayout.CENTER);
        jpLogin.add(jlLogin, BorderLayout.LINE_START);
        jpLogin.add(jcLogin, BorderLayout.CENTER);
        jpMode.add(jlMode, BorderLayout.LINE_START);
        jpMode.add(jcMode, BorderLayout.CENTER);

        jpGraphic.add(jpAtras, BorderLayout.CENTER);
        jpGraphic.add(jpLogin);
        jpGraphic.add(jpMode);

        this.getContentPane().add(jpGraphic, BorderLayout.PAGE_START);
    }
}
