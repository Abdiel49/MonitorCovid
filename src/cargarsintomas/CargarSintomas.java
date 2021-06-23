package cargarsintomas;

import cargarsintomas.gui.GUICargarSintomas;
//import monitor.Sintoma;
import cargarsintomas.utils.SymptomManager;
import monitor.Sintomas;

//import java.util.Arrays;
//import java.util.List;

public class CargarSintomas {

    private final SymptomManager manager;
    private Sintomas sintomas;
    private final GUICargarSintomas gui;

    public CargarSintomas(){
        manager = new SymptomManager();
        sintomas = new Sintomas();
        gui = new GUICargarSintomas();
//        loadSymptoms();
    }

    public Sintomas getSintomas(){
        loadSymptoms();
        return sintomas;
    }

    private void loadSymptoms() {
        sintomas = manager.loadSymptoms();
    }
//
//    private Sintoma makeSymptom(String[] data){
//        String className = data[0];
//        String value = data[1];
//        Object obj = manager.getObjectType(className,value);
//        return (Sintoma) obj;
//    }
}
