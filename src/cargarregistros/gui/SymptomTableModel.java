package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

public class SymptomTableModel extends DefaultTableModel {

    private final List<Sintoma> sintomaList;
    private final int BOOL_COLUMN_INDEX = 2;

    public SymptomTableModel(){
        sintomaList = new LinkedList<>();
    }

    public void insertSymptom(Sintoma s){
        sintomaList.add(s);
        String type = s.getClass().getSimpleName();
        String value = s.toString();
        Object[] data = new Object[]{value, type, false};
        addRow(data);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == BOOL_COLUMN_INDEX; // Only the first column in the table should be editable
    }

    public Sintomas getSelectedSymptoms(){
        Sintomas sintomas = new Sintomas();
        int size = sintomaList.size();
        for (int i = 0; i < size; i++) {
            boolean check = (Boolean) getValueAt(i,BOOL_COLUMN_INDEX);
            if(check) sintomas.add(sintomaList.get(i));
        }
        return sintomas;
    }

    public void unselectItems(){
        int size = sintomaList.size();
        for (int i = 0; i < size; i++) {
            setValueAt(Boolean.FALSE,i,BOOL_COLUMN_INDEX);
        }
    }
}