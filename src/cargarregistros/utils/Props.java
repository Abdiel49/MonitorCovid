package cargarregistros.utils;

import monitor.Sintoma;

import java.lang.reflect.Field;

public class Props {

    public String[] getSymptomProperties(Sintoma s){
        String [] data = null;
        try{

            Class<?> c = s.getClass();
            String typename = c.getTypeName();
            String classType = typename.replaceAll("class ","");
            Field f = c.getSuperclass().getDeclaredField("nombre");
            f.setAccessible(true);
            String name = (String)f.get(s);
            f.setAccessible(false);
            data = new String[]{name, classType};
        }catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }
}
