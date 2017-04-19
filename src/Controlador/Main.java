package Controlador;

import Vista.*;

import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

/**
 * Created by Marc on 30/03/2017.
 */
public class Main {
    private static LinkedList<Socket> sockets;

    public static void main (String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*VistaServidor vista = new VistaServidor();
                vista.setVisible(true);*/

                Graphic vista = new Graphic ();
                vista.setVisible(true);
*/
                try {
                    sockets = new LinkedList<>();
                    ServerSocket sServer = new ServerSocket(10000);
                    while (true) {
                        Socket sClient = sServer.accept();
                        sockets.add(sClient);
                        (new ThreadClient(sClient, sockets)).start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
