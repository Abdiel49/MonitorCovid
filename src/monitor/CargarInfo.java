package monitor;

import java.util.Date;

public class CargarInfo {

    private Sintomas sintomas;
    private Sintoma perdidaDeOlfato;
    private Sintoma temperaturaAlta;

    public CargarInfo() {
        cargarSintoma();
        cargarSintomas();
    }

    private void cargarSintoma() {
//        perdidaDeOlfato = new Determinante("Perdida de olfato");
//        temperaturaAlta = new Importante("Temperatura alta");
    }

    private void cargarSintomas() {
        sintomas = new Sintomas();
        sintomas.add(perdidaDeOlfato);
        sintomas.add(temperaturaAlta);
    }

    public Registros cargarRegistros() {
        Sintomas sintomas = new Sintomas();
        sintomas.add(temperaturaAlta);
        sintomas.add(perdidaDeOlfato);
        Registros registros = new Registros();
        registros.push(new Registro(new Date(),sintomas));
        return registros;
    }

    public Sintomas getSintomas() {
        return sintomas;
    }
}
