package cargarsintomas;

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
        loadSymptoms();
    }

    public Sintomas getSintomas(){
//        loadSymptoms();
        return sintomas;
    }

    private void loadSymptoms(){
        List<String> listData = manager.getSymptomsDataFile();
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
