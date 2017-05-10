package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Serp del model del client
 *
 * És la classe de les serps, guarda la seva posició...
 * Created by Grup 6 on 06/04/2017.
 */
public class Serp implements Serializable{
    private ArrayList<Posicio> posicions;
    private Posicio cap;
    private int dir = 1;

    public Serp(){
        posicions = new ArrayList<>();
        cap = new Posicio(10, 10);
        posicions.add(new Posicio(10, 10));
        dir = 1;
    }
    public void mouSerp(){cap.mouCap(dir);}

    public void canviaDireccio (char c){
        if(c == 's' && dir != 3 && dir != 4){
            dir = 3;
            posicions.add(new Posicio(cap.getX(), cap.getY()));
        }
        if(c == 'a' && dir != 2 && dir != 1){
            dir = 2;
            posicions.add(new Posicio(cap.getX(), cap.getY()));
        }
        if(c == 'd' && dir != 1 && dir != 2){
            dir = 1;
            posicions.add(new Posicio(cap.getX(), cap.getY()));
        }
        if(c == 'w' && dir != 4 && dir != 3){
            dir = 4;
            posicions.add(new Posicio(cap.getX(), cap.getY()));
        }
    }
    /**
     * Getter de la posició
     * @return posicio
     */
    public ArrayList<Posicio> getPosicions() {
        return posicions;
    }

    /**
     * Setter de posicions
     * @param posicions
     */
    public void setPosicions(ArrayList posicions) {
        this.posicions = posicions;
    }

    public void setCap(Posicio cap) {
        this.cap = cap;
    }

    public Posicio getCap() {
        return cap;
    }
}
