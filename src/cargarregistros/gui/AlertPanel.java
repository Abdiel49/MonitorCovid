package cargarregistros.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlertPanel extends JPanel {

    private JLabel phase;
    private JLabel message;

    private AlertPanel(){
        phase = new JLabel();
        message = new JLabel();
        add(phase);
        add(message);
    }

    public void setPhase(String phaseName){
        phase.setText(phaseName);
    }

    public void setMessage(String m){
        message.setText(m);
    }
}
