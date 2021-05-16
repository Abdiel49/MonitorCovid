package monitor;

import diagnosticos.DiagnosticoSimple;

public class Monitor {

    private Registros registros;
    private Sintomas sintomas;
    private FuncionDiagnostico funcion;
    private int resultadoDiagnostico;

    public Monitor() {
        CargarInfo info = new CargarInfo();
        sintomas = info.getSintomas();
        registros = info.cargarRegistros();
        funcion = new DiagnosticoSimple(sintomas);
    }

    public void monitorear() {
        resultadoDiagnostico = funcion.diagnostico(registros);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

}
