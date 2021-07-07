package cargarsintomas.gui;

import cargarsintomas.utils.SymptomManager;
import cargarsintomas.utils.Validator;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUICargarSintomas extends JFrame{

    private final PanelContainer container;
    private final SymptomManager manager;
    private final InputSymptom inputSymptom;
    private TableData tableData;

    public GUICargarSintomas() {
        super("Cargar Sintomas");
        manager = new SymptomManager();
        container = new PanelContainer();
        inputSymptom = new InputSymptom(manager.getSymptomNamesInFile());
        init();
    }

    public void display(){
        setVisible(true);
    }

    private void init(){
        setVisible(false);
        setSize(ESize.WIDTH.get(),ESize.HEIGHT.get());
        setLocationRelativeTo(null);
        initContainer();
        add(container);
        pack();
        closeEvent();
        syncro();
    }

    private void syncro(){
        final GUICargarSintomas frame = this;
        synchronized (frame){
            try {
                display();
                frame.wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void closeEvent(){
        JButton closeButton = new JButton("Terminar");
        closeButton.addActionListener(e->closeAction());
        container.add(closeButton);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                closeAction();
            }
        });
    }

    private void closeAction(){
        final GUICargarSintomas frame = this;
        try {
            synchronized(frame){
                frame.notify();
            }
            setVisible(false);
            dispose();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void initContainer(){
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Monitoreo de Sintomas");
        titlePanel.add(title);
        JPanel buttonPanel = new JPanel();
        JButton addSymptom = new JButton("Aniadir");
        addSymptom.addActionListener(e -> addSymptomAction());
        buttonPanel.add(addSymptom);
        container.add(title);
        container.add(inputSymptom);
        container.add(buttonPanel);
        addTableSymptom();
    }

    private void addTableSymptom(){
        Sintomas sintomas = manager.loadSymptoms();
        tableData = new TableData(sintomas);
        int width = ESize.WIDTH.get()-20;
        int heicht = ESize.HEIGHT.get()/2;
        JScrollPane scroll = new JScrollPane(tableData);
        scroll.setPreferredSize(new Dimension(width, heicht));
        container.add(scroll);
    }

    private void addSymptomAction(){
        String[] data = inputSymptom.getData();
        int TYPE = 0, NAME = 1;
        String type = data[TYPE];
        String param = data[NAME];
        if(param.length()>=3){
            String className = "sintomas."+type;
            Sintoma s = manager.getObjectType(className,param);
            if(! Validator.symptomExists(manager.loadSymptoms(), param)){
                manager.saveSymptomInFile(s);
                tableData.insertRow(param, type);
            }else{
                JOptionPane.showMessageDialog(this,
                    "El sintoma ya existe!",
                    "Nuevo sintoma",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this,
                "El nombre del sintoma no es correcto",
                "Nuevo sintoma",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
