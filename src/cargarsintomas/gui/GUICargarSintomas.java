package cargarsintomas.gui;

import cargarsintomas.utils.SymptomManager;
import cargarsintomas.utils.Validator;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUICargarSintomas extends JFrame{

    private final PanelContainer container;
    private final SymptomManager manager;
    private InputSymptom inputSymptom;
    private JTable tableData;
    private DefaultTableModel model;

    public GUICargarSintomas() {
        super("Cargar Sintomas");
        manager = new SymptomManager();
        container = new PanelContainer();
        inputSymptom = new InputSymptom(manager.getSymptomNamesInFile());
        final GUICargarSintomas frame = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    synchronized(frame){
                        frame.notify();
                    }
                    frame.setVisible(false);
                    frame.dispose();
                } catch (Exception e){
                    System.err.println(e.getMessage());
//                    System.out.println("Error al guardar");
                }
//                super.windowClosing(we);
            }
        });
        init();
        synchronized (frame){
            try {
                frame.setVisible(true);
                frame.wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }

    public void display(){
        this.setVisible(true);
    }

    private void init(){
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setSize(ESize.WIDTH.get(),ESize.HEIGHT.get());
        this.setLocationRelativeTo(null);
        initContainer();
        this.add(container);
        this.pack();
    }

    private void initContainer(){
//        tableData = new TableData(manager.loadSymptoms());
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
//        container.add(tableData);
        addTableSymptom();
    }

    private void addTableSymptom(){
        Sintomas sintomas = manager.loadSymptoms();
        model = new DefaultTableModel();
        //load table
        model.setColumnIdentifiers(new String []{"Nombre","Tipo"});
        for(Sintoma s : sintomas){
            String type = s.getClass().getName().replace("sintomas.","");
            String value = s.toString();
            model.insertRow(0, new String[]{value, type});
        }
        int width = ESize.WIDTH.get()-20;
        int heicht = ESize.HEIGHT.get()/2;
        tableData = new JTable(model);
        JScrollPane scroll = new JScrollPane(tableData);
        scroll.setPreferredSize(new Dimension(width, heicht));
        container.add(scroll);
    }

    private void addSymptomAction(){
        String[] data = inputSymptom.getData();
        int TYPE = 0, NAME = 1;
        String type = data[TYPE];
        String param = data[NAME];
        if(param.length()>3){
            String className = "sintomas."+type;
            Sintoma s = manager.getObjectType(className,param);
            if(! Validator.symptomExists(manager.loadSymptoms(), param)){
                manager.saveSymptomInFile(s);
                model.insertRow(0, new String[]{param, type});
                JOptionPane.showMessageDialog(this,
                    "Se aniadio Correctamente",
                    "Nuevo sintoma",
                    JOptionPane.INFORMATION_MESSAGE);
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
