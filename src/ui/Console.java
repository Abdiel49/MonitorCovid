package ui;

import diagnosticos.DiagnosticoGrupoRiesgo;
import diagnosticos.DiagnosticoSimple;
import monitor.FuncionDiagnostico;
import monitor.Monitor;
import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.Determinante;
import sintomas.Importante;
import sintomas.TomarEnCuenta;

public class Console {

    private FuncionDiagnostico diagnostico;
    private Monitor monitor;
    private final Sintomas sintomas;

    private final Sintoma fiebre;
    private final Sintoma tosSeca;
    private final Sintoma pOlfato;

    private int edad;
    private boolean grupoRiesgo;

    public Console(){
        monitor = new Monitor();
        sintomas = new Sintomas();
        fiebre = new Importante("fiebre");
        tosSeca = new TomarEnCuenta("TosSeca");
        pOlfato = new Determinante("Perdida de olfato");
    }

    public void iniciar(){

    }

    private void menu(){
        println("Monitor");
        cargarDatos();
        cargarSintomas();
        monitor.monitorear();
    }

    private void cargarDatos(){
        println("ingrese su edad");
        edad=Input.getInt();
        // enfermedades de base:
        println("C");
    }

    private void cargarSintomas(){
        boolean loading = true;
        while (loading){
            sintomas();
            loadSintomas();
        }
    }

    private void sintomas(){
        println("Ingrese el numero del sintoma que presneta");
        println("0. Monitorear");
        println("1. Tos seca");
        println("2. Perdida de olfato y/o gusto");
        println("3. Fiebre");
//        println("");
    }

    private void loadSintomas(){
        int i = Input.getInt();
        switch (i){
            case 0 -> monitorear();
            case 1 -> addSintoma( tosSeca );
            case 2 -> addSintoma( pOlfato );
            case 3 -> addSintoma( fiebre );
        }
    }

    private void monitorear(){
        if(definirGrupoDeRiesgo()){
            diagnostico = new DiagnosticoGrupoRiesgo(sintomas);
        }else{
            diagnostico = new DiagnosticoSimple(sintomas);
        }
    }

    private void addSintoma(Sintoma s){
        sintomas.add(s);
    }

    private boolean definirGrupoDeRiesgo(){
        return (edad >= 60);
    }

    private void println(String cad) {
        System.out.println(cad);
    }
}