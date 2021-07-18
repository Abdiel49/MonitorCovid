package monitor;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DatosFase {

    private final String pathFileName;
    private final String DELIMITER = ",";

    public DatosFase(){
        pathFileName = pathFileProject() + "Abdiel-fase.csv";
    }

    private String pathFileProject(){
        File dir = new File("");
        String SEPARATOR = System.getProperty("file.separator");
        boolean production = false;
        String path=".";
        try {
            path = dir.getCanonicalPath();
            dir = new File(path);
            if(dir.list() != null){
                String[] dirFiles = dir.list();
                String files = String.join(DELIMITER, dirFiles != null ? dirFiles : new String[0]);
                production = files.contains("out");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String testPath = "out"+SEPARATOR+"production"+SEPARATOR+"MonitorCovid"+SEPARATOR;
        return production ? path+SEPARATOR+testPath : path+SEPARATOR;
    }

    public Fase readFaseData(){
        Fase fase = new Fase();
        String[] data = new String[]{};
        try{
            if(fileExists()){
                BufferedReader reader = Files.newBufferedReader(Paths.get(pathFileName));
                String line;
                while( (line = reader.readLine()) != null ){
                    if(line.length()> 5){
                        data = line.split(DELIMITER);
                    }
                }
                reader.close();
                fase = parseData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fase;
    }

    private Fase parseData(String[] data){
        Fase f = new Fase();
        if(data.length==2){
            int NAME=0, DAY=1;
            f.setNombre(data[NAME]);
            f.setDia(Integer.parseInt(data[DAY]));
        }
        return f;
    }

    private boolean fileExists(){
        File f = new File(pathFileName);
        return f.exists();
    }

    public void saveFaseData(Fase f){
        String lineData = f.getNombre() + DELIMITER +f.getDia();
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathFileName), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            writer.write(lineData);
//            writer.newLine(); // require? YES
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
//            System.err.println(e.getMessage());
        }
    }
}
