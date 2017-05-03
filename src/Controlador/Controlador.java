package Controlador;

import Model.Model_usuari;
import Vista.VistaServidor;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Classe del controlador
 *
 * Created by Grup 6 on 30/03/2017.
 */
public class Controlador implements ActionListener{

    private VistaServidor vista;
    private Model_usuari model;

    public Controlador(VistaServidor vista, Model_usuari model){
        this.vista = vista;
        this.model = model;
    }

    public void actionPerformed(ActionEvent event){

        try {
            if (event.getSource() instanceof JMenuItem){
                System.out.println(event.getActionCommand() + " - pestaña");

                if (event.getActionCommand().equals("GESTIONAR")){
                    vista.updateList(model.rcuperaLlistaUsuaris());
                    vista.changePanel(event.getActionCommand());


                }
                else if (event.getActionCommand().equals("GRAFIC")){
                    vista.updateLoginList(model.recuperaLogins());
                    vista.changePanel(event.getActionCommand());
                }
                else {
                    vista.changePanel(event.getActionCommand());
                }

            }
            else if (event.getSource() instanceof JButton){
                System.out.println(event.getActionCommand() + " - boto");

                if (event.getActionCommand().equals("REGISTRAR BOTO")){
                    model.registraUsuari(vista.getLogin(), vista.getMail(), vista.getPassword());
                }

            }
            else if (event.getSource() instanceof JComboBox){
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
