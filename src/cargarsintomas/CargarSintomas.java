package cargarsintomas;

import cargarsintomas.gui.GUICargarSintomas;
import cargarsintomas.utils.SymptomManager;
import monitor.Sintomas;

public class CargarSintomas {

    private final SymptomManager manager;
    private Sintomas sintomas;
    private GUICargarSintomas gui;

    public CargarSintomas(){
        manager = new SymptomManager();
        sintomas = new Sintomas();
    }

    public Sintomas getSintomas(){
        gui = new GUICargarSintomas();
        loadSymptoms();
        return sintomas;
    }

    private void loadSymptoms() {
        sintomas = manager.loadSymptoms();
    }
}
