package diagnosticos;

import monitor.*;


import java.util.Calendar;

public class DiagnosticoPorFases extends FuncionDiagnostico {

    private Registros registros;
    private Registro auxRegistro;
    private Sintomas sintomas;
    private boolean isSecondPhase;
    private boolean isFirstPhase;
    private int firstCount;
    private int secondCount;
    private int days;
    private final int START_SECOND_PHASE = 4;
    private final int PERCENTAGE_SYMTOMS = 50;
    private final int DIFFERENCE = 1;
    private Fase fase;

    public DiagnosticoPorFases(Sintomas s){
        super(s);
        sintomas = s;
        auxRegistro = null;
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
        return 0;
    }

    private void loadPhaseControl(){
        for(Registro r : registros){
            control(r);
        }
    }

    public void control(Registro r){
        if(percentageSymptoms(r)){
            if(auxRegistro != null){
                if(differenceDays(auxRegistro, r)) days = 0;
            }
            days++;
            evaluatePhase(days);
        }
    }

    private void evaluatePhase(int days){
        isFirstPhase = days > 0 && days < START_SECOND_PHASE;
        isSecondPhase = days >= START_SECOND_PHASE;
        if(isFirstPhase) managerFirstPhase();
        if(isSecondPhase) managerSecondPhase();
    }

    private void managerFirstPhase(){

    }

    private void managerSecondPhase(){

    }

    private boolean differenceDays(Registro f, Registro s){
        boolean resp;
        long dayMilliseconds = 86400000;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(f.getFecha());
        c2.setTime(s.getFecha());
        int daysDifference = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
        if(daysDifference == DIFFERENCE){
           resp = true;
        }else{
            long timeR1 = c1.getTimeInMillis();
            long timeR2 = c2.getTimeInMillis();
            long difference = DIFFERENCE * dayMilliseconds;
            long goodDifference = timeR1 + difference;
            resp = timeR2 <= goodDifference;
        }
        return true; // for test development
//        return resp; // for production
    }

    private boolean percentageSymptoms(Registro r){
        Sintomas sintomas = r.getSintomas();
        int[] phases = countPhases(sintomas);
        return isSecondPhase ?
                isMoreThanThePercentage(phases[1]) :
                isMoreThanThePercentage(phases[0]);
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
