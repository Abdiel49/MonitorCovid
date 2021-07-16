package cargarregistros.gui;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

public class RecordsList extends JPanel {

    private final Registros registros;
    private final List<Registro> sortedListRecord;
    private final JList<String> recordJList;
    private final DefaultListModel<String> listModel;
    private final DefaultTableModel dataModel;
    private final JPanel recordListPanel;
    private final JPanel recordDataPanel;
    private int indexSelected;

    public RecordsList(Registros r){
        registros = r;
        sortedListRecord = new LinkedList<>();
        listModel = new DefaultListModel<>();
        dataModel = new DefaultTableModel();
        recordJList = new JList<>(listModel);
        recordDataPanel = new JPanel();
        recordListPanel = new JPanel();
        indexSelected=-1;
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
        recordJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        recordDataPanel.setLayout( new BoxLayout(this, BoxLayout.X_AXIS));
        loadData();
        recordJList.setSelectedIndex(0);
        JScrollPane scroll = new JScrollPane(recordJList);
        addActionList();
        recordListPanel.add(scroll);
    }

    private void loadData(){
        for(Registro r : registros){
            addRecord(r);
        }
    }

    public void addRecord(Registro r){
        recordJList.setSelectedIndex(0);
        listModel.add(0, r.getFecha().toString());
        sortedListRecord.add(0,r);
        recordJList.setSelectedIndex(0);
    }

    private void addActionList(){
        recordJList.addListSelectionListener( e ->{
                int index = recordJList.getSelectedIndex();
                loadDataRecord(index);
            }
        );
    }

    private void buildTableRecordData(){
        JTable table = new JTable();
        dataModel.setColumnIdentifiers(new String[]{"Nombre","Tipo"});
        table.setModel(dataModel);
        loadDataRecord(recordJList.getSelectedIndex());
        JScrollPane scroll = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);
        recordDataPanel.add(scroll);
    }

    private void loadDataRecord(int index){
        if (index <= sortedListRecord.size() && sortedListRecord.size() > 0 ){
            Sintomas sintomas = sortedListRecord.get(index).getSintomas();
            dataModel.setRowCount(0);
            for(Sintoma s : sintomas){
                String type = s.getClass().getName().replace("sintomas.","");
                String value = s.toString();
                dataModel.insertRow(0, new String[]{value,type});
            }
        }
    }
}
