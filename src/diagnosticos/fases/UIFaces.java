package diagnosticos.fases;

import monitor.Fase;

import java.util.Scanner;

public class UIFaces {

    private final Fase fase;

    public UIFaces(Fase f){
        fase = f;
    }

    public void display(){
        switch (fase.getDia()){
            case 11 -> println("Recomendacion: Usted presenta varios sintomas y esta en la Primera fase");
            case 12 -> println("Recomendacion: Cuide su salud, puede consumir mas frutas y verduras");
            case 13 -> consultaMedica();
            case 21 -> segundaFase();
            case 22 -> println("Recomendacion: Debe evitar el contacto inecesario con otras personas");
            case 23 -> pruebaTest();
            case 24 -> emergencia();
        }
    }

    private void consultaMedica(){
        println("Se le recomineda que haga cita con un profesional de la salud, por sus sintomas" +
                "\nYa visito visito a un medico?" +
                "\n1.- Si." +
                "\n2.- No.");
        int resp = readOption();
        switch (resp){
            case 1 -> println("Bien, siga cuidando su salud");
            case 2 -> println("No pase por alto las recomendaciones, su salud es mas importante");
        }
    }

    private void pruebaTest(){
        println("Se le recomineda que se tome un Test, por los sintomas presentados" +
                "\nYa hizo el Test?" +
                "\n1.- Si." +
                "\n2.- No.");
        int resp = readOption();
        switch (resp){
            case 1 -> println("Bien, espere las indicaciones de su medico");
            case 2 -> println("No pase por alto las recomendaciones, debe hacerse un Test ");
        }
    }

    private void segundaFase(){
        println("Usted a ingresado a la Fase 2, este atento a sus sintomas y tome registros diarios");
    }

    private void emergencia(){
        println("Ustes presento varios sintomas por vastantes Dias debe ir al medico Urgentemente!");
    }

    private void println(String s){
        System.out.println(s);
    }

    private int readOption(){
        Scanner in = new Scanner(System.in);
        boolean isInt = false;
        String input;
        int resp=0;
        while (!isInt){
            input = in.next();
            try{
                resp = Integer.parseInt(input);
                isInt = true;
            }catch (NumberFormatException e){
                System.err.println("Ingrese una opcion valida");
            }
        }
        return resp;
    }
}
