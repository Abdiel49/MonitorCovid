package cargarsintomas.gui;

import cargarsintomas.gui.constants.ESize;
import cargarsintomas.utils.Validator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.Dimension;

public class InputSymptom extends JPanel {

    private JTextField textField;
    private JComboBox<String> typeSymptomBox;
    private JPanel inputPanel;

    private final String[] symp;

    public InputSymptom(){
        symp = new String[]{
            "Tener en cuenta",
            "Importante",
            "La palmas bro"
        };
        init();
        initInputPanel();
    }

    private void init(){
        this.setPreferredSize( new Dimension(ESize.WIDTH.get(), (ESize.HEIGHT.get()-20)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        inputPanel = new JPanel();
    }

    private void initInputPanel(){
        JLabel label = new JLabel("Nombre del sintoma");
        int columns = 20;
        textField = new JTextField(columns);
        typeSymptomBox = new JComboBox<>(symp);
        inputPanel.add(textField);
        inputPanel.add(typeSymptomBox);
        this.add(label);
        this.add(inputPanel);
    }

    public String[] getData(){
        int boxindex = typeSymptomBox.getSelectedIndex();
        String type = typeSymptomBox.getItemAt(boxindex);
        String name = Validator.normalize(textField.getText());
        textField.setText("");
        return new String[]{type, name};
    }
}
