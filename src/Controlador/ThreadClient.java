package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Grup 6 on 19/04/2017.
 */
public class ThreadClient extends Thread{
    private Socket sClient;
    private ArrayList<Socket> sockets;
    public ThreadClient(Socket sClient, ArrayList<Socket> sockets) {
        this.sClient = sClient;
        this.sockets = sockets;
    }

    @Override
    public void run() {
        String name = null;
        try {
            DataInputStream diStream = new DataInputStream(sClient.getInputStream());
            DataOutputStream doStream = new DataOutputStream(sClient.getOutputStream());
            ObjectOutputStream doStreamO = new ObjectOutputStream(sClient.getOutputStream());
            String message = "";
            name = diStream.readUTF();
            System.out.println("[SERVER]: "+name+" connected  from "+sClient.getRemoteSocketAddress().toString()+" "+sClient.getInetAddress().getHostName());
            while(true){
                message = diStream.readUTF();
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
