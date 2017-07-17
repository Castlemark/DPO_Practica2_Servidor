package Model;

import Client_Servidor.DedicatedServer;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Propietario on 20/06/2017.
 */
public class Partida4 {
    private ArrayList<DedicatedServer> jugadors;
    private String[] logins = new String[4];
    private int[] puntuacions = new int[4];
    private String[] posicions = new String[4];
    private int morts;

    public Partida4(ArrayList<DedicatedServer> jugadors) throws IOException {
        this.jugadors = jugadors;
        morts = 0;
        jugadors.get(0).setPartida4(this);
        jugadors.get(1).setPartida4(this);
        jugadors.get(2).setPartida4(this);
        jugadors.get(3).setPartida4(this);
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
            morts++;
            for (int i = 0; i < jugadors.size(); i++) {

                if (jugadors.get(i).getsClient() != emisor) {
                    jugadors.get(i).getDoStreamO().writeObject("MORT");
                    jugadors.get(i).getDoStreamO().writeObject(j);
                    System.out.println("Han mort " + morts);
                    switch (morts){
                        case 1:
                            posicions[i] = "4t";
                            puntuacions[i] = -20;
                            break;
                        case 2:
                            posicions[i] = "3r";
                            puntuacions[i] = -10;
                            break;
                        case 3:
                            posicions[i] = "2n";
                            puntuacions[i] = 10;
                            fiPartida();
                            break;
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void fiPartida() {
        try {
            int guanyador = -1;
            for (int i = 0; i < jugadors.size(); i++) {
                if (posicions[i].equals("1r")) {
                    guanyador = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {
                jugadors.get(i).getDoStreamO().writeObject("PUNTS");
                jugadors.get(i).getDoStreamO().writeObject(posicions[i]);
                jugadors.get(i).getDoStreamO().writeObject(puntuacions[i]);
                jugadors.get(i).getDoStreamO().writeObject(guanyador);
            }
            reinicia();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reinicia(){
        morts = 0;
        for(int i = 0; i < 4; i++){
            posicions[i] = "1r";
            puntuacions[i] = 20;
        }
    }

}

