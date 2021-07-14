package cargarregistros;

import cargarregistros.gui.GUICargarRegistros;
import cargarregistros.utils.RecordsManagerFiles;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.util.Date;

public class CargarRegistros {

    private final Sintomas sintomas;
    private final RecordsManagerFiles manager;
    private GUICargarRegistros gui;
    private Registros registros;

    public CargarRegistros(Sintomas s){
        sintomas = s;
        manager = new RecordsManagerFiles();
    }

    public Registro getRegistro(){
        displayGUI();
        if(registros == null || registros.isEmpty()){
            return new Registro(new Date(), new Sintomas());
        }
        return registros.peek();
    }

    public Registros getRegistros(){
        displayGUI();
        return registros;
    }

    private void displayGUI(){
        gui = new GUICargarRegistros(sintomas);
        registros = manager.loadRegistros();
    }
}
