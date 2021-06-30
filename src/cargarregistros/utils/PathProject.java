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
            String[] dirFiles = dir.list()==null ? new String[]{} : dir.list();
            String files = String.join(DELIMETER,dirFiles);
            production = files.contains("out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String testPath = "out"+SEPARATOR+"production"+SEPARATOR+"MonitorCovid"+SEPARATOR;
        return production ? path+SEPARATOR+testPath : path+SEPARATOR;
    }
}
