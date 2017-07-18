package Model;

import Client_Servidor.DedicatedServer;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Propietario on 20/06/2017.
 */
public class PartidaTorneig {
    private ArrayList<DedicatedServer> jugadors;
    private String[] logins = new String[4];
    private int[] puntuacions = new int[4];
    private String[] posicions = new String[4];
    private int morts;
    private int ronda;
    private boolean[] eliminats = new boolean[4];
    private int guanyador;
    private int eliminat;

    public PartidaTorneig(ArrayList<DedicatedServer> jugadors) throws IOException {
        this.jugadors = jugadors;
        morts = 0;
        ronda = 1;
        jugadors.get(0).setPartidaTorneig(this);
        jugadors.get(1).setPartidaTorneig(this);
        jugadors.get(2).setPartidaTorneig(this);
        jugadors.get(3).setPartidaTorneig(this);
        logins[0] = jugadors.get(0).getLogin();
        logins[1] = jugadors.get(1).getLogin();
        logins[2] = jugadors.get(2).getLogin();
        logins[3] = jugadors.get(3).getLogin();
        for(int i = 0; i < 4; i++){
            jugadors.get(i).getDoStreamO().writeObject("JUGADOR");
            jugadors.get(i).getDoStreamO().writeObject(logins);
            jugadors.get(i).getDoStreamO().writeObject(jugadors.get(i).getNum());
            jugadors.get(i).getDoStreamO().writeObject("COMENÇA");
            System.out.println("comença " + jugadors.get(i).getLogin());
        }
        for(int i = 0; i < 4; i++){
            posicions[i] = "1r";
            puntuacions[i] = 20;
            eliminats[i] = false;
        }

    }


    public void enviaSerp(int dir, Posicio cap, Socket emisor) {
        try {
            int j=-1;
            for (int i = 0; i < jugadors.size(); i++) {
                if (jugadors.get(i).getsClient() == emisor) {
                    j = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {

                if (jugadors.get(i).getsClient() != emisor) {
                    System.out.println("enviant a" + jugadors.get(i).getLogin());
                    jugadors.get(i).getDoStreamO().writeObject("MOU");
                    jugadors.get(i).getDoStreamO().writeObject(j);
                    jugadors.get(i).getDoStreamO().writeObject(dir);
                    jugadors.get(i).getDoStreamO().writeObject(cap);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void haMort(Socket emisor){
        try{

            int j=-1;
            for (int i = 0; i < jugadors.size(); i++) {
                if (jugadors.get(i).getsClient() == emisor) {
                    j = i;
                }
            }
            switch (ronda) {
                case 1:
                    morts++;
                    for (int i = 0; i < jugadors.size(); i++) {

                        if (jugadors.get(i).getsClient() != emisor) {
                            jugadors.get(i).getDoStreamO().writeObject("MORT");
                            jugadors.get(i).getDoStreamO().writeObject(j);
                            System.out.println("Han mort " + morts);
                            switch (morts) {
                                case 1:
                                    posicions[j] = "4t Estàs eliminat!";
                                    eliminats[j] = true;
                                    eliminat = j;
                                    puntuacions[j] = -20;
                                    break;
                                case 2:
                                    posicions[j] = "3r";
                                    puntuacions[j] = -10;
                                    break;
                                case 3:
                                    posicions[j] = "2n";
                                    puntuacions[j] = 10;
                                    break;
                            }
                        }
                    }
                    if (morts == 3) {
                        fiRonda();
                    }
                    break;
                case 2:
                    morts++;
                    for (int i = 0; i < jugadors.size(); i++) {

                        if (jugadors.get(i).getsClient() != emisor) {
                            jugadors.get(i).getDoStreamO().writeObject("MORT");
                            jugadors.get(i).getDoStreamO().writeObject(j);
                            System.out.println("Han mort " + morts);
                            switch (morts) {
                                case 1:
                                    posicions[j] = "3r Estàs eliminat!";
                                    eliminats[j] = true;
                                    puntuacions[j] = -10;
                                    eliminat = j;
                                    break;
                                case 2:
                                    posicions[j] = "2n";
                                    puntuacions[j] = 0;
                                    break;
                            }
                        }
                    }
                    if (morts == 2) {
                        fiRonda();
                    }
                    break;
                case 3:
                    morts++;
                    for (int i = 0; i < jugadors.size(); i++) {


                        if (jugadors.get(i).getsClient() != emisor) {
                            jugadors.get(i).getDoStreamO().writeObject("MORT");
                            jugadors.get(i).getDoStreamO().writeObject(j);
                            System.out.println("Han mort " + morts);
                            switch (morts) {
                                case 1:
                                    posicions[j] = "2n";
                                    puntuacions[j] = -10;
                                    eliminats[j] = true;
                                    eliminat = j;
                                    break;
                            }
                        }
                    }
                    if (morts == 1) {
                        fiRonda();
                    }
                    break;
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void fiRonda() {

        Model_usuari model_usuari = new Model_usuari();

        try {
            System.out.println("ROnda " + ronda);
           switch (ronda) {
               case 1:
                   guanyador = -1;


                   for (int i = 0; i < jugadors.size(); i++) {
                       jugadors.get(i).getDoStreamO().writeObject("PUNTS");
                       jugadors.get(i).getDoStreamO().writeObject(posicions[i]);
                       jugadors.get(i).getDoStreamO().writeObject(puntuacions[i]);
                       jugadors.get(i).getDoStreamO().writeObject(guanyador);
                       jugadors.get(i).getDoStreamO().writeObject("ELIMINAT");
                       jugadors.get(i).getDoStreamO().writeObject(eliminat);
                   }
                   seguentRonda();
                   break;
               case 2:

                   for (int i = 0; i < jugadors.size(); i++) {
                       jugadors.get(i).getDoStreamO().writeObject("PUNTS");
                       jugadors.get(i).getDoStreamO().writeObject(posicions[i]);
                       jugadors.get(i).getDoStreamO().writeObject(puntuacions[i]);
                       jugadors.get(i).getDoStreamO().writeObject(guanyador);
                       jugadors.get(i).getDoStreamO().writeObject("ELIMINAT");
                       jugadors.get(i).getDoStreamO().writeObject(eliminat);

                   }
                   int num = 0;
                   for(int k = 0; k < eliminats.length; k++){
                       if(eliminats[k]){
                           num++;
                       }
                   }
                   System.out.println("Hi han eliminats " + num);
                   seguentRonda();
                   break;
               case 3:
                   for (int i = 0; i < jugadors.size(); i++) {
                       if(posicions[i].equals("1r Has guanyat el torneig!")){
                           guanyador = i;
                       }

                   }
                   for (int i = 0; i < jugadors.size(); i++) {
                       model_usuari.updatePuntuacio(jugadors.get(i).getLogin(), puntuacions[i]);
                       jugadors.get(i).getDoStreamO().writeObject("PUNTS");
                       jugadors.get(i).getDoStreamO().writeObject(posicions[i]);
                       jugadors.get(i).getDoStreamO().writeObject(puntuacions[i]);
                       jugadors.get(i).getDoStreamO().writeObject(model_usuari.getPuntsUsuari(jugadors.get(i).getLogin()));
                       jugadors.get(i).getDoStreamO().writeObject(guanyador);
                       jugadors.get(i).getDoStreamO().writeObject("ELIMINAT");
                       jugadors.get(i).getDoStreamO().writeObject(eliminat);
                   }
                   seguentRonda();
                   break;
           }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void seguentRonda(){
        switch (ronda) {
            case 1:
                morts = 0;
                for (int i = 0; i < 4; i++) {
                    if(!eliminats[i]){
                        posicions[i] = "1r";
                        puntuacions[i] = 10;
                    }
                }
                ronda++;
                break;
            case 2:
                morts = 0;
                for (int i = 0; i < 4; i++) {
                    if(!eliminats[i]){
                        posicions[i] = "1r Has guanyat el torneig!";
                        puntuacions[i] = 10;
                    }
                }
                ronda++;
                break;
            case 3:
                morts = 0;
                for (int i = 0; i < 4; i++) {
                        posicions[i] = "1r";
                        puntuacions[i] = 20;
                        eliminats[i] = false;
                }
                ronda = 1;
                break;
        }
    }

}


