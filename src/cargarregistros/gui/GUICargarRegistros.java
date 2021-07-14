package cargarregistros.gui;

import cargarregistros.utils.RecordsManagerFiles;
import monitor.Registro;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class GUICargarRegistros extends JFrame {

    private final Sintomas sintomas;
    private final Container container;
    private final RecordsManagerFiles manager;
    private RecordsList recordsList;
    private PanelSintomas panel;
    private boolean isSecondPhase;
    private boolean isFirstPhase;
    private JPanel alertPanel;

    public GUICargarRegistros(Sintomas s){
        super("Cargar Registros");
        sintomas = s;
        manager = new RecordsManagerFiles();
        container = Box.createVerticalBox();
        isFirstPhase = false;
        isSecondPhase = false;
        alertPanel = new JPanel();
        init();
    }

    private void init(){
        setSize(Constants.WIDTH.get(),Constants.HEIGHT.get());
        setLocationRelativeTo(null);
//        setResizable(false);
        above();
        middle();
        below();
        add(container);
        pack();
        closeEvent();
        syncro();
    }

    public void display(){
        setVisible(true);
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
            setVisible(false);
            dispose();
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

    private void makeAlertPanel(){

    }

    private void below(){
        JButton register = new JButton("Guardar Registro");
        register.addActionListener(e -> registerSymptoms());
        container.add(register);
        recordsList = new RecordsList(manager.loadRegistros());
        container.add(recordsList);
    }

    private void registerSymptoms(){
        Sintomas s = panel.getSymptomsSelected();
        if(s.iterator().hasNext()){
            Registro r = new Registro(new Date(), s);
            manager.saveRecordInFile(r);
            recordsList.addRecord(r);
            panel.unselect();
        }else{
            JOptionPane.showMessageDialog(this,
                "No se han seleccionado sintomas",
                "Registro de Sintomas",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
