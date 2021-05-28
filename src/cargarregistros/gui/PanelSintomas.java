package cargarregistros.gui;

import cargarregistros.gui.components.PanelContainer;
import monitor.Sintomas;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PanelSintomas {

    private List<JCheckBox> listCheck;
    private JButton register;
    private PanelContainer panelSymptoms;

    String[] symptoms = new String[]{
        "Dolor de Garganta",
        "Tos Seca",
        "Dolor de Garganta"
    };

    public PanelSintomas(){
        listCheck = new LinkedList<>();
        panelSymptoms = new PanelContainer(Constants.WIDTH.get()/2, Constants.HEIGHT.get());
        int rows = symptoms.length+1, cols=1;
        panelSymptoms.setLayout(new GridLayout(rows, cols));
        init();
    }

    private void init(){
//        listContainer = new JList<>();
        initList();
        register = new JButton("Registrar Sintomas");
        panelSymptoms.add(register);
//        this.add(listContainer);
    }

    private void initList(){
        for (String s : symptoms){
            JCheckBox check = new JCheckBox(s);
            listCheck.add(check);
            panelSymptoms.add(check);
        }
    }
}
