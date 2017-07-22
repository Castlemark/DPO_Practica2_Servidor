package Vista;

import Controlador.Controlador;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Panell de l'opció de gràfic del la Vista del Servidor
 * Created by Grup 6 on 19/04/2017.
 */
public class Graphic extends JPanel {

    private JPanel jpGraphic;
    private JPanel jpLogin;
    private JLabel jlTitle;
    private JLabel jlLogin;
    private JComboBox jcLogin;
    private GraficUsuari gu;


    public Graphic(){

        this.setSize (350,350);

        jpGraphic = new JPanel();
        jpLogin = new JPanel(new FlowLayout());

        jlTitle = new JLabel("Gràfic");
        jlLogin = new JLabel("Login          ");
        jlTitle.setHorizontalAlignment(0);
        jcLogin = new JComboBox();

        this.setLayout(new BorderLayout());
        jpGraphic.setLayout(new BorderLayout());
        jpLogin.add(jlLogin);
        jpLogin.add(jcLogin);
        jpGraphic.add(jlTitle, BorderLayout.NORTH);
        jpGraphic.add(jpLogin, BorderLayout.CENTER);

        ArrayList<Integer> inicialitzador = new ArrayList<>(50);

        this.add(jpGraphic, BorderLayout.NORTH);

        gu = new GraficUsuari(inicialitzador);
        this.add(gu, BorderLayout.CENTER);

    }

    public void registerController(Controlador c){

        jcLogin.setActionCommand("LOGIN");
        jcLogin.addActionListener(c);

    }

    public void setLlistaLogin(ArrayList<String> logins){

        jcLogin.removeAllItems();

        for (String s:logins){
            jcLogin.addItem(s);
        }

    }

    public void creaGrafic(ArrayList<Integer> dades){

        ArrayList<Number> inicialitzador = new ArrayList<>(50);

        gu.setPunts(dades);
        this.add(gu,BorderLayout.CENTER);

    }

    public String getLogin(){
        return (String) jcLogin.getSelectedItem();
    }


}
