package cargarregistros.gui;

import cargarregistros.RecordsManagerFiles;
import cargarregistros.gui.components.GFrame;
import cargarsintomas.CargarSintomas;
import monitor.Registro;
import monitor.Sintomas;

import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Container;
import java.util.Date;

public class GUICargarRegistros {

    private final GFrame frame;
    private final CargarSintomas loader;
    private final Container container;
    private final RecordsManagerFiles manager;
    private PanelSintomas panel;

    public GUICargarRegistros(){
        manager = new RecordsManagerFiles();
        frame = new GFrame("Cargar Registros");
        loader = new CargarSintomas();
//        container = new PanelContainer(Constants.WIDTH.get(), Constants.HEIGHT.get());
        container = Box.createVerticalBox();
        init();
    }

    public void show(){
        frame.setVisible(true);
    }

    private void init(){
        above();
        middle();
        below();
        frame.add(container);
        frame.pack();
    }

    private void above(){
        RecordsTitle title = new RecordsTitle();
        container.add(title);
    }

    private void middle(){
        panel = new PanelSintomas(loader.getSintomas());
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
            JOptionPane.showMessageDialog(frame,
                "Se a guardado un registro nuevo",
                "Registro de Sintomas",
                JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(frame,
                "No se han seleccionado sintomas",
                "Registro de Sintomas",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
