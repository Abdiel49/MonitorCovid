package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class PanelSintomas extends JPanel{

    private final Sintomas symptoms;
    private final SymptomTableModel model;
    private final SymptomTableCheck table;

    public PanelSintomas(Sintomas s){
        symptoms = s;
        model = new SymptomTableModel();
        table = new SymptomTableCheck();
        this.setPreferredSize( new Dimension(Constants.WIDTH.get()/3, Constants.HEIGHT.get()) );
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        init();
    }

    private void init(){
        model.setColumnIdentifiers(new String []{"Nombre","Tipo", "Seleccionar"});
        table.setModel(model);
        loadTableSymptoms();
        sortDataTable();
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
    }

    private void sortDataTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setSortsOnUpdates(true);
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int nameColumn = 0;
        sortKeys.add(new RowSorter.SortKey(nameColumn, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    private void loadTableSymptoms(){
        for(Sintoma s : symptoms){
            model.insertSymptom(s);
        }
    }

    public Sintomas getSymptomsSelected(){
        return model.getSelectedSymptoms();
    }

    public void unselect(){
        model.unselectItems();
    }
}
