package Client_Servidor;

import Controlador.GestionarPartides;
import Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by Marc on 27/06/2017.
 */
public class DedicatedServer extends Thread{
    private Socket sClient;
    private String login;
    private GestionarPartides gPartides;
    private Model_usuari model;
    private final Partida2 partida2;
    private final Partida4 partida4;
    private final PartidaTorneig partidaTorneig;
    private boolean running;

    public DedicatedServer(Socket sClient, GestionarPartides gPartides){
        this.sClient = sClient;
        this.gPartides = gPartides;
        partida2 = null;
        partida4 = null;
        partidaTorneig = null;
    }

    @Override
    public void run() {
       try {
           ObjectOutputStream doStreamO = new ObjectOutputStream(sClient.getOutputStream());
           ObjectInputStream diStreamO = new ObjectInputStream(sClient.getInputStream());
           running = true;
           while (running){
               String opcio = diStreamO.readUTF();
               switch (opcio){
                   case "INICIA":

                       String aux;
                       Inicia inicia = (Inicia) diStreamO.readObject();

                       aux = new Model_usuari().comprovaInicia(inicia);

                       if (aux.equals("error a Model_usuari.comprovaInicia")){
                           doStreamO.writeBoolean(false);
                           System.out.println("enviat false");
                       }
                       else {
                           doStreamO.writeBoolean(true);
                           System.out.println("enviat true");

                       }
                       break;
                   case "REGISTRA":
                       Usuari usuari = (Usuari) diStreamO.readObject();

                       if (new Model_usuari().registraUsuari(usuari.getLogin(), usuari.getMail(), usuari.getPassword(), usuari.getPassword())){
                           doStreamO.writeBoolean(true);
                           System.out.println(usuari.getLogin());
                       }
                       else {
                           doStreamO.writeBoolean(false);
                       }
                       break;
                   case "MOVIMENT":
                       break;
                   case "COLLISIO":
                       break;
                   case "CANVIACONTROLS":
                       break;
                   case "MOSTRARANKING":
                       break;
               }
           }

       }catch (IOException e){
           e.printStackTrace();
       }catch (SQLException e){
           e.printStackTrace();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }

    }

    public void stopRunning(){
        running = false;
    }
}