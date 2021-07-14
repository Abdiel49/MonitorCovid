package cargarregistros.gui;

import monitor.Sintoma;

import javax.swing.JCheckBox;

public class SCheckBox extends JCheckBox {

    private final Sintoma symptom;

    public SCheckBox(Sintoma s){
        symptom = s;
//        String type = s.getClass().getSimpleName();
        String text = s.toString();
        this.setText(text);
        this.setFocusable(false);
    }

    public Sintoma getSymptom(){
        return symptom;
    }
}
