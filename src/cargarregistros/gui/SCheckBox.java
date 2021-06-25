package cargarregistros.gui;

import monitor.Sintoma;

import javax.swing.JCheckBox;

public class SCheckBox extends JCheckBox {

    private final Sintoma symptom;

    public SCheckBox(Sintoma s){
        symptom = s;
        String text = getType()+" - "+s.toString();
        this.setText(text);
        this.setFocusable(false);
    }

    private String getType(){
        String name = symptom.getClass().getName();
        String lowerCase = name.replace("sintomas.", "").toLowerCase();
        String normal = lowerCase.replace("fase", " fase");
        return normal.toUpperCase();
    }

    public Sintoma getSymptom(){
        return symptom;
    }
}
