package cargarregistros.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class RecordsTitle extends JPanel {

    private final JLabel dateLabel;

    public RecordsTitle(){
        this.setLayout(new BorderLayout());
        dateLabel = new JLabel();
        this.setPreferredSize(new Dimension(Constants.WIDTH.get(), 40));
        loadComponents();
    }
    private void loadComponents(){
        JLabel title = new JLabel("Seleccione los sintomas");
        setDateLabel();
        this.add(title, BorderLayout.NORTH);
        this.add(dateLabel,BorderLayout.CENTER);
    }

    private void setDateLabel(){
        String date = "Fecha: " + getToDayFormated();
        dateLabel.setText(date);
    }

    private String getToDayFormated(){
        String PATTER = "dd MMM yyyy HH:mm:ss";
        DateFormat formated = new SimpleDateFormat(PATTER);
        return formated.format(new Date());
    }
}
