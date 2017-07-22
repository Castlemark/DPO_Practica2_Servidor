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
 * Classe del thread general per establir connexió
 * Created by Grup 6 on 27/05/2017.
 */
public class Server extends Thread{


    //Atributs
    private ServerSocket  sSocket;
    private final ArrayList<DedicatedServer> dedicatedServers;

    private GestionarPartides gestionarPartides;

    private boolean running;
    private int port;


    //Mètodes

    public Server (int port, GestionarPartides gestionarPartides){
        this.port = port;
        this.gestionarPartides = gestionarPartides;
        dedicatedServers = new ArrayList<>();
    }

    /**
     * Inicialitza el socket
     * @throws IOException
     */
    public void startServer () throws IOException{

        sSocket = new ServerSocket(11111);
        System.out.println("servidor conectat");
        running = true;
        start();
    }

    public void stopServer(){
        running = false;
    }

    public ServerSocket getsSocket(){
        return sSocket;
    }

    /**
     * Mètode que executa el Thread
     */
    @Override
    public void run(){
        while (running) try{
            Socket socket = sSocket.accept();
            DedicatedServer dServer = new DedicatedServer(socket, gestionarPartides, dedicatedServers);
            dServer.start();
            dedicatedServers.add(dServer);

        }catch (IOException e){
            System.out.println("Servidor aturat");
        }
    }

}
