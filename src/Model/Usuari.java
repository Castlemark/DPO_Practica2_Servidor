package Model;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Classe de l'usuari en el model del client
 *
 * Created by Grup 6 on 30/03/2017.
 */
public class Usuari implements Serializable{
    private String login;
    private String mail;
    private String password;
    private int up;
    private int down;
    private int left;
    private int right;

    public Usuari (){

    }
    public Usuari(String login, String mail, String password){
        this.login = login;
        this.mail = mail;
        this.password = password;
    }

  /*  public Usuari(String login, String mail, String password){
        this.login = login;
        this.mail = mail;
        this.password = password;
    }*/

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Aquesta funció comprova que les dades omplertes per l'usuari tinguin informació i un format correcte
     *
     * @param nomUsuari
     * @param correu
     * @param contrasenya
     * @param confirmacioContra
     * @return Si alguna dada no compleix les condicions, retorna fals
     */

    public boolean comprovaDades(String nomUsuari, String correu, String contrasenya, String confirmacioContra){
        if(nomUsuari.isEmpty()){
            System.out.println("Escriu un nom d'usuari");
            return false;
        }
        if(correu.isEmpty()){
            System.out.println("Format del correu electrònic incorrecte");
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
                        System.out.println("Format del correu electrònic incorrecte");
                        return false;
                    case '@':
                        if(i == 0 || arrova != 0 || i > correu.length() - 1) {
                            System.out.println("Format del correu electrònic incorrecte");
                            return false;}
                        arrova = i;
                        break;
                    case '.':
                        if(arrova != 0){
                            hiHaPunt = true;
                            if(arrova == i - 1 || i + 1 == correu.length()){
                                System.out.println("Format del correu electrònic incorrecte");
                                return false;}
                        }
                        break;
                }
            }
            if (arrova == 0 || !hiHaPunt){
                System.out.println("Format del correu electrònic incorrecte");
                return false;
            }
        }
        /*comprovem el format de la contrasenya mirant lletra per lletra:
            - ha de tenir almenys una minúsucla, una majúsucula i un número
            - ha de tenir mínim 6 lletres
         */
        if(contrasenya.isEmpty()){
            System.out.println("Format de la contrasenya incorrecte");
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
            if(!(hiHaMaj && hiHaMin && hiHaNum && contrasenya.length() >= 6)){
                System.out.println("Format de la contrasenya incorrecte");
                return false;
            }
        }
        if(confirmacioContra.isEmpty()) {
            System.out.println("Format de la contrasenya incorrecte");
            return false;
        }
        if(contrasenya.equals(confirmacioContra)) {return true;}
        //Si totes les dades són correctes arribem aquí i retorna cert
        return false;
    }

    /**
     * Aquesta funció s'ocupa de registar un nou usuari
     * @return cert si s'ha executat amb èxit
     */
    public boolean registreUsuari(){

        Scanner sc = new Scanner(System.in);
        String loginaux;
        String correuaux;
        String passwordaux;
        String passwordaux2;


        System.out.println("Nom Usuari?");
        loginaux = sc.nextLine();
        System.out.println("Mail?");
        correuaux = sc.nextLine();
        System.out.println("contrasenya?");
        passwordaux = sc.nextLine();
        System.out.println("confirmacio contrasenya?");
        passwordaux2 = sc.nextLine();

        while (!comprovaDades( loginaux, correuaux, passwordaux, passwordaux2)){


            System.out.println("Nom Usuari?");
            loginaux = sc.nextLine();
            System.out.println("Mail?");
            correuaux = sc.nextLine();
            System.out.println("contrasenya?");
            passwordaux = sc.nextLine();
            System.out.println("confirmacio contrasenya?");
            passwordaux2 = sc.nextLine();
        }

        login = loginaux;
        mail = correuaux;
        password = passwordaux;

        return true;
    }

    /**
     * Mètode iniciar sessio
     * @param nomUsuariCorreu
     * @param contrasenya
     * @return
     */
    public boolean iniciarSessio(String nomUsuariCorreu, String contrasenya){
        return true;
    }

    /**
     * Mètode per tancar la sessió
     * @return
     */
    public boolean tancarSessio(){
        return true;
    }
}

