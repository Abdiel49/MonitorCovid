package cargarregistros.gui;

import monitor.Sintoma;

import javax.swing.JCheckBox;

public class SCheckBox extends JCheckBox {

    private final Sintoma symptom;

    public SCheckBox(Sintoma s){
        symptom = s;
        this.setText(s.toString());
        this.setFocusable(false);
    }

    public Sintoma getSymptom(){
        return symptom;
    }
}
