package cargarregistros.gui.components;

import cargarregistros.utils.Props;
import monitor.Sintoma;

import javax.swing.JCheckBox;

public class SCheckBox extends JCheckBox {

    private final Props prop;
    private final Sintoma symptom;

    public SCheckBox(Sintoma s){
        prop = new Props();
        symptom = s;
        init();
    }

    private void init(){
        String[] props = prop.getSymptomProperties(symptom);
        int NAME = 0;
//        int CLASS = 1;
        this.setText(props[NAME]);
        this.setFocusPainted(false);
    }

    public Sintoma getSymptom(){
        return symptom;
    }
}
