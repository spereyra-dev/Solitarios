//Santiago Pereyra 245198
//Venancio Portillo 276560

package sistema;

import java.util.ArrayList;

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
