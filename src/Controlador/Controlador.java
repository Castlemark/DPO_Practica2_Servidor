package Controlador;

import Client_Servidor.Server;
import Model.Model_usuari;
import Vista.VistaServidor;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import Model.Arxiu;

/**
 * Classe del controlador
 *
 * Created by Grup 6 on 30/03/2017.
 */
public class Controlador implements ActionListener{

    private VistaServidor vista;
    private Model_usuari model;
    private Server server;
    private final GestionarPartides gPartides;

    private boolean connectat = false;

    public Controlador(VistaServidor vista, Model_usuari model) throws IOException{
        this.vista = vista;
        this.model = model;
        gPartides = new GestionarPartides();
        server = new Server(11111, gPartides);
        Arxiu arxiu = new Arxiu();
//        arxiu = arxiu.llegeixDades();
        vista.actualitzaPort(arxiu.getportClient());

    }

    public void actionPerformed(ActionEvent event){

        try {

            if (event.getActionCommand().equals("INICIAR")){
                String stringPortClient = vista.getPort();
                int portClient = Integer.parseInt(stringPortClient);
     //           Arxiu arxiu = new Arxiu();
     //           arxiu.escriuPort(portClient);
     //           arxiu.llegeixDades();
                System.out.println("Port de la base de Dades desat correctament");
            }



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
                        server.startServer();
                        connectat = true;
                    }
                }
                else if (event.getActionCommand().equals("ATURAR")){
                    if (connectat){

                        server = null;
                        server = new Server(11111, gPartides);
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
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
