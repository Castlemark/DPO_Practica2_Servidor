package Vista;

import Controlador.Controlador;
import sun.plugin.javascript.JSClassLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Andreu on 26/04/2017.
 */
public class Ranquing extends JPanel {

       // private JPanel jpTaula;
        private JLabel jlTitle;
        private JTable jtTaula;
        private int rows;
        private JScrollPane jsRanquing;

        public Ranquing () {

            this.setSize (350, 200);
            jtTaula = new JTable();
            jsRanquing = new JScrollPane(jtTaula);

            jlTitle = new JLabel("Rànquing", SwingConstants.CENTER);

            this.setLayout(new BorderLayout());

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            jtTaula.setDefaultRenderer(Integer.class, centerRenderer);

            TableRanquing tableModel = new TableRanquing();
            jtTaula.setModel(tableModel);

            this.add(jlTitle, BorderLayout.NORTH);
            this.add(jsRanquing, BorderLayout.CENTER);

        }

        public void registerController(Controlador c){

        }

        public void updateList(Object[][] data){

            TableRanquing tableModel = new TableRanquing(data);
            ((DefaultTableModel)jtTaula.getModel()).fireTableDataChanged();
            jtTaula.setModel(tableModel);

        }


    }


