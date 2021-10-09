package sistema;

import java.util.ArrayList;
import java.util.List;
import solitarios.Juegos.Rectangulo;
import solitarios.Juegos.Saltar;

public class Sistema {
    //Registrar jugadores -Listo
    //Elegir juego
    //Tener una bitacora - Mostrar lista
    
    private List<Jugador> jugadores;
    private Bitacora bitacora;
    private Saltar saltar;
    private Rectangulo rectangulo;

    
    public Sistema() {
        this.jugadores = new ArrayList<>();
        this.bitacora = new Bitacora();
        this.saltar = new Saltar();
        this.rectangulo = new Rectangulo();
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

    public Saltar getSaltar() {
        return saltar;
    }

    public void setSaltar(Saltar saltar) {
        this.saltar = saltar;
    }

    public Rectangulo getRectangulo() {
        return rectangulo;
    }

    public void setRectangulo(Rectangulo rectangulo) {
        this.rectangulo = rectangulo;
    }
    
    public void registrarJugador(Jugador j) throws Exception{
        if(!existeJugador(j)){
            jugadores.add(j);
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
    
    public void elegirJuego(){
        
    }
}
