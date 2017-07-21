package Vista;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by sullivan on 20/07/2017.
 */
public class TableRanquing extends DefaultTableModel {


    // T�tol de les columnes
    private final String[] columnNames = {"Posició", "Login", "Data últim accés", "Puntuació"};

    // Indica el tipus de les columnes
    private final Class<?>[] columnTypes = {
            java.lang.Integer.class,
            java.lang.String.class,
            java.util.Date.class,
            java.lang.Integer.class,

    };

    // Les dades que es mostraran a la taula, una llista d'objectes de tipus Object
    private Object[][] dades;

    public TableRanquing(){

        dades= new Object[0][4];
    }

    public TableRanquing(Object[][] dades){

        this.dades = dades;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        if(dades == null) {
            return 0;
        } else {
            return dades.length;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return columnTypes[col];
    }


    public Object getValueAt(int row, int col) {

        switch(col) {
            case 0: return dades[row][col];
            case 1: return dades[row][col];
            case 2: return dades[row][col];
            case 3: return dades[row][col];
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
