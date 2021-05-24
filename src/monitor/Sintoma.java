package monitor;

import java.util.Objects;

public abstract class Sintoma implements Comparable<Sintoma>{
    private String nombre;

    public Sintoma(String n) {
        nombre = n;
    }

    public abstract int peso();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Sintoma s = (Sintoma) obj;
        return nombre.equals(s.nombre);
    }

    @Override
    public int compareTo(Sintoma sintoma) {
        return nombre.compareTo(sintoma.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
