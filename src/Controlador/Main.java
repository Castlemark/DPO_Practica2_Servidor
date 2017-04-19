package Controlador;

import Model.Model_usuari;

import java.sql.SQLException;

/**
 * Created by Marc on 30/03/2017.
 */
public class Main {
    public static void main(String[] args){

        try {
            Model_usuari usuari = new Model_usuari();

            usuari.registraUsuari("marc","hola@gmail.com","1234asdf");
        }
        catch (SQLException e){
            e.getMessage();
        }

    }
}
