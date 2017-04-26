package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Andreu on 26/04/2017.
 */
public class Ranquing extends JFrame {
        private JPanel jpGestionar;
        private JPanel jpTitle;
        private JPanel jpTaula;
        private JButton jbAtras;
        private JLabel jlTitle;
        private ButtonColumn taula;
        private JTable jtTaula;
        private int rows;

        public Ranquing () {

            this.setTitle("Ranquing");
            this.setSize (350, 200);
            this.setResizable(true);

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
            jlTitle = new JLabel("                                  Rànquing");
            //jtTaula = new JTable(rows, 5); DESCOMENTAR ???

            jpGestionar.setLayout(new GridLayout(2,1));

            //jpTaula.add(taula, BorderLayout.CENTER);
            //this.getContentPane().add(new JScrollPane(jtTaula), BorderLayout.CENTER);

            jpTaula.add(jtTaula, BorderLayout.CENTER);


            jpTitle.add(jbAtras, BorderLayout.LINE_START);
            jpTitle.add(jlTitle, BorderLayout.CENTER);

            jpGestionar.add(jpTitle, BorderLayout.PAGE_START);
            jpGestionar.add(jpTaula, BorderLayout.CENTER);

            this.getContentPane().add(jpGestionar, BorderLayout.PAGE_START);
        }

    }


