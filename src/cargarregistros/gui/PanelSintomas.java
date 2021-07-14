package cargarregistros.gui;

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
    private boolean isSecondPhase;

    public PanelSintomas(Sintomas s){
        symptoms = s;
        listCheck = new LinkedList<>();
        isSecondPhase = false;
        this.setPreferredSize( new Dimension(Constants.WIDTH.get()/3, Constants.HEIGHT.get()) );
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        initList();
    }

    private void initList(){
        for (Sintoma s : symptoms){
            if(!isSecondPhase){
                String phaseName = s.getClass().getSimpleName();
                if(!phaseName.equals("SegundaFase")){
                    SCheckBox check = new SCheckBox(s);
                    listCheck.add(check);
                    this.add(check);
                }
            }
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

    public void unselect(){
        for (SCheckBox check : listCheck){
            check.setSelected(false);
        }
    }

    public void changeToSecondPhase(){
        isSecondPhase = true;
        listCheck.clear();
        this.removeAll();
        for (Sintoma s : symptoms){
            SCheckBox check = new SCheckBox(s);
            listCheck.add(check);
            this.add(check);
        }
    }
}
