package cargarregistros;

import cargarregistros.utils.FilesManager;
import monitor.Registro;
import monitor.Sintoma;
import monitor.Sintomas;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecordsManagerFiles {

    private final FilesManager files;
    private final String path;
    private final String DELIMETER;
    private final String PATTER;

    public RecordsManagerFiles(){
        files = new FilesManager();
        path = "cargarregistros/registros.csv";
        DELIMETER = ",";
        PATTER = "EEE MMM dd HH:mm:ss zzz yyyy";
    }

    public List<String> getRowsData(){
        return files.readRowsFile(path);
    }

    public void saveRecordInFile(Registro registro){
        String lineData = getRecordData(registro);
        System.out.println("Record line data is:\t"+lineData);
        files.writeInFile(path,lineData);
    }


    public Registro getRegistroFromFile(String rowData){
// Thu May 27 06:34:58 BOT 2021,sintomas.Determinante,tos seca,sintomas.TomarEnCuenta,Fiebre alta,
        Registro registro = null;
        String[] data = rowData.split(DELIMETER);
        Date date = parseDate(data[0]);
        Sintomas sintomas = loadSintomas(data);
        registro = new Registro(date,sintomas);
        return registro;
    }

    private Sintomas loadSintomas(String[] rowData){
        Sintomas sintomas = new Sintomas();
        for (int i = 1; i < rowData.length; i+=2) {
            String className = rowData[i];
            String value = rowData[i+2];
            Sintoma s = (Sintoma) getObjectType(className,value);
            sintomas.add(s);
        }
        return sintomas;
    }

    private Date parseDate(String cad){
        Date date = null;
        DateFormat format = new SimpleDateFormat(PATTER);
        try{
            date = format.parse(cad);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*
     * abdiel249
     * @param className - complete s path to be Instance
     * @param value - Value to constructor object
     * @return a Object Instance
     */
    public Object getObjectType(String className, String value){
        Object o = null;
        try {
            Class<?> cl = Class.forName(className);
            Constructor<?> cons = cl.getConstructor(String.class);
            o = cons.newInstance(value);
//            System.out.println("Class name\t"+ o.getClass());
        } catch (ClassNotFoundException | NoSuchMethodException |
            InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    private String getRecordData(Registro registro) {
        String recordData="";
        try {
            Class<?> c = registro.getClass();
            Field dateRecord = c.getDeclaredField("fecha");
            Field symptomsRecord = c.getDeclaredField("sintomas");
            dateRecord.setAccessible(true);
            symptomsRecord.setAccessible(true);
            Date date = (Date)dateRecord.get(registro);
            Sintomas symptoms = (Sintomas) symptomsRecord.get(registro);
            String symptomsData = symptomsToStringData(symptoms);
            recordData = String.join(DELIMETER, date.toString(),symptomsData);
            dateRecord.setAccessible(false);
            symptomsRecord.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return recordData;
    }

    private String symptomsToStringData(Sintomas sintomas){
        StringBuilder data = new StringBuilder();
        for (Sintoma s : sintomas){
            data.append(getSymptomData(s));
            data.append(",");
        }
        return data.toString();
    }

    private String getSymptomData(Sintoma symptom) {
        //class sintoma is:	class sintomas.Determinante
        String data = "";
        try{
            Class<?> c = symptom.getClass();
            String typename = c.getTypeName();
            String classType = typename.replaceAll("class ","");
            Field f = c.getSuperclass().getDeclaredField("nombre");
            f.setAccessible(true);
            String name = (String)f.get(symptom);
            data = String.join(DELIMETER, classType, name);
            f.setAccessible(false);
        } catch (NoSuchFieldException  | IllegalAccessException e){
            e.printStackTrace();
        }
        System.out.println("Symptom data is:\t"+data);
        return data;
    }
}
