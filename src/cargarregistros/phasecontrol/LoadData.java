package cargarregistros.phasecontrol;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

public class LoadData {

    private int fisrtPhaseSize;
    private int secondPhaseSize;
    private final Sintomas fisrtSintomas;
    private final Sintomas secondSintomas;
    private final Registros registros;

    LoadData(Registros r){
        registros = r;
        fisrtSintomas = new Sintomas();
        secondSintomas = new Sintomas();
        fisrtPhaseSize = 0;
        secondPhaseSize = 0;
        init();
    }

    private void init(){
        loadSymptoms();
        fisrtPhaseSize = coundSizes(fisrtSintomas);
        secondPhaseSize = coundSizes(secondSintomas);
    }

    public int fisrtPhaseSize(){
        return fisrtPhaseSize;
    }

    public int secondPhaseSize(){
        return secondPhaseSize;
    }

    private void loadSymptoms(){
        for(Registro r : registros){
            mapSymptoms(r.getSintomas());
        }
    }

    private void mapSymptoms(Sintomas sintomas){
        for (Sintoma s : sintomas){
            String phaseName = s.getClass().getSimpleName();
            switch (phaseName){
                case "PrimeraFase" -> fisrtSintomas.add(s);
                case "SegundaFase" -> secondSintomas.add(s);
            }
        }
    }

    private int coundSizes(Sintomas sintomas){
        int resp = 0;
        for(Sintoma s : sintomas){
            resp++;
        }
        return resp;
    }
}