package Vista;

import Controlador.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by sullivan on 18/04/2017.
 */
public class Gestionar extends JPanel {
    private JPanel jpGestionar;
    private JPanel jpTaula;
    private JButton jbAtras;
    private JLabel jlTitle;
    private ButtonColumn taula;
    private JTable jtTaula;
    private int rows;

    Action delete = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable)e.getSource();
            int modelRow = Integer.valueOf( e.getActionCommand() );
            ((DefaultTableModel)table.getModel()).removeRow(modelRow);
        }
    };

    public Gestionar () {
        this.setSize (350, 350);

        jpGestionar = new JPanel();
        jpTaula = new JPanel(new BorderLayout());

        String[] columnNames = {"First Name", "Last Name", ""};
        Object[][] data =
                {
                        {"Homer", "Simpson", "delete"},
                        {"Madge", "Simpson", "delete"},
                        {"Bart",  "Simpson", "delete"},
                        {"Lisa",  "Simpson", "delete"},
                };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable jtTaula = new JTable( model );


        jlTitle = new JLabel("Gestionar Usuari", SwingConstants.CENTER);
        //jtTaula = new JTable(rows, 5); DESCOMENTAR ???
        taula = new ButtonColumn(jtTaula, delete, 2);

        jpGestionar.setLayout(new GridLayout(2,1));

        //jpTaula.add(taula, BorderLayout.CENTER);
        //this.getContentPane().add(new JScrollPane(jtTaula), BorderLayout.CENTER);

        jpTaula.add(jtTaula, BorderLayout.CENTER);




        jpGestionar.add(jlTitle, BorderLayout.PAGE_START);
        jpGestionar.add(jpTaula, BorderLayout.CENTER);

        this.add(jpGestionar, BorderLayout.PAGE_START);
    }

    public void registerController(Controlador c){

    }

}
