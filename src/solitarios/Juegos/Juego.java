package solitarios.Juegos;

import java.util.HashMap;
import sistema.Jugador;

public abstract class Juego {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private int configuracion;
    //private HashMap<Jugador,int> puntajeJugadores;
    
    //Cuando se le asigna el color al jugador?
    //Que significa al azar o predeterminada?
    /*    
        Se indica el jugador eligiéndolo de la lista y la configuración al azar o predeterminada. Se juega (ver 
detalle más abajo). 
        En todos los casos debe validarse la jugada y si es incorrecta, solicitar el reingreso. 
        El sistema debe verificar la condición de terminación del respectivo juego e informarla. También, si se ingresa "X" termina 
        el juego en el momento y se considera el puntaje logrado hasta ahí para la bitácora. 
    
    public abstract void asignarConfiguracion();
    public abstract boolean validarJugada();
    public abstract void registrarEnBitacora();
    */
    public abstract boolean jugar();
      
}
