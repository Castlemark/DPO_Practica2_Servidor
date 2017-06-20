package Controlador;

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
            }

            Serp serp;
            name = diStream.readUTF();
            System.out.println("[SERVER]: "+name+" connected  from "+sClient.getRemoteSocketAddress().toString()+" "+sClient.getInetAddress().getHostName());
            int joc;
            joc = 1;


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

        } catch(EOFException e){
            System.out.println("[SERVER]: "+name+" disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
