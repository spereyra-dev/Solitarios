//Santiago Pereyra 245198
//Venancio Portillo 276560

package sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import solitarios.Juegos.Juego;
import solitarios.Juegos.Rectangulo;
import solitarios.Juegos.Saltar;

public class Sistema {
    //TODO: Tener una bitacora - Mostrar lista
    //TODO: En todos los casos debe validarse la jugada y si es incorrecta, solicitar el reingreso. (No tengo claro si validarJugada va en sistema o va en cada juego)
    //TODO: El sistema debe verificar la condición de terminación del respectivo juego e informarla. También, si se ingresa "X" termina 
    //TODO: el juego en el momento y se considera el puntaje logrado hasta ahí para la bitácora. 
    
    private List<Jugador> jugadores;
    private Bitacora bitacora;
    private Juego juego;
    private Partida partida;
    
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

    public Juego getJuego() {
        return juego;
    }

    public Partida getPartida() {
        return partida;
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
    
    public void elegirJuego(int juego,int configuracion, Jugador jugador){
        switch(juego){
            case 2:
                this.juego = new Saltar(configuracion,jugador);
            break;
            case 3:
                this.juego = new Rectangulo(configuracion,jugador);
            break;
            default:
            break;
        }
        this.partida = new Partida(new Date(),jugador, 0);
    }  
    public boolean finJuego(String args){
        boolean esElFin = this.getJuego().finJuego(args);
        if(esElFin){
            this.partida.setPuntaje(this.juego.getPuntaje());
            this.bitacora.agregarPartida(this.partida);
        }
        return esElFin;
    }
}
