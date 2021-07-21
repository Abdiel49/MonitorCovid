package cargarregistros.gui;

import cargarregistros.utils.RecordDaysControl;
import cargarregistros.utils.RecordsManagerFiles;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.Box;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class GUICargarRegistros extends JFrame {

    private final Sintomas sintomas;
    private final Container container;
    private final RecordsManagerFiles manager;
    private final AlertPanel alertPanel;
    private RecordsSavedPanel recordsList;
    private PanelSintomas panel;
    private Registros registros;

    public GUICargarRegistros(Sintomas s){
        super("Cargar Registros");
        sintomas = s;
        manager = new RecordsManagerFiles();
        container = Box.createVerticalBox();
        alertPanel = new AlertPanel();
        registros = manager.loadRegistros();
        init();
    }

    private void init(){
        setSize(Constants.WIDTH.get(),Constants.HEIGHT.get());
        setLocationRelativeTo(null);
        buildRecordsGui();
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

    private void buildRecordsGui(){
        // title
        RecordsTitle title = new RecordsTitle();
        container.add(title);
        // symptoms panel
        panel = new PanelSintomas(sintomas);
        JScrollPane scroll = new JScrollPane(panel);
        container.add(scroll);
        // alert panel
        container.add(alertPanel);
        // save record button
        JButton register = new JButton("Guardar Registro");
        register.addActionListener(e -> registerSymptoms());
        container.add(register);
        // Records saved panel
        recordsList = new RecordsSavedPanel(manager.loadRegistros());
        container.add(recordsList);
    }

    private void registerSymptoms(){
        Sintomas s = panel.getSymptomsSelected();
        int days = RecordDaysControl.differenceDays(registros.peek(), new Date());
        if(days == 1){
            if(s.iterator().hasNext()){
                Registro r = new Registro(new Date(), s);
                registros.push(r);
                manager.saveRecordInFile(r);
                recordsList.addRecord(r);
                panel.unselect();
                alertPanel.message("Se a guardado el registro",AlertPanel.GREEN);
            }else{
                alertPanel.message("No de an seleccionado sintomas!",AlertPanel.RED);
            }
        }else{
            alertPanel.message("Ya ha regsitrado sus sintomas el dia de hoy!",AlertPanel.ORANGE);
        }
    }
}
