package cargarsintomas.utils;

import monitor.Sintoma;
import monitor.Sintomas;

public class Validator {

    public static String validateInput(){
        return "";
    }

    public static String normalize(String input){
        String s = input.replaceAll("\\s+", " ");
        return s.trim().toUpperCase();
    }

    public static boolean symptomExists(Sintomas sintomas, String name){
        String n = normalize(name);
        for(Sintoma s : sintomas){
            if(n.equals( s.toString() )){
                return true;
            }
        }
        return false;
    }
}
