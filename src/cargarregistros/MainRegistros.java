package cargarregistros;

import cargarregistros.gui.GUICargarRegistros;
import monitor.Registro;
import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.Determinante;
import sintomas.TomarEnCuenta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainRegistros {
    public static void main(String[] args) {
        GUICargarRegistros registros = new GUICargarRegistros();
        registros.show();
//        Sintomas sintomas = new Sintomas();
//        sintomas.add( new Determinante("tos seca"));
//        sintomas.add( new TomarEnCuenta("Fiebre alta"));
//        Date date = new Date();
//        Registro registro = new Registro(date, sintomas);
//        RecordsManagerFiles manager = new RecordsManagerFiles();
//        manager.saveRecordInFile(registro);
//        String cad = "Thu May 27 06:34:58 BOT 2021,sintomas.Determinante,tos seca,sintomas.TomarEnCuenta,Fiebre alta,";
//        String[] data = cad.split(",");
//        System.out.println(Arrays.toString(data));
//        StringBuilder str = new StringBuilder();
//        for(String s : data){
//            str.append(s);
//            str.append(',');
//        }
//        System.out.println("String builder=\t"+str);
//        data = str.toString().split(",");
//        System.out.println(Arrays.toString(data));
//        String patern = "EEE MMM dd HH:mm:ss zzz yyyy";
//        String d = "Thu May 27 06:34:58 BOT 2021";
//        DateFormat format = new SimpleDateFormat(patern);
//
//        Date date = null;
//        try{
//            date = format.parse(d);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Date parseado:\t"+date.toString());
    }
}
