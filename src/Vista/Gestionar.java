package Vista;

import Controlador.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private String[] columnNames = {"Login", "Punts", "Data Registre", "Ultim Acces", ""};
    private Object[][] data =
            {
                    {"Homer", 69, "24-2-98", "21-3-98", "delete"},

            };

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



        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable jtTaula = new JTable( model );


        jlTitle = new JLabel("Gestionar Usuari", SwingConstants.CENTER);
        //jtTaula = new JTable(rows, 5); DESCOMENTAR ???
        taula = new ButtonColumn(jtTaula, delete, 4);

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

    public void updateList(ResultSet rs){
        try {
            int i = 0;
            int j = 0;
            Object[] inserir = new Object[5];

            while (rs.next()){
                i++;
            }
            rs.beforeFirst();

            data = new Object[i][5];

            while (rs.next()){
                inserir[0] = rs.getString(2);
                inserir[1] = rs.getInt(5);
                inserir[2] = rs.getDate(6);
                inserir[3] = rs.getDate(6);
                inserir[4] = "Delete";
            }
        }
        catch (SQLException e){
            e.getMessage();
        }

    }

}
