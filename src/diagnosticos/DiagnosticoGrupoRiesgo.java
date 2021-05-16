package diagnosticos;

import monitor.FuncionDiagnostico;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.HashMap;
import java.util.Map;

public class DiagnosticoGrupoRiesgo extends FuncionDiagnostico {

    private Map<Sintoma,Integer> pesos;
    private final double RIESGO;

    public DiagnosticoGrupoRiesgo(Sintomas ls) {
        super(ls);
        pesos = new HashMap<>();
        RIESGO = 0.3;
        for (Sintoma s: ls) {
            pesos.put(s,s.peso());
        }
    }

    @Override
    public int diagnostico(Registros registros) {
        int pesoSintomas = 0;
        if (!registros.isEmpty()) {
            Registro registro = registros.peek();
            Sintomas sintomas = registro.getSintomas();
            for (Sintoma s: sintomas) {
                pesoSintomas += pesos.get(s);
            }
        }
        return (int) (pesoSintomas*RIESGO);
    }
}
