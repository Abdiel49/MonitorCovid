package cargarregistros.phasecontrol;

import cargarregistros.courier.Message;
import cargarregistros.courier.Notifier;
import cargarregistros.courier.Observer;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class PhaseControl implements Notifier {

    private List<Registro> recordsList;
    private int firstCount;
    private int secondCount;
    private final Registros registros;
    private boolean isSecondPhase;
    private boolean isFirstPhase;
    private final List<Observer> observers;
    private final LoadData data;
    private Registro auxRegistro;
    private int days;

    public PhaseControl(Registros r){
        registros = r;
        data = new LoadData(registros);
        isSecondPhase = false;
        isFirstPhase = false;
        recordsList = new LinkedList<>();
        observers = new LinkedList<>();
        days = 0;
        init();
    }

    private void init(){
        auxRegistro = null;
        firstCount = data.fisrtPhaseSize();
        secondCount = data.secondPhaseSize();
        loadPhaseControl();
    }

    private void loadPhaseControl(){
        for(Registro r : registros){
            phaseControl(r);
        }
    }

    public void phaseControl(Registro r){
        if(percentageSymptoms(r)){
            if(auxRegistro != null){
                if(differenceDays(auxRegistro, r) != 1) days = 0;
            }
            days++;
            evaluatePhase(days);
        }
    }

    private void evaluatePhase(int days){
        isFirstPhase = days > 0 && days < Constants.START_SECOND_PHASE;
        isSecondPhase = days >= Constants.START_SECOND_PHASE;
        if(isFirstPhase) managerFirstPhase();
        if(isSecondPhase) managerSecondPhase();
    }

    private void managerFirstPhase(){
        switch (days){
            case 1 -> sendMessage(Message.FIRST_PHASE);
            case 2 -> sendMessage(Message.MEDICAL_APPOINTMENT);
            case 3 -> sendMessage(Message.TEST);
        }
    }

    private void managerSecondPhase(){
        switch (days){
            case 4 -> sendMessage(Message.SECOND_PHASE);
            case 5 -> sendMessage(Message.U_MEDICAL_APPOINTMENT);
        }
    }

    // only for the ideal case - increase date control
    private int differenceDays(Registro f, Registro s){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(f.getFecha());
        c2.setTime(s.getFecha());
//        int daysDifference = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
//        return Math.abs(daysDifference); // use this for integration
        return 1; // for test code
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
        double percent = (sizeList * Constants.PERCENTAGE_SYMTOMS)/100.0;
        return items >= percent;
    }

    @Override
    public void sendMessage(Message m) {
        for(Observer o : observers) {
            o.mailBox(m);
        }
    }

    @Override
    public void subscribe(Observer o) {
        observers.add(o);
    }
}
