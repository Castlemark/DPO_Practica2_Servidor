package Controlador;

import Client_Servidor.DedicatedServer;
import Model.*;
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


    public GestionarPartides() {

        cua2 = new ArrayList<>();
        cua4 = new ArrayList<>();
        cuaTorneig = new ArrayList<>();

        currentCua2 = new ArrayList<>();
        currentCua4 = new ArrayList<>();
        currentCuaTorneig = new ArrayList<>();
    }

    public void addJoc2(DedicatedServer d) throws IOException {

        if (cua2.isEmpty()) {
            cua2.add(new ArrayList<DedicatedServer>());
            cua2.get(cua2.size() - 1).add(d);
            d.setNum(cua2.get(cua2.size() - 1).size() - 1);
        } else {

            cua2.get(cua2.size() - 1).add(d);
            d.setNum(cua2.get(cua2.size() - 1).size() - 1);

            if (cua2.get(cua2.size() - 1).size() == 2) {

                Partida2 p2 = new Partida2(cua2.get((cua2.size() - 1)));
                cua2.add(new ArrayList<DedicatedServer>());
            }
        }
    }

    public void addJoc4(DedicatedServer d) throws IOException {

        if (cua4.isEmpty()) {
            cua4.add(new ArrayList<DedicatedServer>());
            cua4.get(cua4.size() - 1).add(d);
            d.setNum(cua4.get(cua4.size() - 1).size() - 1);
        } else {

            cua4.get(cua4.size() - 1).add(d);
            d.setNum(cua4.get(cua4.size() - 1).size() - 1);

            if (cua4.get(cua4.size() - 1).size() == 4) {

                Partida4 p4 = new Partida4(cua4.get((cua4.size() - 1)));
                cua4.add(new ArrayList<DedicatedServer>());
            }
        }
    }

    public void addCampeonat(DedicatedServer d) throws IOException {

        if (currentCuaTorneig.size() < 4) {

            currentCuaTorneig.add(d);
            d.setNum(currentCuaTorneig.size() - 1);
        } else {

            cuaTorneig.add(currentCuaTorneig);
            currentCua4.clear();
            currentCua4.add(d);

            //aqui comença una partida perque tenim tota la gent necesari
        }

        if (currentCuaTorneig.size() == 4) {
            PartidaTorneig pt = new PartidaTorneig(currentCuaTorneig);
        }
    }

    public void gestionaAbandona(DedicatedServer d, int tipuscua){
        try {
            boolean conte;
            ArrayList<DedicatedServer> aux = new ArrayList<>();
            System.out.println("Entra un cop");
            System.out.println(cua4.size());
            if (tipuscua == 2) {

                for (int i = 0; i < cua2.size(); i++) {
                    conte = false;
                    for (int j = 0; j < cua2.get(i).size(); j++) {
                        if (cua2.get(i).get(j).getLogin().equals(d.getLogin())) {
                            conte = true;
                        }
                    }
                    if (conte) {
                        System.out.println("tamany: " + cua2.size());
                        for (int j = 0; j < cua2.get(i).size(); j++) {
                            aux.add(cua2.get(i).get(j));
                            if (!aux.get(j).getLogin().equals(d.getLogin())) {
                                Model_usuari model = new Model_usuari();
                                model.updatePuntuacio(aux.get(j).getLogin(), 10);
                                aux.get(j).getDoStreamO().writeObject("ABANDONAT");
                                System.out.println("enviat abandonat");
                            }
                        }
                        System.out.println(cua2.get(i).get(0).getLogin() + " eliminat de la cua");
                        cua2.remove(i);
                        System.out.println("tamany: " + cua2.size());
                        aux.remove(d);
                    }
                }

                for (int i = 0; i < aux.size(); i++) {
                    addJoc2(aux.get(i));
                    System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
                }

            } else if (tipuscua == 4) {
                for (int i = 0; i < cua4.size(); i++) {
                    conte = false;
                    System.out.println("Aqui entra");

                    for (int j = 0; j < cua4.get(i).size(); j++) {
                        if (cua4.get(i).get(j) != null && cua4.get(i).get(j).getLogin().equals(d.getLogin())) {
                            conte = true;
                            System.out.println("Conte funciona");
                        }
                    }
                    if (conte) {
                        if (d.isJuga()) {
                            for (int j = 0; j < cua4.get(i).size(); j++) {
                                if (cua4.get(i).get(j) != null && cua4.get(i).get(j).getLogin().equals(d.getLogin())) {
                                    cua4.get(i).set(j, null);
                                    System.out.println("Eliminat de la partida");
                                }
                            }
                        } else {
                            for (int j = 0; j < cua4.get(i).size(); j++) {
                                if (cua4.get(i).get(j) != null) {
                                    aux.add(cua4.get(i).get(j));
                                    aux.get(aux.size() - 1).getDoStreamO().writeObject("ABANDONAT");
                                    System.out.println("enviat abandonat");
                                }
                            }
                            cua4.remove(i);

                    /*for (int j = 0; j < cua4.get(i).size(); j++){
                        aux.add(cua4.get(i).get(j));
                    }
                    cua4.remove(i);

                    System.out.println("tamany: " + cua4.size());

                    aux.remove(d);*/

                        }
                    }
                }

            /*for (int i = 0; i < aux.size(); i++){

                addJoc4(aux.get(i));
                System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
            }*/

            } else {

                for (int i = 0; i < cuaTorneig.size(); i++) {

                    if (cuaTorneig.get(i).contains(d)) {

                        System.out.println("tamany: " + cuaTorneig.size());

                        for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                            aux.add(cuaTorneig.get(i).get(j));
                        }
                        cuaTorneig.remove(i);

                        System.out.println("tamany: " + cuaTorneig.size());

                        aux.remove(d);
                    }
                }

                for (int i = 0; i < aux.size(); i++) {
                    addCampeonat(aux.get(i));
                    System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void acabaPartida4(DedicatedServer d) throws IOException {

        ArrayList<DedicatedServer> aux = new ArrayList<>();
        boolean conte;
        for (int i = 0; i < cua4.size(); i++) {
            conte = false;
            for (int j = 0; j < cua4.get(i).size(); j++) {
                if (cua4.get(i).get(j) != null && cua4.get(i).get(j).getLogin().equals(d.getLogin())) {
                    conte = true;
                }
            }
            if (conte) {
                System.out.println("Elimina la partida 4");
                for (int j = 0; j < cua4.get(i).size(); j++) {
                    if(cua4.get(i).get(j) != null){
                        aux.add(cua4.get(i).get(j));
                        aux.get(aux.size() - 1).getDoStreamO().writeObject("ABANDONAT");
                        System.out.println("enviat abandonat");
                    }
                }
                cua4.remove(i);
            }
        }

        for (int i = 0; i < aux.size(); i++) {
            aux.get(i).setJuga(false);
            addJoc4(aux.get(i));
            System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
        }
    }

    public void acabaPartidaTorneig(DedicatedServer d) throws IOException{

        ArrayList<DedicatedServer> aux = new ArrayList<>();

        for (int i = 0; i < cuaTorneig.size(); i++) {

            if (cuaTorneig.get(i).contains(d)) {

                System.out.println("tamany: " + cuaTorneig.size());

                for (int j = 0; j < cuaTorneig.get(i).size(); j++) {
                    aux.add(cuaTorneig.get(i).get(j));
                }
                cuaTorneig.remove(i);

                System.out.println("tamany: " + cuaTorneig.size());

                aux.remove(d);
            }
        }

        for (int i = 0; i < aux.size(); i++) {
            addCampeonat(aux.get(i));
            System.out.println(aux.get(i).getLogin() + " afegit a una nova cua");
        }
    }
}
