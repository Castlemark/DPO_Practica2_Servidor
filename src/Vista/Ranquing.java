package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Andreu on 18/04/2017.
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
            this.setSize (500, 200);
            this.setResizable(true);

            jpGestionar = new JPanel();
            jpTitle = new JPanel(new BorderLayout());
            jpTaula = new JPanel(new BorderLayout());

            String[] columnNames = {"Posicio", "Nickname", "Ult. Accés", "Punts"};
            Object[][] data =
                    {
                            {"1", "Homer", "12/12/1234", "1"},
                            {"2", "Madge", "12/12/1235", "10"},
                            {"3", "Bart",  "12/12/1236", "25"},
                            {"4", "Lisa",  "12/12/1237", "50"},
                    };

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable jtTaula = new JTable( model );


            jbAtras = new JButton("<-");
            jlTitle = new JLabel("                                                         Ranquing");
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
