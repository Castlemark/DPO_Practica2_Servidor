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

    public Partida2(ArrayList<DedicatedServer> jugadors) throws IOException{
        this.jugadors = jugadors;

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
