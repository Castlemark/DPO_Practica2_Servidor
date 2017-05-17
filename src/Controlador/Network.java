package Controlador;

import Model.Inicia;
import Model.Model_usuari;
import Model.Usuari;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Propietario on 10/05/2017.
 */
public class Network extends Thread {
    private DataOutputStream doStream;
    private ObjectOutputStream doStreamO; // = new ObjectOutputStream(sClient.getOutputStream());
    private Scanner sc;
    private DataInputStream diStream;
    private ObjectInputStream diStreamO;
    private ArrayList<Socket> sockets;
    private Socket sClient;
    private Controlador controlador;
    private ServerSocket sServer;
    private boolean running;

    public Network(Controlador controlador) {
        sockets = new ArrayList<>();
        this.controlador = controlador;
        running = true;
    }

    public void connect() {
        try {
            sServer = new ServerSocket(11111);

            System.out.println("servidor conectat");
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        running = false;
        for (Socket s: sockets){
            if (s != null){
                try{
                    s.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

            }
        }
        try {

            if (diStream != null){
                diStream.close();
            }

            if (diStreamO != null){
                diStreamO.close();
            }

            if (doStream != null){
                doStream.close();
            }

            if (doStreamO != null){
                doStreamO.close();
            }
            if (sClient != null){
                sClient.close();
            }

            sServer.close();

        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("diconnect executat");
    }

    @Override
    public void run() {
        while (running) {
            try {
                sClient = sServer.accept();
                diStreamO = new ObjectInputStream(sClient.getInputStream());
                Object usuari = (Usuari) diStreamO.readObject();
                if(usuari instanceof Usuari){
                    if(new Model_usuari().registraUsuari(((Usuari) usuari).getLogin(),((Usuari) usuari).getMail(),((Usuari) usuari).getPassword(), ((Usuari) usuari).getPassword())){
                        doStream.writeBoolean(true);
                        sockets.add(sClient);

                        System.out.println(((Usuari) usuari).getLogin());

                        (new ThreadClient(sClient, sockets)).start();
                    }else {
                        doStream.writeBoolean(false);
                    }
                } else{
                    doStream.write(new Model_usuari().comprovaInicia((Inicia) usuari));
                }



            }catch (IOException e){
                //e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
            } catch (SQLException e){
                //e.printStackTrace();
            }

        }
        System.out.println("Run acabat");
    }
}
