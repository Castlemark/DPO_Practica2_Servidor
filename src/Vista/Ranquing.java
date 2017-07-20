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
        private JLabel jlTitle;
        private ButtonColumn taula;
        private JTable jtTaula;
        private int rows;

        public Ranquing () {

            this.setSize (350, 200);

            jpGestionar = new JPanel();
            jpTitle = new JPanel(new BorderLayout());
            jpTaula = new JPanel(new BorderLayout());


            String[] columnNames = {"Posició", "Login", "Data últim accés", "Punts"};
            Object[][] data = {{}};

           // DefaultTableModel model = new DefaultTableModel(data, columnNames);
       //     JTable jtTaula = new JTable(data,columnNames);

            jlTitle = new JLabel("Rànquing", SwingConstants.CENTER);

            jpGestionar.setLayout(new GridLayout(2,1));

         //   jpTaula.add(jtTaula, BorderLayout.CENTER);


            //jpTitle.add(jbAtras, BorderLayout.LINE_START);
            jpTitle.add(jlTitle, BorderLayout.CENTER);

            jpGestionar.add(jpTitle, BorderLayout.PAGE_START);
            jpGestionar.add(jpTaula, BorderLayout.CENTER);

            this.add(jpGestionar, BorderLayout.PAGE_START);
        }

        public void registerController(Controlador c){

        }

        public void updateList(Object[][] data){

            jpTaula.removeAll();
            String[] columnNames = {"Posició", "Login", "Data últim accés", "Punts"};
            System.out.println("prova"+data[1][1]);
            //DefaultTableModel model = new DefaultTableModel(data, columnNames);
           // System.out.println(model);;
            jtTaula = new JTable();

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            jtTaula.setModel(tableModel);

            jpTaula.add(jtTaula, BorderLayout.CENTER);
          //  jtTaula.repaint();
           // jpTaula.repaint();
           // jtTaula.updateUI();
          //  jpTaula.updateUI();


            //   model.fireTableDataChanged();

        }


    }


