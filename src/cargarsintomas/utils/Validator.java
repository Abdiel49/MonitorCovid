package cargarsintomas.utils;

public class Validator {

    public static String validateInput(){
        return "";
    }

    public static String normalize(String input){
        String s = input.replaceAll("\\s+", " ");
        return s.trim().toUpperCase();
    }
}
