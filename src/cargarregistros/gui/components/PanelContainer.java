package cargarregistros.gui.components;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Component;

public class PanelContainer extends JPanel {

    public PanelContainer(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
    }

    public void addComponent(Component c){
        this.add(c);
    }

}
