package cargarregistros.utils;

import java.io.File;
import java.io.IOException;

public class PathProject {

    public static String pathFileProject(){
        File dir = new File("");
        String SEPARATOR = System.getProperty("file.separator");
        String DELIMETER = ",";
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
}
