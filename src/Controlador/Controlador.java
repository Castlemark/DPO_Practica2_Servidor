package Controlador;

import Client_Servidor.Server;
import Model.Model_usuari;
import Vista.VistaServidor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Arxiu;

/**
 * Classe del controlador
 * Created by Grup 6 on 30/03/2017.
 */
public class Controlador implements ActionListener{

    //Atributs
    private VistaServidor vista;
    private Model_usuari model;
    private Server server;
    private final GestionarPartides gPartides;
    private boolean connectat = false;

    //Constructor
    public Controlador(VistaServidor vista, Model_usuari model) throws IOException{

        this.vista = vista;
        this.model = model;
        gPartides = new GestionarPartides();
        server = new Server(vista.getPort(), gPartides);
        Arxiu arxiu = new Arxiu();
        arxiu = arxiu.llegeixDades();
        vista.actualitzaPort(arxiu.getportClient());

    }

    //Metodes

    public void actionPerformed(ActionEvent event){

        try {

            if (event.getSource() instanceof JMenuItem){

                if (event.getActionCommand().equals("GEST")){
                    vista.gsUpdateList(model.recuperaLogins());
                    vista.changePanel(event.getActionCommand());


                }
                else if (event.getActionCommand().equals("GRAFIC")){
                    vista.grUupdateLoginList(model.recuperaLogins());
                    vista.changePanel(event.getActionCommand());
                }
                else if (event.getActionCommand().equals("RANQUING")){
                        vista.rankingUpdateList(model.ompleRanquing());
                        vista.changePanel(event.getActionCommand());


                }
                else {
                    vista.changePanel(event.getActionCommand());
                }

            }
            else if (event.getSource() instanceof JButton){

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
                    Arxiu arxiu = new Arxiu();
                    arxiu.escriuPort(vista.getPort());
                }
                else if (event.getActionCommand().equals("ATURAR")){
                    if (connectat){

                        connectat = false;
                        server.stopServer();
                        server.getsSocket().close();
                        server = new Server(vista.getPort(), gPartides);
                    }
                }

            }
            else if (event.getSource() instanceof JComboBox){

                if (event.getActionCommand().equals("TRIA")){
                    vista.gsUpdateInfo(model.recuperaDadesUsuari(vista.gsGetSelectedLogin()));
                } else if (event.getActionCommand().equals("LOGIN")) {
                    System.out.println("login "+ vista.getGraficLogin());
                    ArrayList<Integer> punts = model.getHistorial(vista.getGraficLogin());

                    if (punts.size() != 1) {
                        vista.creaGrafic(punts);
                    } else if (vista.getGraficLogin()==null) {}
                    else {
                        JOptionPane.showMessageDialog(null, "Aquest jugador no ha jugat cap partida!");
                    }
                }
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
