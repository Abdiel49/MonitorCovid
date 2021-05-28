package cargarregistros.gui.components;

import javax.swing.JPanel;
import java.awt.Dimension;

public class PanelContainer extends JPanel {
    private final int width, height;

    public PanelContainer(int width, int height){
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
    }

}
