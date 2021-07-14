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
    private final GUICargarRegistros gui;
    private Registros registros;

    public CargarRegistros(Sintomas s){
        sintomas = s;
        manager = new RecordsManagerFiles();
        gui = new GUICargarRegistros(sintomas);
        registros = manager.loadRegistros();
    }

    public Registro getRegistro(){
        if(registros == null || registros.isEmpty()){
            return new Registro(new Date(), new Sintomas());
        }
        return registros.peek();
    }

    public Registros getRegistros(){
        return registros;
    }
}
