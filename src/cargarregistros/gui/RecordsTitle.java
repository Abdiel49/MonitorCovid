package cargarregistros.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class RecordsTitle extends JPanel {

    private JLabel dateLabel;

    public RecordsTitle(){
        this.setLayout(new BorderLayout());
        dateLabel = new JLabel();
        this.setPreferredSize(new Dimension(Constants.WIDTH.get(), 40));
        loadComponents();
    }
    private void loadComponents(){
        JLabel title = new JLabel("Seleccione los sintomas");
        setDateLabel();
//        setLabelTime();
        this.add(title, BorderLayout.NORTH);
        this.add(dateLabel,BorderLayout.CENTER);
    }

    private void setDateLabel(){
        String date = "Fecha: " + getToDayFormated();
        dateLabel.setText(date);
    }

    private void setLabelTime(){
        Timer timer = new Timer();
//        while (true){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    setLabelTime();
                }
            }, 12000);
//        }
    }

    private String getToDayFormated(){
        String PATTER = "dd MMM yyyy HH:mm:ss";
        DateFormat formated = new SimpleDateFormat(PATTER);
        return formated.format(new Date());
    }
}
