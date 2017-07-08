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
 * Created by Propietario on 20/06/2017.
 */
public class Partida2 {
    private ArrayList<DedicatedServer> jugadors;
    String[] logins = new String[2];

    public Partida2(ArrayList<DedicatedServer> jugadors) throws IOException{
        this.jugadors = jugadors;
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

    public void enviaSerp(Serp serp, Socket emisor) {
        try {
            int j=-1;
            for (int i = 0; i < jugadors.size(); i++) {
                if (jugadors.get(i).getsClient() == emisor) {
                    j = i;
                }
            }
            for (int i = 0; i < jugadors.size(); i++) {
                if (jugadors.get(i).getsClient() != emisor) {
                    DataOutputStream doStream = new DataOutputStream(emisor.getOutputStream());
                    ObjectOutputStream doStreamO = new ObjectOutputStream(emisor.getOutputStream());
                    doStream.writeInt(j);
                    doStreamO.writeObject(serp);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
