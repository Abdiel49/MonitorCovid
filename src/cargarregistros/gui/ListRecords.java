package cargarregistros.gui;

import monitor.Registro;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.util.LinkedList;
import java.util.List;

public class ListRecords extends JList<Registro> {

    private final DefaultListModel model;
    private final List<Registro> list;

    public ListRecords(){
        model = new DefaultListModel<>();
        list = new LinkedList<>();
        init();
    }

    private void init(){
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setModel(model);
    }

    public void addRecord(int index, Registro r){
        if(r != null){
            String name = r.getFecha().toString();
            list.add(index, r);
            model.add(index, name);
            setSelectedIndex(0);
        }
    }

    public Registro getSelectedItem(){
        int index = getSelectedIndex();
        if(index>=0 && index<list.size()){
            return list.get(index);
        }
        return null;
    }
}
