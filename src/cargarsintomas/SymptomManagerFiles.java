package cargarsintomas;

import cargarsintomas.utils.FilesManager;
import monitor.Sintoma;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

public class SymptomManagerFiles {

    private final FilesManager files;
    private final String pathSymptom;
    private final String DELIMETER;

    public SymptomManagerFiles(){
        files = new FilesManager();
        pathSymptom = "cargarsintomas/sintomas.csv";
        DELIMETER = ",";
    }

    public List<String> getSymptomsDataFile(){
        return files.readRowsFile(pathSymptom);
    }

    /**
     * abdiel249
     * @return Vector<String> with class name files in folder provided in the param or null if pathSymptom does not exists
     */
    public Vector<String> getSymptomNamesInFile(){
        String path = "sintomas/";
        Vector<String> items = new Vector<>();
        String[] fileNames = files.readNameFiles(path);
        for(String fs : fileNames){
            String fileName = fs.replace(".class","");
            if(validateClassNameFile(fileName)){
                items.add(fileName);
            }
        }
        return items;
    }

    private boolean validateClassNameFile(String fileName){
        String symptomClass = "sintomas."+fileName;
        try{
            Class<?> cl = Class.forName(symptomClass);
            return cl.getSuperclass().equals(Sintoma.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * abdiel249
     * @param className - complete s pathSymptom to be Instance
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

    /**
     * abdiel249
     * @param symptom receive a symptom for save in file
     */
    public void saveSymptomInFile(Sintoma symptom) {
        String data="";
        try {
            data = getSymptomData(symptom);
        }catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if(data.length()>0){
            if(files.firstLineInFileIsEmpty(pathSymptom)){
                writeHeadInFile();
                System.out.println("El archivo no existe");
            }
            files.writeInFile(pathSymptom,data);
        }
    }

    /**
     * abdiel249
     * write head in frist ocation
     */
    private void writeHeadInFile(){
        String head = "CLASS_TYPE,NAME";
        files.writeInFile(pathSymptom,head);
    }

    /**
     * abdiel249
     * @param symptom
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private String getSymptomData(Sintoma symptom) throws NoSuchFieldException, IllegalAccessException {
        //class sintoma is:	class sintomas.Determinante
        Class<?> c = symptom.getClass();
        String typename = c.getTypeName();
        String classType = typename.replaceAll("class ","");
        Field f = c.getSuperclass().getDeclaredField("nombre");
        f.setAccessible(true);
        String name = (String)f.get(symptom);
        String data = String.join(DELIMETER, classType, name);
        f.setAccessible(false);
        System.out.println("Symptom data is:\t"+data);
        return data;
    }
}
