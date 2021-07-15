package cargarregistros.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class AlertPanel extends JPanel {

    static final Color GREEN = new Color(60,150,90);
    static final Color ORANGE = new Color(230,50,30);
    static final Color RED = new Color(250,15,15);

    private JLabel phase;
    private JLabel message;

    public AlertPanel(){
        phase = new JLabel();
        message = new JLabel();
        init();
    }

    private void init(){
        Font f = phase.getFont();
        phase.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        f = message.getFont();
        message.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        add(phase);
        add(new JLabel("   -   ")); // separator
        add(message);
    }

    public void setPhase(String phaseName, Color c){
        phase.setForeground(c);
        phase.setText(phaseName.toUpperCase());
    }

    public void setMessage(String m, Color c){
        message.setForeground(c);
        message.setText(m.toUpperCase());
    }
}
