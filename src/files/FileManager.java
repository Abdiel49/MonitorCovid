package files;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.LinkedList;
import java.util.List;

public class FileManager {

    private String absolutePath;

    public FileManager(String absolutePath) {
//    absolutePath = "src/pmonitoreo/backend/cohabitantes/";
        this.absolutePath = absolutePath;
//    csvController = ReaderAndWriterFiles.getInstance(absolutePath);
    }

    public List<String> readRowsFile(String relativePath){
        List <String> data = new LinkedList<>();
        String path = absolutePath + relativePath;
        try {
            BufferedReader reader = Files.newBufferedReader( Paths.get( path ));
            String line;
            while( (line = reader.readLine()) != null ){
//                String[] dataLine = line.split(regex);
                data.add(line);
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeInFile(String relativePath, String lineData) {
        String path = absolutePath + relativePath;
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            String lineData = String.join(",", lineInformation);
            writer.write(lineData);
            writer.newLine(); // require
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String[] readNameFiles(String path){
        File folder = new File(absolutePath + path);
        return folder.list();
    }


}
