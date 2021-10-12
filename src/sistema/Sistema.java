package sistema;

import java.util.ArrayList;
import java.util.List;
import solitarios.Juegos.Juego;
import solitarios.Juegos.Rectangulo;
import solitarios.Juegos.Saltar;

public class Sistema {
    //Registrar jugadores -Listo
    //Elegir juego
    //Tener una bitacora - Mostrar lista
    
    private List<Jugador> jugadores;
    private Bitacora bitacora;
    private Juego juego;

    
    public Sistema() {
        this.jugadores = new ArrayList<>();
        this.bitacora = new Bitacora();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Bitacora getBitacora() {
        return bitacora;
    }

    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    public Juego getJuego() {
        return juego;
    }
    
    
    public void registrarJugador(Jugador j) throws Exception{
        if(!existeJugador(j)){
            if(j.getEdad() > 4 && j.getEdad() < 110){
                jugadores.add(j);
            } else {
                throw new Exception("Se permite como edad del jugador desde 4 a 110. Operación cancelada.");            
            }
        } else {
            throw new Exception("El jugador ya existe. Operación cancelada.");
        }
    }
    private boolean existeJugador(Jugador j){
        boolean bandera = false;
        
        for(Jugador jugador : jugadores){
            if(jugador.equals(j)){
                bandera = true;
            }
        }
        return bandera;
    }
    //TODO: instanciar juego segun si es saltar o rectangulo
    public void elegirJuego(String nombreJuego){
        switch(nombreJuego){
            case "SALTAR":
                this.juego = new Saltar();
            break;
            case "RECTANGULO":
                this.juego = new Saltar();
            break;
            default:
            break;
        }
    }
}
