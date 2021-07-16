package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoPorFases;
import diagnosticos.DiagnosticoSimple;
import diagnosticos.fases.UIFaces;

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
//        registros = new Registros();
        fase = (new DatosFase()).readFaseData();
        cargarRegistros = new CargarRegistros(sintomas.getSintomasFase(fase));
    }

    public void monitorear() {
        registros = cargarRegistros.getRegistros();
//        registros.push(registro);
        resultadoDiagnostico = funcion.diagnostico(registros);
        guardarEstado(resultadoDiagnostico);
        mostrarFase(fase);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

    private void mostrarFase(Fase f){
        UIFaces ui = new UIFaces(f);
        ui.display();
    }

    private void guardarEstado(int diagnostico){
        if(diagnostico>=13) {
            fase.setNombre("SegundaFase");
        } else {
            fase.setNombre("PrimeraFase");
        }
        fase.setDia(diagnostico);
        (new DatosFase()).saveFaseData(fase);
    }
}
