package cargarregistros.utils;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class RecordsManagerFiles {

    private final Registros registros;
    private final String projectDir;
    private final String filePath;
    private final String SEPARATOR;
    private final String DELIMETER;
    private final String PATTER;

    public RecordsManagerFiles(){
        registros = new Registros();
        DELIMETER = ",";
        PATTER = "EEE MMM dd HH:mm:ss zzz yyyy";
        SEPARATOR = System.getProperty("file.separator");
        filePath = /*"cargarregistros"+SEPARATOR+*/"registros.csv";
        projectDir = PathProject.pathFileProject();
    }

    public void saveRecordInFile(Registro registro){
        String lineData = getRecordData(registro);
        String path = projectDir+filePath;
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            writer.write(lineData);
            writer.newLine(); // require? YES
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    private String getRecordData(Registro registro) {
        StringBuilder recordData = new StringBuilder();
        Sintomas sintomas = registro.getSintomas();
        recordData.append(registro.getFecha().toString());
        recordData.append(DELIMETER);
        for(Sintoma s : sintomas){
            String classType = s.getClass().getTypeName();
            String value = s.toString();
            recordData.append(classType);
            recordData.append(DELIMETER);
            recordData.append(value);
            recordData.append(DELIMETER);
        }
        return recordData.toString();
    }

    public Registros loadRegistros(){
        List<String> data = readRowsFile();
        for(String s : data){
            registros.push(buildRegistro(s));
        }
        return registros;
    }

    private Registro buildRegistro(String l){
        String[] data = l.split(DELIMETER);
        Sintomas sintomas = new Sintomas();
        Date date = parseDate(data[0]);
        for (int i = 1; i < data.length; i+=2) {
            String className = data[i];
            String value = data[i+1];
            sintomas.add(buildSintoma(className, value));
        }
        return new Registro(date, sintomas);
    }

    private Sintoma buildSintoma(String className, String value){
        Object o = null;
        try {
            Class<?> cl = Class.forName(className);
            Constructor<?> cons = cl.getConstructor(String.class);
            o = cons.newInstance(value);
        } catch (ClassNotFoundException | NoSuchMethodException |
            InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.err.println(e.getMessage());
        }
        return (Sintoma)o;
    }

    private List<String> readRowsFile(){
        List <String> data = new LinkedList<>();
        String path = projectDir+filePath;
        try {
            BufferedReader reader = Files.newBufferedReader( Paths.get( path ));
            String line;
            while( (line = reader.readLine()) != null ){
                if(line.length()>5) data.add(line);
            }
            reader.close();
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return data;
    }

    private Date parseDate(String cad){
        SimpleDateFormat format = new SimpleDateFormat(PATTER, Locale.US);
        try{
            return format.parse(cad);
        }catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
    