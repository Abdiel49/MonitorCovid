package cargarsintomas.gui;

import cargarsintomas.utils.Validator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.util.Vector;

public class InputSymptom extends JPanel {

    private JTextField textField;
    private final JComboBox<String> typeSymptomBox;
    private JPanel inputPanel;

    public InputSymptom(Vector<String> boxItems){
        typeSymptomBox = new JComboBox<>(boxItems);
        init();
        initInputPanel();
    }

    private void init(){
        this.setPreferredSize( new Dimension(ESize.WIDTH.get(), (10)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        inputPanel = new JPanel();
    }

    private void initInputPanel(){
        JLabel label = new JLabel("Nombre del sintoma");
        int columns = 20;
        textField = new JTextField(columns);

        inputPanel.add(textField);
        inputPanel.add(typeSymptomBox);
        this.add(label);
        this.add(inputPanel);
    }

    public String[] getData(){
        int boxIndex = typeSymptomBox.getSelectedIndex();
        String type = typeSymptomBox.getItemAt(boxIndex);
        String name = Validator.normalize(textField.getText());
        textField.setText("");
        return new String[]{type, name};
    }
}
