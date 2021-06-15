package cargarsintomas;

import cargarsintomas.gui.GUICargarSintomas;

import java.util.Arrays;

public class MainSintomas {
    public static void main(String[] args) {
        GUICargarSintomas sintomas = new GUICargarSintomas();
        sintomas.show();
//        SymptomManagerFiles manager = new SymptomManagerFiles();
//        try {
//            manager.getSymptomsDataFile();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String rowData = "sintomas.Importante,tos seca";
//        String[] dataSplit = rowData.split(",");
//        System.out.println(Arrays.toString(dataSplit));
    }
}
