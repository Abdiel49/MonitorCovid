package files;

import java.io.*;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.LinkedList;
import java.util.List;

public class FilesManager {

    private final String absolutePath;
    private String realPath;

    public FilesManager() {
        absolutePath = "out/production/MonitorCovid/";
        realPath = absolutePath;
    }

    public List<String> readRowsFile(String relativePath){
        List <String> data = new LinkedList<>();
//        if(fileExists(relativePath)){
            String path = realPath + relativePath;
            try {
                BufferedReader reader = Files.newBufferedReader( Paths.get( path ));
                String line;
                while( (line = reader.readLine()) != null ){
                    data.add(line);
                }
                reader.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
//        }
        return data;
    }


    public void writeInFile(String relativePath, String lineData) {
        String path = realPath + relativePath;
//        if(fileExists(relativePath)) {
            try {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                writer.write(lineData);
                writer.newLine(); // require?
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
    }

    public String[] readNameFiles(String path){
//        fileExists(path);
        File folder = new File(realPath + path);// || new File(path) ;
        return folder.list();
    }

    private boolean fileExists(String path){
        File f = new File(absolutePath + path);
        String production = "";
        if(f.exists()){
            realPath = absolutePath;

            return true;
        }else{
            File f2 = new File(production + path);
            if(f2.exists()){
                realPath = production;
                return true;
            } else return false;
        }
    }

    public boolean fristLineInFileIsEmpty(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(realPath+path));
            if (br.readLine() == null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return false;
        }
//        return false;
    }


}
