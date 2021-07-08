package cargarsintomas.utils;

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
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SymptomManager {

    private final String symptomsFile;
    private final String DELIMETER;
    private final String SEPARATOR;
    private final String pathProject;
    private final Sintomas sintomas;

    public SymptomManager(){
        DELIMETER = ",";
        SEPARATOR = System.getProperty("file.separator");
        symptomsFile = "cargarsintomas"+SEPARATOR+"sintomas.csv";
        pathProject = PathProject.pathFileProject();
        sintomas = new Sintomas();
    }

    public Vector<String> getSymptomNamesInFile() {
        String path = pathProject+"sintomas/";
        File f = new File(path);
        String[] fileNames = f.list();
        return (fileNames!=null && fileNames.length>0) ? getNamesInFolder(fileNames) : getNamesInJarFile();
    }

    private Vector<String> getNamesInFolder(String[] fileNames){
        Vector<String> items = new Vector<>();
        for(String fs : fileNames){
            String fileName = fs.replace(".class","");
            if(validateClassNameFile(fileName)){
                items.add(fileName);
            }
        }
        return items;
    }

    private Vector<String> getNamesInJarFile(){
        Vector<String> items = new Vector<>();
        File homeJar = new File(pathProject+"home.jar");
        try {
            JarFile jarFile = new JarFile(homeJar);
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()){
                JarEntry jarEntry = e.nextElement();
                if(jarEntry.getName().startsWith("sintomas/")){
                    String nameClass = jarEntry.getRealName().replace("sintomas/","");
                    String data = nameClass.replace(".class","");
                    System.out.println("data is:\t"+data);
                    items.add(data);
                }
            }
        } catch (IOException e) {
            System.err.println("No se encontro el archivo Home.jar");
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

    public Sintomas loadSymptoms(){
        List<String> symptoms = readRowsFile();
        for(String s : symptoms){
            String[] data = s.split(DELIMETER);
            String className = data[0].replace("class ", "");
            String value = data[1];
            String sData = data[0].replace("sintomas.","");
            if( validateClassNameFile(sData)){
                sintomas.add(getObjectType(className,value));
            }
        }
        return sintomas;
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
        } catch (ClassNotFoundException | NoSuchMethodException |
                InvocationTargetException | InstantiationException | IllegalAccessException e) {
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
            writer.write(data);
            writer.newLine(); // require? YES
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
