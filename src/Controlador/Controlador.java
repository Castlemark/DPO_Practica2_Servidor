package Controlador;

import Model.Model_usuari;
import Vista.VistaServidor;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Classe del controlador
 *
 * Created by Grup 6 on 30/03/2017.
 */
public class Controlador implements ActionListener{

    private VistaServidor vista;
    private Model_usuari model;
    private Network network = new Network(this);;

    private boolean connectat = false;

    public Controlador(VistaServidor vista, Model_usuari model){
        this.vista = vista;
        this.model = model;

    }

    public void actionPerformed(ActionEvent event){

        try {
            if (event.getSource() instanceof JMenuItem){
                System.out.println(event.getActionCommand() + " - pestaña");

                if (event.getActionCommand().equals("GEST")){
                    vista.gsUpdateList(model.recuperaLogins());
                    vista.changePanel(event.getActionCommand());


                }
                else if (event.getActionCommand().equals("GRAFIC")){
                    vista.grUupdateLoginList(model.recuperaLogins());
                    vista.changePanel(event.getActionCommand());
                }
                else {
                    vista.changePanel(event.getActionCommand());
                }

            }
            else if (event.getSource() instanceof JButton){
                System.out.println(event.getActionCommand() + " - boto");

                if (event.getActionCommand().equals("REGISTRAR BOTO")){
                    vista.showMessage(model.registraUsuari(vista.getLogin(), vista.getMail(), vista.getPassword(), vista.getPassword2()));
                }
                else if (event.getActionCommand().equals("ELIMINA")){
                    model.eliminaUsuari(vista.gsGetSelectedLogin());
                    vista.gsUpdateList(model.recuperaLogins());
                }
                else if (event.getActionCommand().equals("INICIAR")){
                    if (!connectat){
                        network.connect();
                        connectat = true;
                    }
                }
                //No es pot obrir i tancar el port més d'una vegada, suposo que perque tot i matar els sockets del run, no matem el thread de run.
                else if (event.getActionCommand().equals("ATURAR")){
                    if (connectat){
                        network.disconnect();
                        connectat = false;
                    }
                }

            }
            else if (event.getSource() instanceof JComboBox){

                if (event.getActionCommand().equals("TRIA")){
                    vista.gsUpdateInfo(model.recuperaDadesUsuari(vista.gsGetSelectedLogin()));
                }
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
