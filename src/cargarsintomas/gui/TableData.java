package cargarsintomas.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableData extends JTable{

    private final DefaultTableModel model;
    private final Sintomas sintomas;

    public TableData(Sintomas s){
        sintomas = s;
        model = new DefaultTableModel();
        initTable();
        loadTable();
    }

    private void initTable(){
        model.setColumnIdentifiers(new String []{"Nombre","Tipo"});
        setModel(model);
    }

    private void loadTable(){
        for(Sintoma s : sintomas){
            String type = s.getClass().getName().replace("sintomas.","");
            String value = s.toString();
            insertRow(value, type);
        }
    }

    public void insertRow(String param, String type){
        model.insertRow(0, new String[]{param,type});
    }
}
