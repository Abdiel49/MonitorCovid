package cargarregistros;

import cargarregistros.gui.GUICargarRegistros;
import monitor.Sintomas;
import sintomas.PrimeraFase;
import sintomas.SegundaFase;

public class MainRegistros {
    public static void main(String[] args) {
        Sintomas sintomas = new Sintomas();
        sintomas.add(new PrimeraFase("Tos Seca"));
        sintomas.add(new PrimeraFase("Diarrea"));
        sintomas.add(new PrimeraFase("Dolor de pecho"));
        sintomas.add(new SegundaFase("Temperatura alta"));
        GUICargarRegistros registros = new GUICargarRegistros(sintomas);
    }
}
