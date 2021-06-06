package cargarsintomas.gui;

import cargarsintomas.SymptomManagerFiles;
import cargarsintomas.gui.components.PanelContainer;
import cargarsintomas.gui.components.SFrame;
import monitor.Sintoma;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
//import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GUICargarSintomas {

    private final SFrame frame;
    private final InputSymptom inputSymptom;
    private final PanelContainer mainPanel;
    private final SymptomManagerFiles manager;
    private final Map<String, String> data;

    public GUICargarSintomas(){
        frame = new SFrame("Cargar Sintomas");
        mainPanel = new PanelContainer();
        inputSymptom = new InputSymptom();
        manager = new SymptomManagerFiles();
        data = new HashMap<>();
        init();
    }

    public void show(){
        frame.setVisible(true);
    }

    private void init(){
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JLabel title = new JLabel("Monitoreo de Sintomas");
        JButton addSymptom = new JButton("Aniadir");
        addSymptom.addActionListener(e -> addSymptomAction());
        titlePanel.add(title);
        buttonPanel.add(addSymptom);
        mainPanel.add(title);
        mainPanel.add(inputSymptom);
        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
    }

    private void addSymptomAction(){
        String[] data = inputSymptom.getData();
        int TYPE = 0, NAME = 1;
        String type = data[TYPE];
        String param = data[NAME];
        String className = "sintomas."+type;

//        data.put(param, type);
//        table.addRowData(new String[]{type, param});
        Sintoma s = (Sintoma)manager.getObjectType(className,param);
        System.out.println("se aniadion un sintoma\n\tclassname:\t"+s.getClass().getName()+"\n\tparam:\t"+param);
        manager.saveSymptomInFile(s);
    }
}
