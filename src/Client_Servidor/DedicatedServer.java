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
    private Partida2 partida2;
    private Partida4 partida4;
    private PartidaTorneig partidaTorneig;
    private boolean running;
    private ObjectInputStream diStreamO;
    private ObjectOutputStream doStreamO;
    private ArrayList<DedicatedServer> dedicatedServers;
    private int num;
    private Inicia inicia;
    private int tipus;
    private boolean juga;

    public DedicatedServer(Socket sClient, GestionarPartides gPartides, ArrayList<DedicatedServer> dedicatedServers) throws IOException{
        this.sClient = sClient;
        this.gPartides = gPartides;
        this.dedicatedServers = dedicatedServers;
        partida2 = null;
        partida4 = null;
        partidaTorneig = null;
        doStreamO = new ObjectOutputStream(sClient.getOutputStream());
        diStreamO = new ObjectInputStream(sClient.getInputStream());
        juga = false;
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
                       inicia = (Inicia) diStreamO.readObject();

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

                           doStreamO.writeObject("RANQUING");
                           doStreamO.writeObject(new Model_usuari().getRanquing());

                           doStreamO.writeObject("ENVIACONTROLS");
                           int[] controls = (new Model_usuari().getControls(login));
                           doStreamO.writeObject(controls[0]);
                           doStreamO.writeObject(controls[1]);
                           doStreamO.writeObject(controls[2]);
                           doStreamO.writeObject(controls[3]);
                       }
                       break;

                   case "REGISTRAR":

                       Usuari usuari = (Usuari) diStreamO.readObject();

                       if (new Model_usuari().registraUsuari(usuari.getLogin(), usuari.getMail(), usuari.getPassword(), usuari.getPassword())){
                           doStreamO.writeObject(true);
                           this.login = usuari.getLogin();

                           doStreamO.writeObject("RANQUING");
                           doStreamO.writeObject(new Model_usuari().getRanquing());

                           doStreamO.writeObject("ENVIACONTROLS");
                           int[] controls = (new Model_usuari().getControls(login));
                           doStreamO.writeObject(controls[0]);
                           doStreamO.writeObject(controls[1]);
                           doStreamO.writeObject(controls[2]);
                           doStreamO.writeObject(controls[3]);
                       }
                       else {
                           doStreamO.writeObject(false);
                       }
                       break;


                   case "JOC2":
                        tipus = 2;
                       gPartides.addJoc2(this);
                       break;

                   case "JOC4":
                        tipus = 4;
                       gPartides.addJoc4(this);
                       break;

                   case "CAMPEONAT":
                        tipus = 5;
                       gPartides.addCampeonat(this);
                       break;

                   case "MOVIMENT":

                       System.out.println("sha rebut serp" + num);

                       if(partida2 != null){
                           partida2.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                       }else{
                           if(partida4 != null){
                               partida4.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                           }else {
                               partidaTorneig.enviaSerp((int)diStreamO.readObject(), (Posicio)diStreamO.readObject(), sClient);
                           }
                        }
                       break;

                   case "ENVIACONTROLS":
                       int [] controls = new Model_usuari().getControls(login);
                       doStreamO.writeObject(controls[0]);
                       doStreamO.writeObject(controls [1]);
                       doStreamO.writeObject(controls [2]);
                       doStreamO.writeObject(controls [3]);


                   case "CONTROLS":

                       int up = (Integer) diStreamO.readObject();
                       int down = (Integer) diStreamO.readObject();
                       int left = (Integer) diStreamO.readObject();
                       int right = (Integer) diStreamO.readObject();

                       new Model_usuari().actualitzaControls(login,up,down,left,right);
                       break;

                   case "MORT":

                       if(partida2 != null){
                           partida2.haMort(sClient);
                       }else {
                           if(partida4 != null){
                               partida4.haMort(num);
                           }else{
                               partidaTorneig.haMort(sClient);
                           }
                       }
                       break;

                   case "ABANDONA":
                       Model_usuari model = new Model_usuari();
                       switch (tipus) {
                           case 2:
                               model.updatePuntuacio(login, -10);
                               gPartides.gestionaAbandona(this, 2);
                               partida2 = null;

                               break;
                           case 4:
                               gPartides.gestionaAbandona(this, 4);
                               if (juga) {
                                   partida4.setAbandona(true);
                                   partida4.haMort(num);
                                   switch (partida4.getMorts()) {
                                       case 0:
                                           model.updatePuntuacio(login, -20);
                                           break;
                                       case 1:
                                           model.updatePuntuacio(login, -10);
                                           break;
                                       case 2:
                                           model.updatePuntuacio(login, 10);
                                           break;
                                   }
                               }
                               partida4 = null;

                               break;
                           case 5:
                          //Abandona torneig
                               if(juga) {
                                    switch (partidaTorneig.getRonda()){
                                        case 1:
                                            model.updatePuntuacio(login, -20);
                                            break;
                                        case 2:
                                            model.updatePuntuacio(login, -10);
                                            break;
                                        case 3:
                                            model.updatePuntuacio(login, -10);
                                            break;
                                    }
                                   if(partidaTorneig.getEliminats()[num]){
                                       partidaTorneig.setAbandona(true, num);
                                       gPartides.gestionaAbandona(this, 0);
                                       partidaTorneig = null;

                                   }else {
                                       partidaTorneig.setAbandona(true, num);
                                       partidaTorneig.haMort(sClient);
                                       gPartides.gestionaAbandona(this, 0);
                                       partidaTorneig.fiRonda();
                                       partidaTorneig = null;
                                   }
                               }else {
                                   System.out.println("Entra a no juga");
                                   gPartides.gestionaAbandona(this, 0);
                               }
                               break;

                       }

                       doStreamO.writeObject("RANQUING");
                       doStreamO.writeObject(new Model_usuari().getRanquing());
                       juga = false;
                       break;

                   case "TANCARSESSIO":
                       this.login="";
                       dedicatedServers.remove(this);
                       break;
               }
           }

       }catch (IOException e){
         //  try{
               Model_usuari model = new Model_usuari();
               switch (tipus){
                   case 2:
                       model.updatePuntuacio(login, -10);
                       gPartides.gestionaAbandona(this,2);
                       partida2 = null;
                       break;
                   case 4:
                       gPartides.gestionaAbandona(this, 4);
                       if (juga) {
                           partida4.setAbandona(true);
                           partida4.haMort(num);
                           switch (partida4.getMorts()) {
                               case 0:
                                   model.updatePuntuacio(login, -20);
                                   break;
                               case 1:
                                   model.updatePuntuacio(login, -10);
                                   break;
                               case 2:
                                   model.updatePuntuacio(login, 10);
                                   break;
                           }
                       }
                       partida4 = null;

                       break;
                   case 5:
                       //Abandona torneig
               }


        /*   }catch(IOException ex){
                e.printStackTrace();
           }*/
           dedicatedServers.remove(this);

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
        System.out.println("Serp numero " + num);
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

    public void acabaPartida4(){
        try{
            gPartides.acabaPartida4(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void acabaPartidaTorneig(){
        try{
            gPartides.acabaPartidaTorneig(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setJuga(boolean juga) {
        this.juga = juga;
    }

    public boolean isJuga() {
        return juga;
    }
}
