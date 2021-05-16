package ui.gui;

import files.FileController;
import files.FileManager;

import javax.swing.*;
import java.awt.*;

public class Window {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final int WIDTH, HEIGHT;
    private final FileController files;
    private final JLabel simpthomLabel;
    private final JLabel title;

    private JComboBox simpthomsBox;
    private JTextField simpthomInput;
    private JButton addButton;

    public Window(){
        frame = new JFrame("Sistema de Monotoreo");
        mainPanel = new JPanel();
        files = new FileController();
        simpthomLabel = new JLabel("Nombre del sintoma");
        title = new JLabel("Monitoreo de Sintomas");
        addButton = new JButton("Aniadir");
        WIDTH = 500;
        HEIGHT = 500;
        init();
    }

    private void init(){
        initFrame();
        initPanel();
        frame.add(mainPanel);
    }

    public void show(){
        frame.setVisible(true);
    }

    private void initFrame(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(false);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
    }

    private void initPanel(){
//        mainPanel.setLayout(new GridLayout(5,1));
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainPanel.setVisible(true);
        simpthomsBox = new JComboBox(files.getNameFiles("./sintomas/"));
        simpthomInput = new JTextField(50);
        mainPanel.add(title);
        mainPanel.add(simpthomLabel);
        mainPanel.add(simpthomInput);
        mainPanel.add(simpthomsBox);
        mainPanel.add(addButton);

    }


}
