package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

public class PanelSintomas extends JPanel{

    private final List<SCheckBox> listCheck;
    private final Sintomas symptoms;
    private boolean isSecondPhase;
    private SymptomTableModel model;
    private SymptomTableCheck table;

    public PanelSintomas(Sintomas s){
        symptoms = s;
        listCheck = new LinkedList<>();
        isSecondPhase = false;
        model = new SymptomTableModel();
        table = new SymptomTableCheck();
        this.setPreferredSize( new Dimension(Constants.WIDTH.get()/3, Constants.HEIGHT.get()) );
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        init();
//        initList();
    }

    private void init(){
        model.setColumnIdentifiers(new String []{"Nombre","Tipo", "Seleccionar"});
        table.setModel(model);
        loadTableSymtoms();
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
    }

    private void loadTableSymtoms(){
        for(Sintoma s : symptoms){
            model.insertSymptom(s);
        }
    }

    public void printLocale(){
        Sintomas ss = model.getSelectedSymptoms();
        for(Sintoma s : ss){
            System.out.println(s.toString());
        }
    }

    public Sintomas getSymptomsSelected(){
        return model.getSelectedSymptoms();
    }

    public void unselect(){
        model.unselectItems();
    }

    public void changeToSecondPhase(){
        isSecondPhase = true;
        listCheck.clear();
        this.removeAll();
        for (Sintoma s : symptoms){
            SCheckBox check = new SCheckBox(s);
            listCheck.add(check);
            this.add(check);
        }
    }

    public void insertRow(String param, String type){
        model.insertRow(0, new Object[]{param,type,false});
    }
}
