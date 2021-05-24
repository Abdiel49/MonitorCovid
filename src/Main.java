import cargarsintomas.gui.GUICargarSintomas;


public class Main {

    public static void main(String[] args) {
        System.out.println("diagnostico");
//        Monitor monitor = new Monitor();
//        monitor.monitorear();
        GUICargarSintomas w = new GUICargarSintomas();
        w.show();

//        String e = "class sintomas.Determinante";
//        System.out.println(e);
//        String neiw = e.replaceAll("class ","");
//        System.out.println(neiw);
//        System.out.println(neiw.trim());

//        ClassToObject cto = new ClassToObject();
//        Field fieldName = Sintoma.class.getDeclaredField("nombre");
//        System.out.println("Se puede cambiar el acceso?\t"+ fieldName.trySetAccessible());
//        fieldName.setAccessible(true);

//        for(Field f : fieldName){
//            f.setAccessible(true);
//            System.out.println("field is\t"+f.getName());
//        }
//        Sintoma s = new Determinante("Tos seca");
//        Field f = Determinante.class.getDeclaredField("nombre");
//        f.setAccessible(true);
//        Field sname = s.getClass().getSuperclass().getDeclaredField("nombre");
//        sname.setAccessible(true);
//        Class c = sname.getClass();
//        String name = "";
//        Object a = sname.get(s);
//        Object nameEsteSiSera = sname.get(a);
//        name = (String)a;
//        System.out.println("simtoma name =\t"+name);
//        Field nameSymptom = s.getClass().getField("nombre");
//        System.out.println("name values is\t"+nameSymptom.getName());
//        fieldName.setAccessible(true);
//        System.out.println("class sintoma is:\t"+fieldName.getName());
//        Class c = s.getClass();
//        Object o = new Object();
//        boolean resp = cto.isAType(o, Sintoma.class);
//        System.out.println("Sintoma s es un tipo de Sintoma?:\t"+resp);
//        if(Sintoma.class == s.getClass().getSuperclass()){
//            System.out.println("ambos objetos son iguales");
//        }else{
//            System.out.println("los objetos no son iguales");
//        }
//        System.out.println(c.getSuperclass().toString());
//        System.out.println(c);

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
