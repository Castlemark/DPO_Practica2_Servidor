package Controlador;

import Client_Servidor.DedicatedServer;
import Model.Partida;
import Model.Partida2;
import com.sun.deploy.resources.Deployment_de;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Signature;
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

        if(currentCua2.size() == 2){
            Partida2 p2 = new Partida2(currentCua2);
            for(int i = 0; i < 2; i++){
                currentCua2.get(i).getDoStreamO().writeObject("COMENÇA");
                System.out.println("comença " + currentCua2.get(i).getLogin());
            }

            System.out.println("afegit a cua");
            cua2.add(currentCua2);
            System.out.println(cua2.get(0).size());
            currentCua2.clear();
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

    public void checkPartida(DedicatedServer d){

        boolean aux = false;
        ArrayList<DedicatedServer> daux = new ArrayList<>();

        System.out.println("tamany cua2: " + cua2.size());
        for (int i = 0; i < cua2.size(); i++){
            ArrayList<DedicatedServer> temp = cua2.get(i);
            System.out.println("tamany temp: " + temp.size());
            System.out.println(d.getLogin() + " esborrat");
            temp.remove(d);
            if (temp.isEmpty()){
                System.out.println("cua buida");
                aux = true;
                daux.addAll(temp);
            }
        }

        if (aux){
            System.out.println("Cua esborrada");
            cua2.remove(daux);
        }

        /*boolean aux = true;

        for (DedicatedServer temp: cua){

            System.out.println(cua.size());
            System.out.println(temp.getLogin() + " esta mort? " + temp.isMort());
            if (!temp.isMort()){
                aux = false;
            }
        }

        if (aux){

            for (DedicatedServer temp: cua){
                temp.setMort(false);
            }

            System.out.println("esborrat");
            cua2.remove();
        }*/
    }

    public ArrayList<DedicatedServer> getCua(DedicatedServer d){
        int k = 0;

        for (int i = 0; i< cua2.size(); i++){

            System.out.println(cua2.get(i).size() + " per i = " + i);
            for (int j = 0; j < cua2.get(i).size(); j++){

                System.out.println(cua2.get(i).get(j).getLogin() + " : " + d.getLogin());
                if (cua2.get(i).get(j).getLogin().equals(d.getLogin())){
                    k = i;
                }

            }
        }
        return cua2.get(k);
    }



}
