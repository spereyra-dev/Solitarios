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
    
    
    public void registrarJugador(Jugador j){
        jugadores.add(j);
    }
    public boolean existeAlias(String alias){
        boolean bandera = false;
        
        for(Jugador jugador : jugadores){
            if(jugador.equals(alias)){
                bandera = true;
            }
        }
        return bandera;
    }
    public boolean validarEdad(int edad){
        boolean esValida = false;
        if(edad > 0 && edad < 100){
            esValida = true;
        }
        
        return esValida;
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
