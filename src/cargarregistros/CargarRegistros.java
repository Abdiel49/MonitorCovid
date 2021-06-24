package cargarregistros;

import cargarregistros.gui.GUICargarRegistros;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.util.List;

public class CargarRegistros {

    private final Sintomas sintomas;
    private final Registros registros;
    private final RecordsManagerFiles manager;
    private final GUICargarRegistros gui;

    public CargarRegistros(Sintomas s){
        sintomas = s;
        registros = new Registros();
        manager = new RecordsManagerFiles();
        gui = new GUICargarRegistros(sintomas);
        loadRegistros();
    }

    public Registro getRegistro(){
        return registros.isEmpty() ? null : registros.peek();
    }

    private void loadRegistros(){
        List<String> registrosData = manager.getRowsData();
        for(String row : registrosData){
            registros.push(manager.getRegistroFromFile(row));
        }
    }
}
