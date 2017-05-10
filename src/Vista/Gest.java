/*
 * Created by JFormDesigner on Mon May 08 16:04:19 CEST 2017
 */

package Vista;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.Document;

import Controlador.Controlador;
import net.miginfocom.swing.*;

/**
 * @author Marc Castells
 */
public class Gest extends JPanel {
    public Gest() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Marc Castells
        label6 = new JLabel();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        hSpacer3 = new JPanel(null);
        hSpacer4 = new JPanel(null);
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();
        hSpacer5 = new JPanel(null);
        hSpacer2 = new JPanel(null);
        button1 = new JButton();

        //======== this ========

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {98, 0, 90, 379, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 118, 166, 56, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label6 ----
        label6.setText(" ");
        add(label6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 7, 7), 0, 0));

        //---- label5 ----
        label5.setText("Usuari");
        add(label5, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 7, 7), 0, 0));
        add(comboBox1, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 7, 7), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textPane1);
        }
        add(scrollPane1, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer5, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 7, 7), 0, 0));
        add(hSpacer2, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 7, 0), 0, 0));

        //---- button1 ----
        button1.setText("Elimina");
        button1.addActionListener(e -> button1ActionPerformed(e));
        add(button1, new GridBagConstraints(2, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 7), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Marc Castells
    private JLabel label6;
    private JLabel label5;
    private JComboBox comboBox1;
    private JPanel hSpacer3;
    private JPanel hSpacer4;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    private JPanel hSpacer5;
    private JPanel hSpacer2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void registerController(Controlador c){
        button1.setActionCommand("ELIMINA");
        comboBox1.setActionCommand("TRIA");

        button1.addActionListener(c);
        comboBox1.addActionListener(c);

    }

    public void setLlistaLogin(ArrayList<String> logins){
        comboBox1.removeAllItems();

        for (String s:logins){
            comboBox1.addItem(s);
        }

    }

    public void updateInfo(String text){
        textPane1.setText("");
        textPane1.replaceSelection(text);
    }

    public String getSelectedLogin(){
        return (String) comboBox1.getSelectedItem();
    }
}
