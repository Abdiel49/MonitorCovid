import java.util.Objects;

public class Elemento implements Comparable<Elemento> {

    private String nombre;

    public Elemento(String n) {
        nombre = n;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Elemento e = (Elemento) obj;
        return nombre.equals(e.nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int compareTo(Elemento elemento) {
        return nombre.compareTo(elemento.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

}
