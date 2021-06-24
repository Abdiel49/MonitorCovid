package cargarregistros.gui;

import cargarregistros.RecordsManagerFiles;
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
        final GUICargarRegistros frame = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
//                super.windowClosing(we);
//                System.out.println("Se cerro la ventana de registros");
                synchronized (frame){
                    frame.notify();
                }
                frame.setVisible(false);
                frame.dispose();
            }
        });
        init();
        synchronized (frame){
            try{
                frame.setVisible(true);
                frame.wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }

    private void init(){
        this.setSize(Constants.WIDTH.get(),Constants.HEIGHT.get());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        above();
        middle();
        below();
        this.add(container);
        this.pack();
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
