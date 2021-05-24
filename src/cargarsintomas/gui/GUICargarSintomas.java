package cargarsintomas.gui;

import cargarsintomas.SymptomManagerFiles;
import cargarsintomas.Validator;
import files.ClassToObject;
import monitor.Sintoma;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GUICargarSintomas {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final int WIDTH, HEIGHT;
    private final SymptomManagerFiles files;
    private final JLabel simpthomLabel;
    private final JLabel title;
    private final JButton addButton;
    private final SymptomManagerFiles manager;

    private JComboBox<String> simpthomsBox;
    private JTextField simpthomInput;
    private TableData table;
    Map<String, String> data;

    public GUICargarSintomas(){
        simpthomLabel = new JLabel("Nombre del sintoma");
        frame = new JFrame("Cargar Sintomas");
        title = new JLabel("Monitoreo de Sintomas");
        addButton = new JButton("Aniadir");
        files = new SymptomManagerFiles();
        mainPanel = new JPanel();
        manager = new SymptomManagerFiles();
        table = new TableData();
        data = new HashMap<>();
        WIDTH = 500;
        HEIGHT = 500;
        init();
    }

    private void init(){
        initFrame();
        initPanel();
        initButton();
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
        simpthomInput = new JTextField(25);
        mainPanel.add(title);
        mainPanel.add(simpthomLabel);
        mainPanel.add(simpthomInput);
        mainPanel.add(simpthomsBox);
        mainPanel.add(addButton);
//        mainPanel.add(table);
    }

    private void initButton(){
        addButton.addActionListener(e -> addSymptomAction() );
    }

    private void addSymptomAction(){
        int boxIdex = simpthomsBox.getSelectedIndex();
        String type = simpthomsBox.getItemAt(boxIdex);
        String param = simpthomInput.getText();
        param = Validator.normalize(param);
        String className = "sintomas."+type;//+".class";
        data.put(param, type);
//        table.addRowData(new String[]{type, param});
        Sintoma s = (Sintoma)manager.getObjectType(className,param);
//        System.out.println("se aniadion un sintoma\n\tclassname:\t"+s.getClass().getName()+"\n\tparam:\t"+param);
        manager.saveSymptomInFile(s);
    }


}
