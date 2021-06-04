package cargarregistros.gui.components;

import cargarregistros.gui.Constants;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GFrame extends JFrame {

    public GFrame(String title){
        super(title);
        init();
    }
    
    private void init(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setSize(Constants.WIDTH.get(),Constants.HEIGHT.get());
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        this.setResizable(false);
    }
}
