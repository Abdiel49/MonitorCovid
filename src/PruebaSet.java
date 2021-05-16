import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class PruebaSet {

    static void prueba() {
        Elemento e1 = new Elemento("valor1");
        Elemento e2 = new Elemento("valor2");

        Elemento e3 = new Elemento("valor1");
        if (e1.equals(e3)) {
            System.out.println("Son iguales.");
        }

        if (e1.equals(new Elemento("valor1"))) {
            System.out.println("son iguales");
        } else {
            System.out.println("uy algo falla");
        }
        int hash1 = Objects.hash("uno");
        int hash2 = Objects.hash("uno");
        System.out.println("hashes: " + hash1 + " " + hash2);

        /* implementacion con HashSet */
        Set<Elemento> conjunto1 = new HashSet<>();
        conjunto1.add(e1);
        conjunto1.add(e2);
        conjunto1.add(new Elemento("valor1"));
        System.out.println("implementacion con HashSet");
        for (Elemento e: conjunto1) {
            System.out.println(e);
        }

        /* implementacion con TreeSet */
        Set<Elemento> conjunto2 = new TreeSet<>();
        conjunto2.add(e1);
        conjunto2.add(e2);
        conjunto2.add(new Elemento("valor1"));
        System.out.println("implementacion con TreeSet");
        for (Elemento e: conjunto2) {
            System.out.println(e);
        }
    }

}
