package Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sullivan on 18/04/2017.
 */
public class Missatge extends JFrame {
    //private JPanel jpMissatge;
    private JLabel jlMissatge;

    public Missatge () {

       this.setSize(350,350);

       //jpMissatge.setLayout(new );
       jlMissatge = new JLabel("Carregant fitxers...");

       //jpMissatge.add(jlMissatge);

       this.add(jlMissatge, BorderLayout.CENTER);
    }
}
