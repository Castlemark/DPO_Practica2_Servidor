package Controlador;

import Vista.Gestionar;
import Vista.Registrar;
import Vista.VistaServidor;

import javax.swing.*;

/**
 * Created by Marc on 30/03/2017.
 */
public class Main {
    public static void main (String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*VistaServidor vista = new VistaServidor();
                vista.setVisible(true);*/

                Gestionar vista = new Gestionar();
                vista.setVisible(true);
            }
        });
    }

}
