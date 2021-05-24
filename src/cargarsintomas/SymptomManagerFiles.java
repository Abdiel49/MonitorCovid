package cargarsintomas;

import files.FilesManager;
import monitor.Sintoma;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SymptomManagerFiles {

    private final FilesManager files;
    private final String path;
    private final String DELIMETER;
    public SymptomManagerFiles(){
        files = new FilesManager();
        path = "cargarsintomas/sintomas.csv";
        DELIMETER = ",";
    }

    /**
     * abdiel249
     * @param path relative path to folder
     * @return String[] with name files in folder provided in the param or null if path does not exists
     */
    public String[] getNameFiles(String path){
        String[] resp;
        String[] fs = files.readNameFiles(path);
        if( fs != null){
            resp = new String[fs.length];
            for (int i = 0; i < fs.length; i++) {
                String[] fileName = fs[i].split(".class");
                resp [i] = fileName[0];
            }
            return resp;
        }else return new String[]{};
    }

    /*
     * abdiel249
     * @param className - complete class path to be Instance
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
            if(files.fristLineInFileIsEmpty(path)){
                writeHeadInFile();
                System.out.println("El archivo no existe");
            }
            files.writeInFile(path,data);
        }
    }

    /**
     * abdiel249
     * write head in frist ocation
     */
    private void writeHeadInFile(){
        String head = "CLASS_TYPE,NAME";
        files.writeInFile(path,head);
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
