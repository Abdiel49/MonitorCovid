package cargarsintomas;

import cargarsintomas.gui.GUICargarSintomas;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.Arrays;
import java.util.List;

public class CargarSintomas {

    private final SymptomManagerFiles manager;
    private Sintomas sintomas;

    public CargarSintomas(){
        manager = new SymptomManagerFiles();
        sintomas = new Sintomas();
//        initGUI();
        loadSymptoms();
    }

    private void initGUI(){
        GUICargarSintomas gui = new GUICargarSintomas();
        gui.show();
        loadSymptoms();
    }

    public Sintomas getSintomas(){
//        initGUI();
//        loadSymptoms();
        return sintomas;
    }

    private void loadSymptoms() {
        List<String> listData = null;
//        try {
            listData = manager.getSymptomsDataFile();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        for (String s : listData){
            String[]data = s.split(",");
            System.out.println(Arrays.toString(data));
            Sintoma symptom = makeSymptom(data);
            sintomas.add( symptom );
        }
    }

    private Sintoma makeSymptom(String[] data){
        String className = data[0];
        String value = data[1];
        Object obj = manager.getObjectType(className,value);
        return (Sintoma) obj;
    }
}
