package Controlador;

import Model.Model_usuari;
import Vista.VistaServidor;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andreu on 30/03/2017.
 */
public class Controlador implements ActionListener, MenuListener{

    private VistaServidor vista;
    private Model_usuari model;

    public Controlador(VistaServidor vista, Model_usuari model){
        this.vista = vista;
        this.model = model;
    }

    public void actionPerformed(ActionEvent event){

    System.out.println("hola1");
        if (event.getSource() instanceof JMenu){
            System.out.println("hola");
            vista.changePanel(event.getActionCommand());
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        vista.changePanel(((JMenu)e.getSource()).getActionCommand());
        System.out.println(((JMenu)e.getSource()).getActionCommand());
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println();
    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
