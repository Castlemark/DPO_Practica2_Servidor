package Model;

import java.sql.ResultSet;
import Model.utils.ConectorDB;

/**
 * Created by Marc on 05/04/2017.
 */
public class Model_usuari {
    private String login;
    private String mail;
    private String password;

    public Model_usuari (){
    }

    //Aquest metode comprova que les dades omplertes per l'usuari tinguin informació i un format correcte
    //Si alguna dada no compleix les condicions, retorna fals
    public boolean comprovaDadesFormat(String nomUsuari, String correu, String contrasenya, String confirmacioContra){
        if(nomUsuari.isEmpty()){
            return false;
        }
        if(correu.isEmpty()){
            return false;

        }else{
            /*comprovem el format del correu mirant lletra per lletra:
              - ha de tenir només una arrova
              - ha de tenir almenys un punt després de l'arrova i aquest no pot estar just després ni al final
              - no pot tenir espais
             */
            int arrova = 0;
            boolean hiHaPunt = false;
            for (int i = 0; i < correu.length(); i++){
                switch(correu.charAt(i)){
                    case ' ':
                        return false;
                    case '@':
                        if(i == 0 || arrova != 0 || i > correu.length() - 1) {return false;}
                        arrova = i;
                        break;
                    case '.':
                        if(arrova != 0){
                            hiHaPunt = true;
                            if(arrova == i - 1 || i + 1 == correu.length()){return false;}
                        }
                        break;
                }
            }
            if (arrova == 0 || !hiHaPunt){return false;}
        }
        /*comprovem el format de la contrasenya mirant lletra per lletra:
            - ha de tenir almenys una minúsucla, una majúsucula i un número
            - ha de tenir mínim 6 lletres
         */
        if(contrasenya.isEmpty()){
            return false;
        }else {
            boolean hiHaMaj = false;
            boolean hiHaMin = false;
            boolean hiHaNum = false;
            for(int i = 0; i < contrasenya.length(); i++){

                if(Character.isLowerCase(contrasenya.charAt(i))){
                    hiHaMin = true;
                }
                if(Character.isUpperCase(contrasenya.charAt(i))){
                    hiHaMaj = true;
                }
                if(Character.isDigit(contrasenya.charAt(i))) {
                    hiHaNum = true;
                }
            }
            //Mirem si s'han complert les condicions de la contrasenya
            if(!(hiHaMaj && hiHaMin && hiHaNum && contrasenya.length() >= 6)){return false;}
        }
        if(confirmacioContra.isEmpty()) {return false;}
        if(contrasenya.equals(confirmacioContra)) {return true;}
        //Si totes les dades són correctes arribem aquí i retorna cert
        return false;
    }

    //Aquest metode comprova que les dades omplertes per l'usuari no existeixin a la base de dades
    public boolean comprovaDadesInsercio(String nomUsuari, String correu, String contrasenya){
        return true;

    }

    public void regitraUsuari(){

        ConectorDB conn = new ConectorDB("root", "12069554eE", "troner", 3306);
        conn.connect();

        conn.insertQuery("INSERT INTO usuari (login, mail, contrasenya) VALUES (" + this.login + this.mail + this.password +")");
        conn.disconnect();

    }

    public ResultSet recuperaUsuaris(){

        ResultSet resultats;

        ConectorDB conn = new ConectorDB("root", "12069554eE", "troner", 3306);
        conn.connect();

        resultats = conn.selectQuery("SELECT id_jugador, login, mail FROM usuari");
        conn.disconnect();

        return  resultats;
    }

    public void eliminaUsuari(String login){
        ConectorDB conn = new ConectorDB("root", "12069554eE", "troner", 3306);
        conn.connect();

        conn.deleteQuery("DELETE FROM usuari WHERE login =" + login );
        conn.disconnect();

    }
}
