package Controlador;

import Model.Partida2;
import Model.Partida4;
import Model.PartidaTorneig;
import Model.Serp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Grup 6 on 19/04/2017.
 */
public class ThreadClient extends Thread{
    private Socket sClient;
    private ArrayList<Socket> sockets;
    private String login;
    private int tipusPartida;
    private Partida2 partida2;
    private Partida4 partida4;
    private PartidaTorneig partidaTorneig;

    public ThreadClient(Socket sClient, ArrayList<Socket> sockets, String login) {
        this.sClient = sClient;
        this.sockets = sockets;
        this.login = login;
    }

    @Override
    public void run() {
        String name = null;
        try {
            DataInputStream diStream = new DataInputStream(sClient.getInputStream());
            DataOutputStream doStream = new DataOutputStream(sClient.getOutputStream());
            ObjectOutputStream doStreamO = new ObjectOutputStream(sClient.getOutputStream());
            ObjectInputStream diStreamO = new ObjectInputStream(sClient.getInputStream());
            String message = "";

            int opcio = diStream.readInt();

            switch(opcio){
                case 1:
                   // afegirCua2(login);
                    //juga2();
                    break;
                case 2:
                    //afegirCua4(login);
                    //juga4();
                    break;
                case 3:
                    //afegirCuaTor(login);
                    //jugaTor();
                    break;

                case 4:
                    Serp serp;
                    name = diStream.readUTF();
                    System.out.println("[SERVER]: "+name+" connected  from "+sClient.getRemoteSocketAddress().toString()+" "+sClient.getInetAddress().getHostName());

                    int tipus;
                    tipus = diStream.readInt();
                    serp = (Serp) diStreamO.readObject();

                    switch(tipus){
                        case 1:
                            partida2.enviaSerp(serp, sClient);
                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                    }


                    while(true){
                        serp = (Serp) diStreamO.readObject();
                        for(Socket s : sockets) {
                            if (s != sClient) {

                                DataOutputStream otherDoStream = new DataOutputStream(s.getOutputStream());
                                otherDoStream.writeUTF("["+name+"]: "+message);
                            }
                        }
                        System.out.println("["+name+"]: "+message);
                    }
            }



        } catch(EOFException e){
            System.out.println("[SERVER]: "+name+" disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
