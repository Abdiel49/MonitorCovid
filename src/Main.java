import monitor.Monitor;
import ui.gui.Window;

import java.io.File;

public class Main {

    public static void main(String[] args){
        System.out.println("diagnostico");
        Monitor monitor = new Monitor();
        monitor.monitorear();
        Window w = new Window();
        w.show();

//        String productionPath = "out/production/MonitorCovid/";
//        String path = productionPath+"sintomas/";
//        String dir = System.getProperty("user.dir");
//        System.out.println("directory is:\t"+dir);
//        File folder = new File(dir);
//        String[] list = folder.list();
//
//        if (list == null || list.length == 0) {
//            System.out.println("No hay elementos dentro de la carpeta actual");
//        }
//        else {
//            for (int i=0; i< list.length; i++) {
//                System.out.println(list[i]);
//            }
//        }


//        System.out.println("Name Window is:\t");
//        System.out.println("resultado: " + monitor.getResultado());

//        PruebaSet.prueba();
    }

}
