package Model;

/**
 * Created by Propietario on 18/05/2017.
 */
public class Partida {
    private int type;
    private String login1;
    private String login2;
    private String login3;
    private String login4;

    public Partida(String l1, String l2){
        type = 1;

    }


    public Partida(int type, String l1, String l2, String l3, String l4){
        this.type = type;
        switch (type){
            case 2:

                break;
            case 3:

                break;
        }

    }



}
