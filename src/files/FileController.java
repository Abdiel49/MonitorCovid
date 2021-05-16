package files;

import java.util.Arrays;

public class FileController {

    private final String productionPath;
    private final FileManager files;

    public FileController(){
        productionPath = "out/production/MonitorCovid/";
        files = new FileManager(productionPath);
    }

    public String[] getNameFiles(String path){
        String[] resp;
        String[] fs = files.readNameFiles(path);
        if( fs != null){
            resp = new String[fs.length];
            for (int i = 0; i < fs.length; i++) {
                String[] fileName = fs[i].split(".class");
                resp [i] = fileName[0];
                System.out.println(Arrays.toString( fileName));
            }
            return resp;
        }else return new String[]{};
//        return fs;
    }
}
