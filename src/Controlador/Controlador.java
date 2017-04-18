package Controlador;

/**
 * Created by Andreu on 30/03/2017.
 */
public abstract class Controlador {

    public Usuari usuari;

    public abstract void registreUsuari();
    public abstract void repRegistre();
    public abstract void comprovaRegistre();
    public abstract void eliminaUsuari();

}

