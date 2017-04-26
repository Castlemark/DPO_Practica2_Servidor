package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sullivan on 11/04/2017.
 */
public class Registrar extends JPanel {
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

        this.setSize (350, 350);

        jpButtons = new JPanel();
        jpAtras = new JPanel(new BorderLayout());
        jpLogin = new JPanel(new BorderLayout());
        jpPassword = new JPanel(new BorderLayout());
        jpMail = new JPanel(new BorderLayout());
        jpRegistrar = new JPanel(new BorderLayout());


        jbAtras = new JButton("<-");
        jPassword = new JPasswordField();
        jlTitle = new JLabel("          Registrar usuari");
        jtMail = new JTextField();
        jbRegistrar = new JButton("Registrar");
        jlLogin = new JLabel("Login               ");
        jlMail = new JLabel("Mail                  ");
        jlPassword = new JLabel("Password      ");
        jtLogin = new JTextField();



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

        this.add(jpButtons, BorderLayout.PAGE_START);
    }

    public void registerController(Controlador c){

    }

}
