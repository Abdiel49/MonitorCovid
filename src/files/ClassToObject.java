package files;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassToObject {
    /*
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
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    public boolean isAType(Object a, Object b ){
        return a.getClass().getSuperclass() == b;
    }
}
