package Controlador;

import Client_Servidor.DedicatedServer;
import Model.Partida;
import Model.Partida2;
import Model.Partida4;
import com.sun.deploy.resources.Deployment_de;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Propietario on 20/06/2017.
 */
public class GestionarPartides {

    private ArrayList<ArrayList<DedicatedServer>> cua2;
    private ArrayList<ArrayList<DedicatedServer>> cua4;
    private ArrayList<ArrayList<DedicatedServer>> cuaTorneig;

    private ArrayList<DedicatedServer> currentCua2;
    private ArrayList<DedicatedServer> currentCua4;
    private ArrayList<DedicatedServer> currentCuaTorneig;


    public GestionarPartides(){

        cua2 = new ArrayList<>();
        cua4 = new ArrayList<>();
        cuaTorneig = new ArrayList<>();

        currentCua2 = new ArrayList<>();
        currentCua4 = new ArrayList<>();
        currentCuaTorneig = new ArrayList<>();
    }

    public void addJoc2(DedicatedServer d) throws IOException{

        if (currentCua2.size() < 2){

            currentCua2.add(d);
            System.out.println("La cua te " + currentCua2.size());
            d.setNum(currentCua2.size() - 1);
        }
        else{

            cua2.add(currentCua2);
            currentCua2.clear();
            currentCua2.add(d);

            //aqui comença una partida perque tenim tota la gent necesari
        }
        if(currentCua2.size() == 2){
            Partida2 p2 = new Partida2(currentCua2);

        }
    }

    public void addJoc4(DedicatedServer d) throws IOException{

        if (currentCua4.size() < 4){

            currentCua4.add(d);
        }
        else {

            cua4.add(currentCua4);
            currentCua4.clear();
            currentCua4.add(d);

            //aqui comença una partida perque tenim tota la gent necesaria
        }
        if(currentCua4.size() == 4){
            Partida4 p4 = new Partida4(currentCua4);
        }
    }

    public void addCampeonat(DedicatedServer d){

        if (currentCuaTorneig.size() < 4){

            currentCuaTorneig.add(d);
        }
        else {

            cuaTorneig.add(currentCuaTorneig);
            currentCua4.clear();
            currentCua4.add(d);

            //aqui comença una partida perque tenim tota la gent necesari
        }
    }
}
