package cargarregistros;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.util.List;

public class CargarRegistros {

    private Sintomas sintomas;
    private Registros registros;
    private RecordsManagerFiles manager;

    public CargarRegistros(Sintomas s){
        sintomas = s;
        registros = new Registros();
        manager = new RecordsManagerFiles();
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
