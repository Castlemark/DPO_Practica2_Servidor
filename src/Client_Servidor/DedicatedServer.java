package Client_Servidor;

import Controlador.GestionarPartides;
import Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Marc on 27/06/2017.
 */
public class DedicatedServer extends Thread{
    private Socket sClient;
    private String login = "";
    private GestionarPartides gPartides;
    private Model_usuari model;
    private Partida2 partida2;
    private Partida4 partida4;
    private PartidaTorneig partidaTorneig;
    private boolean running;
    private ObjectInputStream diStreamO;
    private ObjectOutputStream doStreamO;
    private ArrayList<DedicatedServer> dedicatedServers;
    private int num;

    public DedicatedServer(Socket sClient, GestionarPartides gPartides, ArrayList<DedicatedServer> dedicatedServers) throws IOException{
        this.sClient = sClient;
        this.gPartides = gPartides;
        this.dedicatedServers = dedicatedServers;
        partida2 = null;
        partida4 = null;
        partidaTorneig = null;
        doStreamO = new ObjectOutputStream(sClient.getOutputStream());
        diStreamO = new ObjectInputStream(sClient.getInputStream());
    }

    @Override
    public void run() {
       try {

           running = true;
           while (running){


               String opcio = (String) diStreamO.readObject();

               switch (opcio){
                   case "INICIARSESSIO":

                       String aux;
                       Inicia inicia = (Inicia) diStreamO.readObject();

                       aux = new Model_usuari().comprovaInicia(inicia);

                       if (aux.equals("error a Model_usuari.comprovaInicia") || estaIniciat(aux)){
                           doStreamO.writeObject(false);
                           System.out.println("enviat false");
                       }
                       else {
                           doStreamO.writeObject(true);
                           System.out.println("enviat true");
                           new Model_usuari().actualitzaData(aux);
                           this.login = aux;

                       }
                       break;
                   case "REGISTRAR":
                       Usuari usuari = (Usuari) diStreamO.readObject();

                       if (new Model_usuari().registraUsuari(usuari.getLogin(), usuari.getMail(), usuari.getPassword(), usuari.getPassword())){
                           doStreamO.writeObject(true);
                           this.login = usuari.getLogin();
                           System.out.println(usuari.getLogin());
                       }
                       else {
                           doStreamO.writeObject(false);
                       }
                       break;


                   case "JOC2":

                       gPartides.addJoc2(this);
                       //Aqui fas un OK/KO per avisar al client si hi ha una cua plena,si una cua esta plena el client canvia a la finestra de joc i comença la partida
                       break;

                   case "JOC4":

                       gPartides.addJoc4(this);
                       //Aqui fas un OK/KO per avisar al client si hi ha una cua plena,si una cua esta plena el client canvia a la finestra de joc i comença la partida
                       break;

                   case "CAMPEONAT":

                       gPartides.addCampeonat(this);
                       //Aqui fas un OK/KO per avisar al client si hi ha una cua plena,si una cua esta plena el client canvia a la finestra de joc i comença la partida
                       break;

                   case "MOVIMENT":
                       System.out.println("sha rebut serp");
                       if(partida2 != null){
                           partida2.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                       }else{
                           if(partida4 != null){
                               partida4.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                           }else {

                           }
                        }
                       break;

                   case "COLLISIO":

                       break;

                   case "CANVIACONTROLS":

                       break;

                   case "MOSTRARANKING":

                       break;

                   case "MORT":
                       if(partida2 != null){
                           partida2.haMort(sClient);
                       }else {
                           if(partida4 != null){
                               partida4.haMort(sClient);
                           }
                       }
                       break;

                   case "ABANDONA":
                       if (partida2 != null){
                           gPartides.buidaPartida(this,2);
                           partida2 = null;
                       }
                       else if (partida4 != null){
                           gPartides.buidaPartida(this,4);
                           partida4 = null;
                       }
                       break;

                   case "TANCARSESSIO":
                       this.login="";
                       break;
               }
           }

       }catch (IOException e){
           dedicatedServers.remove(this);
          // e.printStackTrace();
       }catch (SQLException e){
           e.printStackTrace();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }

    }

    public String getLogin() {
        return login;
    }

    public void stopRunning(){
        running = false;
    }

    public boolean estaIniciat(String login){
        for(int i = 0; i < dedicatedServers.size(); i++){

            if (dedicatedServers == null){
                return false;
            }

            if(dedicatedServers.get(i).getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    public Socket getsClient() {
        return sClient;
    }

    public ObjectInputStream getDiStreamO() {
        return diStreamO;
    }

    public ObjectOutputStream getDoStreamO() {
        return doStreamO;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setPartida2(Partida2 partida2) {
        this.partida2 = partida2;
    }

    public void setPartida4(Partida4 partida4) {
        this.partida4 = partida4;
    }

    public void setPartidaTorneig(PartidaTorneig partidaTorneig) {
        this.partidaTorneig = partidaTorneig;
    }
}
