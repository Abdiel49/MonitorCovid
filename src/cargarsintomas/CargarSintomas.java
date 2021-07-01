package cargarsintomas;

import cargarsintomas.gui.GUICargarSintomas;
import cargarsintomas.utils.SymptomManager;
import monitor.Sintomas;

public class CargarSintomas {

    private final SymptomManager manager;
    private Sintomas sintomas;
    private final GUICargarSintomas gui;

    public CargarSintomas(){
        manager = new SymptomManager();
        sintomas = new Sintomas();
        gui = new GUICargarSintomas();
    }

    public Sintomas getSintomas(){
        loadSymptoms();
        return sintomas;
    }

    private void loadSymptoms() {
        sintomas = manager.loadSymptoms();
    }
}
