//Santiago Pereyra 245198
//Venancio Portillo 276560

package sistema;

import java.util.Date;

public class Partida {
    private Date comienzo;
    private Jugador jugador;
    private int puntaje;

    public Partida(Date comienzo, Jugador jugador, int puntaje) {
        this.comienzo = comienzo;
        this.jugador = jugador;
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Date getComienzo() {
        return comienzo;
    }
    
}
