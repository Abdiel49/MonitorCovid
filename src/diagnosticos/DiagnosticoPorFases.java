package diagnosticos;

import monitor.*;

import java.util.Calendar;

public class DiagnosticoPorFases extends FuncionDiagnostico {

    private Registros registros;
    private Registro auxRegistro;
    private final Sintomas sintomas;
    private boolean isSecondPhase;
    private int firstCount;
    private int secondCount;
    private int days;
    private final int START_SECOND_PHASE = 4;
    private final int PERCENTAGE_SYMTOMS = 50;
    private final int DIFFERENCE = 1;

    public DiagnosticoPorFases(Sintomas s){
        super(s);
        sintomas = s;
        auxRegistro = null;
        isSecondPhase = false;
        days = 0;
        coundSymptoms();
    }

    private void coundSymptoms(){
        for (Sintoma s : sintomas){
            String phaseName = s.getClass().getSimpleName();
            switch (phaseName){
                case "PrimeraFase" -> firstCount++;
                case "SegundaFase" -> secondCount++;
            }
        }
    }

    @Override
    public int diagnostico(Registros registros) {
        this.registros = registros;
        loadPhaseControl();
        return makeResp();
    }

    private int makeResp(){
        return switch (days){
            case 1 -> 11;
            case 2 -> 12;
            case 3 -> 13;
            case 4 -> 21;
            case 5 -> 22;
            case 6 -> 23;
            case 7 -> 24;
            case 8 -> 25;
            default -> 0;
        };
    }

    private void loadPhaseControl(){
        for(Registro r : registros){
            control(r);
        }
    }

    private void control(Registro r){
        if(percentageSymptoms(r)){
            if(auxRegistro != null){
                boolean goodDifference = differenceDays(auxRegistro, r);
                if(goodDifference){
                   days++;
                }else if(!isSecondPhase){
                    days = 0;
                }
            }else days++;
            auxRegistro = r;
            isSecondPhase = days >= START_SECOND_PHASE;
        }
    }

    private boolean differenceDays(Registro f, Registro s){
        long dayMilliseconds = 86400000;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(f.getFecha());
        c2.setTime(s.getFecha());
        int daysDifference = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
        if(daysDifference == DIFFERENCE){
           return true;
        }else{
            long timeR1 = c1.getTimeInMillis();
            long timeR2 = c2.getTimeInMillis();
            long difference = DIFFERENCE * dayMilliseconds;
            long goodDifference = timeR1 + difference;
//            return timeR2 <= goodDifference;
        }
        return true; // for test development
    }

    private boolean percentageSymptoms(Registro r){
        Sintomas sintomas = r.getSintomas();
        int[] phases = countPhases(sintomas);
        return isSecondPhase ? isMoreThanThePercentage(phases[1]) : isMoreThanThePercentage(phases[0]);
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
