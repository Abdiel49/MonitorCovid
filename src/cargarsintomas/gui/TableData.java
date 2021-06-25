package cargarsintomas.gui;

import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableData extends JTable{

    private String[] columns;
    private String[][] data;
    private DefaultTableModel model;

    public TableData(Sintomas sintomas){
        this.columns = new String []{"Tipo","Nombre"};
        this.data = new String[][]{{"sintoams","tos"},
                                    {"Primera fase", "Fiebre"}
        };
        model = new DefaultTableModel(0,0);
        model.setColumnIdentifiers(columns);
        this.setModel(model);
//        super(data, colums);
        initTable();
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
//        model.addColumn("Valor");
//        model.addColumn("Hola");
//        model.addColumn("Tipo Sintoma");
        model.insertRow(0, new String[]{"sintoams","tos"});
    }

    private void addRows(){
        for(String[] r : data ){
//            addRowData(r);
        }
    }

//    public void addRowData(String[] row){
////        model.addRow(row);
//    }
}
