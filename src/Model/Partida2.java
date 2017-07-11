package Model;

import Client_Servidor.DedicatedServer;
import Controlador.ThreadClient;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Grup 6 on 20/06/2017.
 */
public class Partida2 {
    private ArrayList<DedicatedServer> jugadors;
    String[] logins = new String[2];

    public Partida2(ArrayList<DedicatedServer> jugadors) throws IOException{
        this.jugadors = jugadors;
        jugadors.get(0).setPartida2(this);
        jugadors.get(1).setPartida2(this);
        logins[0] = jugadors.get(0).getLogin();
        logins[1] = jugadors.get(1).getLogin();
        for(int i = 0; i < 2; i++){
            jugadors.get(i).getDoStreamO().writeObject("JUGADOR");
            jugadors.get(i).getDoStreamO().writeObject(logins);
            jugadors.get(i).getDoStreamO().writeObject(jugadors.get(i).getNum());
            jugadors.get(i).getDoStreamO().writeObject("COMENÇA");
            System.out.println("comença " + jugadors.get(i).getLogin());
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
                System.out.println("enviant a");

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
            for (int i = 0; i < jugadors.size(); i++) {
                System.out.println("enviant a");

                if (jugadors.get(i).getsClient() != emisor) {
                    jugadors.get(i).getDoStreamO().writeObject("MORT");
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
