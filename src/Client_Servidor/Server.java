package Client_Servidor;

import Controlador.GestionarPartides;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 27/06/2017.
 */
public class Server extends Thread{

    private ServerSocket  sSocket;
    private List<DedicatedServer> dedicatedServers;

    private GestionarPartides gestionarPartides;

    private boolean running;
    private int port;

    public Server (int port, GestionarPartides gestionarPartides){
        this.port = port;
        this.gestionarPartides = gestionarPartides;
        dedicatedServers = new ArrayList<>();
    }

    public void startServer () throws IOException{

        sSocket = new ServerSocket(11111);

        System.out.println("servidor conectat");
        running = true;
        start();
    }

    public void stopServer(){
        running = false;
        for(DedicatedServer d: dedicatedServers){
            d.stopRunning();
        }
    }



    @Override
    public void run(){
        while (running) try{
            Socket socket = sSocket.accept();
            DedicatedServer dServer = new DedicatedServer( socket, gestionarPartides);
            dedicatedServers.add(dServer);
            System.out.println("afegit");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
