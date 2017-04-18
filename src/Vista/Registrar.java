package Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sullivan on 11/04/2017.
 */
public class Registrar extends JFrame {
    private JButton jbGrafic;
    private JPanel jpButtons;
    private JPanel jpAtras;
    private JPanel jpLogin;
    private JPanel jpPassword;
    private JPanel jpMail;
    private JPanel jpRegistrar;
    private JButton jbAtras;
    private JLabel jlTitle;
    private JLabel jlLogin;
    private JLabel jlPassword;
    private JLabel jlMail;
    private JTextField jtLogin;
    private JPasswordField jPassword;
    private JTextField jtMail;
    private JButton jbRegistrar;


    public Registrar () {

        this.setTitle("Registrar usuaris");
        this.setSize (350, 350);
        this.setResizable(true);

        jpButtons = new JPanel();
        jpAtras = new JPanel(new BorderLayout());
        jpLogin = new JPanel(new BorderLayout());
        jpPassword = new JPanel(new BorderLayout());
        jpMail = new JPanel(new BorderLayout());
        jpRegistrar = new JPanel(new BorderLayout());


        jbAtras = new JButton("<-");
        jPassword = new JPasswordField();
        jtMail = new JTextField();
        jbRegistrar = new JButton("Registrar");

        jpButtons.setLayout(new GridLayout(5,1));
        jpAtras.add(jbAtras, BorderLayout.LINE_START);
        jpAtras.add(jlTitle, BorderLayout.CENTER);
        jpRegistrar.add(jbRegistrar, BorderLayout.LINE_END);
        jpLogin.add(jlLogin, BorderLayout.LINE_START);
        jpLogin.add(jtLogin, BorderLayout.CENTER);
        jpPassword.add(jlPassword, BorderLayout.LINE_START);
        jpPassword.add(jPassword, BorderLayout.CENTER);
        jpMail.add(jlMail, BorderLayout.LINE_START);
        jpMail.add(jtMail, BorderLayout.CENTER);

        jpButtons.add(jpAtras, BorderLayout.CENTER);
        jpButtons.add(jpLogin);
        jpButtons.add(jpPassword);
        jpButtons.add(jpMail);
        jpButtons.add(jpRegistrar);

        this.getContentPane().add(jpButtons, BorderLayout.CENTER);
    }

}
