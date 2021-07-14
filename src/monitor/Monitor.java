package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoPorFases;
import diagnosticos.DiagnosticoSimple;

public class Monitor {

    private Fase fase;
    private Registros registros;
    private Sintomas sintomas;
    private FuncionDiagnostico funcion;
    private CargarRegistros cargarRegistros;
    private int resultadoDiagnostico;

    public Monitor() {
        CargarSintomas cargarSintomas = new CargarSintomas();
        sintomas = cargarSintomas.getSintomas();
        funcion = new DiagnosticoPorFases(sintomas);
        registros = new Registros();
        fase = (new DatosFase()).leerDatosFase();
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

    private void guardarEstado(int diagnostico){
        
    }

}
