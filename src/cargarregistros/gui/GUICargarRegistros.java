package cargarregistros.gui;

import cargarregistros.gui.components.GFrame;

import javax.swing.JButton;

public class GUICargarRegistros {

    private final GFrame frame;
    private JButton addRegistroButton;
    private PanelSintomas panel;


    public GUICargarRegistros(){
        frame = new GFrame("Cargar Registros");
        panel = new PanelSintomas();
        init();
    }

    private void init(){
        addRegistroButton = new JButton("Registrar Sintomas");
        addRegistroButton.addActionListener(e -> registerSymptoms());
        frame.getContentPane().add(panel);
//        frame.getContentPane().add(addRegistroButton);
    }

    public void show(){
        frame.setVisible(true);
    }

    private void registerSymptoms(){

    }
}
