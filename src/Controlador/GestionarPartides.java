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

    public void addJoc2(DedicatedServer d) throws IOException {

        if (cua2.isEmpty()){
            cua2.add(new ArrayList<DedicatedServer>());
            cua2.get(cua2.size() - 1).add(d);
        }
        else {

            cua2.get(cua2.size() - 1).add(d);

            if (cua2.get(cua2.size() - 1).size() == 2){

                Partida2 p2 = new Partida2(cua2.get((cua2.size() - 1)));
                cua2.add(new ArrayList<DedicatedServer>());
            }
        }
    }

    public void addJoc4(DedicatedServer d) throws IOException{

        if (currentCua4.size() < 4){

            currentCua4.add(d);
            d.setNum(currentCua4.size() - 1);

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

    public void buidaPartida(DedicatedServer d, int tipuscua) throws IOException{

        ArrayList<DedicatedServer> aux = new ArrayList<>();

        if (tipuscua == 2) {

            for (int i = 0; i < cua2.size(); i++) {

                if (cua2.get(i).contains(d)) {

                    System.out.println("tamany: " + cua2.size());

                    for (int j = 0; j < cua2.get(i).size(); j++){
                        aux.add(cua2.get(i).get(j));
                    }
                    cua2.remove(i);

                    System.out.println("tamany: " + cua2.size());

                    aux.remove(d);
                }
            }

            for (int i = 0; i < aux.size(); i++){

                addJoc2(aux.get(i));
                System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
            }

        } else if (tipuscua == 4) {

            for (int i = 0; i < cua4.size(); i++) {

                if (cua4.get(i).contains(d)) {

                    System.out.println("tamany: " + cua4.size());

                    for (int j = 0; j < cua4.get(i).size(); j++){
                        aux.add(cua4.get(i).get(j));
                    }
                    cua4.remove(i);

                    System.out.println("tamany: " + cua4.size());

                    aux.remove(d);
                }
            }

            for (int i = 0; i < aux.size(); i++){

                addJoc4(aux.get(i));
                System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
            }

        } else {

            for (int i = 0; i < cuaTorneig.size(); i++) {

                if (cuaTorneig.get(i).contains(d)) {

                    System.out.println("tamany: " + cuaTorneig.size());

                    for (int j = 0; j < cuaTorneig.get(i).size(); j++){
                        aux.add(cuaTorneig.get(i).get(j));
                    }
                    cuaTorneig.remove(i);

                    System.out.println("tamany: " + cuaTorneig.size());

                    aux.remove(d);
                }
            }

            for (int i = 0; i < aux.size(); i++){

                addCampeonat(aux.get(i));
                System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
            }

        }
    }
}
