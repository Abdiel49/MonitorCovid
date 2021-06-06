package cargarsintomas.gui.components;

import cargarsintomas.gui.constants.ESize;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SFrame extends JFrame {

    public SFrame(String title){
        super(title);
        init();
    }

    private void init(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setSize(ESize.WIDTH.get(),ESize.HEIGHT.get());
        this.setLocationRelativeTo(null);
    }
}
