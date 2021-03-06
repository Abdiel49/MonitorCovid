package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoSimple;

public class Monitor {

    private Registros registros;
    private Sintomas sintomas;
    private FuncionDiagnostico funcion;
    private CargarRegistros cargarRegistros;
    private int resultadoDiagnostico;

    public Monitor() {
        CargarSintomas cargarSintomas = new CargarSintomas();
        sintomas = cargarSintomas.getSintomas();
        funcion = new DiagnosticoSimple(sintomas);
        registros = new Registros();
        cargarRegistros = new CargarRegistros(sintomas);
    }

    public void monitorear() {
        Registro registro = cargarRegistros.getRegistro();
        registros.push(registro);
        resultadoDiagnostico = funcion.diagnostico(registros);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

}
