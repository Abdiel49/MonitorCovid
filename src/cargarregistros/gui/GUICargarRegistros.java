package cargarregistros.gui;

import cargarregistros.utils.RecordsManagerFiles;
import monitor.Registro;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class GUICargarRegistros extends JFrame{

    private final Sintomas sintomas;
    private final Container container;
    private final RecordsManagerFiles manager;
    private PanelSintomas panel;

    public GUICargarRegistros(Sintomas s){
        super("Cargar Registros");
        sintomas = s;
        manager = new RecordsManagerFiles();
        container = Box.createVerticalBox();
        init();
    }

    public void display(){
        setVisible(true);
    }

    private void close(){
        setVisible(false);
        dispose();
    }

    private void init(){
        setSize(Constants.WIDTH.get(),Constants.HEIGHT.get());
        setLocationRelativeTo(null);
        setResizable(false);
        above();
        middle();
        below();
        add(container);
        pack();
        closeEvent();
        syncro();
    }

    private void syncro(){
        final GUICargarRegistros frame = this;
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
        final GUICargarRegistros frame = this;
        try {
            synchronized(frame){
                frame.notify();
            }
            close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void above(){
        RecordsTitle title = new RecordsTitle();
        container.add(title);
    }

    private void middle(){
        panel = new PanelSintomas(sintomas);
        JScrollPane scroll = new JScrollPane(panel);
        container.add(scroll);
    }

    private void below(){
        JButton register = new JButton("Guardar Registro");
        register.addActionListener(e -> registerSymptoms());
        container.add(register);
    }

    private void registerSymptoms(){
        Sintomas s = panel.getSymptomsSelected();
        if(s.iterator().hasNext()){
            manager.saveRecordInFile(new Registro(new Date(), s));
            panel.unselect();
            JOptionPane.showMessageDialog(this,
                "Se a guardado un registro nuevo",
                "Registro de Sintomas",
                JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,
                "No se han seleccionado sintomas",
                "Registro de Sintomas",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
