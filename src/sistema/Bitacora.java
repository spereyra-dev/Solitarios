package sistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Bitacora {
    private ArrayList<Partida> partidas;

    public Bitacora() {
        this.partidas = new ArrayList<Partida>();
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void agregarPartida(Partida partida){
        this.partidas.add(partida);
    }
}
