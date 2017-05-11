package Controlador;

import Model.Model_usuari;

import java.sql.SQLException;

import Vista.*;

import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Main del Servidor
 * Created by Grup 6 on 30/03/2017.
 */
public class Main {
    private static LinkedList<Socket> sockets;

    public static void main (String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

              VistaServidor vista = new VistaServidor();
                Model_usuari model = new Model_usuari();
                Controlador controlador = new Controlador(vista, model);

                vista.registerController(controlador);

                vista.setVisible(true);
            }

        });

    }

        /*try {
            Model_usuari usuari = new Model_usuari();

            usuari.registraUsuari("marrc","holla@gmail.com","1234asdf");
            //usuari.eliminaUsuari("marrc");
        }
        catch (SQLException e){
            e.getMessage();
        }

    }*/
}
