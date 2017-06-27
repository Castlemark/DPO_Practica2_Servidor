package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;



/**
 * Created by Andreu on 10/05/2017.
 */
public class Arxiu {
    @SerializedName("Port Base de Dades")
    private int portBBDD;
    private String IP;
    @SerializedName("Nom Base de Dades")
    private String nomBBDD;
    @SerializedName("Usuari Base de Dades")
    private String usuariBBDD;
    @SerializedName("Password Base de Dadess")
    private String passwordBBDD;
    @SerializedName("Port Client")
    private int portClient;


    public void setportBBDD(int portBBDD) {
        this.portBBDD = portBBDD;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setnomBBDD(String nomBBDDD) {
        this.nomBBDD = nomBBDDD;
    }

    public void setusuariBBDD(String usuariBBDD) {
        this.usuariBBDD = usuariBBDD;
    }

    public void setpasswordBBDD(String passwordBBDD) {
        this.passwordBBDD = passwordBBDD;
    }

    public void setportClient(int portClient) {
        this.portClient = portClient;
    }

    public int getportBBDD() {
        return portBBDD;
    }

    public String getIP() {
        return IP;
    }

    public String getnomBBDD() {
        return nomBBDD;
    }

    public String getusuariBBDD() {
        return usuariBBDD;
    }

    public String getpasswordBBDD() {
        return passwordBBDD;
    }

    public int getportClient() {
        return portClient;
    }


    public Arxiu() {
    }


    public Arxiu llegeixDades() {

        Arxiu arxiu = new Arxiu();
        try {

            Gson gson = new Gson();

            BufferedReader br = new BufferedReader(new FileReader("config.json"));
            arxiu = gson.fromJson(br, Arxiu.class);
            System.out.println(arxiu.getportBBDD());
            System.out.println(arxiu.getIP());
            System.out.println(arxiu.getnomBBDD());
            System.out.println(arxiu.getusuariBBDD());
            System.out.println(arxiu.getpasswordBBDD());
            System.out.println(arxiu.getportClient());

        } catch (IOException e) {
            System.err.println("Hi ha hagut un problema al llegir el fitxer config.json");
        }

        return arxiu;
    }

    public void escriuDades(int portBBDD, String IP, String nomBBDD, String usuariBBDD, String passwordBBDD, int portClient) {
        Arxiu arxiu = new Arxiu();


        arxiu.setportBBDD(portBBDD);
        arxiu.setIP(IP);
        arxiu.setnomBBDD(nomBBDD);
        arxiu.setusuariBBDD(usuariBBDD);
        arxiu.setpasswordBBDD(passwordBBDD);
        arxiu.setportClient(portClient);

        Gson gson = new Gson();
        String textGson = gson.toJson(arxiu);

        try {

            FileWriter writer = new FileWriter("config.json");
            writer.write(textGson);
            writer.close();

        } catch (IOException e) {
            System.err.println("Hi ha hagut un problema escrivint al fitxer config.json");

        }
        System.out.println("S'ha guardat el arxiu");

    }

    public void escriuPort(int portClient){
        Arxiu arxiu = llegeixDades();
        arxiu.setportClient(portClient);
        Gson gson = new Gson();
        String textGson = gson.toJson(arxiu);

        try {

            FileWriter writer = new FileWriter("config.json");
            writer.write(textGson);
            writer.close();

        } catch (IOException e) {
            System.err.println("Hi ha hagut un problema escrivint al fitxer config.json");

        }



    }

}

