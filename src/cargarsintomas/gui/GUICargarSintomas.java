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
    private final PanelContainer container;
    private final SymptomManagerFiles manager;
    private final Map<String, String> dataMap;

    public GUICargarSintomas(){
        manager = new SymptomManagerFiles();
        frame = new SFrame("Cargar Sintomas");
        container = new PanelContainer();
        inputSymptom = new InputSymptom(manager.getSymptomNamesInFile());
        dataMap = new HashMap<>();
        init();
    }

    public void show(){
        frame.setVisible(true);
    }

    private void init(){
//        title component
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Monitoreo de Sintomas");
        titlePanel.add(title);
//        action button component
        JPanel buttonPanel = new JPanel();
        JButton addSymptom = new JButton("Aniadir");
        addSymptom.addActionListener(e -> addSymptomAction());
        buttonPanel.add(addSymptom);
        container.add(title);
        container.add(inputSymptom);
        container.add(buttonPanel);
        frame.add(container);
//        frame.pack();
    }

    private void addSymptomAction(){
        String[] data = inputSymptom.getData();
        int TYPE = 0, NAME = 1;
        String type = data[TYPE];
        String param = data[NAME];
        String className = "sintomas."+type;
        dataMap.put(param, type);
//        table.addRowData(new String[]{type, param});
        Sintoma s = (Sintoma)manager.getObjectType(className,param);
        System.out.println("se aniadion un sintoma\n\tclassname:\t"+s.getClass().getName()+"\n\tparam:\t"+param);
        manager.saveSymptomInFile(s);
    }
}
