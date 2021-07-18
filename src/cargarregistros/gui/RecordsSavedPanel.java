package cargarregistros.gui;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RecordsSavedPanel extends JPanel {

    private final Registros registros;
    private final DefaultTableModel dataModel;
    private final JPanel recordListPanel;
    private final JPanel recordDataPanel;
    private final ListRecords listRecords;

    public RecordsSavedPanel(Registros r){
        registros = r;
        dataModel = new DefaultTableModel();
        listRecords = new ListRecords();
        recordDataPanel = new JPanel();
        recordListPanel = new JPanel();
        init();
        setVisible(true);
    }

    private void init(){
        buildTableRecords();
        buildTableRecordData();
        add(recordListPanel);
        add(recordDataPanel);
    }

    private void buildTableRecords(){
        recordListPanel.setLayout( new BoxLayout(recordListPanel, BoxLayout.Y_AXIS));
        for( Registro r : registros){
            listRecords.addRecord(0,r);
        }
        JScrollPane scroll = new JScrollPane(listRecords);
        addActionList();
        recordListPanel.add(new JLabel("Registros hechos"));
        recordListPanel.add(scroll);
    }

    public void addRecord(Registro r){
        listRecords.addRecord(0,r);
    }

    private void addActionList(){
        listRecords.addListSelectionListener(
            e -> loadDataRecord(listRecords.getSelectedItem())
        );
    }

    private void buildTableRecordData(){
        recordDataPanel.setLayout( new BoxLayout(recordDataPanel, BoxLayout.Y_AXIS));
        JTable table = new JTable();
        dataModel.setColumnIdentifiers(new String[]{"Nombre","Tipo"});
        table.setModel(dataModel);
        loadDataRecord(listRecords.getSelectedItem());
        JScrollPane scroll = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);
        recordDataPanel.add(new JLabel("Sintomas del registro"));
        recordDataPanel.add(scroll);
    }

    private void loadDataRecord(Registro r){
        if(r!=null){
            Sintomas sintomas =r.getSintomas();
            dataModel.setRowCount(0);
            for(Sintoma s : sintomas){
                String type = s.getClass().getSimpleName();
                String value = s.toString();
                dataModel.insertRow(0, new String[]{value,type});
            }
        }
    }
}
