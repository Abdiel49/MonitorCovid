package cargarsintomas.gui;

import cargarsintomas.utils.SymptomManager;
import cargarsintomas.utils.Validator;
import monitor.Sintoma;
//import monitor.Sintomas;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUICargarSintomas extends JFrame{

    private final PanelContainer container;
    private final SymptomManager manager;
    private InputSymptom inputSymptom;

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
            }
        }
    }

    public void display(){
        this.setVisible(true);
    }

    private void init(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setSize(ESize.WIDTH.get(),ESize.HEIGHT.get());
        this.setLocationRelativeTo(null);
        initContainer();
        this.add(container);
        this.pack();
    }

    private void initContainer(){
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
                JOptionPane.showMessageDialog(this,
                    "Se aniadio Correctamente",
                    "Nuevo sintoma",
                    JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this,
                    "El sintoma ya existe, ponga otro",
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
