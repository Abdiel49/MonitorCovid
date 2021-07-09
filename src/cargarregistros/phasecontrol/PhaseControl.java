package cargarregistros.phasecontrol;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class PhaseControl {

    private final Registros registros;
    private List<Registro> recordsList;
    private Sintomas firstPhase;
    private Sintomas secondPhase;
    private int firstCount;
    private int secondCount;
    private boolean isSecondPhase;
    private final int PERCENTAGE_SYMTOMS = 50;

    public PhaseControl(Registros r){
        registros = r;
        firstPhase = new Sintomas();
        secondPhase = new Sintomas();
        firstCount = 0;
        secondCount = 0;
        isSecondPhase = false;
        recordsList = new LinkedList<>();
        loadRecordPhases();
    }

    private void loadRecordPhases(){
        for(Registro r : registros){
            recordsList.add(0,r);
            Sintomas s = r.getSintomas();
            loadSymptomsPhases(s);
        }
    }

    private void loadSymptomsPhases(Sintomas sintomas){
        for(Sintoma s : sintomas){
            String phaseName = s.getClass().getSimpleName();
            switch (phaseName){
                case "PrimeraFase" -> {
                    firstPhase.add(s);
                    firstCount++;
                }
                case "SegundaFase" -> {
                    secondPhase.add(s);
                    secondCount++;
                }
            }
        }
    }

    public void phaseControl(){
        for (int i = 0; i < recordsList.size()-1 ; i++) {
            Registro r1 = recordsList.get(i);
            Registro r2 = recordsList.get(i+1);
            if(differenceDays(r1,r2) == 1){

            }
        }
    }

    // only for the ideal case - increase date control
    private int differenceDays(Registro f, Registro s){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(f.getFecha());
        c2.setTime(s.getFecha());
        int days = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
        return Math.abs(days);
    }

    private boolean percentageSymptoms(Registro r){
        Sintomas sintomas = r.getSintomas();
        int[] phases = countPhases(sintomas);
        if(!isSecondPhase){ // first phase
            isMoreThanThePercentage(phases[0]);
        }else{ // second phase
            isMoreThanThePercentage(phases[1]);
        }
        return false;
    }

    private int[] countPhases(Sintomas sintomas){
        int first = 0, second = 0;
        for(Sintoma s : sintomas){
            String phaseName = s.getClass().getSimpleName();
            switch (phaseName){
                case "PrimeraFase" -> first++;
                case "SegundaFase" -> second++;
            }
        }
        return new int[]{first, second};
    }

    private boolean isMoreThanThePercentage(int items){
        int sizeList = isSecondPhase ? secondCount : firstCount;
        double percent = (sizeList * PERCENTAGE_SYMTOMS)/100.0;
        return items >= percent;
    }
}
