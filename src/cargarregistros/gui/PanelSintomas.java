package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import java.awt.Dimension;

public class PanelSintomas extends JPanel{

    private final Sintomas symptoms;
    private boolean isSecondPhase;
    private final SymptomTableModel model;
    private final SymptomTableCheck table;

    public PanelSintomas(Sintomas s){
        symptoms = s;
        isSecondPhase = false;
        model = new SymptomTableModel();
        table = new SymptomTableCheck();
        this.setPreferredSize( new Dimension(Constants.WIDTH.get()/3, Constants.HEIGHT.get()) );
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        init();
    }

    private void init(){
        model.setColumnIdentifiers(new String []{"Nombre","Tipo", "Seleccionar"});
        table.setModel(model);
        loadTableSymtoms();
        JScrollPane scroll = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);
        add(scroll);
    }

    private void loadTableSymtoms(){
//        Sintomas ss = isSecondPhase ? symptoms :
//            symptoms.getSintomasFase(new Fase("PrimeraFase"));
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

    public void changeToSecondPhase(){
        isSecondPhase = true;
//        listCheck.clear();
//        this.removeAll();
//        for (Sintoma s : symptoms){
//            SCheckBox check = new SCheckBox(s);
//            listCheck.add(check);
//            this.add(check);
//        }
    }
}
