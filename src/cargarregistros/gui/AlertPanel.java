package cargarregistros.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class AlertPanel extends JPanel {

    private JLabel phase;
    private JLabel message;

    public AlertPanel(){
        phase = new JLabel();
        message = new JLabel();
        init();
    }

    private void init(){
        phase.setForeground( new Color(60,150,90));
        Font f = phase.getFont();
        phase.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        message.setForeground(new Color(230,70,30));
        f = message.getFont();
        message.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        add(phase);
        add(message);
    }

    public void setPhase(String phaseName){
        phase.setText(phaseName.toUpperCase());
    }

    public void setMessage(String m){
        message.setText(m.toUpperCase());
    }
}
