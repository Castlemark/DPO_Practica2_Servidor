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
    private JPanel jpMode;

    private JButton jbAtras;
    private JLabel jlTitle;
    private JLabel jlLogin;
    private JLabel jlMode;

    private JComboBox jcLogin;
    private GraficUsuari gu;
    //private JComboBox jcMode;

    //private String[] mode = {"2", "4", "Torneig"};

    public Graphic(){

        this.setSize (350,350);

      //  mode = new String[];

        //mode ;

        jpGraphic = new JPanel();
        jpLogin = new JPanel(new BorderLayout());
        jpMode = new JPanel(new BorderLayout());


        jlTitle = new JLabel("            Graphic");
        jlLogin = new JLabel("Login          ");
        //jlMode = new JLabel("Mode de joc        ");
        jcLogin = new JComboBox();
     //   jcMode = new JComboBox(mode);

        jpGraphic.setLayout(new GridLayout(4,1));
        jpLogin.add(jlLogin, BorderLayout.LINE_START);
        jpLogin.add(jcLogin, BorderLayout.CENTER);
//        jpMode.add(jlMode, BorderLayout.LINE_START);
     //   jpMode.add(jcMode, BorderLayout.CENTER);

        jpGraphic.add(jlTitle, BorderLayout.CENTER);
        jpGraphic.add(jpLogin);
        //jpGraphic.add(jpMode);
        GraficUsuari gu = new GraficUsuari();

        this.add(gu,BorderLayout.PAGE_END);
      // jpGraphic.add(gu);

        this.add(jpGraphic, BorderLayout.LINE_START);





        //this.getContentPane().add(jpGraphic, BorderLayout.PAGE_START);
    }

    public void registerController(Controlador c){
        jcLogin.setActionCommand("LOGIN");
        //jcMode.setActionCommand("MODE");

        jcLogin.addActionListener(c);
        //jcMode.addActionListener(c);
    }

    public void setLlistaLogin(ArrayList<String> logins){
        jcLogin.removeAllItems();

        for (String s:logins){
            jcLogin.addItem(s);
        }

    }

    public void creaGrafic(ArrayList<Integer> dades){

         gu = new GraficUsuari(dades);

        //this.add(gu,BorderLayout.PAGE_END);

    }

    public String getLogin(){
        return (String) jcLogin.getSelectedItem();
    }


}
