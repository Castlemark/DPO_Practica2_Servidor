package Controlador;

import Client_Servidor.DedicatedServer;
import com.sun.deploy.resources.Deployment_de;

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

    public void addJoc2(DedicatedServer d){

        if (currentCua2.size() < 2){

            currentCua2.add(d);
        }
        else{

            cua2.add(currentCua2);
            currentCua2.clear();
            currentCua2.add(d);

            //aqui comença una partida perque tenim tota la gent necesari
        }
    }

    public void addJoc4(DedicatedServer d){

        if (currentCua4.size() < 4){

            currentCua4.add(d);
        }
        else {

            cua4.add(currentCua4);
            currentCua4.clear();
            currentCua4.add(d);

            //aqui comença una partida perque tenim tota la gent necesaria
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
