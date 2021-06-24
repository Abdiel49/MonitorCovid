package cargarsintomas.utils;

import cargarsintomas.utils.Validator;
import monitor.Sintoma;
import monitor.Sintomas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class SymptomManager {

    private final String symptomsFile;
    private final String DELIMETER;
    private final String SEPARATOR;
    private final String pathProject;
    private final Sintomas sintomas;
    private List<String> deprecatedSymptoms;

    public SymptomManager(){
//        files = new FilesManager();
        DELIMETER = ",";
        SEPARATOR = System.getProperty("file.separator");
        symptomsFile = "cargarsintomas"+SEPARATOR+"sintomas.csv";
        pathProject = pathFileProject();
        sintomas = new Sintomas();
    }

    private String pathFileProject(){
        File dir = new File("");
        boolean production = false;
        String path="";
        try {
            path = dir.getCanonicalPath();
            dir = new File(path);
            String files = String.join(DELIMETER,dir.list());
            production = files.contains("out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String testPath = "out"+SEPARATOR+"production"+SEPARATOR+"MonitorCovid"+SEPARATOR;
        String finalpath = production ? path+SEPARATOR+testPath : path+SEPARATOR;
//        System.out.println(finalpath);
        return finalpath;
    }

    public Vector<String> getSymptomNamesInFile() {
        String path = pathProject+"sintomas/";
//        System.out.println("path names files is\t"+path);
        File f = new File(path);
        Vector<String> items = new Vector<>();
        String[] fileNames = f.list();
//        System.out.println(Arrays.toString(fileNames));
        for(String fs : fileNames){
            String fileName = fs.replace(".class","");
            if(validateClassNameFile(fileName)){
                items.add(fileName);
            }
        }
        return items;
    }

    private boolean validateClassNameFile(String fileName) {
        String symptomClass = "sintomas."+fileName;
        try{
            Class<?> cl = Class.forName(symptomClass);
            return cl.getSuperclass().equals(Sintoma.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getDeprecatedSymptoms(){
        return deprecatedSymptoms;
    }

    public Sintomas loadSymptoms(){
        List<String> symptoms = readRowsFile();
        for(String s : symptoms){
            String[] data = s.split(DELIMETER);
            String className = data[0].replace("class ", "");
            String value = data[1];
            String sData = data[0].replace("sintomas.","");
            if( validateClassNameFile(sData)){
//                symptoms.add(s);
                sintomas.add(getObjectType(className,value));
            }
        }
        return sintomas;
    }

    public List<String> getSymptomsDataFile() {
        List<String> symptoms = readRowsFile();
        deprecatedSymptoms = new LinkedList<>();
        for(String s : symptoms){
            String[] data = s.split(DELIMETER);
            String className = data[0].replace("class ", "");
            String value = data[1];
            String sData = data[0].replace("sintomas.","");
            if( validateClassNameFile(sData)){
                symptoms.add(s);
            }else{
                deprecatedSymptoms.add(s);
            }
//            System.out.println("Symptom data in file is:\t"+s);
        }
        return symptoms;
    }

    private List<String> readRowsFile(){
        List<String> list = new LinkedList<>();
        String path = pathProject + symptomsFile;
        try {
            BufferedReader reader = Files.newBufferedReader( Paths.get( path ));
            String line;
            while( (line = reader.readLine()) != null ){
                if(line.length()>0) list.add(line);
            }
            reader.close();
        }catch (IOException e) {
//            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return list;
    }

    public Sintoma getObjectType(String className, String value){
        Object o = null;
        try {
            Class<?> cl = Class.forName(className);
            Constructor<?> cons = cl.getConstructor(String.class);
            o = cons.newInstance(value);
//            System.out.println("Class name\t"+ o.getClass());
        } catch (ClassNotFoundException | NoSuchMethodException |
                InvocationTargetException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return (Sintoma)o;
    }

    public void saveSymptomInFile(Sintoma symptom) {
        String className = symptom.getClass().getName();
        String value = Validator.normalize(symptom.toString());
        String data = className+DELIMETER+value;
        writeData(data);
    }

    private void writeData(String data){
        String path = pathProject+symptomsFile;
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            writer.newLine(); // require?
            writer.write(data);
            writer.close();
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
