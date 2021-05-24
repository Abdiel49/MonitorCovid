package cargarsintomas.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableData extends JTable{

    private final String[] colums;
//    private final String[][] data;
//    private JTable table;
    private DefaultTableModel model;

    public TableData(){
        this.colums = new String []{"Tipo","Nombre"};
//        this.data = new;
        model = new DefaultTableModel();
//        table = new JTable(model);
        initTable();
        this.setModel(model);

        this.setVisible(true);
    }


    private void initTable(){
        // add scroll panel
        this.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane();
        this.setPreferredScrollableViewportSize(new Dimension(250,250));
        addColums();
//        addRows();
    }

    private void addColums(){
        model.addColumn("Tipo");
        model.addColumn("Dato");
    }

//    private void addRows(){
//        for(String[] r : data ){
//            addRowData(r);
//        }
//    }

    public void addRowData(String[] row){
        model.addRow(row);
    }
}
