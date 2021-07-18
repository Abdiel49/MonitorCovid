package cargarregistros.gui;

import javax.swing.JTable;

public class SymptomTableCheck extends JTable {

    @Override
    public Class<?> getColumnClass(int colum){
        return switch (colum) {
            case 0, 1 -> String.class;
            default -> Boolean.class;
        };
    }
}
