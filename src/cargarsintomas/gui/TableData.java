package cargarsintomas.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public class TableData extends JTable{

    private final DefaultTableModel model;
    private final Sintomas sintomas;

    public TableData(Sintomas s){
        sintomas = s;
        model = new DefaultTableModel();
        initTable();
        loadTable();
//        setAutoCreateRowSorter(true);
        sortDataTable();
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

    private void sortDataTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.getModel());
        sorter.setSortsOnUpdates(true);
        setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int nameColumn = 0;
        sortKeys.add(new RowSorter.SortKey(nameColumn, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    public void insertRow(String param, String type){
        model.insertRow(0, new String[]{param,type});
    }
}
