package Controlador;

import Vista.*;

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

                Graphic vista = new Graphic ();
                vista.setVisible(true);
            }
        });
    }

}
