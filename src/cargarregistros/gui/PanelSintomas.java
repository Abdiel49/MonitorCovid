package cargarregistros.gui;

import cargarregistros.gui.components.SCheckBox;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

public class PanelSintomas extends JPanel{

    private final List<SCheckBox> listCheck;
    private final Sintomas symptoms;

    public PanelSintomas(Sintomas s){
        symptoms = s;
        listCheck = new LinkedList<>();
        this.setPreferredSize( new Dimension(Constants.WIDTH.get()/2, Constants.HEIGHT.get()) );
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        initList();
    }

    private void initList(){
        for (Sintoma s : symptoms){
            SCheckBox check = new SCheckBox(s);
            listCheck.add(check);
            this.add(check);
        }
    }

    public Sintomas getSymptomsSelected(){
        Sintomas s = new Sintomas();
        for (SCheckBox check : listCheck){
            if(check.isSelected()){
                s.add(check.getSymptom());
            }
        }
        return s;
    }
}
