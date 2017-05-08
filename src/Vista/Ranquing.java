package Vista;

import Controlador.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Andreu on 26/04/2017.
 */
public class Ranquing extends JPanel {
        private JPanel jpGestionar;
        private JPanel jpTitle;
        private JPanel jpTaula;
        private JButton jbAtras;
        private JLabel jlTitle;
        private ButtonColumn taula;
        private JTable jtTaula;
        private int rows;

        public Ranquing () {

            this.setSize (350, 200);

            jpGestionar = new JPanel();
            jpTitle = new JPanel(new BorderLayout());
            jpTaula = new JPanel(new BorderLayout());

            String[] columnNames = {"Pos.", "Nickname", "Data_U.A", "Punts"};
            Object[][] data =
                    {
                            {"1", "Homer", "10/10/1234", "32"},
                            {"2", "Madge", "11/10/1234", "15"},
                            {"3", "Bart",  "12/10/1242", "7"},
                            {"4", "Lisa",  "13/10/1234", "0"},
                    };

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable jtTaula = new JTable( model );


            jbAtras = new JButton("<-");
            jlTitle = new JLabel("Rànquing", SwingConstants.CENTER);

            jpGestionar.setLayout(new GridLayout(2,1));

            jpTaula.add(jtTaula, BorderLayout.CENTER);


            //jpTitle.add(jbAtras, BorderLayout.LINE_START);
            jpTitle.add(jlTitle, BorderLayout.CENTER);

            jpGestionar.add(jpTitle, BorderLayout.PAGE_START);
            jpGestionar.add(jpTaula, BorderLayout.CENTER);

            this.add(jpGestionar, BorderLayout.PAGE_START);
        }

        public void registerController(Controlador c){

        }

        public void updateList(Object[][] data){

            //Metode bombolla
        }

    }


