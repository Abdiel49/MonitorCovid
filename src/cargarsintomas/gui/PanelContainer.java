package cargarsintomas.gui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class PanelContainer extends JPanel {

    public PanelContainer(){
        setPreferredSize(new Dimension( ESize.WIDTH.get(), ESize.HEIGHT.get() ));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
